from django.contrib import admin

from common.models import *


class IngredientInline(admin.TabularInline):
    model = Ingredient.nutrition.through
    extra = 1


class IngredientAdmin(admin.ModelAdmin):
    inlines = [IngredientInline, ]
    exclude = ('nutrition',)


class RecipeInline(admin.TabularInline):
    model = Recipe.nutrition.through
    extra = 1


class RecipeAdmin(admin.ModelAdmin):
    inlines = [RecipeInline, ]
    exclude = ('nutrition',)


class ProductInline(admin.TabularInline):
    model = Product.nutrition.through
    extra = 1


class ProductAdmin(admin.ModelAdmin):
    inlines = [ProductInline, ]
    exclude = ('nutrition',)


admin.site.register(Nutrition)
admin.site.register(NutritionValue)
admin.site.register(IngredientCategory)
admin.site.register(Tag)
admin.site.register(Ingredient, IngredientAdmin)
admin.site.register(Supplier)
admin.site.register(Gastronomist)
admin.site.register(Recipe)
admin.site.register(Product)
admin.site.register(Favorite)
admin.site.register(Like)
admin.site.register(Moment)
admin.site.register(Comment)