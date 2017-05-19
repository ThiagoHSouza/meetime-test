(function(){
    'use strict';

    angular.module("meetime")
    .factory("pipedriveFactory", pipedriveFactory)

    pipedriveFactory.$inject = ['$http', '$rootScope', 'urlApi']
    function pipedriveFactory($http, $rootScope, urlApi){ 
        var url = urlApi.pipedrive;
       
        return {
            getByQuery :getByQuery,
            getById :getById,
            getFirstTen :getFirstTen,
        };

        function getByQuery(query, keyPipedrive){
            return $http.get(url + '/find?term=' + query + '&start=0&limit=10&search_by_email=0&api_token=' + keyPipedrive);
        }

        function getById(idPerson, keyPipedrive){
            return $http.get(url + "/" + idPerson +"?api_token=" + keyPipedrive);
        }

        function getFirstTen(keyPipedrive){
            return $http.get(url + '?start=0&limit=10&api_token=' + keyPipedrive);
        }

    }
})()