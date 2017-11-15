'use strict';

var controller= angular.module('controller', []);
var screeningController= angular.module('screeningController', []);
var aesErrorField = '';

controller.controller('controller', ['$rootScope', '$scope', '$location', '$window', '$route', '$uibModal', '$http', 'commonService', 'exportOperationFactory', 'commonFactory','$mdDialog', 'masterFactory', '$compile', '$timeout',   
    function($rootScope, $scope, $location, $window, $route, $uibModal, $http, commonService, exportOperationFactory, commonFactory, $mdDialog, masterFactory, $compile, $timeout) {

		$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];
		$scope.licenseItems = [];
				
		$scope.uibModalInstance = null;
		
		$scope.startPage = 1;
		$scope.pageSize = 5;
		
		$scope.dateOptions = {
			    formatYear: 'yy',
			    maxDate: new Date(2099, 5, 22),
			    startingDay: 1,
			    'popup-placement': 'bottom'
	    };
		$scope.dateFormat = "dd-MM-yyyy";
		$scope.altInputFormats = ['M!/d!/yyyy'];
		
		$scope.shipment = {};
		$scope.popup1 = {};
		$scope.popup1.opened = false;
		$scope.child = {};
				
		$scope.$on('$routeChangeSuccess', function() {
			$('.selectpicker').selectpicker();
			angular.element('html,body').animate({ scrollTop: 0 }, 250);
			$scope.clearMessages();
		});
		
		$scope.init = function(){
			
			$scope.user = {};
			commonFactory.getUserThemeClass()
				.then(function(response){
					console.log(response);
					if(response.data.success == true){
						if(response.data.data!=null){
							$scope.user.gtnClass = response.data.data;
							gtnClassGlobal = response.data.data;
						}
						else{
							$scope.user.gtnClass = 'gtn-yellow';
							gtnClassGlobal = 'gtn-yellow';
						}
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						$scope.user.gtnClass = 'gtn-yellow';
						gtnClassGlobal = 'gtn-yellow';
					}
				});
			
		}
		
		$scope.open1 = function() {
		    $scope.popup1.opened = true;
		};
		  
		$scope.currentLink;
		/*$scope.$on('$viewContentLoaded', function(){
			if($scope.currentLink){
				angular.element('.nav2 .active').removeClass('active');
				angular.element('.nav2 #'+$scope.currentLink).addClass('active');
			}
		});*/
		
		$scope.$on('$includeContentLoaded', function(event, templateName){
			if($scope.currentLink){
				angular.element('.nav2 .active').removeClass('active');
				angular.element('.nav2 #'+$scope.currentLink).addClass('active');
				angular.element('.nav2 #'+$scope.currentLink+' > a').removeClass('text-black').removeClass('text-white');
				angular.element('.nav2 #'+$scope.currentLink+' > a').addClass('text-white');
			}
		});
		
		$scope.getCountry = function(request){
			 blockScroll();
			 blockLoader();
			
			 console.log(request);
			 
			return $http({
				url: 'getCountry?req='+request,
				responseType: 'json',
				method: 'GET',
				header: {
					"Content-Type": "application/json"
				}
			}).then(function(response){
				return response.data;
			}, function(error){
				console.log(error);
				return error;
			});
		}
		
		$scope.onCountrySelectShipment = function(item, model, label, name, code){
			$scope.shipment[name] = item.countryName;
			$scope.shipment[code] = item.countryCode;
			//$scope.item.coo = item.countryName;
		}
		
		/**
		 * Generic country select
		 */
		$scope.selectCountryEvent = function(item, model, label, name, code, obj){
			obj[name] = item.countryName;
			obj[code] = item.countryCode;
		}
		
		$scope.onCurrencySelect = function(item, model, label, field, obj){
 			obj[field] = item.currencyCode;
 		}
		
		$scope.selectStateEvent = function(item, model, label, stateName, stateCode, obj){
			obj[stateName] = item.stateName;
			obj[stateCode] = item.stateCode;
		}
		
		$scope.selectPortsEvent = function(item, model, label, portName, portCode, obj){
			obj[portName] = item.description;
			obj[portCode] = item.portCode;
		}
		
		$scope.getStates = function(request, countryCode){
			 blockScroll();
			 blockLoader();
			 
			 if(!request){
				 return;
			 }
			
			return $http({
				url: 'getStates?qry='+request+'&countryCode='+countryCode,
				responseType: 'json',
				method: 'GET',
				header: {
					"Content-Type": "application/json"
				}
			}).then(function(response){
				console.log(response);
				return response.data.data;
			}, function(error){
				console.log(error);
				return error;
			});
		}
		
		$scope.clearCountry = function(ccode, sname, scode, cname, obj){
			if(obj){
				if(!cname){
					obj[ccode] = '';
					obj[sname] = '';
					obj[scode] = '';
				}
			}			
		}
		
		$scope.getCurrency = function(request){
			 blockScroll();
			 blockLoader();
			
			return $http({
				url: 'getCurrency?req='+request,
				responseType: 'json',
				method: 'GET',
				header: {
					"Content-Type": "application/json"
				}
			}).then(function(response){
				return response.data;
			}, function(error){
				console.log(error);
				return error;
			});
		}
		

 		$scope.updateStatesSearch = function(countryCode){
 			blockScroll();
 			exportOperationFactory.getStates(countryCode, query)
				.then(function(response){ 	 					
					if(response.data == null || response.status != 200){
						$scope.errors.push({form:'', field: '', msg: 'Error getting master details'});
	 					commonService.processMsg($scope.errors, 'error');
					}else{
						$scope.states = response.data.data;
					}
		    });
 		}
		
		
		
		$scope.parentInit = function(parentNav){
			
			$scope.currentLink = parentNav;
			$scope.clearMessages();
			$scope.gtnClass = gtnClassGlobal;
			
			if(aesErrorField != ''){
				var field = aesErrorField;
				if(field){
					
					setTimeout(function(){
						$('[name='+field+']').focus();
					}, 500);
					/*var center = $(window).height()/2;
					var top = $('[name='+field+']').offset().top ;
					if (top > center){
						setTimeout(function(){
							//$(window).scrollTop(top-center);
						}, 500);				    
					}*/
				}
				aesErrorField = '';
			}
			
			/*$scope.menuList = [];
			menuList.push({id: 'parties'});
			menuList.push({id: 'billing'});
			menuList.push({id: 'listItemsCartons'});
			menuList.push({id: 'documents'});*/
		}
		
		$scope.clearMessages = function(){
 			$scope.errors.length = 0;
 			$scope.warnings.length = 0;
 			$scope.infos.length = 0;
 		}
	
		$scope.go = function (path) {
			 $location.path(path);
		};
		
		$scope.isViewLoading = false;
			
		$scope.showLicenseScreenPopup = function(shipmentId){
			var config = {
				  controller: 'exportOperationCtlr',
			      templateUrl: 'views/export/resultsLicenseScreening.html',
			      windowTemplateUrl: 'views/export/resultsLicenseScreening.html'
			}
			
			exportOperationFactory.getShipmentItems(shipmentId)
				.then(function (response){						
				if(response.data.success == true){
					
					console.log(" 000000000000 ");
					console.log(response.data.data);
					
					$scope.licenseItems = response.data.data;			
					//commonService.setResponse(null);
					
					config.resolve = {
						items: function(){
							return $scope.licenseItems;
						}
					}
					
					$scope.uibModalInstance = commonService.gtnModal(config);
					
					var response = {
							data: $scope.licenseItems,
							instance: $scope.uibModalInstance
					}
					
					commonService.setResponse(response);
					
				}else{
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(response.data.message);
					commonService.setResponse(null);
					$scope.licenseItems = [];
				}
			
			},function (error){
			   console.log(error);     
			});
			
		}
		
		$scope.showWlsScreenPopup = function(shipmentId, type){
			
			$scope.clearMessages();
			
			var config = {
					  controller: 'exportOperationCtlr',
				      templateUrl: 'views/export/resultsWlsScreening.html',
				      windowTemplateUrl: 'views/export/resultsWlsScreening.html'
				}
				exportOperationFactory.performWlsScreening(shipmentId, type)
					.then(function (response){						
					if(response.data.success == true){
						$scope.screenResponse = response.data.data;			
						//commonService.setResponse(null);
						
						config.resolve = {
							items: function(){
								return $scope.screenResponse;
							}
						}
						
						$scope.uibModalInstance = commonService.gtnModal(config);
						
						var response = {
								data: $scope.screenResponse,
								instance: $scope.uibModalInstance,
								shipmentId: shipmentId
						}
						
						commonService.setResponse(response);
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(response.data.message);
						commonService.setResponse(null);
						$scope.screenResponse = null;
					}
				
				},function (error){
				   console.log(error);     
				});
			
		}
		
		$scope.initWlsScreenPopup = function(){
			$scope.modalInstance = commonService.getResponse().instance;
			$scope.screenResponse = commonService.getResponse().data;
			$scope.shipmentId = commonService.getResponse().shipmentId;
			
			$scope.gtnClass = gtnClassGlobal;
			$scope.visibleDiv = 'showHits';
			$scope.newStatus = null;
			
			commonService.setResponse(null);
		}
		
		$scope.showReasonDiv = function(status){
			$scope.visibleDiv = 'showReasonSubmit';
			$scope.newStatus = status;
			$scope.auditReason = '';
			if(status == "D"){
				$scope.submitBtnText = "Deny";
			}else if(status == "A"){
				$scope.submitBtnText = "Approve";
			}else if(status == "O"){
				$scope.submitBtnText = "Override";
			}else if(status == "H"){
				$scope.submitBtnText = "On Hold";
			}
		}
		
		$scope.changeShipmentWlsStatus = function(){
			//alert($scope.auditReason);
			var dplAuditReasonView = {
					code: '',
					otherReason: $scope.auditReason,
					reasonCodes: [],
					newStatus: $scope.newStatus,
					entityType: '',
					shipmentId: $scope.shipmentId
			};			
			
			exportOperationFactory.updateShipmentDplAuditStatus(dplAuditReasonView)
				.then(function (response){						
					if(response.data.success == true){
						$scope.updateResponse = response.data.data;			
						$scope.modalInstance.dismiss('cancel');
						$route.reload();
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
					}
				
				},function (error){
				   console.log(error);     
				});
			
		}
		
		$scope.wlsResultPopupBack = function(page){
			$scope.visibleDiv = page;
			adjustCols();
		}
		
		/************* AES STart ******************/		
		
		$scope.showAesValidations = function(shipmentId){
			$scope.clearMessages();
			var config = {
					  controller: 'controller',
				      templateUrl: 'views/export/resultsAesValidationPopup.html',
				      windowTemplateUrl: 'views/export/resultsAesValidationPopup.html'
				}
			
			exportOperationFactory.getShipmentAesStatus(shipmentId)
				.then(function (response){						
					if(response.data.success == true){
						$scope.aesResults = response.data.data;			
						//commonService.setResponse(null);
						
						config.resolve = {
							aesResults: function(){
								return $scope.aesResults;
							}
						}
						
						$scope.uibModalInstance = commonService.gtnModal(config);
						
						var response = {
								data: $scope.aesResults,
								instance: $scope.uibModalInstance,
								shipmentId: shipmentId
						}
						
						commonService.setResponse(response);
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(response.data.message);
						commonService.setResponse(null);
					}
				
				},function (error){
				   console.log(error);     
			});
			
		}
		
		$scope.initAesValidationPopup = function(){
			$scope.modalInstance = commonService.getResponse().instance;
			$scope.aesResults = commonService.getResponse().data;
			$scope.shipment.id = commonService.getResponse().shipmentId;
			
			$scope.gtnClass = gtnClassGlobal;
			
			console.log("----------- AES Result ------------");
			console.log($scope.aesResults);
			
			if($scope.aesResults.hasOwnProperty('shipment')){
				$scope.shipmentErrors = $scope.aesResults.shipment;
				if(typeof $scope.shipmentErrors != 'undefined' && $scope.shipmentErrors.length > 0){
					$scope.shipmentClass="red";
				}else{
					$scope.shipmentClass="green";
				}
			}else{
				$scope.shipmentClass="green";
				$scope.shipmentErrors = [];
			}
			
			if($scope.aesResults.hasOwnProperty('invoice')){
				$scope.invoiceErrors = $scope.aesResults.invoice;
				if(typeof $scope.invoiceErrors != 'undefined' && $scope.invoiceErrors.length > 0){
					$scope.invoiceClass="red";
				}else{
					$scope.invoiceClass="green";
				}
			}else{
				$scope.invoiceClass="green";
				$scope.invoiceErrors = [];
			}
			
			if($scope.aesResults.hasOwnProperty('items')){
				$scope.itemsErrors = $scope.aesResults.items;
				if(typeof $scope.itemsErrors != 'undefined' && $scope.itemsErrors.length > 0){
					$scope.itemClass="red";
				}else{
					$scope.itemClass="green";
				}
			}else{
				$scope.itemClass="green";
				$scope.itemsErrors = [];
			}
			
			if($scope.aesResults.hasOwnProperty('aes')){
				$scope.aesErrors = $scope.aesResults.aes;
				if(typeof $scope.aesErrors != 'undefined' && $scope.aesErrors.length > 0){
					$scope.aesClass="red";
				}else{
					$scope.aesClass="green";
				}
			}else{
				$scope.aesClass="green";
				$scope.aesErrors = [];
			}
			
			if($scope.aesResults.hasOwnProperty('license')){
				$scope.licenseErrors = $scope.aesResults.license;
				if(typeof $scope.licenseErrors != 'undefined' && $scope.licenseErrors.length > 0){
					$scope.licenseClass="red";
				}else{
					$scope.licenseClass="green";
				}
			}else{
				$scope.licenseClass="green";
				$scope.licenseErrors = [];
			}
		}
		
		/**
		 * Show AES status history
		 */
		$scope.showAesAudit = function(shipment){
			$scope.clearMessages();
			
			var config = {
					  controller: 'controller',
				      templateUrl: 'views/export/resultsAesAuditHistoryPopup.html',
				      windowTemplateUrl: 'views/export/resultsAesAuditHistoryPopup.html'
				}
			
			exportOperationFactory.getShipmentAesStatusHistory(shipment.shipmentNo)
				.then(function (response){						
					if(response.data.success == true){
						$scope.aesStatusHistory = response.data.data;			
						//commonService.setResponse(null);
						
						config.resolve = {
								aesStatusHistory: function(){
								return $scope.aesStatusHistory;
							}
						}
						
						$scope.uibModalInstance = commonService.gtnModal(config);
						
						var response = {
								data: $scope.aesStatusHistory,
								instance: $scope.uibModalInstance,
								shipmentId: shipment.shipmentNo
						}
						
			 			if(shipment.easeStatus == 'received'){
			 				shipment.easeStatus = 'viewed';
			 			}			 			
			 			
						commonService.setResponse(response);
						//$scope.easeStatus = 'viewed';
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(response.data.message);
						commonService.setResponse(null);
					}
				
				},function (error){
				   console.log(error);     
			});
			
		}
		
		
		$scope.status = {
		    isopen: false
		};

		$scope.toggled = function(open) {
		    //$log.log('Dropdown is now: ', open);
		};

		$scope.toggleDropdown = function($event) {
		    $event.preventDefault();
		    $event.stopPropagation();
		    $scope.status.isopen = !$scope.status.isopen;
		};
		
		$scope.cancel = function () {
 			$scope.modalInstance.dismiss('cancel');
 		};
 		
 		$scope.nagivateToError = function(url, field, itemId, section, cartonId){
 			$scope.modalInstance.dismiss('cancel');
 			
 			if(typeof url == 'undefined' || url == null || url=='')
 				return;
 			
 			if($location.path() == '/'+url){
 				
 				if(url == 'createAddItem'){ 					
 					exportOperationFactory.findShipment($scope.shipment.id, null)
 						.then(function(response){
 							if(response.data.success == true){
 								var request = {
 	 		 							shipment: response.data.data,
 	 		 							itemNo: itemId,
 	 		 							itemId: itemId,
 	 		 							type: 'UPDATE'
 	 		 					}
 	 		 					commonService.setResponse(request);
 	 							
 	 							//$location.path(url);
 	 							$route.reload();
 	 		 	 				aesErrorField = field;
 							}else{
 								$scope.errors.push({form:'', field: '', msg: result.data.message}); 
 							} 							
 						});
 					
 				}else{
 					setTimeout(function(){
 	 					if(field){
 		 	 				$('[name='+field+']').focus();
 		 	 				var center = $(window).height()/2;
 		 	 			    var top = $('[name='+field+']').offset().top ;
 		 	 			    if (top > center){
 		 	 			        //$(window).scrollTop(top-center);
 		 	 			    }
 	 					}
 	 	 			}, 500);
 				}
 				
 			}else{
 				if(url == 'createAddItem'){ 					
 					exportOperationFactory.findShipment($scope.shipment.id, null)
 						.then(function(response){
 							if(response.data.success == true){
 								var request = {
 	 		 							shipment: response.data.data,
 	 		 							itemNo: itemId,
 	 		 							itemId: itemId,
 	 		 							type: 'UPDATE'
 	 		 					}
 	 		 					commonService.setResponse(request);
 	 							
 	 							$location.path(url);
 	 							//$route.reload();
 	 		 	 				aesErrorField = field;
 							}else{
 								$scope.errors.push({form:'', field: '', msg: result.data.message}); 
 							} 							
 						});
 					
 				}else{
 					$location.path(url);
 	 				aesErrorField = field;
 				}
 					
 			}
 			
 			
 			//sessionStorage.setItem("field", field);
 			
 			/*setTimeout(function(){
 				$('[name='+field+']').focus();
 				var center = $(window).height()/2;
 			    var top = $('[name='+field+']').offset().top ;
 			    if (top > center){
 			        $(window).scrollTop(top-center);
 			    }
 			}, 500);*/
 			
 		}
		
 		/***************** AES End ******************/
 		
		/*$scope.showLicenseScreenPopup = function(size, parentSelector){
				
				
			commonService.setResponse($scope.shipment.id);
						
			    var uibModalInstance = $uibModal.open({
			      animation: true,
			      ariaLabelledBy: 'modal-title',
			      ariaDescribedBy: 'modal-body',
			      controller: 'lsResultPopCtrl',
			      templateUrl: 'views/export/resultsLicenseScreening.html',
			      windowTemplateUrl: 'views/export/resultsLicenseScreening.html',
			      component: 'modalComponent',
			      bindings: {
			        resolve: '<',
			        close: '&',
			        dismiss: '&'
			      },
			      size: size,
			      resolve: {
			    	 
			      }
			    });

//			    $scope.modalInstance.result.then(function (selectedItem) {
//			      //$ctrl.selected = selectedItem;
//			    }, function () {
//			      $log.info('Modal dismissed at: ' + new Date());
//			    });
		  }*/
		
		/*$rootScope.$on('$locationChangeStart', function (event, next, current) {
			
			
			
			//event.preventDefault();
		});*/
		
		/*$scope.$on('$routeChangeStart', function() {
			angular.element('#preloader').delay(10).fadeIn(10);
			$scope.isViewLoading = true;
		});
		
		
		$rootScope.$on('$routeChangeSuccess', function() {
			angular.element('#preloader').delay(10).fadeOut(10);
		    $scope.isViewLoading = false;
		    $window.scrollTo(0, 0);
		});
		
		
		$scope.$on('$routeChangeError', function() {
			angular.element('#preloader').delay(10).fadeOut(10);
			$scope.isViewLoading = false;
		});*/
		
 		/****************** Theme Start **********************/
 		
 		$scope.userThemeInit = function(){
 			$scope.clearMessages();
 			
 			commonFactory.getUserThemeClass()
				.then(function(response){
					if(response.data.success == true){
						
						var gtnClass = response.data.data;
						
						$('.selected-colorpicker').removeClass('.selected-colorpicker');
						
						if(gtnClass == 'gtn-yellow'){
							$('.yellow-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-blue'){
							$('.blue-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-neutral-grey'){
							$('.neutral-grey-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-brown'){
							$('.brown-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-dark-blue'){
							$('.dark-blue-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-medium-dark-blue'){
							$('.medium-dark-blue-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-purple'){
							$('.purple-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-dark-purple'){
							$('.dark-purple-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-light-blue'){
							$('.light-blue-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-teal'){
							$('.teal-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-light-teal'){
							$('.light-teal-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-green'){
							$('.green-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-light-green'){
							$('.light-green-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-orange'){
							$('.orange-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-dark-orange'){
							$('.dark-orange-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-dark-red'){
							$('.dark-red-colorpicker').addClass('selected-colorpicker');
						}else if(gtnClass == 'gtn-pink'){
							$('.pink-colorpicker').addClass('selected-colorpicker');
						}
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						
					}
				});
 		}
 		
 		$scope.updateTheme = function(gtnClass){
 			commonFactory.updateUserThemeClass(gtnClass)
 				.then(function(response){
 					if(response.data.success == true){
 						$scope.user.gtnClass = response.data.data;
 						gtnClassGlobal = $scope.user.gtnClass;
 						$route.reload();
 					}else{
 						$scope.errors.push({form:'', field: '', msg: response.data.message});
 						commonService.processMsg($scope.errors, 'error');
 					}
 				});
 		}
 		
 		/******************** Theme end ******************/
 		
 		

 		/********* search product pop up start **************/
 		
 		$scope.searchProductPopup = function(type){
 			if(type == 'shipment'){
 				var childScope = angular.element('#addItemController').scope();
 				//var childScope = $scope.child;
 				commonService.searchProductsPopup(type, childScope.item);
 			}else if(type == 'ls'){
 				var childScope = angular.element('#licenseScreeningCtlr').scope();
 				commonService.searchProductsPopup(type, childScope.license);
 			}
 			
 			//commonService.searchExporterPopup('shipment', $scope.shipment);
 		}
 		
 		$scope.initSearchProductsPopup = function(){
 			$scope.gtnClass = gtnClassGlobal;
 			
 			$scope.product = {};
			
 			$scope.product.sortBy = "PART_NO";
			$scope.product.direction = "ASC";
			$scope.searchPage = true;
			$scope.modalInstance = commonService.getResponse().instance;
			
			commonService.populateSbu($scope, true, 'product', 'sbu');
 		}
 		
 		$scope.searchProducts = function(reqType){
			$scope.clearMessages();
			
			$scope.product.reqNavType = reqType;
						
			masterFactory.searchRecordsCount($scope.product, 'searchProductCount')
				.then(function(response){
					if(response.data.success == true){
						
						$scope.productColumns = [	
						                         	'delete',
						                         	'partNo',
									                'exportClass',
									                'importClass',
									                'modelNo',
									                'hazmat',
									                'sbu',
									                'skuNo',
									                'createdOn',
									               ];
						
						$scope.searchPage = false;
						
						//$('.result-conatiner .column div[index=1]').click();
							
						var colMap = {
								"PART_NO": 1,
								"PART_TYPE": 1,
								"MODEL_NO": 4,
								"EXPORT_CLASS": 2,
								"IMPORT_CLASS": 3
								
						}
						
						var arrInd = colMap[$scope.product.sortBy];
						
						//var options = {"columnSort":[{"sort":false},{"sort":"desc"},{"sort":false},{"sort":false},{"sort":false},{"sort":false},{"sort":false},{"sort":false},{"sort":false}]};
						
						$scope.getProductList(1,5);
						$timeout(function(){														
							$('.result-conatiner .column div[index='+arrInd+']').click();
			            });
						 /*var htmlcontent = $('#product-table-container');
					     htmlcontent.load('views/template/productsTableTmpl.html');
					     $compile(htmlcontent.contents())($scope);*/
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
			
		}
 		
 		/** 
 		 * get list of products
 		 */
 		$scope.getProductList = function(page, pageSize, options){
			$scope.clearMessages();
			
			if(!page) { page = 1 }
			//alert(pageSize);
            var offset = (page-1) * pageSize;
            
            $scope.entity = {
        		'offset': offset,
                'limit':pageSize
            }
            
            if(options){
	            var sortIndex = commonService.findSortIndex(options.columnSort);
	            
	            if(sortIndex){
	            	$scope.entity.sortBy = $scope.productColumns[sortIndex.index];
	            	$scope.entity.direction = sortIndex.ad;
	            }
			}
            //blockLoader();
            //$('.result-conatiner tbody tr.tbodyTrRow').remove();
            
            //$compile($('.result-conatiner tbody').contents())($scope);
            
       
        		/*return $http({
        		    url: 'searchProductValue',
        		    responseType:"json",
        		    method: "POST",
        		    data: $scope.entity,
        		    headers: {
        		        "Content-Type": "application/json"
        		    }
        		}).then(function(response){
        			//alert(JSON.stringify(response.data.data));
        			return response.data.data;
        		}, function(error){
        			console.log(error);
        			return error;
        		});*/
      
            
            	
	            return masterFactory.searchRecords($scope.entity, 'searchProductValue')
						.then(function(response){
							if(response.data.success == true){
								
								//console.log(response.data.data);
								//unblockLoader();
								scroll();
								
							
								return response.data.data;
								//commonService.setResponse(response);
								//$location.path('resultSearchConsignee');
							}else{
								$scope.errors.push({form:'', field: '', msg: response.data.message});
			 					commonService.processMsg($scope.errors, 'error');
							}
				});
        }
 		

 		$scope.selectProduct = function(){
 			var partNo = angular.element('input[name=selectedProduct]:checked').val();
 
 			if(partNo){
 				masterFactory.findEntity(partNo, 'getProduct')
 					.then(function(response){
						if(response.data.success == true){
							
							var obj = commonService.getResponse().shipment;
							var type = commonService.getResponse().type;
							//$scope.shipment = shipment.shipment;
							
							var productData = response.data.data;
							
							//$scope.onExporterSelect(expData, null, null);
							if(type == 'shipment') {
								obj.partNo = productData.partNo;
								obj.eccn = productData.exportClass;
								
								obj.partDescription = productData.partDesc;
								obj.currency = productData.currency;
				 				
							}else if(type == 'ls'){
								if(obj.type == 'product_no'){
									obj.input = productData.partNo;
								}else if(obj.type == 'eccn'){
									obj.input = productData.exportClass;
								}
							}
			 				
			 				
							$scope.modalInstance.dismiss('cancel');
							commonService.setResponse(null);
							//alert(JSON.stringify(expData));
						}else{
							$scope.errors.push({form:'', field: '', msg: 'error getting product details.'});
						}
					});
 			}else{
 				$scope.errors.push({form:'', field: '', msg: 'Please select a product.'});
 			}
 			
 		}
 		
 		$scope.goToSearchProduct = function(){
 			$scope.searchPage = true;
 			$scope.initSearchProductsPopup();
 		}
 		
 		/********* search product pop up end **************/
 		
 		$scope.mdConfirm = function(ev){

			var confirm = $mdDialog.confirm()
	            .title('Delete')
	            .textContent('Are you sure you want to delete?')
	            .ariaLabel('')
	            .targetEvent(ev)
	            .ok('OK')
	            .cancel('Cancel');
			
			return confirm;
 		}
 		
 		$scope.mdConfirmCancel = function(ev){

			var confirm = $mdDialog.confirm()
	            .title('Cancel')
	            .textContent('Are you sure you want to cancel?')
	            .ariaLabel('')
	            .targetEvent(ev)
	            .ok('OK')
	            .cancel('Cancel');
			
			return confirm;
 		}
 		
 		$scope.cancelController = function () {
 			$scope.modalInstance.dismiss('cancel');
 		};
 		
 		/** product look up call **/
 		$scope.getProducts = function(request){
			 blockScroll();
			 blockLoader();
			
			 if(typeof request == 'undefined' || request == '')
				 return;
			 
			return $http({
				url: 'getProducts?req='+request,
				responseType: 'json',
				method: 'GET',
				header: {
					"Content-Type": "application/json"
				}
			}).then(function(response){
				return response.data;
			}, function(error){
				console.log(error);
				return error;
			});
		}
 		
 		$scope.onProductSelect = function(item, model, label){
 			if(item){
 				$scope.item.partNo = item.partNo;
 				$scope.item.eccn = item.exportClass;
 				$scope.item.partDescription = item.partDesc;
 				$scope.item.currency = item.currency;
 			}
 		}
 		
    }
]);

/**
*
*/
dashboardApp.controller('adminController', ['$scope', '$location', 'commonService', 'commonFactory', '$mdDialog', function($scope, $location, commonService, commonFactory, $mdDialog){
	
	$scope.sbuConfigList = [];
	$scope.errors = [];
	$scope.warnings = [];
	$scope.infos = [];
	$scope.sbuConfigView = {};
	
	$scope.sbuParamSearchInit = function(){
		$scope.sbuConfigView = {};
		$scope.sbuList = [];
		
		commonService.populateSbu($scope, true, 'sbuConfigView', 'sbu');
		
		//$scope.sbuList = ['1', '2', '3'];
		/*commonFactory.getSbuList()
			.then(function(result){
				if(result.data.success == false){
					$scope.errors.push({form:'', field: '', msg: result.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(result.data.message);
					$scope.sbuList = [];
				}else{ 					
					$scope.sbuList = result.data.data;
				} 
			});*/
	}
	
	$scope.sbuParamResultInit = function(){
		$scope.sbuConfigList = commonService.getResponse();
		console.log($scope.sbuConfigList);
	}
	
	$scope.sbuParamCreateInit = function(){
		if(commonService.getResponse()!=null && !$.isEmptyObject(commonService.getResponse())){
			var configValue = commonService.getResponse();			
			//alert(configValue);
			$scope.sbuConfigView = configValue;
			
			commonService.populateSbu($scope, false, 'sbuConfigView', 'sbu');
			$scope.screenType = 'Edit';
			
			/*commonFactory.getSbuList()
				.then(function(result){
					if(result.data.success == false){
						$scope.errors.push({form:'', field: '', msg: result.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(result.data.message);
						$scope.sbuList = [];
					}else{ 					
						$scope.sbuList = result.data.data;
					} 
				});*/

			commonService.setResponse(null);
		}else{
			$scope.sbuConfigView = {};
			commonService.populateSbu($scope, true, 'sbuConfigView', 'sbu');
			$scope.screenType = 'New';
			
			/*commonFactory.getSbuList()
				.then(function(result){
					if(result.data.success == false){
						$scope.errors.push({form:'', field: '', msg: result.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(result.data.message);
						$scope.sbuList = [];
					}else{ 					
						$scope.sbuList = result.data.data;
					} 
			});*/
		}
	}
	
	$scope.searchSbuConfig = function(reqType){
		console.log($scope.sbuConfigView);
		$scope.clearMessages();
		$scope.sbuConfigView.reqType = reqType;
		
		var promise;
		if(typeof $scope.sbuConfigView == 'undefined'){
			$scope.sbuConfigView = {};					
		}
		
		promise = commonFactory.searchSbuConfig($scope.sbuConfigView); 
	 			
		promise.then(function(result){
			if(result.data.success == false){
				$scope.errors.push({form:'', field: '', msg: result.data.message});
				commonService.processMsg($scope.errors, 'error');
				//alert(result.data.message);
				$scope.sbuConfigList = [];
			}else{ 					
				$scope.sbuConfigList = result.data.data;
				commonService.setResponse($scope.sbuConfigList);
				$location.path("/resultSbuConfig");
			} 				
		});
	}
	
	$scope.deleteSbuConfig = function(id, ev){		
		/*var conf = confirm('Are you sure you want to delete?');
		
		if(!conf)
			return;
		
		commonFactory.deleteSbuConfig(id)
			.then(function(result){
				console.log(result);
				if(result.status != 200){
					$scope.errors.push({form:'', field: '', msg: result.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(result.data.message);
				}else{			
					$scope.sbuConfigList = $.grep($scope.sbuConfigList, function(element, index){return element.id == id}, true);
					$scope.clearMessages();
				}
			});*/
		
		var confirm = $scope.mdConfirm(ev);

        $mdDialog.show(confirm).then(function() {
     	   
        	commonFactory.deleteSbuConfig(id)
				.then(function(result){
					console.log(result);
					if(result.status != 200){
						$scope.errors.push({form:'', field: '', msg: result.data.message});
						commonService.processMsg($scope.errors, 'error');
						//alert(result.data.message);
					}else{			
						$scope.sbuConfigList = $.grep($scope.sbuConfigList, function(element, index){return element.id == id}, true);
						$scope.clearMessages();
					}
				});
     	   
        	}, function() {
        		
        	});
		
	}
	
	$scope.editSbuConfig = function(id){
		var promise = commonFactory.findSbuConfig(id);
		promise.then(function(response){
			if(response.data.success == true){
				commonService.setResponse(response.data.data);
				$location.path('/createSbuConfig');
			}else{
			   $scope.errors.length = 0;
         	   $scope.errors.push({form:'', field: '', msg: response.data.message});
         	   commonService.processMsg($scope.errors, 'error');
			}
		});
	}
	
	$scope.saveSbuConfig = function(type){
		$scope.errors.length = 0;
		var path = '';
			
		if(commonService.isEmpty($scope.sbuConfigView.paramName)){
			$scope.errors.push({form:'createSbuConfig', field: 'input[name=paramName]', msg: "Parameter name is required"});
		}
		if(commonService.isEmpty($scope.sbuConfigView.paramValue)){
			$scope.errors.push({form:'createSbuConfig', field: 'input[name=paramValue]', msg: "Parameter value is required"});
		}
		
		if($scope.errors.length > 0){
			commonService.processMsg($scope.errors, 'error');
			return;
		}else{
						
			commonFactory.saveSbuConfig($scope.sbuConfigView)
				.then(function(result){
					if(result.data.success == false){
						$scope.errors.push({form:'', field: '', msg: result.data.message});
						commonService.processMsg($scope.errors, 'error');
												
						//alert(result.data.message);
					}else{	
						if(type=="save"){
							$scope.sbuConfigView = result.data.data;
							//$scope.editSbuConfig(result.data.data.id);
						}else if(type=="return"){
							path = "/sbuParams";
							$location.path(path);
						}
						
					}					
			});
			
		}
		
		
	}
	
	$scope.schedularAesInit = function(){
		
		$scope.aesFilingConfigView = commonService.getResponse();
		
		if($scope.aesFilingConfigView.cron){
			$scope.aesFilingConfigView.cron = $scope.aesFilingConfigView.cron.substring(2);
		}
		
		
		$('#cron-selector').cron({
		    initial: $scope.aesFilingConfigView.cron,
		    onChange: function() {
		    	$scope.aesFilingConfigView.cron = $(this).cron("value");
		    	//$('.jobDesc').trigger('change');
		        //$('#example1-val').text();
		    },
		    effectOpts: {
		        openEffect: "fade",
		        openSpeed: "slow"
		    }
		});
		
		waitForElementToDisplay('#cron-selector select', 10);
		
	}
	
	$scope.editScheduledJob = function(jobCode){		
		
		commonFactory.getSchedulerJob(jobCode)
			.then(function(result){
				if(result.data.success == false){
					$scope.errors.push({form:'', field: '', msg: result.data.message});
					commonService.processMsg($scope.errors, 'error');
											
					//alert(result.data.message);
				}else{
					
					
					$location.path("/createSchedularAES");
					commonService.setResponse(result.data.data);
					
					/*$scope.aesFilingConfigView = result.data.data;
					
					if($scope.aesFilingConfigView.cron){
						$scope.aesFilingConfigView.cron = $scope.aesFilingConfigView.cron.substring(2);
					}
					
					
					
					$('#cron-selector').cron({
					    initial: $scope.aesFilingConfigView.cron,
					    onChange: function() {
					    	$scope.aesFilingConfigView.cron = $(this).cron("value");
					    	//$('.jobDesc').trigger('change');
					        //$('#example1-val').text();
					    },
					    effectOpts: {
					        openEffect: "fade",
					        openSpeed: "slow"
					    }
					});
					
					waitForElementToDisplay('#cron-selector select', 10);*/
				}					
	});
		
		
	}
	
	$scope.searchScheduledJobInit = function(){
		$scope.job = {};
	}
	
	$scope.editScheduleJobCancel = function(){
		
	}
	
	$scope.searchScheduledJob = function(type){
		console.log($scope.job);
		$scope.clearMessages();
		
		var promise;
		if(typeof $scope.job == 'undefined'){
			$scope.job = {};					
		}
		$scope.job.reqNavType = type;
		
		promise = commonFactory.searchScheduledJobs($scope.job); 
	 			
		promise.then(function(result){
			if(result.data.success == false){
				$scope.errors.push({form:'', field: '', msg: result.data.message});
				commonService.processMsg($scope.errors, 'error');
				//alert(result.data.message);
				$scope.jobList = [];
			}else{
				$scope.jobList = result.data.data;
				commonService.setResponse(result.data);
				$location.path("/resultJobSchedular");
			} 				
		});
	}
	
	$scope.scheduledJobSearchResultInit = function(){
		if($.isEmptyObject(commonService.getResponse())){
			$location.path('/createSearchScheduledJob');
		}
		
		$scope.jobSchedularColumns = [
		                              	'jobCode',
		                              	'jobName',
		                              	'cron',
		                              	'jobDesc',
		                              	'createdOn',
		                              ];
		
		$scope.scheduledJobsList = commonService.getResponse().data;
		
		//$scope.jobList = commonService.getResponse();
	}
	
	function waitForElementToDisplay(selector, time) {
        if(document.querySelector(selector)!=null) {
            //alert("The element is displayed, you can put your code instead of this alert.")
            $('#cron-selector select').addClass('form-control');
            return;
        }
        else {
            setTimeout(function() {
                waitForElementToDisplay(selector, time);
            }, time);
        }
    }
	
	$scope.saveAesFilingConfig = function(){
		console.log($scope.aesFilingConfigView);
		
		$scope.errors.length = 0;
		var path = '';
			
		if(commonService.isEmpty($scope.aesFilingConfigView.jobCode)){
			$scope.errors.push({form:'createAesConfig', field: 'input[name=jobCode]', msg: "Job code is required"});
		}
		
		if($scope.errors.length > 0){
			commonService.processMsg($scope.errors, 'error');
			return;
		}else{
						
			commonFactory.saveAesFilingConfig($scope.aesFilingConfigView)
				.then(function(result){
					if(result.data.success == false){
						$scope.errors.push({form:'', field: '', msg: result.data.message});
						commonService.processMsg($scope.errors, 'error');
												
						//alert(result.data.message);
					}else{	
						console.log(result.data.data);
					}					
			});
			
		}
		
	}
	
	$scope.clearMessages = function(){
		$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];
	}
	
}]);