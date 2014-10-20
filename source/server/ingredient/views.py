from django.core import serializers
from django.http import HttpResponse
from common.models import Ingredient


def _list(request):
    ingredients = Ingredient.objects.all()
    data = serializers.serialize("json", ingredients)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    ingredient = Ingredient.objects.get(id=id)
    data = serializers.serialize("json", [ingredient])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
