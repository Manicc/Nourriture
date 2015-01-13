from rest_framework import serializers, permissions, status, generics
from rest_framework.response import Response

from common.models import Ingredient, NutritionValue, Tag
from common.models import IngredientCategory


class IngredListSerializer(serializers.ModelSerializer):
    """
    for get method
    """

    class Meta:
        model = Ingredient
        fields = ('id', 'name', 'image', 'category')
        depth = 3


class IngredCreateSerializer(serializers.ModelSerializer):
    """
    for post method
    """
    tags = serializers.CharField()

    class Meta:
        model = Ingredient

    def create(self, validated_attrs):
        nutritioninfo = validated_attrs.pop('nutrition')
        taginfo = validated_attrs.pop('tags')
        # create ingredient
        ingre = Ingredient.objects.create(**validated_attrs)

        # create nutrition value
        for item in nutritioninfo:
            ingre.nutrition.add(NutritionValue.objects.create(**item))

        # create tags
        tag_list = taginfo.split(',')
        for tag in tag_list:
            if tag:
                try:
                    old_tag = Tag.objects.get(name=tag)
                    ingre.tags.add(old_tag)
                except Tag.DoesNotExist:
                    new_tag = Tag(name=tag)
                    new_tag.save()
                    ingre.tags.add(new_tag)

        ingre.save()

        return ingre


class IngredDetialSerializer(serializers.ModelSerializer):
    """
    for other method
    """

    class Meta:
        model = Ingredient
        depth = 3


class IngredientList(generics.ListCreateAPIView):
    queryset = Ingredient.objects.all()
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    serializer_class = IngredListSerializer

    def post(self, request):
        serializer = IngredCreateSerializer(data=request.data)
        if serializer.is_valid():
            ingredient = serializer.save()
            output = IngredDetialSerializer(ingredient)
            return Response(output.data, status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status.HTTP_400_BAD_REQUEST)


class IngredientDetial(generics.RetrieveUpdateDestroyAPIView):
    queryset = Ingredient.objects.all()
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    serializer_class = IngredDetialSerializer


    def put(self, request, pk):
        obj = self.get_object(pk)
        serializer = IngredCreateSerializer(obj, data=request.data)
        if serializer.is_valid():
            ingredient = serializer.save()
            output = IngredDetialSerializer(ingredient)
            return Response(output.data, status.HTTP_200_OK)
        else:
            return Response(serializer.errors, status.HTTP_400_BAD_REQUEST)


class IngredCatSerializer(serializers.ModelSerializer):
    class Meta:
        model = IngredientCategory


class IngredCatList(generics.ListAPIView):
    queryset = IngredientCategory.objects.all()
    serializer_class = IngredCatSerializer

