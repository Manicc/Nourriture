var ingredient = angular.module('ingredient', ['config']);

//url configuration
ingredient.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/ingredient/', {
      templateUrl: 'view/ingredients.html',
      controller: 'IngredientListCtrl'
    }).
    when('/ingredient/:id', {
      templateUrl: 'view/ingredient_detial.html',
      controller: 'IngredientDetialCtrl'
    })
}]);

//controllers
ingredient.controller('IngredientListCtrl', ['$scope', '$http', 'CONFIG',
	function($scope, $http, CONFIG){
		$http.get(CONFIG.SERVER_ROOT+'/ingredient/').success(function(data){
	  		$scope.ingredients = data;
		})	
}]);

ingredient.controller('IngredientDetialCtrl', ['$scope', '$http', '$routeParams', 'CONFIG',
	function ($scope, $http, $routeParams, CONFIG) {
		$http.get(CONFIG.SERVER_ROOT+'/ingredient/'+ $routeParams.id).success(function(data){
  			$scope.ingredient = data;
  		})
}]);