(function(){
    'use strict';

    angular.module("meetime")
    .factory("carFactory", carFactory)

    carFactory.$inject = ['$http', '$rootScope', '$mdToast', 'urlApi']
    function carFactory($http, $rootScope, $mdToast, urlApi){ 
        var url = urlApi.car;

       
        return {
            getAll :getAll,
            getById :getById,
            update : update,
            create : create,
            remove : remove
        };

        function getAll(){
            $rootScope.loading++;
            return $http.get(url).then(defaultSuccess, defaultError);
        }

        function getById(idCar){
            $rootScope.loading++;
            return $http.get(url + "/" + idCar).then(defaultSuccess, defaultError);
        }

        function create(car){
            if($rootScope.loading){ return }
            $rootScope.loading++;
            return $http.post(url, car).then(defaultSuccess, defaultError);
        }

        function update(car){
            if($rootScope.loading){ return }
            $rootScope.loading++;
            return $http.put(url, car).then(defaultSuccess, defaultError);
        }

        function remove(idCar){
            if($rootScope.loading){ return }
            $rootScope.loading++;
            return $http.delete(url + "/" + idCar).then(defaultSuccess, defaultError);
        }

        function defaultSuccess(resp){
            $rootScope.loading--;
            return resp;
        }

        function defaultError(resp){
            $rootScope.loading--;
            showToast(resp.data)
            throw resp.data
        }

        function showToast(msg){
			var toast = $mdToast.simple()
				.textContent(msg)
				.position('top right');
			$mdToast.show(toast)
		}

    }
})()