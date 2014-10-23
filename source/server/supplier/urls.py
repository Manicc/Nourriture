__author__ = 'guy'
from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'supplier.views._list'),
    url(r'^(?P<id>\d+)$', 'supplier.views.detail'),
)