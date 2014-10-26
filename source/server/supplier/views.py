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



def search(request):
    avaiable_field = {'country','ingredients','name'}

    argument =dict()
    for k,v in request.GET.iteritems:
        if k in avaiable_field:
            argument[k] = v

        if k == 'ingredients':
            argument[k+'_in']=str.split(v,',')

    supplier = Supplier.objects.filter(**argument)
    data = serializers.serialize("json", [supplier])
    response_kwargs = dict()
    response_kwargs['content_type'] = 'application/json'
    return HttpResponse(data, **response_kwargs)

