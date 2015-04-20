
var myapp = angular.module('myApp',['directive','service','routing']);
myapp.controller('hello',['$scope', function($scope){
  
  $scope.hello = "Angular JS"
}]);

