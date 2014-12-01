from django.core import serializers
from django.http import HttpResponse
from common.models import Ingredient
import json


def _list(request):
    ingredients = Ingredient.objects.all()
    data = serializers.serialize("json", ingredients)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    ingredient = Ingredient.objects.get(id=id)
    data = dict()
    data['name'] = ingredient.name
    data['tags'] = [t.name for t in ingredient.tags.all()]
    data['alias'] = ingredient.alias
    data['category'] = ingredient.category.name
    data['desc'] = ingredient.desc

    nutrition = []

    for n in ingredient.nutrition.all():
        nutrition.append({'name':n.nutrition.name, 'value':n.value})

    data['nutrition'] = nutrition
    data['function'] = ingredient.function

    json_data = json.dumps(data)

    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(json_data, **response_kwargs)


def search(request):
    ingredient = Ingredient.objects.all()
    ingredient_name = request.GET.get('name','')

    if ingredient_name != '':
        ingredient_name_list = ingredient_name.split(',')
        for ingredient_name in ingredient_name_list:
            ingredient = ingredient.filter(name__contains=ingredient_name)

    ingredient_alias = request.GET.get('name','')
    if ingredient_alias != '':
        ingredient_alias_list = ingredient_alias.split(',')
        for ingredient_alias in ingredient_alias_list:
            ingredient = ingredient.filter(alias__contains=ingredient_alias)

    data = serializers.serialize("json", ingredient)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


