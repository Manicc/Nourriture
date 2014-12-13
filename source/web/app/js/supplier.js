var supplier = angular.module('supplier', ['config'])

//url configuration
supplier.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/supplier/', {
      templateUrl: 'view/suppliers.html',
      controller: 'supplierListCtrl'
    }).
    when('/supplier/:id', {
      templateUrl: 'view/supplier_detail.html',
      controller: 'supplierDetailCtrl'
    })
}]);

//controllers
supplier.controller('supplierListCtrl', ['$scope', '$http', 'CONFIG',
  function($scope, $http, CONFIG){
      // for test
      $scope.suppliers = [{"fields": {"ingredients": [1, 2], "location": "Beijing", "user": 1, "name": "dashan"}, "model": "common.supplier", "pk": 1}, {"fields": {"ingredients": [2], "location": "Jiangxi", "user": 2, "name": "zhuzi"}, "model": "common.supplier", "pk": 2}]
      //$http.get(CONFIG.SERVER_ROOT+'/supplier/').success(function(data){
      //    $scope.suppliers = data;
  //  })
}]);

supplier.controller('supplierDetailCtrl', ['$scope', '$http', '$routeParams', 'CONFIG',
	function ($scope, $http, $routeParams, CONFIG) {
        // for test
        $scope.supplier = [{"fields": {"ingredients": [1, 2], "location": "Beijing", "user": 1, "name": "dashan"}, "model": "common.supplier", "pk": 1}]
		//$http.get(CONFIG.SERVER_ROOT+'/supplier/'+ $routeParams.id).success(function(data){
  		//	$scope.supplier = data[0];
  		//})
}]);

