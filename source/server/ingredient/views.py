from django.http import Http404
from rest_framework import serializers, permissions, status, views, generics
from rest_framework.response import Response

from common.models import Ingredient, NutritionValue


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
            return Response(output.data, status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status.HTTP_400_BAD_REQUEST)
