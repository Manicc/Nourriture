from django.conf.urls import patterns, url

from product.views import ProductList, ProductDetial


urlpatterns = patterns('',
    url(r'^$', ProductList.as_view()),
    url(r'^(?P<pk>\d+)/$', ProductDetial.as_view()),
)