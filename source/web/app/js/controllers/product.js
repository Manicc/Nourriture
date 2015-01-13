var product = angular.module('product', ['config'])

//url configuration
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/product/', {
                templateUrl: 'view/products.html',
                controller: 'ProductListCtrl'
            }).
            when('/product/:id', {
                templateUrl: 'view/product_detial.html',
                controller: 'ProductDetialCtrl'
            })
    }]);

//controllers
app.controller('ProductListCtrl', ['$scope', '$http', 'CONFIG',
    function ($scope, $http, CONFIG) {
        $scope.server = CONFIG.SERVER_ROOT;
        $http.get(CONFIG.SERVER_ROOT + '/product/').success(function (data) {
            $scope.products = data;
        });
    }
]);

app.controller('ProductDetialCtrl', ['$scope', '$http', '$routeParams', 'CONFIG',
    function ($scope, $http, $routeParams, CONFIG) {
        $http.get(CONFIG.SERVER_ROOT + '/product/' + $routeParams.id).success(function (data) {
            $scope.product = data;
        })
    }]);