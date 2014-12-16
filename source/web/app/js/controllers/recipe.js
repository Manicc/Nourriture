var recipe = angular.module('recipe', ['config'])

//url configuration
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/recipe/', {
      templateUrl: 'view/recipes.html',
      controller: 'RecipeListCtrl'
    }).
    when('/recipe/:id', {
      templateUrl: 'view/recipe_detail.html',
      controller: 'RecipeDetailCtrl'
    })
}]);

//controllers
app.controller('RecipeListCtrl', ['$scope', '$http', 'CONFIG', 
  function($scope, $http, CONFIG){
  $http.get(CONFIG.SERVER_ROOT+'/recipe/').success(function(data){
      $scope.recipes = data;
    })
}]);

app.controller('RecipeDetailCtrl', ['$scope', '$http', '$routeParams', 'CONFIG',
	function ($scope, $http, $routeParams, CONFIG) {
		$http.get(CONFIG.SERVER_ROOT+'/recipe/'+ $routeParams.id).success(function(data){
  			$scope.recipe = data;
  		})
}]);