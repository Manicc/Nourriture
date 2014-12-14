from rest_framework import serializers, generics, permissions

from common.models import Product


class ProductListSerializer(serializers.ModelSerializer):
    """
    for get method
    """
    class Meta:
        model = Product
        fields = ('id', 'name', 'image')
        depth = 3


class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model = Product


class ProductList(generics.ListCreateAPIView):
    queryset = Product.objects.all()
    serializer_class = ProductListSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)


class ProductDetial(generics.RetrieveUpdateDestroyAPIView):
    queryset = Product.objects.all()
    serializer_class = ProductSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)


