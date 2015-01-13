from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from django.contrib import admin

from common.views import CommentList, FavoriteList, FavoriteDelete, Search
from server import settings


urlpatterns = patterns('',
                       url(r'^$', 'common.views.index', name='index'),
                       url(r'^user/', include('common.urls')),
                       url(r'^ingredient/', include('ingredient.urls')),
                       url(r'^product/', include('product.urls')),
                       url(r'^recipe/', include('recipe.urls')),
                       url(r'^supplier/', include('supplier.urls')),
                       url(r'^gastronomist/', include('gastronomist.urls')),
                       url(r'^nutrition/', include('nutrition.urls')),
                       url(r'^(?P<type>\w+)/(?P<pk>\d+)/comment/$', CommentList.as_view()),
                       url(r'^(?P<type>\w+)/(?P<pk>\d+)/favorite/$', FavoriteList.as_view()),
                       url(r'^(?P<type>\w+)/(?P<id>\d+)/favorite/(?P<pk>\d+)/$', FavoriteDelete.as_view()),
                       url(r'^search/$', Search.as_view()),

                       # Uncomment the admin/doc line below to enable admin documentation:
                       # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

                       # Uncomment the next line to enable the admin:
                       url(r'^admin/', include(admin.site.urls)),

                       url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
                       url(r'^o/', include('oauth2_provider.urls', namespace='oauth2_provider')),
) \
              + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT) \
              + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
