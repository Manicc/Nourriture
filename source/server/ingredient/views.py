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


def search(request):
    available_field = ['name', 'region']

    arguments = dict()
    for k, v in request.GET.iteritems():
        if k in available_field:
            arguments[k] = v

        if k == 'region':
            arguments[k+'__in'] = str.split(v, ',')

    ingredient = Ingredient.objects.filter(**arguments)
    data = serializers.serialize("json", ingredient)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)

