/**
 * 
 */

var myDirective = angular.module('directive',[]);

myDirective.directive("leftMenu", function() {
  
  return {
    restrict: "E",
    templateUrl: "/assets/views/students/left_menu.html"
   
  };
});
myDirective.directive("tabPanel", function() {
  return {
    restrict: "E",
    templateUrl: "/assets/views/students/tabs_panel.html"
  };
});

myDirective.directive("searchBox", function() {
  return {
    restrict: "E",
    templateUrl: "/assets/views/students/search.html",
    controller: function($scope) {
      $scope.orderProp = "age";
    },
    controllerAs: "search"
  };
});

myDirective.directive("footerTag", function() {
  return {
    restrict: "E",
    templateUrl: "/assets/views/students/footer.html"
  };
  
});