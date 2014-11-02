from django.core import serializers
from django.db.models import Q
from django.http import HttpResponse
from common.models import Nutrition


def _list(request):
    nutrition = Nutrition.objects.all()
    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    nutrition = Nutrition.objects.filter(id=id)
    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def search(request):
    name = request.GET.get('name')
    nutrition = None

    if name:
        # search the name and alias at the same time
        nutrition = Nutrition.objects.filter(Q(name__contains=name) | Q(alias__contains=name))

    if not nutrition:
        nutrition = []

    data = serializers.serialize("json", nutrition)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)