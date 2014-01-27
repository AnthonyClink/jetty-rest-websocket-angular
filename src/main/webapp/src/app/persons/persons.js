angular.module('test.persons', [])

.config(function config($stateProvider) {
    $stateProvider.state('persons', {
        url: '/persons',
        views: {
            main : {
                controller: 'PersonsController',
                templateUrl: 'persons/persons.tpl.html'
            }
        }
    });
})

.controller('PersonsController', function($scope, $http) {
    $http({method: 'GET', url: '/person'})
        .success(function (persons){
            $scope.persons = persons;
        })
        .error(function(data, status, headers, config) {
            console.error("Superbad error has occured.");
        });
});
