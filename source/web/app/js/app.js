var app = angular.module('app', [
  'ngRoute',
  'appControllers'
]);

app.config(['$routeProvider'
  function($routeProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'view/root.html',
        controller: 'RootCtrl'
      }).
      when('/ingredient/', {
        templateUrl: 'view/ingredients.html',
        controller: 'IngredientListCtrl'
      }).
      when('/ingredient/:id', {
        templateUrl: 'view/ingredient_detial.html',
        controller: 'IngredientDetialCtrl'
      }).
      when('/product/', {
        templateUrl: 'view/products.html',
        controller: 'ProductListCtrl'
      }).
      when('/recipe/', {
        templateUrl: 'view/recipes.html',
        controller: 'RecipeListCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });

      //$locationProvider.html5Mode(true);
  }]);