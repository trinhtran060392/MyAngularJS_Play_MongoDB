'use strict';

/* jasmine specs for controllers go here */
describe('Student Controllers', function(){

	beforeEach(function(){
		this.addMatchers({
			toEqualData: function(expected){
				return angular.equals(this.actual, expected);
			}
		});

	});
	beforeEach(module('myApp'));
	beforeEach(module('routing'));
	beforeEach(module('directive'));
	describe('list student', function() {
		var scope, ctrl, $httpBackend;
	  beforeEach(module('routing'));

	 	it('should set the default value of orderProp model', function() {
	 		expect(scope.orderProp).toBe('name');
	 	});
	});
});