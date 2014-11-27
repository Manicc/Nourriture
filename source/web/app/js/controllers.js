var appControllers = angular.module('appControllers', []);

appControllers.controller('RootCtrl', function ($scope, $http) {
  $http.get('http://nourriture.sinaapp.com/ingredient/').success(function(data){
  	$scope.ingredients = data;
  })

});