from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from django.contrib import admin
from server import settings

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
) \
              + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT) \
              + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
