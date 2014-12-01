var product = angular.module('product', ['config'])

product.controller('ProductList', ['$scope', '$http', 'GENERAL_CONFIG', 
	function($scope, $http, GENERAL_CONFIG){
	$http.get(GENERAL_CONFIG.URLROOT+'/product/').success(function(data){
  		$scope.products = data;
  	})
}])