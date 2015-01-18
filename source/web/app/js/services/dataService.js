'use strict';

var app = angular.module('app');

app.factory('dataService', ['$http', '$q', 'CONFIG', function ($http, $q, CONFIG) {
    var dataServiceFactory = {};

    var getdata = function (location) {

        var deferred = $q.defer();

        $http.get(CONFIG.SERVER_ROOT + location).
            success(function (data) {
                deferred.resolve(data);
            }).
            error(function (err, status) {
                deferred.reject(err);
            });

        return deferred.promise;
    };

    //ingredient
    var _ingredient_list = function () {
        return getdata('ingredient/');
    };

    var _ingredient_detial = function (pk) {
        return getdata('ingredient/' + pk + '/');
    };

    var _ingredient_cat = function () {
        return getdata('ingredient/category/');
    };

    //product
    dataServiceFactory.product_list = function () {
        return getdata('product/');
    };

    dataServiceFactory.product_detial = function (pk) {
        return getdata('product/' + pk + '/');
    };

    //recipe
    dataServiceFactory.recipe_list = function () {
        return getdata('recipe/');
    };

    dataServiceFactory.recipe_detial = function (pk) {
        return getdata('recipe/' + pk + '/');
    };

    // favorite
    var _add_like = function (type, id) {
        var deferred = $q.defer();

        $http.post(CONFIG.SERVER_ROOT + type + '/' + id + '/favorite/', {}).
            success(function (data) {
                deferred.resolve(data);
            }).
            error(function (err, status) {
                deferred.reject(err);
            });

        return deferred.promise;
    };

    var _get_like = function (type, id) {
        return getdata(type + '/' + id + '/favorite/');
    };

    var _delete_like = function (type, id, like_id) {
        var deferred = $q.defer();

        $http.delete(CONFIG.SERVER_ROOT + type + '/' + id + '/favorite/' + like_id + '/').
            success(function (data) {
                deferred.resolve(data);
            }).
            error(function (err, status) {
                deferred.reject(err);
            });

        return deferred.promise;
    };

    //comment
    var _add_comment = function (type, id, content) {
        var deferred = $q.defer();

        var data = {};
        data.content = content;

        $http.post(CONFIG.SERVER_ROOT + type + '/' + id + '/comment/', data).
            success(function (data) {
                deferred.resolve(data);
            }).
            error(function (err, status) {
                deferred.reject(err);
            });

        return deferred.promise;
    };

    var _get_comment = function (type, id) {
        return getdata(type + '/' + id + '/comment/');
    };

    dataServiceFactory.ingredient_list = _ingredient_list;
    dataServiceFactory.ingredient_detial = _ingredient_detial;
    dataServiceFactory.ingredient_cat = _ingredient_cat;

    dataServiceFactory.add_like = _add_like;
    dataServiceFactory.get_like = _get_like;
    dataServiceFactory.delete_like = _delete_like;

    dataServiceFactory.add_comment = _add_comment;
    dataServiceFactory.get_comment = _get_comment;

    return dataServiceFactory;
}]);