from django.core import serializers
from django.http import HttpResponse
from common.models import Ingredient
from common.models import Product
from common.models import ProductTag


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
    if name_param != '':
        name_products = Product.objects.filter(name__contains=name_param)
        data = serializers.serialize("json", name_products)
    elif ingredient_param != '':
        ingredient_param_list = ingredient_param.split(',')
        result = []
        products = Product.objects.all()
        for p in products:
            for ingredient_param_list_name in ingredient_param_list:
                p.ingredients.filter(name__contains=ingredient_param_list_name)
            result.insert(-1, p)
        data = serializers.serialize("json", result)
    elif tag_param != '':
        tag_param_list = tag_param.split(',')
        for tag_param_list_name in tag_param_list:
            product_tags = ProductTag.objects.filter(name=tag_param_list_name)
            ingredient_param = request.GET.get('ingredient', '')
            if ingredient_param != '':
                ingredient_param_list = ingredient_param.split(',')
                for ingredient_param_list_name in ingredient_param_list:
                    ingredient = Ingredient.objects.get(name=ingredient_param_list_name)
                    ingredient_id = ingredient.id
                    product_tags = product_tags.filter(product_ingredients=ingredient_id)

        for product_tag in product_tags:
            products = product_tag.product.all();

        data = serializers.serialize("json", products)

    response_kwargs = {'content_type': 'application/json'}
    return HttpResponse(data, **response_kwargs)

