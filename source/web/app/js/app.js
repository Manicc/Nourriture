//the main module
var app = angular.module('app', [
  'ngRoute',
  'gastronomist',
  'ingredient',
  'nutrition',
  'product',
  'recipe',
  'supplier',
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