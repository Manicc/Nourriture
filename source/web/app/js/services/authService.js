app.factory('authService', ['$http', '$q', 'localStorageService', 'CONFIG', function($http, $q, localStorageService, CONFIG){
	var serviceBase = CONFIG.SERVER_ROOT;
	var authServiceFactory = {};

    var _authentication = {
        isAuth: false,
        username: "",
        token: ""
    };

    var _login = function (loginData) {

        var data = {
        	grant_type:'password',
        	username: loginData.username,
        	password: loginData.password,
        	client_id: CONFIG.CLIENT_ID,
        	client_secret: CONFIG.CLIENT_SECRET
        };

        var deferred = $q.defer();

        $http.post(serviceBase + 'o/token/', $.param(data), { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }).success(function (response) {

            localStorageService.set('authorizationData', { token: response.access_token, username: loginData.username});

            _authentication.isAuth = true;
            _authentication.username = loginData.username;

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
        _authentication.username = "";
        _authentication.token = "";

    };

    var _fillAuthData = function () {

        var authData = localStorageService.get('authorizationData');
        if (authData) {
            _authentication.isAuth = true;
            _authentication.username = authData.username;
            _authentication.useRefreshTokens = authData.useRefreshTokens;
        }

    };

    authServiceFactory.login = _login;
    authServiceFactory.logout = _logout;
    authServiceFactory.fillAuthData = _fillAuthData;
    authServiceFactory.authentication = _authentication;

    return authServiceFactory;
}]);