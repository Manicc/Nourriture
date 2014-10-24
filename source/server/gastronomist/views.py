from django.core import serializers
from django.http import HttpResponse
from common.models import Gastronomist


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
