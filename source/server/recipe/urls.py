from django.conf.urls import patterns, url
from recipe import views

urlpatterns = patterns('',
    url(r'^$', views.RecipeList.as_view()),
    url(r'^(?P<id>\d+)$', views.RecipeDetial.as_view()),
)