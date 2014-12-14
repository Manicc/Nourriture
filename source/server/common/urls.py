from django.conf.urls import patterns, url

from common.views import UserCreate, UserDetial


urlpatterns = patterns('',
                       url(r'^$', UserCreate.as_view()),
                       url(r'^(?P<pk>\d+)/$', UserDetial.as_view()),
)
