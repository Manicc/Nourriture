from django.db.models import Q
from django.http import HttpResponse
from rest_framework import serializers
from rest_framework import permissions
from rest_framework import generics

from common.models import Nutrition, Ingredient, Product, Recipe, NutritionValue


class NutritionBrief(serializers.ModelSerializer):
    class Meta:
        model = Nutrition
        fields = ('id', 'name', 'unit')


class NutritionValueCreate(serializers.ModelSerializer):

    class Meta:
        model = NutritionValue
        fields = ('nutrition', 'value')



class NutritionValueSerializer(serializers.ModelSerializer):
    nutrition = NutritionBrief()

    class Meta:
        model = NutritionValue
        fields = ('nutrition','value')


class NutritionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Nutrition
        fields = ('id', 'name', 'unit', 'alias', 'desc')


class NutritionList(generics.ListCreateAPIView):
    queryset = Nutrition.objects.all()
    serializer_class = NutritionSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )

    def get_serializer_class(self):
        if self.request.method == 'get':
            return NutritionBrief
        return super(NutritionList, self).get_serializer_class()


class NutritionDetial(generics.ListCreateAPIView):
    queryset = Nutrition.objects.all()
    serializer_class = NutritionSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly, )



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
