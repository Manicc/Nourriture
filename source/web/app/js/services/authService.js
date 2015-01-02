app.factory('authService', ['$http', '$q', 'localStorageService', 'CONFIG', function($http, $q, localStorageService, CONFIG){
	var serviceBase = CONFIG.SERVER_ROOT;
	var authServiceFactory = {};

    var _authentication = {
        isAuth: false,
        userName: "",
        token: ""
    };

    var _login = function (loginData) {

        var data = {
        	grant_type:'password',
        	username: loginData.userName,
        	password: loginData.password,
        	client_id: CONFIG.CLIENT_ID,
        	client_secret: CONFIG.CLIENT_SECRET
        }

        var deferred = $q.defer();

        $http.post(serviceBase + '/o/token/', $.param(data), { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }).success(function (response) {

            localStorageService.set('authorizationData', { token: response.access_token, userName: loginData.userName});

            _authentication.isAuth = true;
            _authentication.userName = loginData.userName;

            deferred.resolve(response);

        }).error(function (err, status) {
            _logout();
            deferred.reject(err);
        });

        return deferred.promise;
    };

    var _logout = function () {

        localStorageService.remove('authorizationData');

        _authentication.isAuth = false;
        _authentication.userName = "";
        _authentication.token = "";

    };

    var _fillAuthData = function () {

        var authData = localStorageService.get('authorizationData');
        if (authData) {
            _authentication.isAuth = true;
            _authentication.userName = authData.userName;
            _authentication.useRefreshTokens = authData.useRefreshTokens;
        }

    };

    authServiceFactory.login = _login;
    authServiceFactory.logout = _logout;
    authServiceFactory.fillAuthData = _fillAuthData;
    authServiceFactory.authentication = _authentication;

    return authServiceFactory;
}])