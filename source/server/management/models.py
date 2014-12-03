from django.db import models


class ApiGroup(models.Model):
    name = models.CharField(max_length=20)

    def __unicode__(self):
        return self.name


class Api(models.Model):
    url = models.CharField(max_length=100)
    example = models.TextField()
    desc = models.CharField(max_length=100)
    group = models.ForeignKey(ApiGroup)

    def __unicode__(self):
        return self.url