//url configuration
app.config(['$routeProvider',
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
app.controller('IngredientListCtrl', ['$scope', '$http', 'CONFIG',
	function($scope, $http, CONFIG){
    $scope.server = CONFIG.SERVER_ROOT;

		$http.get(CONFIG.SERVER_ROOT+'/ingredient/').success(function(data){
	  		$scope.ingredients = data;
		})	
}]);

app.controller('IngredientDetialCtrl', ['$scope', '$http', '$routeParams', 'CONFIG',
	function ($scope, $http, $routeParams, CONFIG) {
    $scope.server = CONFIG.SERVER_ROOT;

		$http.get(CONFIG.SERVER_ROOT+'/ingredient/'+ $routeParams.id).success(function(data){
  			$scope.ingredient = data;
  		})
}]);