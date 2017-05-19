(function(){
	'use strict';

	angular.module('meetime')
	.controller('homeController', homeController);

	homeController.$inject = ['$scope', '$timeout', '$mdSidenav', '$mdMedia', '$state', 'carFactory', '$rootScope'];
	function homeController($scope, $timeout, $mdSidenav, $mdMedia, $state, carFactory, $rootScope){
		var vm = this;



		$scope.toggleMenu = buildDelayedToggler('menu');
		$scope.isState = isState;
		$scope.toState = toState;
		$scope.openSideMedia = 'gt-sm'

	    function buildDelayedToggler(navID) {
	    	return function(){
	    		if($mdMedia($scope.openSideMedia)){return}
				$mdSidenav(navID)
				.toggle()	    		
	    	}
	    }

	    function toState(state){
	    	if(isState(state)){return}
	    	$state.go(state)
	    }

	    function isState(state){
	    	return $state.includes(state)
	    }
		
		
		 $rootScope.$watch('loading', function(r){
			$scope.loading = $rootScope.loading;
		});
		


	}
})()