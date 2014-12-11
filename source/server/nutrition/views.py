from django.db.models import Q
from django.http import HttpResponse
from rest_framework import serializers
from common.models import Nutrition, Ingredient, Product, Recipe
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework import mixins
from rest_framework import generics


class NutritionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Nutrition
        fields = ('id', 'name', 'alias', 'desc')


class NutritionList(generics.ListCreateAPIView):
    queryset = Nutrition.objects.all()
    serializer_class = NutritionSerializer

class NutritionDetial(generics.RetrieveUpdateDestroyAPIView):
    queryset = Nutrition.objects.all()
    serializer_class = NutritionSerializer


"""
def _list(request):
    count_per_page = request.GET.get('count', 10)
    page = request.GET.get('page', 0)

    nutrition = Nutrition.objects.all()[page * count_per_page:count_per_page]

    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    nutrition = Nutrition.objects.get(id=id)
    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
"""

def search(request):
    name = request.GET.get('name')
    nutrition = None

    if name:
        # search the name and alias at the same time
        nutrition = Nutrition.objects.filter(Q(name__contains=name) | Q(alias__contains=name)).order_by('name')

    if not nutrition:
        nutrition = []

    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def ingredient_rank(request, id):
    count_per_page = request.GET.get('count', 10)
    page = request.GET.get('page', 0)

    ingredients = Ingredient.objects.filter(nutrition__nutrition__id=id).order_by('nutrition__value')[
                  page * count_per_page:count_per_page]

    data = serializers.serialize("json", ingredients)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def product_rank(request, id):
    count_per_page = request.GET.get('count', 10)
    page = request.GET.get('page', 0)

    products = Product.objects.filter(nutrition__nutrition__id=id).order_by('nutrition__value')[
               page * count_per_page:count_per_page]

    data = serializers.serialize("json", products)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def recipe_rank(request, id):
    count_per_page = request.GET.get('count', 10)
    page = request.GET.get('page', 0)

    recipes = Recipe.objects.filter(nutrition__nutrition__id=id).order_by('nutrition__value')[
              page * count_per_page:count_per_page]

    data = serializers.serialize("json", recipes)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
