from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'recipe.views._list'),
    url(r'^(?P<id>\d+)$', 'recipe.views.detail'),
    url(r'search/$','recipe.views.search'),
)