from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'ingredient.views._list'),
    url(r'^(?P<id>\d+)$', 'ingredient.views.detail'),
)
