from django.core import serializers
from django.http import HttpResponse
from common.models import Gastronomist
from common.models import Ingredient
from common.models import User
from common.models import Recipe

def _list(request):
    gastronomist = Gastronomist.objects.all()
    data = serializers.serialize("json", gastronomist)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    gastronomist = Gastronomist.objects.get(id=id)
    data = serializers.serialize("json", [gastronomist])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)

def search(request):
    """
        search: using ingredient name and  recipe
        form: http://127.0.0.1:8000/gastronomist/searchgastronomist?ingredientname=ljm&recipename=1
    """
    ingredientname = request.GET.get('ingredientname')
    recipename = request.GET.get('recipename')

    ingredient = Ingredient.objects.get(name=ingredientname)
    ingredientid = ingredient.id
    recipe = Recipe.objects.get(name=recipename)
    recipeid = recipe.id

#    g = Recipe.objects.get(id=1).gastronomist
    if(ingredientid!=''):
        gastronomist = Recipe.objects.get(ingredients=ingredientid).gastronomist
    if(recipeid!=''):
        gastronomist = Recipe.objects.get(id=recipeid).gastronomist

    data = serializers.serialize("json", [gastronomist])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
