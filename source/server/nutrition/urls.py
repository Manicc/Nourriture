from django.conf.urls import patterns, url

urlpatterns = patterns('',
    url(r'^$', 'nutrition.views._list'),
    url(r'^(?P<id>\d+)$', 'nutrition.views.detail'),
    url(r'^search$', 'nutrition.views.search'),
    url(r'^(?P<id>\d+)/rank/ingredient$', 'nutrition.views.ingredient_rank'),
    url(r'^(?P<id>\d+)/rank/product', 'nutrition.views.product_rank'),
    url(r'^(?P<id>\d+)/rank/recipe', 'nutrition.views.recipe_rank'),
)
