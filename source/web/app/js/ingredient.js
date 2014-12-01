var ingredientController = angular.module('ingredientController', []);

ingredientController.controller('IngredientListCtrl', ['$scope', '$http', function($scope, $http){
	$http.get(serverRoot+'/ingredient/').success(function(data){
  	$scope.ingredients = data;
  })	
}])