var recipe = angular.module('recipe', ['config'])

//url configuration
app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    when('/recipe/', {
      templateUrl: 'view/recipes.html',
      controller: 'RecipeListCtrl'
    }).
    when('/recipe/:id', {
      templateUrl: 'view/recipe_detail.html',
      controller: 'RecipeDetailCtrl'
    })
}]);

//controllers
app.controller('RecipeListCtrl', ['$scope', '$http', 'CONFIG', 
  function($scope, $http, CONFIG){
  $http.get(CONFIG.SERVER_ROOT+'recipe/').success(function(data){
      $scope.recipes = data;
    })
}]);

app.controller('RecipeDetailCtrl', ['$scope', 'dataService', 'authService', '$routeParams',
	function ($scope, dataService, authService, $routeParams) {
		$scope.liked = false;

        dataService.recipe_detial($routeParams.id).then(function (response) {
            $scope.recipe = response;
        });

        dataService.get_like('recipe', $routeParams.id).then(function (response) {
            for (var item in response) {
                if (response[item].user.username == authService.authentication.username) {
                    $scope.liked = true;
                    $scope.like_id = response[item].id;
                }
            }
        });

        dataService.get_comment('recipe', $routeParams.id).then(function (response) {
            $scope.comments = response;
        });

        $scope.add_comment = function () {
            dataService.add_comment('recipe', $routeParams.id, $scope.content).then(function (response) {
                $scope.comments.splice(0, 0, response);
                $scope.content = '';
            });
        };

        $scope.like = function () {
            if ($scope.liked) {
                dataService.delete_like('recipe', $routeParams.id, $scope.like_id).then(function (response) {
                    $scope.liked = false;
                });
            }
            else {
                dataService.add_like('recipe', $routeParams.id).then(function (response) {
                    $scope.liked = true;
                    $scope.like_id = response.id;
                });
            }
        };
}]);