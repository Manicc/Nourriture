//the main module
var app = angular.module('app', [
  'ngRoute',
  'LocalStorageModule'
]);

//url configuration
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/', {
      templateUrl: 'view/root.html'
    }).
    otherwise({
      redirectTo: '/'
    });
}]);

app.run(['authService', function(authService) {
    authService.fillAuthData(); 
}]);

app.controller('indexController', ['$scope', '$location', 'authService', function ($scope, $location, authService) {

    $scope.logout = function () {
        authService.logout();
        $location.path('/login/');
    }

    $scope.authentication = authService.authentication;

}]);