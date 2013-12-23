app.controller('PersonController', function($scope, $http) {
    $http({method: 'GET', url: '/person'})
        .success(function (persons){
            $scope.persons = persons;
        })
        .error(function(data, status, headers, config) {
            console.error("Superbad error has occured.");
        });
});
