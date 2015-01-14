from django.contrib.auth.models import User
from django.shortcuts import render
from rest_framework import generics, serializers, permissions, status
from rest_framework.response import Response
from rest_framework.views import APIView

from common.models import Comment, TARGET_TYPE, Favorite, Ingredient, Product, Recipe
from ingredient.views import IngredListSerializer
from product.views import ProductListSerializer
from recipe.views import RecipeSerializer


def index(request):
    return render(request, 'index.html')


class UserBriefSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username')


class UserCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'password')

    def create(self, validated_attrs):
        user = User.objects.create_user(**validated_attrs)

        return user


class UserCreate(generics.CreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserCreateSerializer


class UserDetial(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserCreateSerializer


class UserFavoriteListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Favorite
        fields = ('target_type', 'target_id')


class UserFavorite(generics.ListAPIView):
    serializer_class = UserFavoriteListSerializer
    permission_classes = (permissions.IsAuthenticated,)


    def list(self, request, id):
        ret = []
        fav = Favorite.objects.filter(user_id=id)

        for f in fav:
            if f.target_type == 0:
                try:
                    obj = Ingredient.objects.get(pk=f.target_id)
                    single = {'type': 0, 'content': {'id': obj.pk, 'name': obj.name}}
                    ret.append(single)
                except Ingredient.DoesNotExist:
                    pass
            elif f.target_type == 1:
                try:
                    obj = Product.objects.get(pk=f.target_id)
                    single = {'type': 1, 'content': {'id': obj.pk, 'name': obj.name}}
                    ret.append(single)
                except Product.DoesNotExist:
                    pass
            elif f.target_type == 2:
                try:
                    obj = Recipe.objects.get(pk=f.target_id)
                    single = {'type': 2, 'content': {'id': obj.pk, 'name': obj.name}}
                    ret.append(single)
                except Recipe.DoesNotExist:
                    pass

        return Response(ret, status.HTTP_200_OK)


class CommentCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = ('content',)


class CommentListSerializer(serializers.ModelSerializer):
    user = UserBriefSerializer()

    class Meta:
        model = Comment
        fields = ('user', 'content', 'time')


class CommentList(generics.ListCreateAPIView):
    queryset = Comment.objects.all()
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    serializer_class = CommentListSerializer

    def create(self, request, type, pk):
        serializer = CommentCreateSerializer(data=request.data)
        type_id = TARGET_TYPE.get(type, -1)
        if serializer.is_valid() and type_id != -1:
            ingredient = serializer.save(user=request.user, target_type=type_id, target_id=pk)
            output = CommentListSerializer(ingredient)
            return Response(output.data, status.HTTP_201_CREATED)
        else:
            return Response(serializer.errors, status.HTTP_400_BAD_REQUEST)

    def list(self, request, type, pk):
        type_id = TARGET_TYPE.get(type, -1)
        if type_id != -1:
            comments = Comment.objects.filter(target_type=type_id, target_id=pk)
            output = CommentListSerializer(comments, many=True)
            return Response(output.data, status.HTTP_200_OK)
        else:
            return Response([], status.HTTP_200_OK)


class FavoriteListSerializer(serializers.ModelSerializer):
    user = UserBriefSerializer()

    class Meta:
        model = Favorite
        fields = ('id','user',)


class FavoriteList(generics.ListCreateAPIView):
    queryset = Favorite.objects.all()
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    serializer_class = FavoriteListSerializer

    def create(self, request, type, pk):
        type_id = TARGET_TYPE.get(type, -1)
        if type_id != -1:
            fav_obj = Favorite(user=request.user, target_type=type_id, target_id=pk)
            fav_obj.save()

            output = FavoriteListSerializer(fav_obj)
            return Response(output.data, status.HTTP_201_CREATED)
        else:
            return Response(status=status.HTTP_400_BAD_REQUEST)


class FavoriteDelete(generics.DestroyAPIView):
    queryset = Favorite.objects.all()
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
    serializer_class = FavoriteListSerializer


class Search(APIView):
    def get(self, request):
        ret = {}
        key = request.GET.get('key')
        if key:
            # ingredient
            ingre = Ingredient.objects.filter(name__icontains=key)
            if ingre:
                ser = IngredListSerializer(ingre, many=True)
                ret['ingredient'] = ser.data
            else:
                ret['ingredient'] = []


            # product
            products = Product.objects.filter(name__icontains=key)
            if products:
                ser = ProductListSerializer(products, many=True)
                ret['product'] = ser.data
            else:
                ret['product'] = []

            # recipe
            recipes = Recipe.objects.filter(name__icontains=key)
            if recipes:
                ser = RecipeSerializer(recipes, many=True)
                ret['recipe'] = ser.data
            else:
                ret['recipe'] = []

        return Response(ret)
