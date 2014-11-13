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
    products = []

    query = Q()

    if name_param:
        query = Q(name__contains=name_param)

    if tag_param:
        query = query & Q(tags__name=tag_param)

    if query.children:
        products = Product.objects.filter(query)

    if ingredient_param:
        ingredient_param_list = ingredient_param.split(',')
        if not products:
            products = Product.objects.filter(ingredients__name__contains = ingredient_param_list[0])
        else:
            products = products.filter(ingredients__name__contains = ingredient_param_list[0])

        for name in ingredient_param_list[1:]:
            if name:
                products = products.filter(ingredients__name__contains = name)

    data = serializers.serialize("json", products)

    response_kwargs = {'content_type': 'application/json'}
    return HttpResponse(data, **response_kwargs)
