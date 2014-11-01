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
        search: using  name
        http://127.0.0.1:8000/gastronomist/search?name=xiaoxiaomeishijia
    """
    gastronomist = Gastronomist.objects.all()

    gastronomistname=request.GET.get('name')
    if gastronomistname !=None:
        gastronomist=gastronomist.filter(name__contains=gastronomistname)

    data = serializers.serialize("json", gastronomist)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
