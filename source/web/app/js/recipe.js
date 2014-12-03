var recipe = angular.module('recipe', ['config'])

//url configuration
recipe.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/recipe/', {
      templateUrl: 'view/recipes.html',
      controller: 'RecipeListCtrl'
    })
}]);

//controllers
recipe.controller('RecipeListCtrl', ['$scope', '$http', 'CONFIG', 
  function($scope, $http, CONFIG){
  $http.get(CONFIG.SERVER_ROOT+'/recipe/').success(function(data){
      $scope.recipes = data;
    })
}]);