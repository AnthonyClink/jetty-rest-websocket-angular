angular.module("test", [
        'templates-app',
        'templates-common',
        'test.new-person',
        'test.persons'])

.config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise('/persons');
});
