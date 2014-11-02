from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'nutrition.views._list'),
    url(r'^(?P<id>\d+)$', 'nutrition.views.detail'),
    url(r'^search$', 'nutrition.views.search'),
)
