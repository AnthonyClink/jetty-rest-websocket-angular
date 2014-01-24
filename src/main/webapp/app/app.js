var app = angular.module("test", ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/newPerson", {
            controller: 'NewPersonController',
            templateUrl: '/app/partials/newPerson.html'
        })
        .otherwise({
            controller: 'PersonController',
            templateUrl: '/app/partials/persons.html'
        });
});
