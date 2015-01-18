//set this to true when using the local server
var LOCALSERVER = false;

var config_data;
if (LOCALSERVER)
{
	config_data = {
		'CONFIG': {
			'SERVER_ROOT': 'http://127.0.0.1:8000/',
			'CLIENT_ID': 'lXRLf2cfW-3dtK!yYKcTX5I6tPPuQ@t3nZ;IJNcs',
			'CLIENT_SECRET': '8gW;pX?VTEz0pyL?EX7smQUveTppxbTJGzDeG0.r=mM:8JR_nn!ZK@wzWm;FZI7nEj?MmpW3rs8Z_r08.ApnEaykC0:MXC0sINNee8KhQI0vFoW!5F;eh:CYcUyXz;DT'
		}
	}
}
else
{
	config_data = {
		'CONFIG': {
			'SERVER_ROOT': 'http://nourriture.sinaapp.com/',
            'CLIENT_ID': 'fLPDC=Nvj8tl0vChOZRx9hvcryBZje@FWfU5PGFP',
			'CLIENT_SECRET': 'IAHaGKoGc9i:DzJ@-C8yAnhDo-UHkUL?Liw0A5ma15jdSFZYCkuCzghdHW3HRdkA9RqR1P:gQGBZtl4H3qClRxCFLpZKHd3e?We_mMaoOCWQkMB2e@vpXf-NxRR@dyWg'
		}
	}
}

angular.forEach(config_data,function(key,value) {
	app.constant(value,key);
})