from django.conf.urls import patterns, url

from common.views import UserCreate, UserDetial, UserFavorite


urlpatterns = patterns('',
                       url(r'^$', UserCreate.as_view()),
                       url(r'^(?P<pk>\d+)/$', UserDetial.as_view()),
                       url(r'^(?P<id>\d+)/favorite/$', UserFavorite.as_view()),
)
