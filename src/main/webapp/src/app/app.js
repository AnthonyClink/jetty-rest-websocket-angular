angular.module("test", [
        'ui.state',
        'templates-app',
        'templates-common',
        'test.new-person',
        'test.persons'])

.config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise('/persons');
});
