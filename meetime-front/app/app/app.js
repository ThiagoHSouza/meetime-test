(function(){
	'use strict';

	angular.module('meetime', [
		'ui.router', 
		'ngMaterial', 
		'ngMessages',
		'ui.mask'
	])

	.config(config)
	.run(run)
	.constant('urlApi', {
		car : 'http://localhost:9000/api/car',
		colorCar : 'http://localhost:9000/api/colorCar',
		pipedrive : 'https://api.pipedrive.com/v1/persons'
	});

	config.$inject = ['$stateProvider', '$urlRouterProvider'];
	function config($stateProvider, $urlRouterProvider){
		$stateProvider
		.state('index', {
			url:'/',
	      	abstract: true,	    	
	    	templateUrl: 'app/home.view.html',
	    	controller: "homeController",
        	controllerAs: "homeCtrl"
		})

		.state('index.car', {
			url:"^/car?key",
			resolve: {
				keyPipedrive: ['$stateParams', function($stateParams){
					return $stateParams.key
				}],
				name: [function(){
					return 'Carros';
				}]
			},
	    	views: {
		        "toolbar": { templateUrl: "app/templates/toolbar/toolbar.view.html" },
			    "sidenav":{ templateUrl: "app/templates/sidenav/sidenav.view.html" },
			    "content":{
		        	templateUrl: "app/templates/content/car/car.view.html",
		        	controller: "carController",
		        	controllerAs: "carCtrl"
			    }
	      	}
		});

    	$urlRouterProvider.otherwise('/car');
	}



	run.$inject = ['$rootScope'];
	function run($rootScope){
		$rootScope.loading = 0;
	}

})()