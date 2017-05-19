(function(){
	'use strict';

	angular.module('meetime')
	.controller('carController', carController);

	carController.$inject = ['$scope', '$mdDialog', 'carFactory', '$timeout', "$http", "$q", "$mdToast", '$rootScope', 'colorCarFactory', 'pipedriveFactory', 'keyPipedrive', '$state'];
	function carController($scope, $mdDialog, carFactory, $timeout, $http, $q, $mdToast, $rootScope, colorCarFactory, pipedriveFactory, keyPipedrive, $state){
		var vm = this;   
		// 7d757b05b06b123b8773918c85b358ef906a1b03

			
        (function init(){
            vm.listCars = [];
			listColors();

			$scope.changeKey = true;
			saveKey(keyPipedrive);

			$scope.keyMsg = {
					msg: "Favor informar a chave da API da Pipedrive.",
					success: false
				}

			vm.showChangeKey = showChangeKey;
			vm.saveKey = saveKey;
			vm.searchPersonPipedrive = searchPersonPipedrive;
			vm.selectedItemChange = selectedItemChange;

			vm.showConfirmRemove = showConfirmRemove

			vm.showCarRegister = showCarRegister;
			vm.cancelCreate = cancelCreate;
			vm.saveCar = saveCar;


		})()

		function showChangeKey(){
			$scope.keyMsg = {
				msg: "Favor informar a chave da API da Pipedrive.",
				success: false
			}
			vm.keyPipedrive = null
			cancelCreate()
			$scope.changeKey = true;
			$state.go('index.car', {key:null})
		}

		function saveKey(key){
			pipedriveFactory.getFirstTen(key)
			.then(function(resp){
				vm.keyPipedrive = key;
				$scope.keyMsg = {
					msg: "Chave: " + key,
					success: true
				}
				$scope.changeKey = false
				listPerson();
				listAllCars();
				$state.go('index.car', {key:key})
			}, function( resp){
				$scope.keyMsg = {
					msg: "Chave inválida! favor tentar novamente.",
					success: false
				}
				$state.go('index.car', {key:null})
			})
		}

		function listColors(){
			colorCarFactory.list()
			.then(function(resp){
				vm.listColors = resp.data.enums
			})

		}


		function selectedItemChange(item){
			if(item){
				vm.car.idPerson = item.id;
			}
		}

		function searchPersonPipedrive(query){
			 return !query || query.length < 2 ? listPerson() : createQuery(query)
		}

		function createQuery(query){
			var deferred = $q.defer();

			pipedriveFactory.getByQuery(query, vm.keyPipedrive)
			.then(function(resp){
				var results = null;
				if(resp.data.data){
					results = resp.data.data.map(function(person){
						return person;
					})
				}
				deferred.resolve( results );
			})
			return deferred.promise;
		}

		function listPerson(){
			if(vm.resultsPersons){
				return vm.resultsPersons;
			}

			var deferred = $q.defer();

			pipedriveFactory.getFirstTen(vm.keyPipedrive)
			.then(function(resp){
				
				vm.resultsPersons = resp.data.data.map(function(person){
					return getObjectPerson(person)
				})

				deferred.resolve( vm.resultsPersons );
			})
			return deferred.promise;
		}

		function getPersonById(carId){

			var index = vm.listCars.map(function(a){return a.id}).indexOf(carId);
			var idPerson = vm.listCars[index].idPerson;

			pipedriveFactory.getById(idPerson, vm.keyPipedrive)
			.then(function(resp){
				vm.listCars[index].personPipedrive = getObjectPerson(resp.data.data)
			})
		}

        function listAllCars(){
            carFactory.getAll()
            .then(function(resp){
				vm.listCars = resp.data;
				if(resp.data.length){
					resp.data.map(function(item){
						getPersonById(item.id)
					})
				}
            })
        }

		function getObjectPerson(person){
			return {
				id: person.id,
				name : person.name,
				email : person.email != 0 ? person.email[0].value:null,
			}
		}

		

		function saveCar(car){
			if(!validateCar(car)){ return }
			if(!car.id){
				carFactory.create(car)
				.then(function(resp){
					car = resp.data
					vm.listCars.push(car);

					getPersonById(car.id)
					showToast("Carro salvo com sucesso!")
					cancelCreate();


				})
			}else{
				carFactory.update(car)
				.then(function(resp){
					vm.listCars = vm.listCars.map(function(a){ 
						if(a.id == car.id){
							getPersonById(resp.data.id)
							return resp.data
						}else{
							return a
						}
					 })
					showToast("Carro alterado com sucesso!")
					cancelCreate();
				})
			}
		}

		function showToast(msg){
			var toast = $mdToast.simple()
				.textContent(msg)
				.position('top right');

			$mdToast.show(toast)
		}

		function validateCar(car){
			$scope.createError = null;

			["model", "year", "idPerson", "color"]
			.forEach(function(key){
				if(!car[key]){
					console.log(key)
					$scope.createError = "Favor preencher todos os campos."
				}
			})

			if(!$scope.createError){
				if(car.year < new Date().getFullYear() - 30){
					$scope.createError = "O carro não pode ter mais de 30 anos."
				}
			}
			
			return !$scope.createError;
		}

		function showConfirmRemove(ev, index){
			var carro = vm.listCars[index];
			var confirm = $mdDialog.confirm()
				.title('Deseja remover o registro?')
				.textContent("(" + carro.id + ") " + carro.model + "/" + carro.color.description)
				.ariaLabel('Remover carro')
				.targetEvent(ev)
				.ok('remover')
				.cancel('cancelar');

			$mdDialog.show(confirm).then(function() {
				console.log(index)
				carFactory.remove(carro.id)
				.then(function(resp){
					vm.listCars.splice(index, 1)
				})
			});


			
		}

		function cancelCreate(){
			vm.car = {};
			$scope.displayInformation = false;
			$scope.createError = null;
		}

		// Controls animations
		function showCarRegister(car){
			
			vm.car = getCar(car) || {};

			$scope.displayInformation = true;
		}

		function getCar(car){
			var updateCar = angular.copy(car)

			if(updateCar){
				var index = vm.listColors.map(function(a){return a.code}).indexOf(updateCar.color.code);
				updateCar.color = vm.listColors[index]

				var personIndex = vm.resultsPersons.map(function(a){ return a.id}).indexOf(updateCar.idPerson);
				if(personIndex == -1){
					console.log(personIndex, vm.resultsPersons, updateCar.idPerson)
					vm.resultsPersons.push(updateCar.personPipedrive);
					vm.selectedItem = vm.resultsPersons[vm.resultsPersons.length - 1]
				}else{
					vm.selectedItem = vm.resultsPersons[personIndex]
				}

				
			}else{
				vm.selectedItem = null;
			}
			return updateCar
		}

		$scope.mouseEnterTable = function(id){
			$scope.trOver = id;
		}
		$scope.mouseLeaveTable = function(id){
			$scope.trOver = null;
		}
		
		
	}
})()