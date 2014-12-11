from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from django.contrib import admin
from django.contrib.auth.models import User, Group
from rest_framework import serializers
from server import settings

from rest_framework import viewsets, routers
from rest_framework import permissions

from oauth2_provider.ext.rest_framework import TokenHasReadWriteScope, TokenHasScope

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'username', 'email', 'groups')

# ViewSets define the view behavior.
class UserViewSet(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticated, TokenHasReadWriteScope]
    queryset = User.objects.all()
    serializer_class = UserSerializer


class GroupViewSet(viewsets.ModelViewSet):
    permission_classes = [permissions.IsAuthenticated, TokenHasScope]
    required_scopes = ['groups']
    model = Group


# Routers provide an easy way of automatically determining the URL conf
router = routers.DefaultRouter()
router.register(r'users', UserViewSet)
router.register(r'groups', GroupViewSet)

urlpatterns = patterns('',
                       # Examples:
                       url(r'^$', 'common.views.index', name='index'),
                       url(r'^', include(router.urls)),
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

                       url(r'^o/', include('oauth2_provider.urls', namespace='oauth2_provider')),
) \
              + static(settings.STATIC_URL, document_root=settings.STATIC_ROOT) \
              + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
