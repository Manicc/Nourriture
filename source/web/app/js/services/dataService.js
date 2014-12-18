app.factory('dataService', ['$http', '$q', 'CONFIG', function($http, $q, CONFIG){
	var urlroot = CONFIG.SERVER_ROOT;
	var dataServiceFactory = {}

	var getdata = function(location){

		var deferred = $q.defer();

		$http.get(CONFIG.SERVER_ROOT+location).
		success(function(data){
	  		deferred.resolve(data);
		}).
		error(function (err, status) {
            deferred.reject(err);
        });

        return deferred.promise;
	}

	var _ingredient_list = function(){
		return getdata('/ingredient/');
	}

	var _ingredient_detial = function(pk){
		return getdata('/ingredient/'+pk+'/');
	}

	dataServiceFactory.ingredient_list = _ingredient_list;
	dataServiceFactory.ingredient_detial = _ingredient_detial;

	return dataServiceFactory;	
}])