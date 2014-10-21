from django.core import serializers
from django.http import HttpResponse
from common.models import Recipe

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
