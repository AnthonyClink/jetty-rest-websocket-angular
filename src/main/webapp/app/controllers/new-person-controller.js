app.controller('NewPersonController', function ($scope, $http) {
    $scope.addNewPerson = function () {
        $http.post('/person/newPerson', $scope.newPersonName);
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
