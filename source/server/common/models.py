from django.contrib.auth.models import User
from django.db import models


class Food(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=50)
    grade = models.IntegerField(default = 0)
    grad_count = models.IntegerField(default=0)
    count = models.IntegerField()
    suppliers = models.ManyToManyField(User, related_name='supplier')
    sellers = models.ManyToManyField(User, related_name='seller')


class Recipe(models.Model):
    user = models.ForeignKey(User)
    desc = models.CharField(max_length=100)
    process = models.TextField()


class Comment(models.Model):
    user = models.ForeignKey(User)
    comment_type = models.IntegerField()
    # The target comment is on
    target_id = models.IntegerField()
    ref_comment_id = models.IntegerField()
    content = models.CharField(max_length=200)


class Collect(models.Model):
    user = models.ForeignKey(User)
    collect_type = models.CharField(max_length=50)
    target_id = models.IntegerField()