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
        version1
        search: using ingredient name and gastronomist name
        form: http://127.0.0.1:8000/recipe/searchrecipe?gastronomistname=linan&ingredientname=1

        version2
        search: using ingredient name(maybe more than one) and gastronomist name
        form: http://127.0.0.1:8000/recipe/searchrecipe?gastronomistname=linan&ingredientname=1,2,3
        and gastronomistname and igredientname may be none
        form: http://127.0.0.1:8000/recipe/searchrecipe?gastronomistname=&ingredientname=1,2,3
    """

    recipes = Recipe.objects.all()
    ingredient = Ingredient.objects.all()

    recipename=request.GET.get('name')
    if recipename !=None:
        recipes=recipes.filter(name__contains=recipename)

    ingrname = request.GET.get('ingredient')
    if ingrname != None:

        ingredientnamelist  = ingrname.split(',')

        for ingredientname in ingredientnamelist:
            newingredient = ingredient.filter(name__contains=ingredientname)
            # ingredient = Ingredient.objects.get(name__contains=ingredientname)
            newrecipe = Recipe.objects.none()
            if newingredient!=None:
                
                for ingredientObj in newingredient:
                    ingredientid = ingredientObj.id
                    recipes1 = recipes.filter(ingredients=ingredientid)
                    newrecipe = newrecipe|recipes1
                recipes = newrecipe   

    gastronomistname = request.GET.get('gname')
    if gastronomistname != None:
        try:
            gastro =User.objects.get(username__contains=gastronomistname)
            if gastro!=None:
                gastronomistid = gastro.id
                recipes = recipes.filter(gastronomist=gastronomistid)
        except Exception, e:
            recipes = Recipe.objects.none()
        
 

    data = serializers.serialize("json", recipes)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
