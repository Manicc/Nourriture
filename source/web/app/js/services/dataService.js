app.factory('dataService', ['$http', '$q', 'CONFIG', function ($http, $q, CONFIG) {
    var urlroot = CONFIG.SERVER_ROOT;
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

    var _ingredient_list = function () {
        return getdata('ingredient/');
    };

    var _ingredient_detial = function (pk) {
        return getdata('ingredient/' + pk + '/');
    };

    var _ingredient_cat = function () {
        return getdata('ingredient/category/');
    };

    // favorite
    var _add_like = function (type, id) {
        var deferred = $q.defer();

        $http.post(CONFIG.SERVER_ROOT + type + '/' + id + '/favorite/').
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

        $http.delete(CONFIG.SERVER_ROOT + type + '/' + id + '/favorite/'+like_id+'/').
            success(function (data) {
                deferred.resolve(data);
            }).
            error(function (err, status) {
                deferred.reject(err);
            });

        return deferred.promise;
    };

    dataServiceFactory.ingredient_list = _ingredient_list;
    dataServiceFactory.ingredient_detial = _ingredient_detial;
    dataServiceFactory.ingredient_cat = _ingredient_cat;

    dataServiceFactory.add_like = _add_like;
    dataServiceFactory.get_like = _get_like;
    dataServiceFactory.delete_like = _delete_like;

    return dataServiceFactory;
}])