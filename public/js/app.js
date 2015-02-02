
var myapp = angular.module('myApp',['directive','routing']);
myapp.controller('hello',['$scope', function($scope){
  
  $scope.hello = "Angular JS"
}]);

