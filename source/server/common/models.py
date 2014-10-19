from django.contrib.auth.models import User
from django.db import models


class Ingredient(models.Model):
    name = models.CharField(max_length=20)
    alias = models.CharField(max_length=20)
    # place of production
    origin = models.CharField(max_length=30)
    # detail description of ingredient
    nutrition = models.CharField(max_length=100)
    supplier = models.ManyToManyField(User)


class Product(models.Model):
    name = models.CharField(max_length=20)
    ingredients = models.ManyToManyField(Ingredient)
    nutrition = models.CharField(max_length=100)
    certification = models.CharField(max_length=20)


class ProductTag(models.Model):
    name = models.CharField(max_length=20)
    product = models.ManyToManyField(Product)


class Recipe(models.Model):
    ingredients = models.ManyToManyField(Ingredient)
    processing = models.TextField()
    nutrition = models.CharField(max_length=100)
    gastronomist = models.ForeignKey(User)


class Favorite(models.Model):
    user = models.ForeignKey(User)
    collect_type = models.CharField(max_length=50)
    target_id = models.IntegerField()


class Like(models.Model):
    user = models.ForeignKey(User)
    like_type = models.CharField(max_length=50)
    like_id = models.IntegerField()


class Moment(models.Model):
    user = models.ForeignKey(User)
    description = models.CharField(max_length=200)
    media = models.CharField(max_length=100)


class Comment(models.Model):
    user = models.ForeignKey(User)
    # the object commented upon
    target_type = models.IntegerField()
    # the object's id commented upon
    target_id = models.IntegerField()
    replay_comment_id = models.IntegerField()
    content = models.CharField(max_length=200)