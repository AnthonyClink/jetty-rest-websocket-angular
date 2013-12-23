var app = angular.module("test", ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .otherwise({
            controller: 'PersonController',
            templateUrl: '/app/partials/persons.html'
        });
});
