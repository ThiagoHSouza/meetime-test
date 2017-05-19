(function(){
    'use strict';

    angular.module("meetime")
    .factory("colorCarFactory", colorCarFactory)

    colorCarFactory.$inject = ['$http', '$rootScope', '$mdToast', 'urlApi']
    function colorCarFactory($http, $rootScope, $mdToast, urlApi){ 
        var url = urlApi.colorCar;

       
        return {
            list :list,
        };

        function list(){
            $rootScope.loading++;
            return $http.get(url).then(defaultSuccess, defaultError);
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