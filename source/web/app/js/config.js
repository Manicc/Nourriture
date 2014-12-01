var config_module = angular.module('config', ['']);

var config_data = {
  'GENERAL_CONFIG': {
    'URLROOT': 'http://127.0.0.1:8000'
  }
}
angular.forEach(config_data,function(key,value) {
  config_module.constant(value,key);
}