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

routing.controller('StudentLoginController',['$scope', '$http', '$location', '$cookieStore', function($scope, $http, $location, $cookieStore) {
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

routing.controller('StudentListController',['$scope', '$http', '$cookieStore', function($scope, $http, $cookieStore){
  
  $scope.name = $cookieStore.get("name");
  pagination($scope);
  
  if ($scope.name) {
    $("#myTab").find("li .glyphicon-registration-mark").replaceWith("<a href='#/logout'>Logout</a>");
  }
  $scope.orderProp = "name";
  $scope.$watch("currentPage", function() {
    $http.get('/liststudent/'+$scope.currentPage).success( function (data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
    
  });
  
}]);

routing.controller('StudentDetailController',['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
  $scope.studentId = $routeParams.studentId;
  $http.get('/students/'+$scope.studentId).success( function(data) {
    $scope.studentDetail = data;
  }).error();
}]);

routing.controller('StudentUpdateController',['$scope', '$http', '$routeParams','$location', function($scope, $http, $routeParams,$location) {
  $scope.title= "Update";
  $scope.studentId = $routeParams.studentId;
  $http.get('/updatestudents/'+$scope.studentId).success( function(data) {
    $scope.studentUpdateInfo = data;
  });
  
  $scope.processForm = function() {
    $http.put('/doupdate/'+$scope.studentId, $scope.studentUpdateInfo).success( function(data) {
      $location.path('/')
    }).error();
  };
  $scope.isRegister = function (){
    return true;
  }
  
}]);

routing.controller('StudentDeleteController',['$scope', '$http', '$routeParams','$location', function($scope, $http, $routeParams,$location) {
  $scope.studentId = $routeParams.studentId;
  $http.delete('/deletestudent/'+$scope.studentId).success( function(data) {
    $location.path('/');
  }).error();
}]);

routing.controller('StudentRegister',['$scope', function($scope){
  $scope.title = "Register";
}]);


routing.controller('StudentRegisterController',['$scope', '$http', '$location','$route', function($scope, $http, $location,$route) {
  $scope.title = "Register";
  $scope.processForm = function () {
    $http.post('/newstudent',$scope.studentUpdateInfo).success( function(data) {
        $location.path('/');
    }).error();
  };
  $scope.isRegister = function () {
    return true;
  }
  
}]);

routing.controller('BoyStudentController', ['$scope', '$http', '$location', function($scope, $http, $location) {
  
  pagination($scope);
  $scope.$watch("currentPage", function() {
    $http.get('/getBoyStudents/'+$scope.currentPage).success( function(data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
  });
  
}]);

routing.controller('GirlStudentController',['$scope','$http', '$location', function($scope, $http, $location) {
  
  pagination($scope);
  
  $scope.$watch("currentPage", function() {
    $http.get('/getGirlStudents/'+$scope.currentPage).success( function(data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
  });
  
}]);

routing.controller('LogoutController', ['$cookieStore','$location', function($cookieStore, $location) {
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
