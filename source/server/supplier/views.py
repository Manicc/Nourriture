# Create your views here.
from django.core import serializers
from django.http import HttpResponse
from common.models import Supplier


def _list(request):
    supplier = Supplier.objects.all()
    data = serializers.serialize("json", supplier)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    # request.GET['country']
    supplier = Supplier.objects.get(id=id)
    data = serializers.serialize("json", [supplier])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)