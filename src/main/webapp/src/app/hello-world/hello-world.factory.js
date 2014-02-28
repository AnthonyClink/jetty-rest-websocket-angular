angular.module('my-module.hello-world')
  .factory("helloWorldFactory", function ($http) {
    function getGreeting(callback) {
      $http({
        method: 'GET',
        url: '/rest/helloWorld'
      }).success(function(data) {
        callback(data);
      });
    }
    return {
      getGreeting: getGreeting
    };
  });

