//url configuration
app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/ingredient/', {
                templateUrl: 'view/ingredients.html',
                controller: 'IngredientListCtrl'
            }).
            when('/ingredient_create/', {
                templateUrl: 'view/ingredient_create.html',
                controller: 'IngredientCreateCtrl'
            }).
            when('/ingredient/:id', {
                templateUrl: 'view/ingredient_detial.html',
                controller: 'IngredientDetialCtrl'
            })
    }
]);

//controllers
app.controller('IngredientListCtrl', ['$scope', 'dataService',
    function ($scope, dataService) {
        dataService.ingredient_list().then(function (response) {
            $scope.ingredients = response;
        });
    }
]);

app.controller('IngredientDetialCtrl', ['$scope', 'dataService', 'authService', '$routeParams', 'CONFIG',
    function ($scope, dataService, authService, $routeParams) {
        $scope.liked = false;

        dataService.ingredient_detial($routeParams.id).then(function (response) {
            $scope.ingredient = response;
        });

        dataService.get_like('ingredient', $routeParams.id).then(function (response) {
            for (var item in response) {
                if (response[item].user.username == authService.authentication.username) {
                    $scope.liked = true;
                    $scope.like_id = response[item].id;
                }
            }
        });

        dataService.get_comment('ingredient', $routeParams.id).then(function (response) {
            $scope.comments = response;
        });

        $scope.add_comment = function () {
            dataService.add_comment('ingredient', $routeParams.id, $scope.content).then(function (response) {
                $scope.comments.splice(0, 0, response);
                $scope.content = '';
            });
        };

        $scope.like = function () {
            if ($scope.liked) {
                dataService.delete_like('ingredient', $routeParams.id, $scope.like_id).then(function (response) {
                    $scope.liked = false;
                });
            }
            else {
                dataService.add_like('ingredient', $routeParams.id).then(function (response) {
                    $scope.liked = true;
                    $scope.like_id = response.id;
                });
            }
        };
    }
]);

app.controller('IngredientCreateCtrl', ['$scope', 'dataService', '$timeout', '$location', '$upload', function ($scope, dataService, $timeout, $location, $upload) {
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
