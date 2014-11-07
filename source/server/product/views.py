from django.core import serializers
from django.db.models import Q
from django.http import HttpResponse
from common.models import Ingredient, Tag, Product


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


def search(request):
    name_param = request.GET.get('name', '')
    ingredient_param = request.GET.get('ingredient', '')
    tag_param = request.GET.get('tag', '')

    query = Q()

    if name_param:
        query = Q(name__contains=name_param)

    # TODO not implement
    if ingredient_param:
        ingredient_param_list = ingredient_param.split(',')
        temp_query = Q(ingredients__name__in = ingredient_param_list)

        query = query & temp_query

    if tag_param:
        query = query & Q(tags__name=tag_param)

    products = Product.objects.filter(query)
    data = serializers.serialize("json", products)

    response_kwargs = {'content_type': 'application/json'}
    return HttpResponse(data, **response_kwargs)
