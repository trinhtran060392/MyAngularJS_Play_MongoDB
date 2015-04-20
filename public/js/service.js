
angular.module('service',[]).factory('Student', function($resource) {
  
  return $resource('/:id', {id: '@_id'}, {
    update: {
      method: 'PUT'
    }
  });
});