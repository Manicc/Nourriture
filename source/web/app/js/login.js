var user = angular.module('user', ['config']);

//url configuration
ingredient.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/user/', {
      templateUrl: 'view/login.html',
      controller: 'LoginCtrl'
    })
}]);

//controllers
ingredient.controller('LoginCtrl', ['$scope', '$http', 'CONFIG',
	function($scope, $http, CONFIG){
      $scope.login= function(){
        $http.post("login.do",$.param($scope.user),{
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' }}
).success(function(data, status, headers, config){})}
}]);

