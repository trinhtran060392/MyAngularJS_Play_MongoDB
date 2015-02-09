'use strict';

/* jasmine specs for controllers go here */
describe('PhoneCat controllers', function(){

  beforeEach(function(){
    this.addMatchers({
      toEqualData: function(expected){
        return angular.equals(this.actual, expected);
      }
    });

  });
  beforeEach(module('myApp'));
  beforeEach(module('directive'));
  beforeEach(module('routing'));
  describe('Student List Ctrl', function() {
    var scope, ctrl, $httpBackend;
    
    beforeEach(inject(function(_$httpBackend_,$rootScope, $controller) {
      $httpBackend = _$httpBackend_;
      scope = $rootScope.$new();
      ctrl = $controller('StudentListController',{$scope: scope});
    }));
    it('should create "Students" model with 2 students fetched from xhr', function() {
      $httpBackend.expectGET('/liststudent/1').respond([{name: 'trinh'},{name: 'trinh tran'}]);
      $httpBackend.flush();
      expect(scope.students).toEqualData([{name: 'trinh'},{name: 'trinh tran'}]);
      
    });
    
    it('should set the default value of orderProp model', function() {
      expect(scope.orderProp).toBe('name');
    });
  });

});