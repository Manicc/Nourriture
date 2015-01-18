var product = angular.module('product', ['config'])

//url configuration
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/product/', {
                templateUrl: 'view/products.html',
                controller: 'ProductListCtrl'
            }).
            when('/product_create/', {
                templateUrl: 'view/product_create.html',
                controller: 'ProductCreateCtrl'
            }).
            when('/product/:id', {
                templateUrl: 'view/product_detial.html',
                controller: 'ProductDetialCtrl'
            })
    }]);

//controllers
app.controller('ProductListCtrl', ['$scope', 'dataService',
    function ($scope, dataService) {
        dataService.product_list().then(function (response) {
            $scope.products = response;
        });
    }
]);

app.controller('ProductDetialCtrl', ['$scope', '$routeParams','dataService','authService',
    function ($scope, $routeParams, dataService, authService) {
        $scope.liked = false;

        dataService.product_detial($routeParams.id).then(function (response) {
            $scope.product = response;
        });

        dataService.get_like('product', $routeParams.id).then(function (response) {
            for (var item in response) {
                if (response[item].user.username == authService.authentication.username) {
                    $scope.liked = true;
                    $scope.like_id = response[item].id;
                }
            }
        });

        dataService.get_comment('product', $routeParams.id).then(function (response) {
            $scope.comments = response;
        });

        $scope.add_comment = function () {
            dataService.add_comment('product', $routeParams.id, $scope.content).then(function (response) {
                $scope.comments.splice(0, 0, response);
                $scope.content = '';
            });
        };

        $scope.like = function () {
            if ($scope.liked) {
                dataService.delete_like('product', $routeParams.id, $scope.like_id).then(function (response) {
                    $scope.liked = false;
                });
            }
            else {
                dataService.add_like('product', $routeParams.id).then(function (response) {
                    $scope.liked = true;
                    $scope.like_id = response.id;
                });
            }
        };
    }]);


app.controller('ProductCreateCtrl', ['$scope', 'dataService', '$timeout', '$location', '$upload', function ($scope, dataService, $timeout, $location, $upload) {
    $scope.ingredient = {};
    dataService.ingredient_cat().then(function (response) {
        $scope.category = response;
    });

    $scope.fileReaderSupported = window.FileReader != null && (window.FileAPI == null || FileAPI.html5 != false);

    $scope.uploadPic = function (files) {
        $scope.formUpload = true;
        if (files != null) {
            var file = files[0];
            $scope.generateThumb(file);
            $scope.errorMsg = null;

            var data = $scope.formUpload ? $scope.ingredient : {};
            //data = $.param(data);

            file.upload = $upload.upload({
                url: 'http://127.0.0.1:8000/ingredient/',
                method: 'POST',
                data: data,
                file: file,
                fileFormDataName: 'image'
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                    var path = '/ingredient/' + response.data.id;
                    $location.path(path);
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            });

            file.upload.progress(function (evt) {
                // Math.min is to fix IE which reports 200% sometimes
                file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            });

            file.upload.xhr(function (xhr) {
                // xhr.upload.addEventListener('abort', function(){console.log('abort complete')}, false);
            });
        }
    }

    $scope.generateThumb = function (file) {
        if (file != null) {
            if ($scope.fileReaderSupported && file.type.indexOf('image') > -1) {
                $timeout(function () {
                    var fileReader = new FileReader();
                    fileReader.readAsDataURL(file);
                    fileReader.onload = function (e) {
                        $timeout(function () {
                            file.dataUrl = e.target.result;
                        });
                    }
                });
            }
        }
    }

}]);
