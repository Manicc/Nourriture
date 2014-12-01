var appControllers = angular.module('appControllers', []);

appControllers.controller('RootCtrl', function ($scope, $http) {
  /*$http.get('http://nourriture.sinaapp.com/ingredient/').success(function(data){
  	$scope.ingredients = data;
  })*/

});

appControllers.controller('IngredientListCtrl', function ($scope, $http) {
  $http.get(serverRoot+'/ingredient/').success(function(data){
  	$scope.ingredients = data;
  })
});

appControllers.controller('IngredientDetialCtrl', function ($scope, $http, $routeParams) {
  $http.get(serverRoot+'/ingredient/'+ $routeParams.id).success(function(data){
  	$scope.ingredient = data;
  })
});

appControllers.controller('ProductListCtrl', function ($scope, $http) {
  $http.get(serverRoot+'/product/').success(function(data){
  	$scope.products = data;
  })

});

appControllers.controller('RecipeListCtrl', function ($scope, $http) {
  $http.get(serverRoot+'/recipe/').success(function(data){
  	$scope.recipes = data;
  })

});