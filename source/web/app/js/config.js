//set this to true when using the local server
var LOCALSERVER = true;

var config_data;
if (LOCALSERVER)
{
	config_data = {
		'CONFIG': {
			'SERVER_ROOT': 'http://172.29.70.197:8000',
			'CLIENT_ID': 'lXRLf2cfW-3dtK!yYKcTX5I6tPPuQ@t3nZ;IJNcs',
			'CLIENT_SECRET': '8gW;pX?VTEz0pyL?EX7smQUveTppxbTJGzDeG0.r=mM:8JR_nn!ZK@wzWm;FZI7nEj?MmpW3rs8Z_r08.ApnEaykC0:MXC0sINNee8KhQI0vFoW!5F;eh:CYcUyXz;DT'
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
	app.constant(value,key);
})