# -*- coding: utf-8 -*-
from django.contrib.auth.models import User
from django.db import models


class Nutrition(models.Model):
    name = models.CharField(max_length=30, unique=True)
    alias = models.CharField(max_length=50, blank=True)
    unit = models.CharField(max_length=2, choices=(('g','g'),('mg','mg'),(u'μg',u'μg')), default='mg')
    desc = models.TextField(default='', blank=True)

    def __unicode__(self):
        return self.name


class NutritionValue(models.Model):
    nutrition = models.ForeignKey(Nutrition)
    value = models.FloatField()

    def __unicode__(self):
        return self.nutrition.name + ' ' + str(self.value) + 'mg'


class Tag(models.Model):
    name = models.CharField(max_length=30, unique=True)

    def __unicode__(self):
        return self.name


class IngredientCategory(models.Model):
    name = models.CharField(max_length=20, unique=True)

    def __unicode__(self):
        return self.name


class Ingredient(models.Model):
    name = models.CharField(max_length=30, unique=True)
    alias = models.CharField(max_length=50, blank=True)
    # the category of the ingredient
    category = models.ForeignKey(IngredientCategory)
    # detail description of ingredient
    desc = models.TextField(blank=True)
    function = models.TextField(blank=True)
    nutrition = models.ManyToManyField(NutritionValue, blank=True)
    tags = models.ManyToManyField(Tag, blank=True)
    image = models.ImageField(upload_to="image/ingredient")

    def __unicode__(self):
        return self.name


class Supplier(models.Model):
    user = models.OneToOneField(User)
    name = models.CharField(max_length=30, unique=True)
    location = models.CharField(max_length=30)
    ingredients = models.ManyToManyField(Ingredient)

    def __unicode__(self):
        return self.user.username


class Gastronomist(models.Model):
    user = models.OneToOneField(User)
    openid = models.CharField(max_length=40, unique=True)
    name = models.CharField(max_length=30, unique=True)
    desc = models.CharField(max_length=200, blank=True)

    def __unicode__(self):
        return self.name


class Recipe(models.Model):
    name = models.CharField(max_length=40, unique=True)
    gastronomist = models.ForeignKey(Gastronomist)
    ingredients = models.ManyToManyField(Ingredient)
    processing = models.TextField()
    nutrition = models.ManyToManyField(NutritionValue, blank=True)
    tags = models.ManyToManyField(Tag, blank=True)
    image = models.ImageField(upload_to="image/recipe")

    # added by linan --10.29
    # how many users have browsed this recipe
    browse_count = models.IntegerField(default=0)
    # how many users have collected this recipe
    collect_count = models.IntegerField(default=0)
    # this recipe is belong to which type of food,such as spicy or breakfast or other
    food_type = models.CharField(max_length=20)
    # how long will it take to make this food, in minutes
    making_time = models.IntegerField(default=60)
    # some tips to make this food
    making_tip = models.TextField(blank=True)

    def __unicode__(self):
        return self.name


class Product(models.Model):
    name = models.CharField(max_length=20)
    desc = models.TextField(blank=True)
    ingredients = models.ManyToManyField(Ingredient)
    nutrition = models.ManyToManyField(NutritionValue, blank=True)
    tags = models.ManyToManyField(Tag, blank=True)
    image = models.ImageField(upload_to="image/product")

    def __unicode__(self):
        return self.name


TARGET_TYPE = {
    'ingredient':0,
    'product':1,
    'recipe':2,
}


class Favorite(models.Model):
    user = models.ForeignKey(User)
    target_type = models.IntegerField()
    target_id = models.IntegerField()

    def __unicode__(self):
        return self.user.username


class Like(models.Model):
    user = models.ForeignKey(User)
    like_type = models.CharField(max_length=50)
    like_id = models.IntegerField()

    def __unicode__(self):
        return self.user.username


class Moment(models.Model):
    user = models.ForeignKey(User)
    desc = models.CharField(max_length=200)
    media = models.CharField(max_length=100)
    image = models.ImageField(upload_to="image/moment", blank=True, null=True)

    def __unicode__(self):
        return self.user.username


class Comment(models.Model):
    user = models.ForeignKey(User)
    # the object commented upon
    target_type = models.IntegerField()
    # the object's id commented upon
    target_id = models.IntegerField()
    replay_comment_id = models.IntegerField(blank=True, default=0)
    content = models.CharField(max_length=200)
    time = models.DateTimeField(auto_now_add=True)

    def __unicode__(self):
        return self.user.username