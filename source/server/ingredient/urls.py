from django.conf.urls import patterns, url

from ingredient.views import IngredientList, IngredientDetial, IngredCatList


urlpatterns = patterns('',
                       url(r'^$', IngredientList.as_view()),
                       url(r'^(?P<pk>\d+)/$', IngredientDetial.as_view()),
                       url(r'^category/$', IngredCatList.as_view()),
)
