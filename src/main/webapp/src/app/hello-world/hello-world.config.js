angular.module('my-module.hello-world', [])
  .config(function config($stateProvider) {
    $stateProvider.state('hello-world', {
      url: '/hello-world',
      views: {
        main: {
          controller: 'HelloWorldController',
          templateUrl: 'hello-world/hello-world.tpl.html'
        }
      }
    });
  });
