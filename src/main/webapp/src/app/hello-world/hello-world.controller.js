angular.module('my-module.hello-world')
  .controller('HelloWorldController',
  function ($scope, helloWorldFactory) {
    helloWorldFactory.getGreeting(function(data) {
      $scope.greeting = data;
    });
  });
