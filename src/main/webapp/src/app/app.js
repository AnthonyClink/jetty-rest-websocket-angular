angular.module("my-module", [
    'ui.router',
    'templates-app',
    'templates-common',
    'my-module.hello-world'
])

.config(function ($urlRouterProvider) {
    $urlRouterProvider.otherwise('/hello-world');
});
