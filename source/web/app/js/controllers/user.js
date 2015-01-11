var user = angular.module('user', ['config']);

//url configuration
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/login/', {
      templateUrl: 'view/login.html',
      controller: 'LoginCtrl'
    }).
    when('/register/', {
      templateUrl: 'view/register.html',
      controller: 'RegisterCtrl'
    })
}]);

//controllers
app.controller('LoginCtrl', ['$scope', '$location', 'authService', 'CONFIG',
	function($scope, $location, authService, CONFIG){
    $scope.loginData = {
      username: "",
      password: ""
    };

    $scope.message = "";

    $scope.login = function () {
      authService.login($scope.loginData).then(function (response) {
        $location.path('/ingredient/');
      },
      function (err) {
        $scope.message = err.error_description;
      });
    };
}]);

app.controller('RegisterCtrl', ['$scope', '$http', '$location', 'CONFIG',
  function($scope, $http, $location, CONFIG){
    $scope.user = {
      username: "",
      password: ""
    };

    $scope.message = "";

    $scope.register = function(){
      $http.post(CONFIG.SERVER_ROOT+"/user/", $scope.user).success(function(){
        $location.path('/login/');
      });
    };    
}]);

