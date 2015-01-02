from rest_framework import serializers, viewsets, permissions
from common.models import Gastronomist


class GastronomistSerializer(serializers.ModelSerializer):
    class Meta:
        model = Gastronomist


class GastronomistViewSet(viewsets.ModelViewSet):
    queryset = Gastronomist.objects.all()
    serializer_class = GastronomistSerializer
    permission_classes = (permissions.IsAuthenticatedOrReadOnly,)


