var product = angular.module('product', ['config'])

//url configuration
product.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/product/', {
      templateUrl: 'view/products.html',
      controller: 'ProductListCtrl'
    })
}]);

//controllers
product.controller('ProductListCtrl', ['$scope', '$http', 'CONFIG', 
	function($scope, $http, CONFIG){
	$http.get(CONFIG.SERVER_ROOT+'/product/').success(function(data){
  		$scope.products = data;
  	})
}])