from django.contrib.auth.models import User
from django.shortcuts import render
from rest_framework import generics, serializers


def index(request):
    return render(request, 'index.html')


class UserCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'password')

    def create(self, validated_attrs):
        user = User.objects.create_user(**validated_attrs)

        return user


class UserCreate(generics.CreateAPIView):
    queryset = User.objects.all()
    serializer_class = UserCreateSerializer


class UserDetial(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserCreateSerializer

