from django.core import serializers
from django.http import HttpResponse
from common.models import Recipe
from common.models import Ingredient
from common.models import User

def _list(request):
    recipes = Recipe.objects.all()
    data = serializers.serialize("json", recipes)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    recipe = Recipe.objects.get(id=id)
    data = serializers.serialize("json", [recipe])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)

def search(request):
    """
        search: using ingredient name and gastronomist name
        form: http://127.0.0.1:8000/recipe/searchrecipe?gastronomistname=linan&ingredientname=1
    """
    ingredientname = request.GET.get('ingredientname')
    gastronomistname = request.GET.get('gastronomistname')

    ingredient = Ingredient.objects.get(name=ingredientname)
    ingredientid = ingredient.id
    gastronomist = User.objects.get(username=gastronomistname)
    gastronomistid = gastronomist.id

    if(gastronomistid!=''):
        recipes = Recipe.objects.filter(gastronomist=ingredientid)
    if(ingredientid!=''):
        recipes = recipes.filter(ingredients=ingredientid)

    data = serializers.serialize("json", recipes)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
