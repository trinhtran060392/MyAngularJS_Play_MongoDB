
var service = angular.module('service',['ngResource']).factory('Student', function($resource) {
  
  return $resource('/students/:id', {id: '@_id'}, {
    'update': {
      method: 'PUT'
    }
  });
}).factory('Boy', function($resource) {
  return $resource('/boys');
}).factory('Girl', function($resource) {
  return $resource('/girls');
});