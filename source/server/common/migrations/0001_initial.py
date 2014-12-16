# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
from django.conf import settings


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Comment',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('target_type', models.IntegerField()),
                ('target_id', models.IntegerField()),
                ('replay_comment_id', models.IntegerField()),
                ('content', models.CharField(max_length=200)),
                ('user', models.ForeignKey(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Favorite',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('collect_type', models.CharField(max_length=50)),
                ('target_id', models.IntegerField()),
                ('user', models.ForeignKey(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Gastronomist',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('openid', models.CharField(unique=True, max_length=40)),
                ('name', models.CharField(unique=True, max_length=30)),
                ('desc', models.CharField(max_length=200, blank=True)),
                ('user', models.OneToOneField(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Ingredient',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=30)),
                ('alias', models.CharField(max_length=50, blank=True)),
                ('desc', models.TextField(blank=True)),
                ('function', models.TextField(blank=True)),
                ('image', models.ImageField(upload_to=b'image/ingredient')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='IngredientCategory',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=20)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Like',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('like_type', models.CharField(max_length=50)),
                ('like_id', models.IntegerField()),
                ('user', models.ForeignKey(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Moment',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('desc', models.CharField(max_length=200)),
                ('media', models.CharField(max_length=100)),
                ('image', models.ImageField(null=True, upload_to=b'image/moment', blank=True)),
                ('user', models.ForeignKey(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Nutrition',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=30)),
                ('alias', models.CharField(max_length=50, blank=True)),
                ('unit', models.CharField(default=b'mg', max_length=2, choices=[(b'g', b'g'), (b'mg', b'mg'), ('\u03bcg', '\u03bcg')])),
                ('desc', models.TextField(default=b'', blank=True)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='NutritionValue',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('value', models.FloatField()),
                ('nutrition', models.ForeignKey(to='common.Nutrition')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Product',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(max_length=20)),
                ('desc', models.TextField(blank=True)),
                ('image', models.ImageField(upload_to=b'image/product')),
                ('ingredients', models.ManyToManyField(to='common.Ingredient')),
                ('nutrition', models.ManyToManyField(to='common.NutritionValue', blank=True)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Recipe',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=40)),
                ('processing', models.TextField()),
                ('image', models.ImageField(upload_to=b'image/recipe')),
                ('browse_count', models.IntegerField(default=0)),
                ('collect_count', models.IntegerField(default=0)),
                ('food_type', models.CharField(max_length=20)),
                ('making_time', models.IntegerField(default=60)),
                ('making_tip', models.TextField(blank=True)),
                ('gastronomist', models.ForeignKey(to='common.Gastronomist')),
                ('ingredients', models.ManyToManyField(to='common.Ingredient')),
                ('nutrition', models.ManyToManyField(to='common.NutritionValue', blank=True)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Supplier',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=30)),
                ('location', models.CharField(max_length=30)),
                ('ingredients', models.ManyToManyField(to='common.Ingredient')),
                ('user', models.OneToOneField(to=settings.AUTH_USER_MODEL)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Tag',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('name', models.CharField(unique=True, max_length=30)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.AddField(
            model_name='recipe',
            name='tags',
            field=models.ManyToManyField(to='common.Tag', blank=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='product',
            name='tags',
            field=models.ManyToManyField(to='common.Tag', blank=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='ingredient',
            name='category',
            field=models.ForeignKey(to='common.IngredientCategory'),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='ingredient',
            name='nutrition',
            field=models.ManyToManyField(to='common.NutritionValue', blank=True),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='ingredient',
            name='tags',
            field=models.ManyToManyField(to='common.Tag', blank=True),
            preserve_default=True,
        ),
    ]
