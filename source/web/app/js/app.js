var app = angular.module('app', [
  'ngRoute',
  'appControllers'
]);

app.config(['$routeProvider','$locationProvider',
  function($routeProvider, $locationProvider) {
    $routeProvider.
      when('/', {
        templateUrl: 'view/root.html',
        controller: 'RootCtrl'
      }).
      when('/ingredient', {
        templateUrl: 'view/ingredient_list.html',
        controller: 'IngredientListCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });

      $locationProvider.html5Mode(true);
  }]);