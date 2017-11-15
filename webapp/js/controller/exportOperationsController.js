dashboardApp.controller('exportOperationCtlr', ['$scope', '$location', '$http', '$compile', '$uibModal', '$log', '$document', 'exportOperationFactory', 'commonService', 'masterFactory', 'commonFactory', '$mdDialog', '$route', '$timeout',   
      function($scope, $location, $http, $compile, $uibModal, $log, $document, exportOperationFactory, commonService, masterFactory, commonFactory, $mdDialog, $route, $timeout) {
 		
		$scope.shipments = [];
		$scope.shipmentDocs = [];
		
		
			
 		$scope.init = function(){
 			//$('.selectpicker').selectpicker();
 			$scope.shipment = {};
 			 			
 			$scope.shipmentColumns = ['shipmentNo','exportDate','status','countryOfUltConsigneeName',
 			                         'createdOn','aesStatus','rates','delete'];
 			
 			/*$scope.getAllShipments();
 			
 			var table = window.table = new KingTable({
 		        id: "colors-table",
 		        data: [["shipmentNo","exportDate","status","ultCons","ultDest","getRates","fileToEase","deleteShipment"],["9yLU-000001",null,"P",null,null,"",1,1],["9yLU-00002",null,"P",null,null,"",2,2],["12332",1497637800000,"H",null,null,"",3,3],["9yLU-00003",1497724200000,"H",null,null,"",4,4],["9yLU-00004",1498069800000,"H","HONG KONG",null,"",8,8],["9yLU-00005",null,"H","INDIA",null,"",9,9],["9yLU-00006",null,"P",null,null,"",10,10],["Test Master",1498761000000,"H","PAKISTAN",null,"",12,12],["GTN001",1499106600000,"H","PAKISTAN","PAKISTAN","",17,17],["DEMO",1499106600000,"H","IRAQ","IRAQ","",18,18]], // array of items: this demo shows a Fixed table (see tables mode wiki page for more information)
 		        caption: "Current shipments",
 		        element: document.getElementById("shipment-result-table"),
 		        columns: {		         
 		          shipmentNo: {
 		        	 name: "Shipment No",
  		             html: function (item, value) {
  		              return "<a ng-click=editShipment("+item.deleteShipment+")>" + value + "</a>";
  		            }
 		          },
 		          exportDate: "Export Date",
 		          status: "Status",
 		          ultCons: "Ultimate Consignee",
 		          ultDest: "Ultimate Destination",
 		          getRates: "Get Rates",
 		          fileToEase: "File TO EASE",
 		          deleteShipment: "Delete"
 		        },
 		        extraViews: [
 		          {
 		            name: "Squares",
 		            value: "squares",
 		            getItemTemplate: function () {
 		              return "<li title=\"{{name}}\" style=\"background-color:{{color}}\"></li>";
 		            }
 		          }
 		        ]
 		      });

 		      table.render();*/
 		      commonService.populateSbu($scope, true, 'searchShip', 'sbu');
 		}
 		
 		$scope.getShipmentList = function(page, pageSize, options){

 			view = $scope.searchShip;
 			
			if(!page) { page = 1 }
			//alert(pageSize);
            var offset = (page-1) * pageSize;
            
            if(view){
            	$scope.entity = view;
            	$scope.entity.offset = offset;
            	$scope.entity.limit = pageSize;
            }else{
            	$scope.entity = {
                		'offset': offset,
                        'limit':pageSize
                    }
            }
            
            
            if(options){
	            var sortIndex = commonService.findSortIndex(options.columnSort);
	            
	            if(sortIndex){
	            	$scope.entity.sortBy = $scope.shipmentColumns[sortIndex.index];
	            	$scope.entity.direction = sortIndex.ad;
	            }
			} 
            
            return masterFactory.searchRecords($scope.entity, 'searchShipmentsPagination')
				.then(function(response){
					if(response.data.success == true){
						
						//console.log(response.data.data);
						//unblockLoader();
						//scroll();
						return response.data.data;
						//commonService.setResponse(response);
						//$location.path('resultSearchConsignee');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
            
            //blockLoader();
            /*return masterFactory.searchRecords($scope.entity, 'searchExporterValue')
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
			});*/
        
 		}
 		
 		/**
 		 * save shipment object to db
 		 */
 		$scope.saveShipment = function(type, screen){
 			
 			var path; 			
 			angular.element('.invalid-ele').removeClass('invalid-ele');
 			$scope.clearMessages();
 			
 			if(screen == 'new'){
 				$scope.shipment.status = "P";
 				
 	 			var promise = exportOperationFactory.saveShipment($scope.shipment); 
 	 			if(type=="save"){
 					path = "/createShipmentWizard";
 				}else if(type=="continue"){
 					path = "/createExporter";
 				} 	 			
 			}else if(screen == 'exporter'){
 				var validated = $scope.validateShipmentExporter();
 				
 				if(validated){
 					var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveExporter");
 	 				if(type=="save"){
 	 					path = "/createExporter";
 	 				}else if(type=="continue"){
 	 					path = "/utlimateConsignee";
 	 				}
 				}else{
 					commonService.processMsg($scope.errors, 'error');
 					return;
 				}
 				
 			}else if(screen == 'utlConsignee'){
 				var validated = $scope.validateShipmentUltCons();
 				
 				if(validated){
	 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveUltConsignee");
	 				if(type=="save"){
	 					path = "/utlimateConsignee";
	 				}else if(type=="continue"){
	 					path = "/intermediateConsignee";
	 				} 			
 				}else{
 					commonService.processMsg($scope.errors, 'error');
 					return;
 				}
 				
 			}else if(screen == 'interConsignee'){
 				
 				if(!commonService.isEmpty($scope.shipment.interConsigneeEmail)){
 	 				var valid = commonService.validateEmail($scope.shipment.interConsigneeEmail);
 	 				
 	 				if(!valid){
 	 					$scope.errors.push({form:'intermediateConsForm', field: 'input[name=email]', msg: "Please enter valid email ID."});
 	 					return;
 	 				}
 	 			}
 				if(!$scope.intermediateConsForm.phoneNo.$valid){
 	 				var errorObj = $scope.intermediateConsForm.phoneNo.$error;
 	 				
 	 				commonService.processNgError(errorObj, $scope.errors, 'intermediateConsForm', 'input[name=phoneNo]', 'phone no');
 	 				
 	 				/*if(errorObj.hasOwnProperty('min') || errorObj.hasOwnProperty('number')){
 	 					$scope.errors.push({form:'billingForm', field: 'amount', msg: "Please enter a valid amount."});
 	 		    	}*/
 	 				return;
 	 			}
 				
 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveInterConsignee");
 				if(type=="save"){
 					path = "/intermediateConsignee";
 				}else if(type=="continue"){
 					path = "/freightForwarder";
 				} 				
 			}else if(screen == 'freightForwarder'){
 				
 				if(!commonService.isEmpty($scope.shipment.freightForwaderEmail)){
 	 				var valid = commonService.validateEmail($scope.shipment.freightForwaderEmail);
 	 				
 	 				if(!valid){
 	 					$scope.errors.push({form:'exporter-form', field: 'input[name=email]', msg: "Please enter valid email ID."});
 	 					return;
 	 				}
 	 			}
 				if(!$scope.ffForm.phoneNo.$valid){
 	 				var errorObj = $scope.ffForm.phoneNo.$error;
 	 				
 	 				commonService.processNgError(errorObj, $scope.errors, 'ffForm', 'input[name=phoneNo]', 'phone no'); 	 				
 	 				return;
 	 			}
 				
 				
 				
 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveFreightForwarder");
 				if(type=="save"){
 					path = "/freightForwarder";
 				}else if(type=="continue"){
 					path = "/shipmentBilling";
 				} 				
 			}else if(screen == 'shipmentBilling'){
 				
 				var validated = $scope.validateShipmentBilling();
 				
 				if(!validated){
	 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveShipmentBilling");
	 				if(type=="save"){
	 					path = "/shipmentBilling";
	 				}else if(type=="continue"){
	 					path = "/listItemCartons";
	 				} 				
 				}
 				else{
 					commonService.processMsg($scope.errors, 'error');
 					return;
 				}
 			}else if(screen == 'shipmentDocument'){
 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveShipmentDocument");
 				if(type=="save"){
 					path = "/shipmentDocument";
 				}				
 			}else if(screen == 'shipmentBookingCustom'){
 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveShipmentBookingCustom");
 				if(type=="save"){
 					path = "/shipmentBookingCustom";
 				}else if(type=="continue"){
 					path = "/shipmentDocument";
 				}
 			}
 			 						 			
 			promise.then(function(result){
 				
 				if(result.data == null){
 					$scope.errors.push({form:'', field: '', msg: 'Server encountered error, please try later.'});
 					
 					//$('html,body').animate({ scrollTop: 0 }, 'slow');
 					return;
 				}
 				
 				commonService.setResponse(result.data.data);
 				if(result.data.success == false){ 					
 					//server side validation fail
 					if(result.status == 417){
 						var msgs = result.data.data.msgs;
 						angular.forEach(msgs, function(index, value){
 							$scope.errors.push({form:'', field: '', msg: index});
 						});
 					}else{
 						$scope.errors.push({form:'', field: '', msg: result.data.message}); 	 					
 					}					
 					//commonService.processMsg($scope.errors, 'error');
 					//alert(result.data.message);
 				}else if(type!="save"){ 					
 					$location.path(path);
 					$scope.clearMessages();
 				}else if(type == "save" && path == "/createShipmentWizard"){
 					$scope.shipment = {}; 					
 					$scope.getAllShipments(); 	
 					$scope.clearMessages();
 				}
 				//$('html,body').animate({ scrollTop: 0 }, 'slow');
 				//$scope.clearMessages();
 				angular.element('input[name=shipmentDoc]').val('');
 			});
 		}
 		
 		$scope.getAllShipments = function(){
 			var promise = exportOperationFactory.getAllShipments(); 
 			
 			promise.then(function(result){
 				if(result.data.success == false){
 					$scope.errors.push({form:'', field: '', msg: result.data.message});
 					commonService.processMsg($scope.errors, 'error');
 					
 					//alert(result.data.message);
 					$scope.shipments = [];
 				}else{ 					
 					
 					//console.log(result);
 					
 					var ships = result.data.data;
 					
 					var transData = [];
 					transData.push(['no', 'shipmentNo', 'exportDate', 'status', 'ultCons', 'ultDest', 'getRates', 'fileToEase', 'deleteShipment']);
 					
 					for(var i=0; i<ships.length; i++){
 						var shipObj = ships[i];
 						var transArr = new Array(9);
 						transArr[0] = i+1;
 						transArr[1] = shipObj.shipmentNo;
 						transArr[2] = shipObj.exportDate;
 						transArr[3] = shipObj.status;
 						transArr[4] = shipObj.ultConsigneeCountryName;
 						transArr[5] = shipObj.countryOfUltConsigneeName;
 						transArr[6] = '';
 						transArr[7] = shipObj.id;
 						transArr[8] = shipObj.id;
 						
 						transData.push(transArr);
 					}
 					
 					//$scope.shipments = result.data.data;
 					console.log(JSON.stringify(transData));
 					$scope.shipments = transData;
 					
 					
 					//old
 					$scope.shipments = result.data.data;
 				} 				
 			}); 			
 			//var response = commonService.getResponse(); 			
 		}
 		
 		$scope.deleteShipment = function(shipment, ev){
 			/*var cnf = confirm('Are you sure you want to delete?');
 			if(cnf) {
	 			var promise = exportOperationFactory.removeShipment(shipment);
	 			blockScroll();
	 			
	 			promise.then(function(response){
	 				if(response.status == 200){
	 					var index = $scope.shipments.indexOf(shipment);
			 			$scope.shipments.splice(index, 1);
	 				}else{
	 					$scope.errors.push({form:'', field: '', msg: response.statusText});
	 					commonService.processMsg($scope.errors, 'error');
	 					//alert(response.statusText);
	 				}	 				
	 				//unblockLoader();
	 			 });	 			
 			}*/
 			$scope.clearMessages();
 			
 			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  var promise = exportOperationFactory.removeShipment(shipment);
		 			blockScroll();
		 			
		 			promise.then(function(response){
		 				if(response.status == 200){
		 					
		 					$route.reload();
		 					
		 					//var index = $scope.shipments.indexOf(shipment);
				 			//$scope.shipments.splice(index, 1);
		 				}else{
		 					$scope.errors.push({form:'', field: '', msg: response.statusText});
		 					commonService.processMsg($scope.errors, 'error');
		 					//alert(response.statusText);
		 				}	 				
		 				//unblockLoader();
		 			 });	
	       	   
	          	}, function() {
	          		
	          });
 		}
 		
 		$scope.searchShipment = function(){
 			blockScroll();
 			
 			$scope.getShipmentList(1, 5, null);
 			
 			/*var promise;
 			if(typeof $scope.searchShip != 'undefined'){
 				$scope.searchShip.sbu = angular.element('#searchShipSbu').val();
 				promise = exportOperationFactory.searchShipment($scope.searchShip); 				
 			}else{
 				promise = exportOperationFactory.getAllShipments();
 			}
 		 			
 			promise.then(function(result){
 				if(result.data.success == false){
 					$scope.errors.push({form:'', field: '', msg: result.data.message});
 					commonService.processMsg($scope.errors, 'error');
 					//alert(result.data.message);
 					$scope.shipments = [];
 				}else{ 					
 					$scope.shipments = result.data.data;
 				} 				
 			});*/
 			//var response = commonService.getResponse();
 		}
 		
 		$scope.resetSearchShipment = function(){
 			$scope.searchShip = {};
 			$('.selectpicker').selectpicker();
 		}
 		
 		/**
 		 * exporter
 		 */
 		$scope.initExporter = function(){
 			
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Exporter");
 			}else{
 				$scope.shipment = shipment;
 	 			$scope.page_title = "Exporter";
 	 			commonService.setResponse(null);
 	 			
 	 			//$scope.populateMaster($scope.shipment.exporterCountry);
 	 			
 	 			refreshSelectpicker(); 
 			}
 			
 		}
 		
 		/*$scope.$on('openSearchExpPopup', function(event, args){
 			alert(123);
 		});*/
 		
 		
 		/********************* Search exporter popup start ******************************/
 		
 		
 		/**
 		 * search exporter pop up
 		 */
 		$scope.searchExporterPopup = function(){

 			commonService.searchExporterPopup('shipment', $scope.shipment);
 			
			/*var config = {
				  controller: 'exportOperationCtlr',
			      templateUrl: 'views/export/searchExporterPopup.html',
			      windowTemplateUrl: 'views/export/searchExporterPopup.html'
			}
			
			config.resolve = {
				items: function(){
					shipment: $scope.shipment
				}
			}
			
			$scope.uibModalInstance = commonService.gtnModal(config);
			
			var response = {
					instance: $scope.uibModalInstance,
					shipment: $scope.shipment
			}
			commonService.setResponse(response);*/	
 		}
 		
 		/*$scope.getCountry = function(request){
 			console.log(request);
 		}*/
 		
 		$scope.initSearchExporterPopup = function(){
 			$scope.gtnClass = gtnClassGlobal;
 			
 			$scope.exporter = {};
			
 			$scope.exporter.sortBy = "expCode";
			$scope.exporter.direction = "ASC";
			$scope.searchPage = true;
			$scope.modalInstance = commonService.getResponse().instance;
			$scope.exporterResults=[];
			
			commonService.populateSbu($scope, true, 'exporter', 'sbu');
			
			//commonService.setResponse(null);
			
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
 		
 		
 		
 		$scope.getExporterList = function(page, pageSize, options){
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
	            	$scope.entity.sortBy = $scope.exporterColumns[sortIndex.index];
	            	$scope.entity.direction = sortIndex.ad;
	            }
			}
            //blockLoader();
            return masterFactory.searchRecords($scope.entity, 'searchExporterValue')
					.then(function(response){
						if(response.data.success == true){
							
							//console.log(response.data.data);
							//unblockLoader();
							scroll();
							/*$scope.searchPage = false;
							console.log(' --------- ');
							console.log(response.data.data);*/
							return response.data.data;
							//commonService.setResponse(response);
							//$location.path('resultSearchConsignee');
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
			});
        }
 		
 		
 		$scope.searchExporter = function(){
 			
 			masterFactory.searchRecordsCount($scope.exporter, 'searchExporterValueCount')
				.then(function(response){
					if(response.data.success == true){
						
						$scope.exporterColumns = [	'delete',
						                          	'exporterCode',
									                'department',
									                'stateName',
									                'countryName',
									                'sbu',
									                'status',
									                'createdOn'];
						
			 			
						//commonService.setResponse(response);
						//$scope.getConsigneeList(1, 5);
						//$scope.getExporterList(1, 5);
						$scope.searchPage = false;
						
						var colMap = {
								"expCode": 1,
								"expName": 2,
								"expAddr1": 1,
								"expAddr2": 1,
								"expCity": 1,
								"expStateName": 1,
								"expCountryName": 4
								
						}
						
						var arrInd = colMap[$scope.exporter.sortBy];
						
						$scope.getExporterList(1,5);
						$timeout(function(){														
							$('.result-conatiner .column div[index='+arrInd+']').click();
			            });
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
 			
 			
 			/*masterFactory.searchRecords($scope.exporter, 'searchExporterValue')
				.then(function(response){
					if(response.data.success == true){
						//commonService.setResponse(response.data.data);
						$scope.searchPage = false;
						$scope.exporterResults = response.data.data;
						
						//$location.path('resultSearchExporter');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});*/
 		}
 		
 		$scope.selectExporter = function(){
 			var exp = angular.element('input[name=selectedExporter]:checked').val();
 			
 			if(exp){
 				masterFactory.findExporter(exp)
					.then(function(response){
						if(response.data.success == true){
							
							var obj = commonService.getResponse().shipment;
							var type = commonService.getResponse().type;
							//$scope.shipment = shipment.shipment;
							
							var expData = response.data.data;
							
							//$scope.onExporterSelect(expData, null, null);
							if(type == 'shipment') {
								obj.exporterCode = expData.exporterCode;
								obj.exporterName = expData.department;
				 				
								obj.exporterAddressLine1 = expData.addrLine1;
								obj.exporterCity = expData.city;
				 				
								obj.exporterCountry = expData.country;
								obj.exporterCountryName = expData.countryName;
				 				
								obj.exporterState = expData.state;
								obj.exporterStateName = expData.stateName;
				 				
								obj.exporterCompanyName = expData.department;
								obj.exporterPhoneNo = expData.telephone;
				 				obj.exporterZip = expData.zipCode;
				 				obj.exporterEmail = expData.email;
				 				
				 				obj.usppiExporterIdType = expData.filerTypeId;
				 				obj.usppiExporterId = expData.filerId;
							}else if(type == 'wls'){
								obj.entityCode = expData.exporterCode;
								obj.name = expData.department;
				 				
								obj.addressLine1 = expData.addrLine1;
								obj.addressLine2 = expData.addrLine2;
				 				
								obj.city = expData.city;
								obj.country = expData.country;
								obj.countryName = expData.countryName;
				 				
								obj.state = expData.state;
								obj.stateName = expData.stateName;
								obj.zip = expData.zipCode;
							}
			 				
			 				
							$scope.modalInstance.dismiss('cancel');
							commonService.setResponse(null);
							//alert(JSON.stringify(expData));
						}else{
							$scope.errors.push({form:'', field: '', msg: 'error getting exporter details.'});
						}
					});
 			}else{
 				$scope.errors.push({form:'', field: '', msg: 'Please select a exporter.'});
 			}
 			
 		}
 		
 		$scope.goToSearch = function(){
 			$scope.searchPage = true;
 			$scope.exporter = {};
 		}
 		
 		
 		/********************* Search exporter popup end ******************************/
 		
 		
 		/********************* Search exporter popup start ******************************/
 		
 		/**
 		 * Consignee popup
 		 */
 		$scope.searchConsigneePopup = function(type){
 			
 			commonService.searchConsigneePopup(type, $scope.shipment);

			/*var config = {
				  controller: 'exportOperationCtlr',
			      templateUrl: 'views/export/searchConsigneePopup.html',
			      windowTemplateUrl: 'views/export/searchConsigneePopup.html'
			}
			
			config.resolve = {
				items: function(){
					shipment: $scope.shipment
				}
			}
			
			$scope.uibModalInstance = commonService.gtnModal(config);
			
			var response = {
					instance: $scope.uibModalInstance,
					shipment: $scope.shipment,
					type: type
			}
			commonService.setResponse(response);*/	
 		}
 		
 		$scope.initSearchConsigneePopup = function(){
 			$scope.gtnClass = gtnClassGlobal;
 			
 			$scope.consignee = {};
			
 			$scope.consignee.sortBy = 'consigneeId';
			$scope.consignee.direction = 'DESC';
			$scope.searchPage = true;
			$scope.modalInstance = commonService.getResponse().instance;
			$scope.type = commonService.getResponse().type;
			//$scope.entityObj = commonService.getResponse().shipment;
			
			$scope.consigneeResults=[];
			
			
			commonService.populateSbu($scope, true, 'consignee', 'sbuCode');
			
			//commonService.setResponse(null);
			
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
 		
 		$scope.searchConsignee = function(){
 			
 			$scope.clearMessages();
 			
 			masterFactory.searchRecordsCount($scope.consignee, 'searchConsigneeCount')
				.then(function(response){
					if(response.data.success == true){
						

			 			$scope.consigneeColumns = [
			 			                            'delete',
			 			                           	'consigneeId',
									                'consigneeName',
									                'consigneeCountryName',
									                'salesPerson',
									                'typeOfConsignee',
									                'sbuCode',
									                'status'
									               ];
						
			 			
						//commonService.setResponse(response);
						//$scope.getConsigneeList(1, 5);
						$scope.searchPage = false;
						
						var colMap = {
								"consigneeId": 1,
								"consigneeName": 2,
								"consigneeAddr1": 1,
								"consigneeAddr2": 1,
								"consigneeCity": 1,
								"consigneeState": 1,
								"consigneeCountryName": 4
								
						}
						
						var arrInd = colMap[$scope.consignee.sortBy];
						
						$scope.getConsigneeList(1,5);
						$timeout(function(){														
							$('.result-conatiner .column div[index='+arrInd+']').click();
			            });
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
 			
 			/*masterFactory.searchRecords($scope.consignee, 'searchConsigneeValue')
				.then(function(response){
					if(response.data.success == true){
						//commonService.setResponse(response.data.data);
						$scope.searchPage = false;
						$scope.consigneeResults = response.data.data;						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});*/
 		}
 		
 		$scope.getConsigneeList = function(page, pageSize, options){
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
	            	$scope.entity.sortBy = $scope.consigneeColumns[sortIndex.index];
	            	$scope.entity.direction = sortIndex.ad;
	            }
			}
            //blockLoader();
            return masterFactory.searchRecords($scope.entity, 'searchConsigneeValue')
					.then(function(response){
						if(response.data.success == true){

							//console.log(response.data.data);
							//unblockLoader();
							scroll();
							//commonService.setResponse(response);
							return response.data.data;
							//commonService.setResponse(response);
							//$location.path('resultSearchConsignee');
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
			});
        }
 		
 		$scope.selectConsignee = function(type){
 			var consigneeId = angular.element('input[name=selectedConsignee]:checked').val();
 			
 			if(consigneeId){	
 				
				masterFactory.findEntity(consigneeId, 'findConsignee')
					.then(function(response){
						if(response.data.success == true){
							
							var shipment = commonService.getResponse().shipment;
							
							//$scope.shipment = shipment.shipment;
							
							var expData = response.data.data;
							
							//$scope.onExporterSelect(expData, null, null);
							if(type == 'ultCons'){
								shipment.ultConsigneeCode = expData.consigneeId;
				 				shipment.ultConsigneeName = expData.consigneeName;
				 				
				 				shipment.ultConsigneeAddrLine1 = expData.consigneeAddr1;
				 				shipment.ultConsigneeCity = expData.consigneeCity;
				 				
				 				shipment.ultConsigneeCountryCode = expData.consigneeCountry;
				 				shipment.ultConsigneeCountryName = expData.consigneeCountryName;
				 				
				 				shipment.ultConsigneeState = expData.consigneeState;
				 				shipment.ultConsigneeStateName = expData.consigneeStateName;
				 				
				 				//shipment.ultConsigneeCompanyName = expData.department;
				 				shipment.ultConsigneePhone = expData.phone;
				 				shipment.ultConsigneeZip = expData.zip;
				 				shipment.ultConsigneeEmail = expData.email;
				 				
				 				shipment.ultConsigneeType = expData.type;
							}else if(type == 'interCons'){
								shipment.interConsigneeCode = expData.consigneeId;
				 				shipment.interConsigneeName = expData.consigneeName;
				 				
				 				shipment.interConsigneeAddrLine1 = expData.consigneeAddr1;
				 				shipment.interConsigneeCity = expData.consigneeCity;
				 				
				 				shipment.interConsigneeCountryCode = expData.consigneeCountry;
				 				shipment.interConsigneeCountryName = expData.consigneeCountryName;
				 				
				 				shipment.interConsigneeState = expData.consigneeState;
				 				shipment.interConsigneeStateName = expData.consigneeStateName;
				 				
				 				//shipment.ultConsigneeCompanyName = expData.department;
				 				shipment.interConsigneePhone = expData.phone;
				 				shipment.interConsigneeZip = expData.zip;
				 				shipment.interConsigneeEmail = expData.email;
				 				
				 				//shipment.ultConsigneeType = expData.type;
							}else if(type == 'wls'){
								//shipment = $scope.entityObj;
								shipment.entityCode = expData.consigneeId;
								shipment.name = expData.consigneeName;
				 				
								shipment.addressLine1 = expData.consigneeAddr1;
								shipment.addressLine2 = expData.consigneeAddr2;
				 				
								shipment.city = expData.consigneeCity;
								shipment.country = expData.consigneeCountry;
								shipment.countryName = expData.consigneeCountryName;
				 				
								shipment.state = expData.consigneeState;
								shipment.stateName = expData.consigneeStateName;
								shipment.zip = expData.zip;
							
							}
							
			 			
							$scope.modalInstance.dismiss('cancel');
							commonService.setResponse(null);
							//alert(JSON.stringify(expData));
						}else{
							$scope.errors.push({form:'', field: '', msg: 'error getting consignee details.'});
							
						}
					});
 			}else{
 				$scope.errors.push({form:'', field: '', msg: 'Please select a consignee.'});
 				
 			}
 			
 		}
 		
 		
 		/************ cancel shipment start **************/
 		
 		$scope.cancelShipmentStatus = function(shipmentId, ev){
 			
 			$scope.clearMessages();
 			
 			if($scope.shipment.status == 'C'){
 				$scope.errors.push({form:'', field: '', msg: 'Shipment already canceled.'});
 				scroll();
 				return;
 			}
 			
 			var confirm = $scope.mdConfirmCancel(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  exportOperationFactory.cancelShipment(shipmentId)
					.then(function(response){
						console.log(response);
						if(response.data == null || response.status == -1){
							$scope.errors.push({form:'', field: '', msg: 'Server encounter an error, please try later.'});
		 					commonService.processMsg($scope.errors, 'error');
						}else if(response.data.success == false){
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}else{
							$scope.shipment.status = 'C';
							scroll();
						}
					});	
	       	   
	          	}, function() {
	          		
	          });
 			
 			
 		}
 		
 		/************ cancel shipment end **************/
 		
 		/*$scope.cancel = function () {
 			$scope.modalInstance.dismiss('cancel');
 		};*/
 		
 		$scope.populateMaster = function(exporterCountry){
 			exportOperationFactory.getDropDown(exporterCountry)
				.then(function(response){ 	 					
					if(response.data == null || response.status != 200){
						$scope.errors.push({form:'', field: '', msg: 'Error getting master details'});
	 					commonService.processMsg($scope.errors, 'error');
					}else{
						$scope.countries = response.data.data.countries;
						$scope.states = response.data.data.states;
					}
			});
 		}
 		
 		$scope.updateStates = function(countryCode){
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
 		
 		
 		$scope.populateFromSession = function(title){
 			exportOperationFactory.getShipmentFromSession()
				.then(function(response){
					console.log(response);
					$scope.shipment = response.data.data;
					$scope.populateMaster($scope.shipment.exporterCountry);
					if(response.data.success == true){
		 	 			$scope.page_title = title;
		 	 			commonService.setResponse(null);
		 	 			refreshSelectpicker();
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
 		}
 		
 		$scope.editShipment = function(id){
 			var promise = exportOperationFactory.findShipmentByNo(id, 'edit'); 	
 			
 			promise.then(function(result){ 				
 				if(result.data.success == false){ 	
 					$scope.errors.push({form:'', field: '', msg: result.data.message});
 					commonService.processMsg($scope.errors, 'error');
 					//alert(result.data.message);
 				}else{
 					commonService.setResponse(result.data.data); 
 	 				$location.path("/createExporter");
 				}
 			});
 		}
 		
 		/**
 		 * ultimate consignee
 		 */
 		$scope.initUltConsignee = function(){
 			
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Ultimate Consignee");
 			}else{
 				$scope.shipment = shipment;
 	 			$scope.page_title = "Ultimate Consignee";
 	 			
 	 			//$scope.populateMaster($scope.shipment.ultConsigneeCountryCode);
 	 			
 	 			commonService.setResponse(null);
 	 			refreshSelectpicker(); 
 			}
 			
 			/*exportOperationFactory.getDropDown($scope.shipment.ultConsigneeCountryCode)
			.then(function(response){ 	 					
				if(response.data == null || response.status != 200){
					$scope.errors.push({form:'', field: '', msg: 'Error getting master details'});
 					commonService.processMsg($scope.errors, 'error');
				}else{
					$scope.countries = response.data.data.countries;
					$scope.states = response.data.data.states;
				}
			});*/
 			
 			/*var shipment = commonService.getResponse();
 			$scope.shipment = shipment;
 			$scope.page_title = "Ultimate Consignee";
 			commonService.setResponse(null);
 			refreshSelectpicker();*/
 		}
 		
 		/**
 		 * Cancel action on shipment wizard screens
 		 */
 		$scope.cancelShipment = function(id, url){
 			var promise = exportOperationFactory.findShipment(id);
 			
 			//$scope.clearMessages();
 			
 			promise.then(function(result){
 				if(result.data.success == false){
 					$scope.errors.push({form:'', field: '', msg: result.data.message});
 					commonService.processMsg($scope.errors, 'error');
 					//alert(result.data.message);
 				}else{
 					commonService.setResponse(result.data.data); 
 	 				$location.path(url);
 				}
 			});
 		}
 		
 		/**
 		 * Intermediate consignee
 		 */
 		$scope.initInterConsignee = function(){
 			
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Intermediate Consignee");
 			}else{
 				$scope.shipment = shipment;
 	 			$scope.page_title = "Intermediate Consignee";
 	 			commonService.setResponse(null);
 	 			
 	 			$scope.populateMaster($scope.shipment.interConsigneeCountryCode);
 	 			
 	 			refreshSelectpicker(); 
 			} 	
 			/*exportOperationFactory.getDropDown($scope.shipment.interConsigneeCountryCode)
				.then(function(response){ 	 					
					if(response.data == null || response.status != 200){
						$scope.errors.push({form:'', field: '', msg: 'Error getting master details'});
	 					commonService.processMsg($scope.errors, 'error');
					}else{
						$scope.countries = response.data.data.countries;
						$scope.states = response.data.data.states;
					}
			 });*/
 			/*var shipment = commonService.getResponse();
 			$scope.shipment = shipment;
 			$scope.page_title = "Intermediate Consignee";
 			commonService.setResponse(null);
 			refreshSelectpicker();*/
 		}
 		
 		/**
 		 * Freight Forwarder
 		 */
 		$scope.initFreightForwarder = function(){
 			
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Freight Forwarder");
 			}else{
 				$scope.shipment = shipment;
 				
 				/*if($scope.shipment.transmitToAes == '1')
					$scope.shipment.transmitToAes = 1;
				else
					$scope.shipment.transmitToAes = 0;*/
 				 				
 	 			$scope.page_title = "Freight Forwarder";
 	 			commonService.setResponse(null);
 	 			
 	 			$scope.populateMaster($scope.shipment.freightForwaderCountryCode);
 	 			
 	 			refreshSelectpicker(); 
 			} 
 			
 			/*exportOperationFactory.getDropDown($scope.shipment.freightForwaderCountryCode)
			.then(function(response){ 	 					
				if(response.data == null || response.status != 200){
					$scope.errors.push({form:'', field: '', msg: 'Error getting master details'});
 					commonService.processMsg($scope.errors, 'error');
				}else{
					$scope.countries = response.data.data.countries;
					$scope.states = response.data.data.states;
				}
			});*/
 			/*var shipment = commonService.getResponse();
 			$scope.shipment = shipment;
 			$scope.page_title = "Freight Forwarder";
 			commonService.setResponse(null);
 			refreshSelectpicker();*/
 		}
 		
 		/**
 		 * Shipment Billing
 		 */
 		$scope.initBilling = function(){
 			
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Billing & Terms");
 			}else{
 				$scope.shipment = shipment;
 	 			$scope.page_title = "Billing & Terms";
 	 			commonService.setResponse(null);
 	 			refreshSelectpicker(); 
 			} 
 			
 			 			
 			/*var shipment = commonService.getResponse();
 			$scope.shipment = shipment;
 			$scope.page_title = "Billing & Terms";
 			commonService.setResponse(null);
 			refreshSelectpicker();*/
 		}
 		
 		$scope.currencies = loadAll();
 		
 		$scope.querySearch = function(query) {
 			
 		      var results = query ? $scope.currencies.filter( createFilterFor(query) ) : $scope.currencies,
 		          deferred;
 		      if (self.simulateQuery) {
 		        deferred = $q.defer();
 		        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
 		        return deferred.promise;
 		      } else {
 		        return results;
 		      }
	    }
 		
 		 function loadAll() {
 		      var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
 		              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
 		              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
 		              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
 		              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
 		              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
 		              Wisconsin, Wyoming';

 		      return allStates.split(/, +/g).map( function (state) {
 		        return {
 		          value: state.toLowerCase(),
 		          display: state
 		        };
 		      });
	    }
 		
 		$scope.searchTextChange = function(text) {
 			  $scope.shipment.currency = text;
 		      $log.info('Currency changed to ' + text);
	    }
 		
 		$scope.selectedItemChange = function(item) {
 			  $scope.shipment.currency = item.value;
 		      $log.info('Currency changed to ' + JSON.stringify(item));
	    }
 		
 		function createFilterFor(query) {
 		      var lowercaseQuery = angular.lowercase(query);

 		      return function filterFn(state) {
 		        return (state.value.indexOf(lowercaseQuery) === 0);
 		      };

	    }

 		
 		/**
 		 * Shipment document
 		 */
 		$scope.initShipmentDocument = function(){
 			var response = commonService.getResponse();
 			
            
            $scope.shipmentDocColumns = [
                                         'id', 'fileType', 'fileName', 'createdOn', 'delete'
                                         ];
 			
 			if(commonService.isEmpty(response) || !response.hasOwnProperty('shipment')){
 				//$scope.populateFromSession("");
 				
 				exportOperationFactory.getShipmentFromSession()
				.then(function(response){
					console.log(response);
					$scope.shipment = response.data.data;
					
					if(response.data.success == true){
						
							exportOperationFactory.getShipmentDocs($scope.shipment.id)
								.then(function(response){
									if(response.data.success == false){
										$scope.errors.push({form:'', field: '', msg: response.data.message});
					 					commonService.processMsg($scope.errors, 'error');
										//alert('Error getting carton.');
									}else{
										var docs = response.data.data;
										$.each(docs, function(k, v){
											v['delete'] = v['id'];
										});
										//alert(JSON.stringify(docs));
										$scope.shipmentDocList = docs;
									}
							}); 
						
		 	 			$scope.page_title = "Documents";
		 	 			commonService.setResponse(null);
		 	 			refreshSelectpicker();
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
 				
 				return;
 			}
 			

 			var shipment = response.shipment;
 			var docs = response.docs;
 			
 			$scope.shipment = shipment;
 			$scope.shipmentDocs = docs;
 			$scope.errors = [];
 			
 			$scope.page_title = "Documents";
 			commonService.setResponse(null);
 			angular.element('input[name=shipmentDoc]').val('');
 			refreshSelectpicker();
 		}
 		
 		/**
 		 * Upload document
 		 */
 		$scope.uploadShipmentDoc = function(){
 			var file = $scope.shipmentDoc;
 			$scope.errors.length = 0;
 			
 			if(file=='' || file == undefined){
 				$scope.errors.push({form:'', field: '', msg: 'Please select a file.'});
				commonService.processMsg($scope.errors, 'error');
					
 				//alert('Please select a file.');
 				return;
 			}
 			
 			var shipment = $scope.shipment;
            
            console.log('file is ' );
            console.dir(file);
            
            var uploadUrl = "uploadShipmentDoc";
            var promise = exportOperationFactory.uploadFileToUrl(file, uploadUrl, shipment.id);
            
            promise.then(function(response){
               if(response.data.success == false){
          		   //alert('Error uploading file : '+response.data.message);
            	   $scope.errors.length = 0;
            	   $scope.errors.push({form:'', field: '', msg: response.data.message});
            	   commonService.processMsg($scope.errors, 'error');
          	   }else{
          		   $scope.shipmentDocList = response.data.data;
          		   $scope.shipmentDoc= "";
          		   angular.element('input[name=shipmentDoc]').val('');
          		   $scope.errors.length = 0;
          	   }
            	          	
            });
 		}
 		
 		$scope.deleteDoc = function(id, ev){
 			/*var cnf = confirm('Are you sure you want to delete?');
 			if(cnf) {
 				blockScroll();
	 			exportOperationFactory.removeDocument(id)
	 				.then(function(response){	 					
	 					if(response.data.success == true){
	 						$scope.shipmentDocs = $.grep($scope.shipmentDocs, function(element, index){return element.id == id}, true);
	 						$scope.clearMessages();
	 					}else{
	 						$scope.errors.push({form:'', field: '', msg: 'Error deleting shipment document.'});
	 	 					commonService.processMsg($scope.errors, 'error');
	 						//alert('Error deleting shipment document.');
	 					}	 					
	 				});	 			
 			}*/
 			
 			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  blockScroll();
		 			exportOperationFactory.removeDocument(id)
		 				.then(function(response){	 					
		 					if(response.data.success == true){
		 						$scope.shipmentDocList = $.grep($scope.shipmentDocList, function(element, index){return element.id == id}, true);
		 						$scope.clearMessages();
		 					}else{
		 						$scope.errors.push({form:'', field: '', msg: 'Error deleting shipment document.'});
		 	 					commonService.processMsg($scope.errors, 'error');
		 						//alert('Error deleting shipment document.');
		 					}	 					
		 				});	 
	       	   
	          	}, function() {
	          		
	          });
 		}
 		
 		
 		/**
 		 * booking and custom filings
 		 */
 		$scope.initBookingCustoms = function(){
 			var shipment = commonService.getResponse();
 			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
 				$scope.populateFromSession("Booking & Custom Filings");
 			}else{
 				$scope.shipment = shipment;
 	 			$scope.page_title = "Booking & Custom Filings";
 	 			commonService.setResponse(null);
 	 			refreshSelectpicker(); 
 			}
 			
 			$scope.status = {
 				bookingOpen: true,
 				customsOpen: true,
 				responseOpen: true
			};
 		}
 		
		$scope.onCountrySelectCode = function(item, model, label, name, code){
			$scope.shipment[name] = item.countryName;
			$scope.shipment[code] = item.countryCode;
			//$scope.item.coo = item.countryName;
		}
 		
 		$scope.validateShipmentExporter = function(){
 			$scope.errors.length = 0;
 			
 			if(commonService.isEmpty($scope.shipment.exporterName)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=name]', msg: "Exporter name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterFirstName)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=firstname]', msg: "Exporter first name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterAddressLine1)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=address_tt]', msg: "Address is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterLastName)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=lastname]', msg: "Exporter last name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterCity)){
 				$scope.errors.push({form:'exporter-form', field: 'select[name=export_city]', msg: "City is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterState)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=export_state]', msg: "State is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterCountry)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=export_country]', msg: "Country is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterZip)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=export_postal]', msg: "Zip code is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.exporterCompanyName)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=Company]', msg: "Company name is required"});
 			}
 			/*if(commonService.isEmpty($scope.shipment.exporterPhoneNo)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=phoneNo]', msg: "Phone number is required"});
 			}*/
 			
 			if(!$scope.exportForm.phoneNo.$valid){
 				var errorObj = $scope.exportForm.phoneNo.$error; 				
 				commonService.processNgError(errorObj, $scope.errors, 'exportForm', 'input[name=phoneNo]', 'phone no');
 			}
 			
 			if(commonService.isEmpty($scope.shipment.exporterEmail)){
 				$scope.errors.push({form:'exporter-form', field: 'input[name=email]', msg: "Email is required"});
 			}else if(!commonService.isEmpty($scope.shipment.exporterEmail)){
 				var valid = commonService.validateEmail($scope.shipment.exporterEmail);
 				
 				if(!valid){
 					$scope.errors.push({form:'exporter-form', field: 'input[name=email]', msg: "Please enter valid email ID."});
 				}
 			}
 			
 			/*angular.element("form[name=exporter-form] input").removeClass('invalid-ele');
 			angular.element("form[name=exporter-form] select").removeClass('invalid-ele');*/
 			
 			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
 		}
 		
 		$scope.validateShipmentUltCons = function(){
 			$scope.errors.length = 0;
 			
 			if(commonService.isEmpty($scope.shipment.ultConsigneeName)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=name]', msg: "Consignee name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeFirstName)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=firstname]', msg: "Consignee first name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeAddrLine1)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=address_tt]', msg: "Address is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeLastName)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=lastname]', msg: "Consignee last name is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeCity)){
 				$scope.errors.push({form:'ultConsForm', field: 'select[name=export_city]', msg: "City is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeState)){
 				$scope.errors.push({form:'ultConsForm', field: 'select[name=export_state]', msg: "State is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeCountryCode)){
 				$scope.errors.push({form:'ultConsForm', field: 'select[name=export_country]', msg: "Country is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeZip)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=export_postal]', msg: "Zip code is required"});
 			}
 			if(commonService.isEmpty($scope.shipment.ultConsigneeCompanyName)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=Company]', msg: "Company name is required"});
 			}
 			/*if(commonService.isEmpty($scope.shipment.ultConsigneePhone)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=phoneNo]', msg: "Phone number is required"});
 			}*/
 			
 			if(!$scope.ultConsForm.phoneNo.$valid){
 				var errorObj = $scope.ultConsForm.phoneNo.$error; 				
 				commonService.processNgError(errorObj, $scope.errors, 'ultConsForm', 'input[name=phoneNo]', 'phone no');
 			}
 			
 			if(commonService.isEmpty($scope.shipment.ultConsigneeEmail)){
 				$scope.errors.push({form:'ultConsForm', field: 'input[name=email]', msg: "Email is required"});
 			}else if(!commonService.isEmpty($scope.shipment.ultConsigneeEmail)){
 				var valid = commonService.validateEmail($scope.shipment.ultConsigneeEmail);
 				
 				if(!valid){
 					$scope.errors.push({form:'ultConsForm', field: 'input[name=email]', msg: "Please enter valid email ID."});
 				}
 			}
 			
 			/*angular.element("form[name=exporter-form] input").removeClass('invalid-ele');
 			angular.element("form[name=exporter-form] select").removeClass('invalid-ele');*/
 			
 			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
 		}
 		
 		
 		
 		
 		$scope.validateShipmentBilling = function(){
 			
 			var error = false;
 			
 			if(!$scope.billingForm.convers.$valid){
 				var errorObj = $scope.billingForm.convers.$error;
 				
 				commonService.processNgError(errorObj, $scope.errors, 'billingForm', 'input[name=convers]', 'conversion rate');
 				
 				/*if(errorObj.hasOwnProperty('min') || errorObj.hasOwnProperty('number')){
 					$scope.errors.push({form:'billingForm', field: 'convers', msg: "Please enter a valid conversion rate."});
 		    	}*/
 				error = true;
 			}
 			
 			if(!$scope.billingForm.amount.$valid){
 				var errorObj = $scope.billingForm.amount.$error;
 				
 				commonService.processNgError(errorObj, $scope.errors, 'billingForm', 'input[name=amount]', 'amount');
 				
 				/*if(errorObj.hasOwnProperty('min') || errorObj.hasOwnProperty('number')){
 					$scope.errors.push({form:'billingForm', field: 'amount', msg: "Please enter a valid amount."});
 		    	}*/
 				error = true;
 			}
 			
 			if(!$scope.billingForm.shipmemntValue.$valid){
 				var errorObj = $scope.billingForm.shipmemntValue.$error;
 				
 				commonService.processNgError(errorObj, $scope.errors, 'billingForm', 'input[name=shipmemntValue]', 'shipment value');
 				
 				/*if(errorObj.hasOwnProperty('min') || errorObj.hasOwnProperty('number')){
 					$scope.errors.push({form:'billingForm', field: 'amount', msg: "Please enter a valid amount."});
 		    	}*/
 				error = true;
 			}
 			
 			return error;
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
 		
 		
 		$scope.getPorts = function(request, country){
			 blockScroll();
			 blockLoader();
			
			return $http({
				url: 'getPorts?req='+request+"&country="+country,
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
 		
 			
 		$scope.editFileType = function(doc) {
 			doc.editing = true;
 			//$('.fileTypeSelect').not('.ng-hide').focus();
 		}
		
 		$scope.doneEditing = function (doc) {
 			doc.editing = false;
 			blockScroll();
 			exportOperationFactory.updateFileType(doc.id, doc.fileType)
				.then(function(response){	 	
					
					$scope.clearMessages();
					
					/*if(response.data.success == true){
						$scope.shipmentDocs = $.grep($scope.shipmentDocs, function(element, index){return element.id == id}, true);
						$scope.clearMessages();
					}else{
						$scope.errors.push({form:'', field: '', msg: 'Error deleting shipment document.'});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error deleting shipment document.');
					}	 */					
				});
 			
 		    //dong some background ajax calling for persistence...
 		}; 		
 		
 		$scope.validateShipment = function(shipmentId){
 			$scope.clearMessages();
 			exportOperationFactory.validateShipment(shipmentId)
				.then(function(response){	 	
					
					if(response.data == null){
						$scope.errors.push({form:'', field: '', msg: 'Error validating shipment.'});
	 					commonService.processMsg($scope.errors, 'error');
	 					return;
					}
					
					if(response.data.success == true){
						
						var validations = response.data.data;
						
						if(validations.length == 0){
							$scope.infos.push({form:'', field: '', msg: 'Shipment validated successfully.'});
		 					commonService.processMsg($scope.infos, 'info');
						}else{
							$scope.showShipmentValidationPopup(validations, 'lg')
						}
												
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error deleting shipment document.');
					}	 					
				});	
 			
 		}
 		
 		$scope.modalInstance = null;
 		
 		$scope.initLsPopup = function(){	
 			
 			//alert(commonService.getResponse().instance);
 			$scope.modalInstance = commonService.getResponse().instance;
 			$scope.licenseItems = commonService.getResponse().data;
 			commonService.setResponse(null);
 			
 			$scope.gtnClass = gtnClassGlobal;
 			
 			//alert($scope.naveen.check);
 			//$scope.licenseItems = items;
 			
 		}
 		
 		$scope.initAesStatusHistoryPopup = function(){	
 			
 			//alert(commonService.getResponse().instance);
 			$scope.modalInstance = commonService.getResponse().instance;
 			$scope.aesStatusHistory = commonService.getResponse().data;
 			$scope.shipmentNo = commonService.getResponse().shipmentId;
 			
 			commonService.setResponse(null);
 			
 			$scope.gtnClass = gtnClassGlobal;
 			
 			//alert($scope.naveen.check);
 			//$scope.licenseItems = items;
 			
 		}
 		
 		
 		
 		$scope.cancel = function () {
 			$scope.modalInstance.dismiss('cancel');
 		};
 		  
 		$scope.showShipmentValidationPopup = function(){
			
			//commonService.setResponse(validations);
 			
 			$scope.licenseItems = [];
					
		    var uibModalInstance = $uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      controller: 'lsResultPopCtrl',
		      templateUrl: 'views/export/resultsShipmentValidation.html',
		      windowTemplateUrl: 'views/export/resultsShipmentValidation.html',
		      size: 'lg',
		      resolve: {
		    	  items: function(){
						return $scope.licenseItems;
					}
		      }
		    });

//		    $scope.modalInstance.result.then(function (selectedItem) {
//		      //$ctrl.selected = selectedItem;
//		    }, function () {
//		      $log.info('Modal dismissed at: ' + new Date());
//		    });
		}
 		
 		$scope.getExporters = function(request){
			 blockScroll();
			 blockLoader();
			
			 if(typeof request == 'undefined' || request == '')
				 return;
			 
			return $http({
				url: 'getExporters?req='+request,
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
 		
 		$scope.getConsignees = function(request){
			 blockScroll();
			 blockLoader();
			
			 if(typeof request == 'undefined' || request == '')
				 return;
			 
			return $http({
				url: 'getConsignees?req='+request,
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
 		
 		
 		$scope.onExporterSelect = function(item, model, label){
 			if(item){
 				$scope.shipment.exporterCode = item.expCode;
 				$scope.shipment.exporterName = item.expName;
 				
 				$scope.shipment.exporterAddressLine1 = item.expAddr1;
 				$scope.shipment.exporterCity = item.expCity;
 				
 				$scope.shipment.exporterCountry = item.expCountry;
 				$scope.shipment.exporterCountryName = item.expCountryName;
 				
 				$scope.shipment.exporterState = item.expState;
 				$scope.shipment.exporterStateName = item.expStateName;
 				
 				$scope.shipment.exporterCompanyName = item.expName;
 				$scope.shipment.exporterPhoneNo = item.phone;
 				$scope.shipment.exporterZip = item.zip;
 				$scope.shipment.exporterEmail = item.email;
 				
 				$scope.shipment.usppiExporterIdType = item.filerExpIdType;
 				$scope.shipment.usppiExporterId = item.filerExpId;
 				
 			}
 		}
 		
 		$scope.onConsigneeSelect = function(item, model, label){
 			if(item){
 				$scope.shipment.ultConsigneeCode = item.consigneeId;
 				$scope.shipment.ultConsigneeName = item.consigneeName;
 				
 				$scope.shipment.ultConsigneeAddrLine1 = item.consigneeAddr1;
 				$scope.shipment.ultConsigneeCity = item.consigneeCity;
 				
 				$scope.shipment.ultConsigneeState = item.consigneeState; 
 				$scope.shipment.ultConsigneeStateName = item.consigneeStateName; 				
 				
 				$scope.shipment.ultConsigneeCountryCode = item.consigneeCountry;
 				$scope.shipment.ultConsigneeCountryName = item.consigneeCountryName; 		
 				
 				$scope.shipment.ultConsigneeZip = item.zip;
 				
 				$scope.shipment.ultConsigneeCompanyName = item.consigneeName;
 				$scope.shipment.ultConsigneePhone = item.phone;
 				$scope.shipment.ultConsigneeEmail = item.email;
 			}
 		}
 		
 		$scope.onInterConsigneeSelect = function(item, model, label){
 			if(item){
 				$scope.shipment.interConsigneeCode = item.consigneeId;
 				$scope.shipment.interConsigneeName = item.consigneeName;
 				
 				$scope.shipment.interConsigneeAddrLine1 = item.consigneeAddr1;
 				$scope.shipment.interConsigneeCity = item.consigneeCity;
 				
 				$scope.shipment.interConsigneeState = item.consigneeState;
 				$scope.shipment.interConsigneeStateName = item.consigneeStateName; 		
 				
 				$scope.shipment.interConsigneeCountryCode = item.consigneeCountry;
 				$scope.shipment.interConsigneeCountryName = item.consigneeCountryName; 		
 				
 				$scope.shipment.interConsigneeZip = item.zip;
 				
 				$scope.shipment.interConsigneeCompanyName = item.consigneeName;
 				$scope.shipment.interConsigneePhone = item.phone;
 				$scope.shipment.interConsigneeEmail = item.email;
 			}
 		}
 		
 		/**
 		 * Create shipment in EASE
 		 */
 		$scope.createShipmentInEase = function(shipmentId){
 			
 			exportOperationFactory.createShipmentInEase(shipmentId)
				.then(function(response){
					console.log(response);					
				});	
 		}
 		
 		$scope.submitShipmetToEASE = function(shipmentId, ev){ 		

 			$scope.clearMessages();
 			
 			if($scope.shipment.easeStatus == 'submitted'){
 				//var sel = window.confirm("Shipment is already submitted to EASE. Do you want to submit again?");
 				
 				//var confirm = $scope.mdConfirm(ev);
 				var confirm = $mdDialog.confirm()
		            .title('Submit to AES')
		            .textContent('Shipment is already submitted to AES. Do you want to submit again?')
		            .ariaLabel('')
		            .targetEvent(ev)
		            .ok('OK')
		            .cancel('Cancel')
		            
		          $mdDialog.show(confirm).then(function() {		       	   
		        	  blockScroll();
		        	  exportOperationFactory.createShipmentInEase(shipmentId)
	 					.then(function(response){
	 						console.log(response);
	 						
	 						if(response.data.success == true){						
	 							$scope.infos.push({form:'', field: '', msg: 'Shipment submitted to AES successfully.'});
	 	 	 					commonService.processMsg($scope.infos, 'info');
	 	 	 					$scope.shipment.easeStatus = 'submitted';
	 			 	 			refreshSelectpicker();
	 						}else{
	 							$scope.errors.push({form:'', field: '', msg: response.data.message});
	 		 					commonService.processMsg($scope.errors, 'error');
	 						}
	 						
	 						//angular.element('html,body').animate({ scrollTop: 0 }, 250);
	 					});	
		       	   
		          	}, function() {
		          		
		          });
 				
 				
 				/*if(sel){
 					exportOperationFactory.createShipmentInEase(shipmentId)
 					.then(function(response){
 						console.log(response);
 						
 						if(response.data.success == true){						
 							$scope.infos.push({form:'', field: '', msg: 'Shipment created in EASE successfully.'});
 	 	 					commonService.processMsg($scope.infos, 'info');
 	 	 					$scope.shipment.easeStatus = 'submitted';
 			 	 			refreshSelectpicker();
 						}else{
 							$scope.errors.push({form:'', field: '', msg: response.data.message});
 		 					commonService.processMsg($scope.errors, 'error');
 						}
 						
 						//angular.element('html,body').animate({ scrollTop: 0 }, 250);
 					});	
 				}*/
 			}else{
 				exportOperationFactory.createShipmentInEase(shipmentId)
 				.then(function(response){
					console.log(response);					
						if(response.data.success == true){						
							$scope.infos.push({form:'', field: '', msg: 'Shipment created in EASE successfully.'});
	 	 					commonService.processMsg($scope.infos, 'info');
	 	 					$scope.shipment.easeStatus = 'submitted';
			 	 			refreshSelectpicker();
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
 					
					//angular.element('html,body').animate({ scrollTop: 0 }, 250);
				});	
 			}
 			
 			
 		}
 		
 		$scope.getMot = function(request){
			 blockScroll();
			 blockLoader();
			
			return $http({
				url: 'getMot?req='+request,
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
 		

		$scope.onMotSelect = function(item, model, label, field, code, obj){
			obj[field] = item.motDes;
			obj[code] = item.motId;
		}
		
		
		/************* generate shipment document starts ******************/
		
		$scope.generateShipmentDocPopup = function(shipmentId){
			$scope.clearMessages();
			var config = {
				  controller: 'exportOperationCtlr',
			      templateUrl: 'views/export/generateShipmenteDocPopup.html',
			      windowTemplateUrl: 'views/export/generateShipmenteDocPopup.html'
			}
			
			$scope.uibModalInstance = commonService.gtnModal(config);
			
			response = {
					instance: $scope.uibModalInstance,
					shipmentId: shipmentId,
					shipmentNo: $scope.shipment.shipmentNo
			}
			
			commonService.setResponse(response);
		}
		
		$scope.initGenerateShipmentDocPopup = function(){
			var response = commonService.getResponse();		
			$scope.modalInstance = response.instance;
			
			$scope.shipmentId = response.shipmentId;
			$scope.shipmentNo = response.shipmentNo;
			$scope.docEmail = {};
			
			$scope.docList = [{
								docName: 'Commercial Invoice',
								docType: 'commercialInvoice',
								active: true
							  },
							  {
								docName: 'Packaging List',
								docType: 'packingList',
								active: true
							  }];
			
			$scope.docEmailParam = true;
			$scope.gtnClass = gtnClassGlobal;
			commonService.setResponse(null);
			
			/**
			 * ,
							  {
								docName: 'Proforma Invoice',
								docType: 'proformaInvoice',
								active: false	
							  },{
								docName: 'Summary Commercial Invoice',
								docType: 'summaryCommercial',
								active: false
							  },{
								docName: 'WholeSaler Commercial Invoice',
								docType: 'wholesalerCommercial',
								active: false
							  }
			 */
		}
		
		$scope.showEmailDiv = function(docType){
			$scope.clearMessages();
			$scope.docEmail.email = "";
			$scope.docEmail.subject = "Shipping documents for Shipment Number ["+$scope.shipmentNo+"]";
			$scope.docEmail.message = "Please see attached the shipping documents for Shipment Number : "+$scope.shipmentNo+"\n\nThank you.";
			
			if(docType == "commercialInvoice"){
				$scope.docEmail.attachement = "Commercial Invoice - "+$scope.shipmentNo+".pdf";
			}else if(docType == "packingList"){
				$scope.docEmail.attachement = "Packing List - "+$scope.shipmentNo+".pdf";
			}else if(docType == "proformaInvoice"){
				$scope.docEmail.attachement = "Proforma Invoice - "+$scope.shipmentNo+".pdf";
			}else if(docType == "summaryCommercial"){
				$scope.docEmail.attachement = "Summary Commercial Invoice - "+$scope.shipmentNo+".pdf";
			}else if(docType == "wholesalerCommercial"){
				$scope.docEmail.attachement = "WholeSaler Commercial Invoice - "+$scope.shipmentNo+".pdf";
			}
			
			$scope.docEmail.docType = docType;
			$scope.docEmailParam = false;
		}
		$scope.showDocsDiv = function(docType){
			$scope.clearMessages();
			$scope.docEmailParam = true;
		}
		
		$scope.sendDocEmail = function(){
			//alert($scope.docEmail.docType+" - "+$scope.shipmentId);
			$scope.clearMessages();
			$scope.emailObj = {
					to: $scope.docEmail.email,
					subject: $scope.docEmail.subject,
					message: $scope.docEmail.message,
					shipId: $scope.shipmentId,
					shipmentNo: $scope.shipmentNo,
					docType: $scope.docEmail.docType
			}
			
			if($scope.docEmail.email == ''){
				$scope.errors.push({form:'', field: '', msg: 'Email id is required.'});
			}
			if($scope.docEmail.subject == ''){
				$scope.errors.push({form:'', field: '', msg: 'Email subject is required.'});
			}
			if($scope.docEmail.message == ''){
				$scope.errors.push({form:'', field: '', msg: 'Email message is required.'});
			}
			if($scope.errors.length > 0){
				return;
			}			
			
			exportOperationFactory.sendDocEmail($scope.emailObj)
				.then(function(response){
				console.log(response);					
					if(response.data.success == true){			
						$scope.docEmail.email = '';
						$scope.infos.push({form:'', field: '', msg: response.data.message});
					}else{
						if(response.status == 417){
							var errorList = response.data.data.validationErrors;
							
							$.each(errorList, function(k,v){
								$scope.errors.push({form:'', field: '', msg: v});
							});
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
						}
						
	 					//commonService.processMsg($scope.errors, 'error');
					}
				//angular.element('html,body').animate({ scrollTop: 0 }, 250);
			});	
			
			
		}
		
		$scope.downloadShipmentDoc = function(shipmentId, type){
			//alert(shipmentId+" - "+type);
			var downloadPath = 'getShipmentReport?shipId='+shipmentId+'&type='+type;
		    window.open(downloadPath, '_blank', '');
			/*exportOperationFactory.getShipmentReport(shipmentId, type)
				.then(function(response){
					console.log(response);
					//angular.element('html,body').animate({ scrollTop: 0 }, 250);
				}, function(error){
					alert(error);
					console.log(error);
				});*/
		}
		
 		/*$scope.validateItem = function(){
 			
 			var error = false;
 			
 			
 			
 			
 			return error;
 		}*/
 		
 		/*$scope.downloadDoc = function(id){
 			
 		}*/
}]);


dashboardApp.controller('lsResultPopCtrl', function ($scope, exportOperationFactory, commonService) {
	//$scope.licenseItems = [];
	$scope.initLsPopup = function(){	
		
		//$scope.licenseItems = items;
		
		/*var shipmentId = commonService.getResponse();
		
		exportOperationFactory.getShipmentItems(shipmentId)
			.then(function (response){						
			if(response.data.success == true){
				$scope.licenseItems = response.data.data;			
				commonService.setResponse(null);
			}else{
				$scope.errors.push({form:'', field: '', msg: response.data.message});
				commonService.processMsg($scope.errors, 'error');
				//alert(response.data.message);
				commonService.setResponse(null);
				$scope.licenseItems = [];
			}
		
		},function (error){
		   console.log(error);     
		});*/
	}
	
	$scope.initShipmentValidationPopup = function(){
		var validations = commonService.getResponse();
		
		$scope.validations = validations;
		
		commonService.setResponse(null);
	}
	
	$scope.closePopup = function(){
		$scope.dismiss({$value: 'cancel'});
	}
});

/*dashboardApp.controller('lsResultPopCtrl', ['$scope', '$uibModalInstance', 'exportOperationFactory', function ($scope, $uibModalInstance, exportOperationFactory, shipment) {
	
		exportOperationFactory.getShipmentItems($scope.shipment.id)
			.then(function (response){						
				if(response.data.success == true){
					$scope.licenseItems = response.data.data;
					
				}else{
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(response.data.message);
					$scope.licenseItems = [];
				}
			
		},function (error){
 	      console.log(error);     
		});
}]);*/











/**
 * Item carton controller
 */
dashboardApp.controller('itemCartonController', ['$scope', '$location', '$http', '$compile', '$uibModal', 'exportOperationFactory', 'commonService', '$mdDialog', '$route', 
        function($scope, $location, $http, $compile, $uibModal, exportOperationFactory, commonService, $mdDialog, $route) {
	
		$scope.items = [];
		$scope.cartons = [];
		$scope.item = {};
		$scope.carton = {};
		
		var parentScope = $scope.$parent;
		parentScope.child = $scope;
		
		/*$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];*/
	
		/*$scope.clearMessages = function(){
 			$scope.errors.length = 0;
 			$scope.warnings.length = 0;
 			$scope.infos.length = 0;
 		}*/
		
		/**
		 * List Items and cartons
		 */
		$scope.initListItemCarton = function(){
			var shipment = commonService.getResponse();
			
			if(commonService.isEmpty(shipment) || !shipment.hasOwnProperty('id')){
				
				exportOperationFactory.getShipmentFromSession()
					.then(function(response){
						console.log(response);
						$scope.shipment = response.data.data;
						
						if(response.data.success == true){						
			 	 			$scope.page_title = "Item & Cartons";
			 	 			
			 	 			$scope.getShipmentItems($scope.shipment.id);
			 				$scope.getShipmentCartons($scope.shipment.id); 	
			 				
			 	 			commonService.setResponse(null);
			 	 			refreshSelectpicker();
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
				});
				 				
 			}else{
 				 				
 				$scope.shipment = shipment;
 				$scope.page_title = "Item & Cartons";
 				commonService.setResponse(null);
 				
 				$scope.getShipmentItems($scope.shipment.id);
 				$scope.getShipmentCartons($scope.shipment.id); 				
 				
 				refreshSelectpicker();
 			}		
		}
		
		$scope.getShipmentItems = function(shipmentId){
			
			$scope.itemColumns =[
			                     'itemNo', 'partNo', 'htsSchedule', 'htsScheduleUom', 'invoiceQty',
			                     'invoiceValue', 'wholeSaleValue', 'coo', 'copy', 'delete'
			                     ];
			
			exportOperationFactory.getShipmentItems(shipmentId)
				.then(function (response){
				
				if(response.data.success == true){
					//$scope.items = response.data.data;
					$scope.itemList = response.data.data;
				}else{
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(response.data.message);
					return $scope.itemList = [];
				}
				
	         },function (error){
	     	      console.log(error);     
	         });
		}
		
		$scope.getShipmentCartons = function(shipmentId){
				            
			$scope.cartonColumns =[
			                     'cartonNo', 'grossWeight', 'packageNo', 'haszmat', 'copy', 'delete'
			                     ];
			
			exportOperationFactory.getShipmentCartons(shipmentId)
				.then(function (response){
				
				if(response.data.success == true){
					//$scope.cartons = response.data.data;
					$scope.cartonsList = response.data.data;
				}else{
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					//alert(response.data.message);
					$scope.cartonsList = [];
				}
				
	         },function (error){
	        	  $scope.errors.push({form:'', field: '', msg: error});
				  commonService.processMsg($scope.errors, 'error');
	        	  //alert(error);
	     	      console.log(error);     
	         });
		}
		
		$scope.initAddItem = function(){
			//load data from master
			$scope.shipment = {};
			var request = commonService.getResponse();
			
			if(!commonService.isEmpty(request))
				$scope.shipment = request.shipment;
			
			if(commonService.isEmpty(request) || commonService.isEmpty($scope.shipment) || !$scope.shipment.hasOwnProperty('id')){
				$location.path('/listItemCartons');
				return ;
			}
			
			var itemNo = request.itemNo;
			
			if(request.type == 'NEW'){
				$scope.item = {};
				$scope.item.shipmentId = $scope.shipment.id;
				$scope.item.itemNo = itemNo;
				$scope.page_title = "Add Item";
			}else if(request.type == 'COPY'){
				request.item.id = null;
				$scope.item = request.item;
				$scope.item.itemNo = itemNo;
				$scope.page_title = "Add Item";
			}else{
				//$scope.item.itemNo = itemNo;
				
				exportOperationFactory.findItem(request.itemId)
					.then(function(response){
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting item.');
					}else{
						var item = response.data.data;
						$scope.item = item;
						
						if($scope.item.ftrEffDate)
							$scope.item.ftrEffDate = new Date($scope.item.ftrEffDate); 
					}
				});
				
				$scope.page_title = "Update Item";
			}
						
			commonService.setResponse(null);
			refreshSelectpicker();
		}
		
		$scope.addItem = function(shipment, itemId, itemNo, copy, copyData){
			
			if(!shipment){
				shipment = $scope.shipment;
			}
			var request = {
					shipment: shipment,
					itemNo: itemNo
			}
			
			exportOperationFactory.getNextItemNo(shipment.id)
				.then(function(response){
					
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting next item number.');
					}
					else{
						if(itemNo == null && !copy){
							request.type = 'NEW';
							request.itemNo = response.data.data;
						}else if(itemNo == null && copy){
							request.type = 'COPY';
							request.item = copyData;
							request.itemNo = response.data.data;
						}else{
							
							/*console.log(' --- items ---- ');
							console.log($scope.itemList);
							*/
							request.itemId = itemId;
							//request.itemId = getItemIdFromNo($scope.itemList, itemNo);
							request.type = 'UPDATE';
						}
						commonService.setResponse(request);
						$location.path("createAddItem");						
					}
				});
		}
		
		function getItemIdFromNo(itemList, itemNo){
			var id;
			angular.forEach(itemList, function(value, key){
			     if(value.itemNo == itemNo){
			    	 id = value.id;
			     }
			});
			return id;
		}
		
		$scope.saveItem = function(type){
			var item = $scope.item;
			angular.element('.invalid-ele').removeClass('invalid-ele');
 			$scope.clearMessages();
			var validated = $scope.validateItem();
				
			if(validated){

				exportOperationFactory.saveItem(item)
					.then(function(response){					
						if(response.data.success ==  false){
							if(response.status == 417){
		 						var msgs = response.data.data.msgs;
		 						angular.forEach(msgs, function(index, value){
		 							$scope.errors.push({form:'', field: '', msg: index});
		 						});
		 					}else{							
								$scope.errors.push({form:'', field: '', msg: response.data.message});
			 					commonService.processMsg($scope.errors, 'error');
		 					}
							//$('html,body').animate({ scrollTop: 0 }, 'slow');
							return;
							//alert('Error saving item : '+response.data.message);
						}else{
							if(type=='continue'){
								exportOperationFactory.findShipment(item.shipmentId)
									.then(function(shipment){
										commonService.setResponse(shipment.data.data);
										$location.path('/listItemCartons');
									});								
							}else{
								$scope.item = response.data.data;
								$scope.shipment.lsStatus = $scope.item.lsStatus;
								$('html,body').animate({ scrollTop: 0 }, 'slow');
								$location.path('/createAddItem');
							}
						}
					});
				
			}else{
				commonService.processMsg($scope.errors, 'error');
				//$('html,body').animate({ scrollTop: 0 }, 'slow');
				return;
			}
			
		}
		
		$scope.validateItem = function(){
 			$scope.errors.length = 0;
 			
 			/*if(commonService.isEmpty($scope.item.partDescription)){
 				$scope.errors.push({form:'itemForm', field: 'textarea[name=products_desc]', msg: "Product description is required"});
 			}*/
 			/*if(commonService.isEmpty($scope.item.invoiceQty)){
 				$scope.errors.push({form:'itemForm', field: 'input[name=aes_invoice]', msg: "AES quantity is required"});
 			}
 			if(commonService.isEmpty($scope.item.invoiceValue)){
 				$scope.errors.push({form:'itemForm', field: 'input[name=invoice_value]', msg: "Invoice value is required"});
 			}
 			if(commonService.isEmpty($scope.item.grossWeight)){
 				$scope.errors.push({form:'itemForm', field: 'input[name=gross_weight]', msg: "Gross weight is required"});
 			}
 			if(commonService.isEmpty($scope.item.netWeight)){
 				$scope.errors.push({form:'itemForm', field: 'input[name=net_weight]', msg: "Net weight is required"});
 			}*/
 			
 			
 			/// data validations ///
 			
 			if(!$scope.itemForm.products_desc.$valid){
 				var errorObj = $scope.itemForm.products_desc.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'textarea[name=products_desc]', 'product description');
 				error = true;
 			} 
 			if(!$scope.itemForm.aes_invoice.$valid){
 				var errorObj = $scope.itemForm.aes_invoice.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=aes_invoice]', 'AES quantity');
 				error = true;
 			} 			
 			if(!$scope.itemForm.invoice_value.$valid){
 				var errorObj = $scope.itemForm.invoice_value.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=invoice_value]', 'invoice value');
 				error = true;
 			} 			
 			if(!$scope.itemForm.discount_tt.$valid){
 				var errorObj = $scope.itemForm.discount_tt.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=discount_tt]', 'discount');
 				error = true;
 			}
 			if(!$scope.itemForm.convers_rate.$valid){
 				var errorObj = $scope.itemForm.convers_rate.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=convers_rate]', 'conversion rate');
 				error = true;
 			}
 			if(!$scope.itemForm.wholesale_rate.$valid){
 				var errorObj = $scope.itemForm.wholesale_rate.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=wholesale_rate]', 'whole sale rate');
 				error = true;
 			}
 			if(!$scope.itemForm.unit_price.$valid){
 				var errorObj = $scope.itemForm.unit_price.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=unit_price]', 'unit price');
 				error = true;
 			}
 			if(!$scope.itemForm.wholesale_value.$valid){
 				var errorObj = $scope.itemForm.wholesale_value.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=wholesale_value]', 'whole sale value');
 				error = true;
 			}
 			if(!$scope.itemForm.gross_weight.$valid){
 				var errorObj = $scope.itemForm.gross_weight.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=gross_weight]', 'gross weight');
 				error = true;
 			}
 			if(!$scope.itemForm.net_weight.$valid){
 				var errorObj = $scope.itemForm.net_weight.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'itemForm', 'input[name=net_weight]', 'net weight');
 				error = true;
 			}
 			
 			
 			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
 		}
		
		/**
 		 * Cancel action on shipment wizard screens
 		 */
 		$scope.cancelShipment = function(id, url){
 			var promise = exportOperationFactory.findShipment(id);
 			
 			//$scope.clearMessages();
 			
 			promise.then(function(result){
 				if(result.data.success == false){
 					$scope.errors.push({form:'', field: '', msg: result.data.message});
 					commonService.processMsg($scope.errors, 'error');
 					//alert(result.data.message);
 				}else{
 					commonService.setResponse(result.data.data); 
 	 				$location.path(url);
 				}
 			});
 		}
 		
 		$scope.deleteItem = function(itemId, ev){ 			
 			/*var cnf = confirm('Are you sure you want to delete?');
 			if(cnf){
 				blockScroll();
	 			exportOperationFactory.deleteItem(itemId)
	 				.then(function(response){
	 					$scope.items = $.grep($scope.items, function(element, index){return element.id == itemId}, true);
	 				});
 			}*/
 			
 			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  blockScroll();
		 			exportOperationFactory.deleteItem(itemId)
		 				.then(function(response){
		 					//$route.reload();
		 					if(response.data.success == true){
		 						$scope.shipment.lsStatus = response.data.data;
			 					$scope.itemList = $.grep($scope.itemList, function(element, index){return element.id == itemId}, true);
		 					}else{
		 						$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					}
		 					
		 				});
	       	   
	          	}, function() {
	          		//$scope.errors.push({form:'', field: '', msg: 'Error updating shipment status.'});
	          });
 		}
 		
 		$scope.copyItem = function(shipment, itemId){
 			exportOperationFactory.findItem(itemId)
				.then(function(response){
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting item.');
					}else{
						var item = response.data.data;
						if(shipment){
							$scope.addItem(shipment, null, null, true, item);
						}else{
							$scope.addItem($scope.shipment, null, null, true, item);
						}
						
					}
			});
 		}
 		
 		$scope.populateFromSession = function(title){
 			exportOperationFactory.getShipmentFromSession()
				.then(function(response){
					console.log(response);
					$scope.shipment = response.data.data;
					
					if(response.data.success == true){
		 	 			$scope.page_title = title;
		 	 			commonService.setResponse(null);
		 	 			refreshSelectpicker();
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
 		}
 		
 		//////// carton start ////////
 		
 		$scope.initAddCarton = function(){
			//load data from master
			$scope.shipment = {};
			var request = commonService.getResponse();
			$scope.shipment = request.shipment;
			
			if(commonService.isEmpty($scope.shipment) || !$scope.shipment.hasOwnProperty('id')){
				$location.path('/listItemCartons');
				return ;
			}
			
			var cartonNo = request.cartonNo;
			
			if(request.type == 'NEW'){
				$scope.carton = {};
				$scope.carton.shipmentId = $scope.shipment.id;
				$scope.carton.cartonNo = cartonNo;
				$scope.page_title = "Add Carton";
			}else if(request.type == 'COPY'){
				request.carton.id = null;
				$scope.carton = request.carton;
				$scope.carton.cartonNo = cartonNo;
				$scope.page_title = "Add Carton";
			}else{
				//$scope.item.itemNo = itemNo;
				
				exportOperationFactory.findCarton(request.cartonId)
					.then(function(response){
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting Carton.');
					}else{
						var carton = response.data.data;
						$scope.carton = carton;
					}
				});
				
				$scope.page_title = "Update Carton";
			}
						
			commonService.setResponse(null);
			refreshSelectpicker();
		}
 		
 		
 		$scope.saveCarton = function(type){ 			
			var carton = $scope.carton;			
			
			angular.element('.invalid-ele').removeClass('invalid-ele');
 			$scope.clearMessages();
 			
			var validated = $scope.validateCarton();
			
			if(validated){			
				exportOperationFactory.saveCarton(carton)
					.then(function(response){					
						if(response.data.success ==  false){							
							if(result.status == 417){
		 						var msgs = result.data.data.msgs;
		 						angular.forEach(msgs, function(index, value){
		 							$scope.errors.push({form:'', field: '', msg: index});
		 						});
		 					}else{
		 						$scope.errors.push({form:'', field: '', msg: response.data.message});
			 					commonService.processMsg($scope.errors, 'error');
		 					}							
							//$('html,body').animate({ scrollTop: 0 }, 'slow');
							return;
							//alert('Error saving carton : '+response.data.message);
						}else{					
							if(type=='continue'){
								exportOperationFactory.findShipment(carton.shipmentId)
									.then(function(shipment){
										commonService.setResponse(shipment.data.data);
										$location.path('/listItemCartons');
									});								
							}else{
								$scope.item = response.data.data;
								//$('html,body').animate({ scrollTop: 0 }, 'slow');
								$location.path('/createAddCarton');
							}
						}
				});
			}else{
				commonService.processMsg($scope.errors, 'error');
				//$('html,body').animate({ scrollTop: 0 }, 'slow');
				return;
			}
		}
 		
 		$scope.validateCarton = function(){
 			$scope.errors.length = 0;
 			
 			if(!$scope.cartonForm.pay_term.$valid){
 				var errorObj = $scope.cartonForm.pay_term.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=pay_term]', 'package no');
 				error = true;
 			}
 			/*if(!$scope.cartonForm.dimesion_uom.$valid){
 				var errorObj = $scope.cartonForm.dimesion_uom.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'select[name=dimesion_uom]', 'dimension uom');
 				error = true;
 			}*/
 			if(commonService.isEmpty($scope.carton.dimUom)){
 				$scope.errors.push({form:'billingForm', field: 'select[name=dimesion_uom]', msg: "Dimesion UOM is required."});
 			}
 			if(!$scope.cartonForm.carton_length.$valid){
 				var errorObj = $scope.cartonForm.carton_length.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=carton_length]', 'length');
 				error = true;
 			}
 			if(!$scope.cartonForm.carton_width.$valid){
 				var errorObj = $scope.cartonForm.carton_width.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=carton_width]', 'width');
 				error = true;
 			}
 			if(!$scope.cartonForm.carton_height.$valid){
 				var errorObj = $scope.cartonForm.carton_height.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=carton_height]', 'height');
 				error = true;
 			}
 			/*if(!$scope.cartonForm.weight_uom.$valid){
 				var errorObj = $scope.cartonForm.weight_uom.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'select[name=weight_uom]', 'weight uom');
 				error = true;
 			}*/
 			if(commonService.isEmpty($scope.carton.weightUom)){
 				$scope.errors.push({form:'billingForm', field: 'select[name=weight_uom]', msg: "Weight UOM is required."});
 			}
 			if(!$scope.cartonForm.fv_paid.$valid){
 				var errorObj = $scope.cartonForm.fv_paid.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=fv_paid]', 'gross weight');
 				error = true;
 			}
 			if(!$scope.cartonForm.cd_paid.$valid){
 				var errorObj = $scope.cartonForm.cd_paid.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'cartonForm', 'input[name=cd_paid]', 'net weight');
 				error = true;
 			}
 			
 			/*if(commonService.isEmpty($scope.carton.packageNo)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=pay_term]', msg: "Package no is required"});
 			}
 			if(commonService.isEmpty($scope.carton.dimUom)){
 				$scope.errors.push({form:'billingForm', field: 'select[name=dimesion_uom]', msg: "Dimesion UOM is required"});
 			}
 			if(commonService.isEmpty($scope.carton.length)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=carton_length]', msg: "Length is required"});
 			}
 			if(commonService.isEmpty($scope.carton.width)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=carton_width]', msg: "Width is required"});
 			}
 			if(commonService.isEmpty($scope.carton.height)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=carton_height]', msg: "Height is required"});
 			}
 			
 			if(commonService.isEmpty($scope.carton.weightUom)){
 				$scope.errors.push({form:'billingForm', field: 'select[name=weight_uom]', msg: "Weight UOM is required"});
 			}
 			if(commonService.isEmpty($scope.carton.grossWeight)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=fv_paid]', msg: "Gross weight is required"});
 			}
 			if(commonService.isEmpty($scope.carton.netWeight)){
 				$scope.errors.push({form:'billingForm', field: 'input[name=cd_paid]', msg: "Net weight is required"});
 			}*/
 			
 			/*angular.element("form[name=billingForm] input").removeClass('invalid-ele');
 			angular.element("form[name=billingForm] select").removeClass('invalid-ele');
 			angular.element("form[name=billingForm] textarea").removeClass('invalid-ele');*/
 			
 			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
 		}
 		
 		
		
		$scope.onCurrencySelect = function(item, model, label){
			$scope.item.currency = item.currencyCode;
		}
			
		
		
		$scope.onCountrySelect = function(item, model, label){
			$scope.item.coo = item.countryName;
		}

 		
 		$scope.addCarton = function(shipment, cartonId, cartonNo, copy, copyData){
 			
 			if(!shipment){
				shipment = $scope.shipment;
			}
 			
			var request = {
					shipment: shipment,
					cartonNo: cartonNo
			}
			
			exportOperationFactory.getNextCartonNo(shipment.id)
				.then(function(response){
					
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting next carton number.');
					}
					else{
						if(cartonNo == null && !copy){
							request.type = 'NEW';
							request.cartonNo = response.data.data;
						}else if(cartonNo == null && copy){
							request.type = 'COPY';
							request.carton = copyData;
							request.cartonNo = response.data.data;
						}else{
							request.cartonId = cartonId;
							//request.cartonId = getCartonIdFromNo($scope.cartonsList, cartonNo);
							request.type = 'UPDATE';
						}
						
						$location.path("createAddCarton");
						commonService.setResponse(request);
					}
				});
		}
 		
 		function getCartonIdFromNo(cartonList, cartonNo){
			var id;
			angular.forEach(cartonList, function(value, key){
			     if(value.cartonNo == cartonNo){
			    	 id = value.id;
			     }
			});
			return id;
		}
 		
 		$scope.deleteCarton = function(cartonId, ev){ 			
 			/*var cnf = confirm('Are you sure you want to delete?');
 			if(cnf){
 				blockScroll();
	 			exportOperationFactory.deleteCarton(cartonId)
	 				.then(function(response){
	 					$scope.cartons = $.grep($scope.cartons, function(element, index){return element.id == cartonId}, true);
	 				});
 			}*/
 			
 			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  blockScroll();
		 			exportOperationFactory.deleteCarton(cartonId)
		 				.then(function(response){
		 					//$route.reload();
		 					$scope.cartonsList = $.grep($scope.cartonsList, function(element, index){return element.id == cartonId}, true);
		 				});
	       	   
	          	}, function() {
	          		
	          });
 		}
 		
 		$scope.copyCarton = function(shipment, cartonId){
 			exportOperationFactory.findCarton(cartonId)
				.then(function(response){
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
						//alert('Error getting carton.');
					}else{
						var carton = response.data.data;
						
						if(shipment){
							$scope.addCarton(shipment, null, null, true, carton);
						}else{
							$scope.addCarton($scope.shipment, null, null, true, carton);
						}
						
					}
			});
 		}
 		
 		/*$scope.saveListItemCarton = function(type){
 			
 			if(type == 'save'){ 				
 				var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveListItemCarton"); 	
 				//$('html,body').animate({ scrollTop: 0 }, 'slow');
 			}else if(type == 'continue'){
 				
 				exportOperationFactory.getShipmentDocs($scope.shipment.id)
 					.then(function(response){
 						if(response.data.success == false){
 							$scope.errors.push({form:'', field: '', msg: response.data.message});
 		 					commonService.processMsg($scope.errors, 'error');
 							//alert('Error getting carton.');
 						}else{
 							var docs = response.data.data;
 							
 							commonService.setResponse({
 								shipment: $scope.shipment,
 								docs: docs
 							});
 							
 							$location.path("shipmentDocument");
 						}
 					}); 				
 				
 			} 			
 		}*/
 		
 		$scope.saveListItemCarton = function(type){
 			
 			var promise = exportOperationFactory.saveGenericShipment($scope.shipment, "saveListItemCarton");
 			
 			if(type == 'save'){		
 				 	
 				//$('html,body').animate({ scrollTop: 0 }, 'slow');
 			}else if(type == 'continue'){
 				$scope.clearMessages();
 				 				
 				promise.then(function(result){
 	 				
 	 				if(result.data == null){
 	 					$scope.errors.push({form:'', field: '', msg: 'Server encountered error, please try later.'});
 	 					return;
 	 				}
 	 				
 	 				commonService.setResponse(result.data.data);
 	 				if(result.data.success == false){				
 	 					//server side validation fail
 	 					if(result.status == 417){
 	 						var msgs = result.data.data.msgs;
 	 						angular.forEach(msgs, function(index, value){
 	 							$scope.errors.push({form:'', field: '', msg: index});
 	 						});
 	 					}else{
 	 						$scope.errors.push({form:'', field: '', msg: result.data.message}); 	 					
 	 					}
 	 					//commonService.processMsg($scope.errors, 'error');
 	 					//alert(result.data.message);
 	 				}else {
 	 					$location.path("bookingCustomFiling");
 	 				}
 	 				
 	 			});
 				 				
 				/*exportOperationFactory.getShipmentDocs($scope.shipment.id)
 					.then(function(response){
 						if(response.data.success == false){
 							$scope.errors.push({form:'', field: '', msg: response.data.message});
 		 					commonService.processMsg($scope.errors, 'error');
 							//alert('Error getting carton.');
 						}else{
 							var docs = response.data.data;
 							
 							commonService.setResponse({
 								shipment: $scope.shipment,
 								docs: docs
 							});
 							
 							$location.path("shipmentDocument");
 						}
 					}); */				
 				
 			}// if continue		
 		}
 		
 		$scope.onCountrySelectCodeItem = function(item, model, label, name, code){
			$scope.item[name] = item.countryName;
			$scope.item[code] = item.countryCode;
			//$scope.item.coo = item.countryName;
		}
 		
 		$scope.onProductSelect = function(item, model, label){
 			if(item){
 				$scope.item.partNo = item.partNo;
 				$scope.item.eccn = item.exportClass;			
 				$scope.item.partDescription = item.partDesc;
 				$scope.item.currency = item.currency;
 			}
 		}
 		
 		/*$scope.showLicenseScreenPopup = function(size, parentSelector){
				var parentElem = parentSelector ? 
						angular.element($document[0].querySelector('.modal-demo ' + '.modal-parent')) : undefined;
				
				commonService.setResponse($scope.shipment.id);
						
			    var uibModalInstance = $uibModal.open({
			      animation: true,
			      ariaLabelledBy: 'modal-title',
			      ariaDescribedBy: 'modal-body',
			      controller: 'lsResultPopCtrl',
			      templateUrl: 'views/export/resultsLicenseScreening.html',
			      windowTemplateUrl: 'views/export/resultsLicenseScreening.html',
			      size: size,
			      resolve: {
			    	 shipmentId: function(){
			    		 return angular.copy($scope.shipment.id);
			         },
			         exportOperationFactory: function(){
				          return exportOperationFactory;
			         },
			         commonService: function(){
				          return commonService;
		         }
			      }
			    });

//			    $scope.modalInstance.result.then(function (selectedItem) {
//			      //$ctrl.selected = selectedItem;
//			    }, function () {
//			      $log.info('Modal dismissed at: ' + new Date());
//			    });
 		}*/
 		
 		
}]);


function refreshSelectpicker(){
	setTimeout(function(){
			$('.selectpicker').selectpicker('refresh');
		}, 5);
}

