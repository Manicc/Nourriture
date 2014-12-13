from rest_framework import serializers, generics, permissions

from common.models import Recipe


class RecipeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Recipe


class RecipeList(generics.ListCreateAPIView):
    queryset = Recipe.objects.all()
    serializer_class = RecipeSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)


class RecipeDetial(generics.RetrieveUpdateDestroyAPIView):
    queryset = Recipe.objects.all()
    serializer_class = RecipeSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)