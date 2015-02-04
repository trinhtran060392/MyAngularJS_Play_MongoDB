/**
 * 
 */
var routing = angular.module('routing',['ngRoute']);


routing.config(['$routeProvider', function($routeProvider) {
  
  $routeProvider.
    when('/', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: StudentListController,
      controllerAs: 'list'
    }).
    when('/studentsdetail/:studentId', {
      templateUrl: "/assets/views/students/student-detail.html",
      controller: StudentDetailController
    }).
    when('/updatestudents/:studentId', {
      templateUrl: "/assets/views/students/student-update.html",
      controller: StudentUpdateController
    }).
    when('/deletestudent/:studentId', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: StudentDeleteController
    }).
    when('/createnew', {
      templateUrl: "/assets/views/students/student-register.html",
      controller: StudentRegisterController
    }).
    when('/login', {
      templateUrl: "/assets/views/students/login.html",
      controller: StudentLoginController
    }).
    when('/boys', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: BoyStudentController
    }).
    when('/girls', {
      templateUrl: "/assets/views/students/student-list.html",
      controller: GirlStudentController
    }).
    otherwise({
      redirectTo: '/'
    });
}]);

function StudentLoginController($scope, $http, $location) {
 
  $scope.formData = {};
  $scope.processForm = function (){
    $http.post('/login',$scope.formData).success( function(data) {
      
       if (data ==="true") {
         $location.path('/');
       } else location.reload();
    });
  };
}

function StudentListController($scope, $http) {

  pagination($scope);
  
  $scope.$watch("currentPage", function() {
    $http.get('/liststudent/'+$scope.currentPage).success( function (data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
    
  });
}

function StudentDetailController($scope, $http, $routeParams) {
 
  $scope.studentId = $routeParams.studentId;
  $http.get('/students/'+$scope.studentId).success( function(data) {
    $scope.studentDetail = data;
  }).error();
    
}

function StudentUpdateController($scope, $http, $routeParams,$location) {
  
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
}

function StudentDeleteController($scope, $http, $routeParams,$location) {
  
  $scope.studentId = $routeParams.studentId;
  $http.delete('/deletestudent/'+$scope.studentId).success( function(data) {
    $location.path('/');
  }).error();
  
}

function StudentRegister($scope) {
  
  $scope.title = "Register";
  
}

function StudentRegisterController($scope, $http, $location,$route) {
  
  $scope.title = "Register";
  $scope.processForm = function () {
    $http.post('/newstudent',$scope.studentUpdateInfo).success( function(data) {
        $location.path('/');
    }).error();
  };
  $scope.isRegister = function () {
    return true;
  }
 
}

function BoyStudentController($scope, $http, $location) {
  
  pagination($scope);
  
  $scope.$watch("currentPage", function() {
    $http.get('/getBoyStudents/'+$scope.currentPage).success( function(data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
  });
  
}

function GirlStudentController($scope, $http, $location) {
  
  pagination($scope);
  
  $scope.$watch("currentPage", function() {
    $http.get('/getGirlStudents/'+$scope.currentPage).success( function(data) {
      $scope.students = data;
      $scope.total = $scope.students[0].total;
      $scope.totalItems = $scope.students[0].total;
    });
  });
  
}

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
