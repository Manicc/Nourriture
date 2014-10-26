__author__ = 'LJM'

from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'gastronomist.views._list'),
    url(r'^(?P<id>\d+)$', 'gastronomist.views.detail'),
    url(r'gastronomist/$','gastronomist.views.search'),
)
