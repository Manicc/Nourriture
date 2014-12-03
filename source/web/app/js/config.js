//set this to true when using the local server
var LOCALSERVER = false;
var config_module = angular.module('config', []);

var config_data;
if (LOCALSERVER)
{
	config_data = {
		'CONFIG': {
			'SERVER_ROOT': 'http://127.0.0.1:8000'
		}
	}
}
else
{
	config_data = {
		'CONFIG': {
			'SERVER_ROOT': 'http://nourriture.sinaapp.com'
		}
	}
}

angular.forEach(config_data,function(key,value) {
	config_module.constant(value,key);
})