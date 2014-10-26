from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'product.views._list'),
    url(r'^(?P<id>\d+)$', 'product.views.detail'),
    url(r'^search/$','product.views.search'),
)