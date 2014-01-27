angular.module('test.new-person', ['ui.state'])

.config(function ($stateProvider) {
    $stateProvider.state('new-person', {
        url: '/new-person',
        views: {
            main: {
                controller: 'NewPersonController',
                templateUrl: 'new-person/new-person.tpl.html'
            }
        }
    });
})

.controller('NewPersonController', function ($scope, $http) {
    $scope.addNewPerson = function () {
        $http.post('/person/new-person', $scope.newPersonName);
        update();
    };

    update();

    function update() {
        $http({method: 'GET', url: '/person'})
            .success(function (persons){
                $scope.persons = persons;
            })
            .error(function(data, status, headers, config) {
                console.error("Superbad error has occured.");
            });
    }
});
