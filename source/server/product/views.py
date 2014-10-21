from django.core import serializers
from django.http import HttpResponse
from common.models import Product


def _list(request):
    products = Product.objects.all()
    data = serializers.serialize("json", products)
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)


def detail(request, id):
    product = Product.objects.get(id=id)
    data = serializers.serialize("json", [product])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)
