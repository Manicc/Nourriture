from django.conf.urls import patterns, url

from nutrition import views


urlpatterns = patterns('',
    url(r'^$', views.NutritionList.as_view()),
    url(r'^(?P<pk>[0-9]+)$', views.NutritionDetial.as_view()),
    url(r'^search$', 'nutrition.views.search'),
    url(r'^(?P<id>\d+)/rank/ingredient$', 'nutrition.views.ingredient_rank'),
    url(r'^(?P<id>\d+)/rank/product', 'nutrition.views.product_rank'),
    url(r'^(?P<id>\d+)/rank/recipe', 'nutrition.views.recipe_rank'),
)
