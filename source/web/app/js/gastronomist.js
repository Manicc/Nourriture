var gastronomistAPP = angular.module('gastronomistAPP', []);

gastronomistAPP.controller('FoodListCtrl', function ($scope) {
  $scope.foods = [
    {"name": "cooked rice",
     "snippet": "made with good rice."},
    {"name": "dumplings",
     "snippet": "boiled pork dumpling"},
    {"name": "apple",
     "snippet": "apple can't not eat."}
  ];
});