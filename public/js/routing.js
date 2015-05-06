/**
 * 
 */
var routing = angular.module('routing',['ngRoute','ngCookies']);

routing.config(['$routeProvider', function($routeProvider) {
  
  $routeProvider.
    when('/', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: 'StudentListController',
      controllerAs: 'list'
    }).
    when('/studentsdetail/:studentId', {
      templateUrl: "/assets/views/students/student-detail.html",
      controller: 'StudentDetailController'
    }).
    when('/updatestudents/:studentId', {
      templateUrl: "/assets/views/students/student-update.html",
      controller: 'StudentUpdateController'
    }).
    when('/deletestudent/:studentId', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: 'StudentDeleteController'
    }).
    when('/createnew', {
      templateUrl: "/assets/views/students/student-register.html",
      controller: 'StudentRegisterController'
    }).
    when('/login', {
      templateUrl: "/assets/views/students/login.html",
      controller: 'StudentLoginController'
    }).
    when('/boys', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: 'BoyStudentController'
    }).
    when('/girls', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: 'GirlStudentController'
    }).
    when('/logout', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: 'LogoutController'
    }).
    otherwise({
      redirectTo: '/'
    });
}]);

routing.controller('StudentLoginController',['$scope', '$http', '$location', '$cookieStore', 'Student', function($scope, $http, $location, $cookieStore) {
  $scope.formData = {};
  $scope.processForm = function (){
    $http.post('/login',$scope.formData).success( function(data) {
      
       $cookieStore.put("name",$scope.formData.name);
       if (data ==="true") {
         $location.path('/');
       } else {
         $cookieStore.remove("name");
         location.reload();
       }
    });
  };
}]);

routing.controller('StudentListController',['$scope', '$http', '$cookieStore', 'Student', function($scope, $http, $cookieStore, Student){
  
  $scope.name = $cookieStore.get("name");
  pagination($scope);
  
  if ($scope.name) {
    $("#myTab").find("li .glyphicon-registration-mark").replaceWith("<a href='#/logout'>Logout</a>");
  }
  $scope.orderProp = "name";
  
  $scope.students = Student.query();
}]);

routing.controller('StudentDetailController',['$scope', '$http', '$routeParams', 'Student', function($scope, $http, $routeParams, Student) {
  $scope.studentId = $routeParams.studentId;
  
  $scope.studentDetail = Student.get({id:$scope.studentId});
  
  console.log("detail");
}]);

routing.controller('StudentUpdateController',['$scope', '$http', '$routeParams','$location', 'Student', function($scope, $http, $routeParams,$location, Student) {
  $scope.title= "Update";
  $scope.studentId = $routeParams.studentId;
  
  $scope.studentUpdateInfo = Student.get({id:$scope.studentId});
  
  $scope.processForm = function() {
    console.log($scope.studentUpdateInfo.name);
    $scope.studentUpdateInfo.$update(function() {
      $location.path('/');
    });
  }
  $scope.isRegister = function (){
    return true;
  }
  
}]);

routing.controller('StudentDeleteController',['$scope', '$http', '$routeParams','$location', 'Student', function($scope, $http, $routeParams,$location, Student) {
  $scope.studentId = $routeParams.studentId;
  
  Student.delete({id:$scope.studentId}, function() {
    $location.path('/');
  });
}]);

routing.controller('StudentRegister',['$scope', function($scope){
  $scope.title = "Register";
}]);


routing.controller('StudentRegisterController',['$scope', '$http', '$location','$route', 'Student', function($scope, $http, $location,$route, Student) {
  $scope.title = "Register";
  
  $scope.studentUpdateInfo = new Student();
  
  $scope.processForm = function () {
    $scope.studentUpdateInfo.$save( function () {
      $location.path('/');
    });
  };
  $scope.isRegister = function () {
    return true;
  }
  
}]);

routing.controller('BoyStudentController', ['$scope', '$http', '$location', 'Boy', function($scope, $http, $location, Boy) {
  
  pagination($scope);
  $scope.students = Boy.query();
  
}]);

routing.controller('GirlStudentController',['$scope','$http', '$location', 'Girl', function($scope, $http, $location, Girl) {
  
  pagination($scope);
  
  $scope.students = Girl.query();
  
}]);

routing.controller('LogoutController', ['$cookieStore','$location', 'Student', function($cookieStore, $location, Student) {
  $cookieStore.remove("name");
  $("#myTab").find("li.active").replaceWith("<li><a href='#/login' class='glyphicon glyphicon-registration-mark'> Login </a></li>");
  $location.path('/');
  
}]);

var pagination = function(scope) {
  
  scope.totalItems = 0;
  scope.currentPage = 1;

  scope.setPage = function (pageNo) {
    scope.currentPage = pageNo;
  };

  scope.maxSize = 5;
  scope.bigTotalItems = 175;
  scope.bigCurrentPage = 1;
  
}
