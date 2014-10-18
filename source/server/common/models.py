from django.contrib.auth.models import User
from django.db import models


class Food(models.Model):
    name = models.CharField(max_length=50)
    description = models.CharField(max_length=50)
    grade = models.IntegerField(default = 0)
    grad_count = models.IntegerField(default=0)
    count = models.IntegerField()
    suppliers = models.ManyToManyField(Supplier)


class Supplier(models.Model):
    grade = models.IntegerField(default = 0)
    grad_count = models.IntegerField(default=0)
    commentId = models.IntegerField()
    foodId = models.IntegerField()


class Recipe(models.Model):
    user = models.ForeignKey(User)
    desc = models.CharField(max_length=100)
    process = models.TextField()


class Comment(models.Model):
    user = models.ForeignKey(User)
    comment_type = models.CharField(max_length=50)


class Collect(models.Model):
    userID = models.ForeignKey(User)
    collect_type = models.CharField(max_length=50)


class FoodUserMap(models.Model):
    userID = models.ForeignKey(User)
    foodId = models.ForeignKey(Food)