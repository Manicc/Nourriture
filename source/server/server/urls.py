from django.conf.urls import patterns, include, url

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
from server import settings

admin.autodiscover()

urlpatterns = patterns('',
                       # Examples:
                       url(r'^$', 'common.views.index', name='index'),
                       url(r'^api/$', 'common.views.api', name='api'),
                       url(r'^ingredient/', include('ingredient.urls')),
                       url(r'^product/', include('product.urls')),
                       url(r'^recipe/', include('recipe.urls')),
                       url(r'^supplier/', include('supplier.urls')),
                       url(r'^gastronomist/', include('gastronomist.urls')),
                       url(r'^nutrition/', include('nutrition.urls')),

                       # Uncomment the admin/doc line below to enable admin documentation:
                       # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

                       # Uncomment the next line to enable the admin:
                       url(r'^admin/', include(admin.site.urls)),
                       url(r'^static/(?P<path>.*)$', 'django.views.static.serve',
                           {'document_root': settings.STATIC_ROOT, 'show_indexes': settings.DEBUG}),
)
