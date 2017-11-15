var isEdit = '';

dashboardApp.controller('masterCtlr', ['$scope', '$location', '$http', '$compile', '$uibModal', '$log', '$document', '$route', '$rootScope', 'commonService', 'masterFactory', 'commonFactory', '$mdDialog', 'masterService',  
                                       function($scope, $location, $http, $compile, $uibModal, $log, $document, $route, $rootScope, commonService, masterFactory, commonFactory, $mdDialog, masterService) {
	
		$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];
		
		$scope.initSearchExporter = function(){			
			$scope.clearMessages();
			$scope.exporter = {};
			
			$scope.exporter.sortBy = "expCode";
			$scope.exporter.direction = "ASC";
			
			commonService.populateSbu($scope, true, 'exporter', 'sbu');
			
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
		
		
		$scope.initCreateExporter = function(){
			$scope.clearMessages();
			
			
			if(isEdit == 'Edit'){
				var exp = commonService.getResponse();
				
				$scope.exporter = exp;
				commonService.setResponse(null);
				$scope.isEdit = true;
				commonService.populateSbu($scope, false, 'exporter', 'sbu');
				
			}else if(isEdit == 'Copy'){
				var exp = commonService.getResponse();
				
				$scope.exporter = exp;
				$scope.exporter.status = 'T';
				commonService.setResponse(null);
				
				commonService.populateSbu($scope, true, 'exporter', 'sbu');
				
			}else if(isEdit == 'New'){
				$scope.exporter = {};
				$scope.exporter.status = 'T';
				$scope.exporter.active = true;
				$scope.exporter.type = 'New';
				$scope.isEdit = false;
				
				commonService.populateSbu($scope, true, 'exporter', 'sbu');
				
			}else{
				$location.path('/createSearchExporter');
			}
			
		}
		
		$scope.onCountrySelectCode = function(item, model, label, name, code){
			$scope.exporter[name] = item.countryName;
			$scope.exporter[code] = item.countryCode;
			//$scope.item.coo = item.countryName;
		}
		
		$scope.onCountrySelectProductManuf = function(item, model, label, name, code, obj){
			obj[name] = item.countryName;
			obj[code] = item.countryCode;
		}
		
		$scope.saveExporter = function(type){
			
 			$scope.clearMessages();
 			
 			//client side validation
			var validated = $scope.validateExporter();
			
			if(validated){
				masterFactory.saveEntity($scope.exporter, 'saveExporterValue')
					.then(function(response){
						if(response.data.success == true){
							if(type == 'save'){
								$scope.exporter = response.data.data;
							}else if(type == 'return'){
								
								if(isEdit == 'Edit'){
									$scope.searchExporterNew('cancel');
								}else{
									$location.path('createSearchExporter');
								}
								
							}							
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
			}else{
				commonService.processMsg($scope.errors, 'error');
				return;
			}
		}
		
		$scope.validateExporter = function(){
			if(!$scope.exporterForm.exporterCode.$valid){
 				var errorObj = $scope.exporterForm.exporterCode.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'exporterForm', 'input[name=exporterCode]', 'exporter code');
 				error = true;
 			}
			
			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
		}
		
		$scope.searchExporter = function(reqType){
			$scope.clearMessages();
			$scope.exporter.reqType = reqType;
			masterFactory.searchRecords($scope.exporter, 'searchExporterValue')
				.then(function(response){
					
					if(response.status != 200){
						$scope.errors.push({form:'', field: '', msg: response.statusText});
	 					commonService.processMsg($scope.errors, 'error');
	 				}else if(response.data.success == false){
	 					$scope.errors.push({form:'', field: '', msg: response.statusText});
	 					commonService.processMsg($scope.errors, 'error');
	 				}else{	 					
	 					var index = $scope.shipments.indexOf(shipment);
			 			$scope.shipments.splice(index, 1);	 					
	 					//alert(response.statusText);
	 				}
					
					
					/*if(response.data.success == true){
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}*/
				});
		}
		
		
		$scope.searchExporterNew = function(reqType){
			
			$scope.exporter.reqType = reqType;
			
			$scope.clearMessages();
			masterFactory.searchRecordsCount($scope.exporter, 'searchExporterValueCount')
				.then(function(response){
					if(response.data.success == true){
						
						commonService.setResponse(response);
						$location.path('resultSearchExporter');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
			
			/*$scope.clearMessages();
			$scope.exporter.reqType = reqType;
			
			$scope.exporter.limit = 5;
			$scope.exporter.page = 1;
			$scope.exporter.filter = '';
			$scope.exporter.sortBy = 'expCode';
			
			masterFactory.searchRecordsCount($scope.exporter, 'searchExporterValueCount')
				.then(function(response){
					if(response.data.success == true){
						
						$scope.exporter.totalCount = response.data.data;
						commonService.setResponse($scope.exporter);
						$location.path('resultSearchExporter');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			}, function(error){
				$scope.errors.push({form:'', field: '', msg: error.status+" - "+error.statusText});
				commonService.processMsg($scope.errors, 'error');
			});*/
			
			
			/*masterFactory.searchRecords($scope.exporter, 'searchExporterValue')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchExporter');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});*/
		}
	
		$scope.initResultExporter = function(){
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchExporter');
			}
			
			$scope.exporterColumns = ['exporterCode',
						                'department',
						                'stateName',
						                'countryName',
						                'sbu',
						                'status',
						                'createdOn',
						                'delete'];
			
			
			//$scope.exporters = commonService.getResponse().data.data;
			commonService.setResponse(null);
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
							return response.data.data;
							//commonService.setResponse(response);
							//$location.path('resultSearchConsignee');
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
			});
        }
		
		$scope.cancelExporter = function(){
			if(isEdit == 'New'){
				$location.path('/createSearchExporter');
			}else{
				$scope.searchExporterNew('cancel');
			}
		}
			
		
		$scope.wlsAuditHistory = function(tableKey, type){

			masterService.wlsAuditHistory(tableKey, type);
			
			/*var config = {
				  controller: 'masterCtlr',
			      templateUrl: 'views/masters/resultDplAudit.html',
			      windowTemplateUrl: 'views/masters/resultDplAudit.html'
			}
			
			masterFactory.getDplAudit(tableKey, type)
				.then(function (response){						
					if(response.data.success == true){
						$scope.wplAudits = response.data.data;			
						//commonService.setResponse(null);
						
						config.resolve = {
							items: function(){
								return $scope.wplAudits;
							}
						}
						
						$scope.uibModalInstance = commonService.gtnModal(config);
						
						var response = {
								data: $scope.wplAudits,
								instance: $scope.uibModalInstance
						}
						
						if(type == 'EXPORTER'){
							response.code = $scope.exporter.exporterCode;
							response.type = 'Exporter ID';
						}else if(type == 'CONSIGNEE'){
							response.code = $scope.consignee.consigneeId;
							response.type = 'Consignee ID';
						}
						
						commonService.setResponse(response);
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						commonService.setResponse(null);
					}
			
			},function (error){
			   console.log(error);     
			});*/
			
		}
		$scope.initDplAuditPopup = function(){
			var response = commonService.getResponse();		
			$scope.modalInstance = response.instance;
			$scope.wplAudits = response.data;
			$scope.code = response.code;
			
			$scope.type = response.type;
			
			commonService.setResponse(null);
			$('.selectpicker').selectpicker();	
		}
		
		$scope.getDplReasons = function(statusCode, type){
			$scope.clearMessages();
				var config = {
					  controller: 'masterCtlr',
				      templateUrl: 'views/masters/resultDplAuditReason.html',
				      windowTemplateUrl: 'views/masters/resultDplAuditReason.html'
				}
				
				masterFactory.getDplAuditReason(statusCode)
					.then(function (response){
						if(response.data.success == true){
							$scope.wplReasons = response.data.data;			
							//commonService.setResponse(null);
							
							config.resolve = {
								items: function(){
									return $scope.wplReasons;
								}
							}
							
							$scope.uibModalInstance = commonService.gtnModal(config);
							
							var response = {};
							
							response = {
									data: $scope.wplReasons,
									instance: $scope.uibModalInstance,
									statusCode: statusCode,
									entityType: type
							}
							
							if(type == 'exporter'){
								response.code = $scope.exporter.exporterCode;
								response.entity = $scope.exporter;
							}else if(type == 'consignee'){
								response.code = $scope.consignee.consigneeId;
								response.entity = $scope.consignee;
							}							
							
							commonService.setResponse(response);
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
							commonService.processMsg($scope.errors, 'error');
							commonService.setResponse(null);
						}
				
				},function (error){
				   console.log(error);     
				});
		}
		$scope.initDplReasonPopup = function(){
			var response = commonService.getResponse();		
			$scope.modalInstance = response.instance;
			$scope.wplReasons = response.data;
			
			$scope.entityType = response.entityType;
			
			if($scope.entityType == 'exporter'){
				$scope.exporterCode = response.code;
				$scope.exporter = response.entity;
			}else if($scope.entityType == 'consignee'){
				$scope.consigneeId = response.code;
				$scope.consignee = response.entity;
			}
			
			$scope.statusCode = response.statusCode;
			$scope.code = response.code;
					
			$scope.selected = {};
			
			$scope.gtnClass = gtnClassGlobal;
			commonService.setResponse($scope.wplReasons);
			$('.selectpicker').selectpicker();	
		}
		
		$scope.updateWlsStatus = function(){
			
			var reason = $scope.otherReason;
			
			var entityType = $scope.entityType;
			var code = $scope.code;
			
			/*if(type == 'exporter'){
				code = $scope.exporterCode;
			}else if(type == 'consignee'){
				code = $scope.consigneeId;
			}*/
						
			var allReasons = commonService.getResponse();
			var newStatus = $scope.statusCode;
			
			$scope.selRecords = $.grep(allReasons, function( record ) {
		        return $scope.selected[ record.reasonCode ];
		    });
			var reasons = [];
			$.each($scope.selRecords, function(index){
				reasons.push($scope.selRecords[index].reasonCode);
			});
			
			var dplAuditReasonView = {
				code: code,
				otherReason: reason,
				reasonCodes: reasons,
				newStatus: newStatus,
				entityType: entityType
			};
			
			masterFactory.updateDplAuditStatus(dplAuditReasonView)
				.then(function(response){
					if(response.data.success == true){
						
						var response = response.data.data;
						
						//exporter is in different scope.						
						if(entityType == 'exporter'){
							$scope.exporter.status = response.newStatus;
						}else if(entityType == 'consignee'){
							$scope.consignee.status = response.newStatus;
						}
						
						commonService.setResponse(null);
						$scope.modalInstance.dismiss('cancel');
						//$route.reload();
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						
					}
			});
			
			
		}
		
		/*$scope.wlsAuditReasonHistory = function(){
			$scope.exporters = commonService.getResponse();
		}*/
		
		$scope.wlsAuditReasonHistory = function(tableKey, type){
			$scope.clearMessages();
			var config = {
				  controller: 'masterCtlr',
			      templateUrl: 'views/masters/resultDplAuditReasonHistory.html',
			      windowTemplateUrl: 'views/masters/resultDplAuditReasonHistory.html'
			}
			
			masterFactory.getDplAuditReasonHistory(tableKey, type)
				.then(function (response){						
					if(response.data.success == true){
						$scope.wplAuditsReasons = response.data.data;			
						//commonService.setResponse(null);
						
						config.resolve = {
							items: function(){
								return $scope.wplAuditsReasons;
							}
						}
						
						$scope.uibModalInstance = commonService.gtnModal(config);
						
						var response = {
								data: $scope.wplAuditsReasons,
								instance: $scope.uibModalInstance
						}
						
						if(type == 'EXPORTER'){
							response.code = $scope.exporter.exporterCode;
							response.type = 'Exporter ID';
						}else if(type == 'CONSIGNEE'){
							response.code = $scope.consignee.consigneeId;
							response.type = 'Consignee ID';
						}
						
						
						commonService.setResponse(response);
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
						commonService.processMsg($scope.errors, 'error');
						commonService.setResponse(null);
					}
			
			},function (error){
			   console.log(error);     
			});
			
		}
		
		
		$scope.initDplReasonHistoryPopup = function(){
			$scope.gtnClass = gtnClassGlobal;
			var response = commonService.getResponse();
			
			
			//$scope.exporterCode = response.exporterId;
			$scope.type = response.type;
			$scope.code = response.code;
			
			$scope.modalInstance = response.instance;
			$scope.wplReasonHistory = response.data;
			commonService.setResponse(null);
		}
		
		$scope.cancel = function () {
 			$scope.modalInstance.dismiss('cancel');
 		};
		
		$scope.editExporter = function(expCode, type){
			
			if(type == "New"){
				isEdit = 'New';
				$scope.reqFrom = 'newExp';
				$location.path('createAddExporter');
			}else{
				masterFactory.findExporter(expCode)
				.then(function(response){
					$scope.reqFrom = 'editExp';
					if(response.data.success == true){						
						if(type == 'Copy'){
							isEdit = 'Copy';
							var exporter = response.data.data;
							exporter.exporterCode = '';
							exporter.type = 'New';
							commonService.setResponse(exporter);
							$location.path('createAddExporter');
						}else{
							isEdit = 'Edit';
							commonService.setResponse(response.data.data);
							$location.path('createAddExporter');
						}					
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
			}
			
			
		}
		
		$scope.deleteExporter = function(expCode, ev){
			/*var cnf = confirm('Are you sure you want to delete?');
			if(cnf){
				masterFactory.deleteExporter(expCode)
					.then(function(response){
						if(response.data.success == true){
							$scope.exporters = $.grep($scope.exporters, function(element, index){return element.expCode == expCode}, true);
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
				});
			}*/
			
			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  masterFactory.deleteExporter(expCode)
					.then(function(response){
						if(response.data.success == true){
							//$scope.exporters = $.grep($scope.exporters, function(element, index){return element.expCode == expCode}, true);
						
							commonService.setResponse(response);
							$route.reload();
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
	       	   
	          	}, function() {
	          		
	          });
		
		}
		
		$scope.initSearchProducts = function(){
			$scope.product = {};
			$scope.product.manufacture = {};
			$scope.product.sortBy = "PART_NO";
			$scope.product.direction = "DESC";
			
			commonService.populateSbu($scope, true, 'product', 'sbu');
		}
		
		$scope.editProduct = function(partNo, type){
			$scope.clearMessages();
			
			if(type == "New"){
				isEdit = 'New';
				$location.path('createAddProduct');
			}else{
				masterFactory.findEntity(partNo, 'getProduct')
					.then(function(response){
						if(response.data.success == true){						
							if(type == 'Copy'){
								isEdit = 'Copy';
								var exporter = response.data.data;
								exporter.exporterCode = '';
								exporter.type = 'New';
								commonService.setResponse(exporter);
								$location.path('createAddProduct');
							}else{
								isEdit = 'Edit';
								commonService.setResponse(response.data.data);
								$location.path('createAddProduct');
							}					
							
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
			}
		}
		
		$scope.initCreateProduct = function(){
			$scope.clearMessages();
			
			if(isEdit == 'New'){
				$scope.product = {};
				$scope.product.hazmat = false;
				$scope.product.reqType = 'New';
				
				commonService.populateSbu($scope, true, 'product', 'sbu');
			}else if(isEdit == 'Edit'){
				$scope.product = commonService.getResponse();
				$scope.product.reqType = 'Edit';
				commonService.setResponse(null);
				
				commonService.populateSbu($scope, false, 'product', 'sbu');
			}else{
				$location.path('/createSearchProduct');
			}
		}
		
		$scope.saveProduct = function(type){
			$scope.clearMessages();
			
			var validated = $scope.validateProduct();
			
			if(validated){				
				masterFactory.saveEntity($scope.product, 'saveProduct')
					.then(function(response){
						if(response.data.success == true){
							if(type == 'save'){
								isEdit = 'Edit';
								var product = response.data.data;
								
								commonService.setResponse(product);
								$route.reload();
								//$location.path('createAddExporter');
							}else{							
								
								if(isEdit == 'Edit'){
									$scope.searchProducts('cancel');
								}else{
									$location.path('createSearchProduct');
								}							
								
								//$location.path('createSearchProduct');
								isEdit = 'New';
							}					
							
						}else{
							if(response.status == 417){
		 						var msgs = response.data.data.msgs;
		 						angular.forEach(msgs, function(index, value){
		 							$scope.errors.push({form:'', field: '', msg: index});
		 						});
		 						commonService.processMsg($scope.errors, 'error');
		 					}else{
		 						$scope.errors.push({form:'', field: '', msg: response.data.message});
			 					commonService.processMsg($scope.errors, 'error');
		 					}							
						}
				});				
			}else{
				commonService.processMsg($scope.errors, 'error');
				return;
			}
		}
		
		$scope.validateProduct = function(){
			if(!$scope.productForm.partNo.$valid){
 				var errorObj = $scope.productForm.partNo.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productForm', 'input[name=partNo]', 'part no');
 				error = true;
 			}
			if(!$scope.productForm.partDesc.$valid){
 				var errorObj = $scope.productForm.partDesc.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productForm', 'textarea[name=partDesc]', 'part description');
 				error = true;
 			}
			if(!$scope.productForm.unitPrice.$valid){
 				var errorObj = $scope.productForm.unitPrice.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productForm', 'input[name=unitPrice]', 'unit price');
 				error = true;
 			}
			if(!$scope.productForm.netWeight.$valid){
 				var errorObj = $scope.productForm.netWeight.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productForm', 'input[name=netWeight]', 'net weight');
 				error = true;
 			}
			
			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
		}
		
		$scope.updateHazmat = function(){
			if(!$scope.product.hazmat){
				$scope.product.hazmatCode = '';
				$scope.product.hazmatDesc = '';
				$scope.product.flashPointTemp = '';
				$scope.product.hazmatContactName = '';
				$scope.product.hazmatContactPhone = '';
			}
		}
		
		/*$scope.searchProducts = function(reqType){
			$scope.clearMessages();
			
			$scope.product.reqNavType = reqType;
			
			masterFactory.searchRecords($scope.product, 'searchProductsValue')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchProducts');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
		}
		
		$scope.initResultProducts = function(){
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchProduct');
			}
			$scope.products = commonService.getResponse().data.data;
			commonService.aetResponse(null);
		}*/
		
		$scope.searchProducts = function(reqType){
			$scope.clearMessages();
			
			$scope.product.reqNavType = reqType;
			
			/*masterFactory.searchRecords($scope.product, 'searchProductsValue')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchProducts');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});*/
			
			masterFactory.searchRecordsCount($scope.product, 'searchProductCount')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchProducts');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
			
		}
		
		$scope.initResultProducts = function(){
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchProduct');
			}
			$scope.productColumns = ['partNo',
						                'exportClass',
						                'importClass',
						                'modelNo',
						                'hazmat',
						                'sbu',
						                'skuNo',
						                'createdOn',
						                'delete'];
		            
			
			//$scope.consignees = commonService.getResponse().data.data;
			//$scope.getConsigneeList($scope.startPage, $scope.pageSize);
			commonService.setResponse(null);
		}
		
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
		
		$scope.cancelProduct = function(){
			//$scope.searchProducts('cancel');
			
			if(isEdit == 'New'){
				$location.path('/createSearchProduct');
			}else{
				$scope.searchProducts('cancel');
			}
		}
		
		$scope.deleteProduct = function(partNo, ev){
			//var cnf = confirm('Are you sure you want to delete?');
			
		  var confirm = $scope.mdConfirm(ev);

          $mdDialog.show(confirm).then(function() {
       	   
        	  masterFactory.deletEntity(partNo, 'deleteProduct')
				.then(function(response){
					if(response.data.success == true){
						//$scope.products = $.grep($scope.products, function(element, index){return element.partNo == partNo}, true);
						
						commonService.setResponse(response);
						$route.reload();
						
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
       	   
       	   //$scope.status = 'You decided to get rid of your debt.';
          }, function() {
       	   //alert('canceled1');
              //$scope.status = 'You decided to keep your debt.';
          });
			
			
			/*if(cnf){
				masterFactory.deletEntity(partNo, 'deleteProduct')
					.then(function(response){
						if(response.data.success == true){
							$scope.products = $.grep($scope.products, function(element, index){return element.partNo == partNo}, true);
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
				});
			}*/
		}
		
		
		$scope.productManufacturer = function(partNo){
			$scope.clearMessages();
			
			masterFactory.getProductManufacture(partNo)
				.then(function(response){
					if(response.data.success == true){
						
						
						var data = {
							data: response.data.data,
							partNo: partNo
						}
						commonService.setResponse(data);
						$location.path('resultSearchProductManuf');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
			
		}
		
		$scope.initResultProductManuf = function(){
			$scope.clearMessages();
			
			$scope.product = {};
			
			var data = commonService.getResponse();
			
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchProduct');
			}
			
			$scope.productManufacturerColumns = ['partNo',
									                'itemNo',
									                'manufactureId',
									                'name',
									                'cooName',
									                'contactName',
									                'createdOn',
									                'delete'];
						
			localStorage.setItem('partNo', data.partNo);
			
			$scope.product.partNo = data.partNo;
			//console.log(data.data);
			$scope.productManufacturerList = data.data;
			
			//$scope.manufs = data.data;
			
			commonService.setResponse(null);
		}
		
		$scope.editProductManuf = function(partNo, itemNo, type){
			$scope.clearMessages();
			
			if(type == 'New'){
				isEdit = 'New';				
				masterFactory.findEntity(partNo, 'getProductManufactureItemNo')
					.then(function(response){
						if(response.data.success == true){
							var data = {
								itemNo: response.data.data,
								partNo: partNo
							}
							commonService.setResponse(data);
							$location.path('createAddProductManufacture');
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
			}else{
				
				partNo = localStorage.getItem('partNo');
				
				var obj = {
						partNo: partNo,
						itemNo: parseInt(itemNo)
					}
				
				masterFactory.findEntityObj(obj, 'getProductManufactureObj')
					.then(function(response){
						if(response.data.success == true){						
							if(type == 'Copy'){
								isEdit = 'Copy';
								var exporter = response.data.data;
								exporter.exporterCode = '';
								exporter.type = 'New';
								commonService.setResponse(exporter);
								$location.path('createAddProduct');
							}else{
								isEdit = 'Edit';
								commonService.setResponse(response.data.data);
								$location.path('createAddProductManufacture');
							}					
							
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
				
			}
		}
		
		$scope.initCreateProductManufacture = function(){						
			if(isEdit == 'New'){
				var data = commonService.getResponse();
				$scope.product = {};
				$scope.product.partNo = data.partNo;
				
				$scope.pm = {};
				$scope.pm.partNo = data.partNo;
				$scope.pm.itemNo = data.itemNo;
				$scope.pm.reqType = 'New';
				commonService.setResponse(null);
			}else if(isEdit == 'Edit'){
				$scope.pm = commonService.getResponse();
				$scope.pm.reqType = 'Edit';
				
				$scope.product = {};
				$scope.product.partNo = $scope.pm.partNo;
				commonService.setResponse(null);
			}else{
				$location.path('/createSearchProduct');
			}		
		}
		
		$scope.saveProductManufacture = function(type){
			$scope.clearMessages();
			
			var validated = $scope.validateProductManufacturer();
			
			if(validated){
				masterFactory.saveEntity($scope.pm, 'saveProductManufacture')
					.then(function(response){
						if(response.data.success == true){
							if(type == 'save'){
								isEdit = 'Edit';
								var productManufacture = response.data.data;
								
								commonService.setResponse(productManufacture);
								$route.reload();
								//$location.path('createAddExporter');
							}else{
								$scope.productManufacturer($scope.pm.partNo)
								isEdit = 'New';
							}
							
						}else{
							//server side validation messages
							if(response.status == 417){
		 						var msgs = response.data.data.msgs;
		 						angular.forEach(msgs, function(index, value){
		 							$scope.errors.push({form:'', field: '', msg: index});
		 						});
		 						commonService.processMsg($scope.errors, 'error');
		 					}else{
		 						$scope.errors.push({form:'', field: '', msg: response.data.message});
			 					commonService.processMsg($scope.errors, 'error');
		 					}							
						}
				});				
			}else{
				commonService.processMsg($scope.errors, 'error');
				return;
			}
		}
		
		
		$scope.validateProductManufacturer = function(){
			if(!$scope.productManufactureForm.partNo.$valid){
 				var errorObj = $scope.productManufactureForm.partNo.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=partNo]', 'part no');
 				error = true;
 			}
			if(!$scope.productManufactureForm.itemNo.$valid){
 				var errorObj = $scope.productManufactureForm.itemNo.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=itemNo]', 'item no');
 				error = true;
 			}
			if(!$scope.productManufactureForm.manufactureId.$valid){
 				var errorObj = $scope.productManufactureForm.manufactureId.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=manufactureId]', 'manufacture id');
 				error = true;
 			}
			if(!$scope.productManufactureForm.email.$valid){
 				var errorObj = $scope.productManufactureForm.email.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=email]', 'email');
 				error = true;
 			}
			if(!$scope.productManufactureForm.assistValue.$valid){
 				var errorObj = $scope.productManufactureForm.assistValue.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=assistValue]', 'assist value');
 				error = true;
 			}
			if(!$scope.productManufactureForm.assistBalance.$valid){
 				var errorObj = $scope.productManufactureForm.assistBalance.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'productManufactureForm', 'input[name=assistBalance]', 'assist balance');
 				error = true;
 			}
			
			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
		}
		
		
		$scope.deleteProductManuf = function(partNo, itemNo, ev){
			/*var cnf = confirm('Are you sure you want to delete?');
			if(cnf){
				
				var obj = {
					partNo: partNo,
					itemNo: itemNo
				}
				
				masterFactory.deleteEntityObj(obj, 'deleteProductManufacture')
					.then(function(response){
						if(response.data.success == true){
							console.log($scope.manufs);
							$scope.manufs = $.grep($scope.manufs, function(element, index){return (element.partNo == partNo && element.itemNo == itemNo)}, true);
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
				});
			}*/
			
			var confirm = $scope.mdConfirm(ev);
			partNo = localStorage.getItem('partNo');
			
	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  var obj = {
	  					partNo: partNo,
	  					itemNo: itemNo
	  				}
	  				
	  				masterFactory.deleteEntityObj(obj, 'deleteProductManufacture')
	  					.then(function(response){
	  						if(response.data.success == true){
	  							console.log($scope.manufs);
	  						
	  							//$route.reload();
	  							
	  							$scope.productManufacturerList = $.grep($scope.productManufacturerList, function(element, index){return (element.partNo == partNo && element.itemNo == itemNo)}, true);
	  						
	  						}else{
	  							$scope.errors.push({form:'', field: '', msg: response.data.message});
	  		 					commonService.processMsg($scope.errors, 'error');
	  						}
	  				});
	       	   
	          	}, function() {
	          		
	          });
		}
		
		/********
		 * New consignee code for lib and server side pagination
		 */
		
		
		
		
		
		
		
		
		
		$scope.initSearchConsignee = function(){
			$scope.consignee = {};
			
			$scope.consignee.sortBy = 'consigneeId';
			$scope.consignee.direction = 'DESC';
			
			commonService.populateSbu($scope, true, 'consignee', 'sbuCode');
			
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
		
		/*$scope.searchConsignee = function(reqType){
			
			$scope.consignee.reqNavType = reqType;
			
			$scope.clearMessages();
			masterFactory.searchRecords($scope.consignee, 'searchConsigneeValue')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchConsignee');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
		}
		
		$scope.initResultConsignee = function(){
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchConsignee');
			}
			$scope.consignees = commonService.getResponse().data.data;
			commonService.setResponse(null);
		}
		
		$scope.cancelConsignee = function(){
			if(isEdit == 'New'){
				$location.path('/createSearchConsignee');
			}else{
				$scope.searchConsignee('cancel');
			}			
		}*/
		
		/********
		 * New consignee code for lib and server side pagination
		 */
		
		$scope.searchConsignee = function(reqType){
			
			$scope.consignee.reqNavType = reqType;
			
			$scope.clearMessages();
			masterFactory.searchRecordsCount($scope.consignee, 'searchConsigneeCount')
				.then(function(response){
					if(response.data.success == true){
						commonService.setResponse(response);
						$location.path('resultSearchConsignee');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				});
		}
		
		$scope.initResultConsignee = function(){
			if($.isEmptyObject(commonService.getResponse())){
				$location.path('/createSearchConsignee');
			}
			
			$scope.consigneeColumns = ['consigneeId',
						                'consigneeName',
						                'consigneeCountryName',
						                'salesPerson',
						                'typeOfConsignee',
						                'sbuCode',
						                'status',
						                'delete'];
		            
			
			//$scope.consignees = commonService.getResponse().data.data;
			//$scope.getConsigneeList($scope.startPage, $scope.pageSize);
			commonService.setResponse(null);
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
							return response.data.data;
							//commonService.setResponse(response);
							//$location.path('resultSearchConsignee');
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
			});

            /*return $http.post('https://api.nutritionix.com/v1_1/search', {
                    'appId':'a03ba45f',
                    'appKey':'b4c78c1472425c13f9ce0e5e45aa1e16',
                    'offset': offset,
                    'limit':pageSize,
                    'query': '*',
                    'fields': ['*'],
                    'sort':{
                        'field':'fields.consigneeId',
                        'order':'desc'
                    }
                }).then(function(result){
                    return {
                        results: result.data.hits,
                        totalResultCount: result.data.total
                    }
                });*/
        }
		
		$scope.cancelConsignee = function(){
			if(isEdit == 'New'){
				$location.path('/createSearchConsignee');
			}else{
				$scope.searchConsignee('cancel');
			}			
		}
		
		/********
		 * New consignee code for lib and server side pagination END
		 */
		
		$scope.editConsignee = function(consigneeId, type){
			
			if(type == "New"){
				isEdit = 'New';
				$location.path('createAddConsignee');
			}else{
				masterFactory.findEntity(consigneeId, 'findConsignee')
					.then(function(response){
						if(response.data.success == true){						
							if(type == 'Copy'){
								isEdit = 'Copy';
								var exporter = response.data.data;
								exporter.exporterCode = '';
								exporter.type = 'New';
								commonService.setResponse(exporter);
								$location.path('createAddConsignee');
							}else{
								isEdit = 'Edit';
								commonService.setResponse(response.data.data);
								$location.path('createAddConsignee');
							}					
							
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
			}
		}
		
		
		$scope.initCreateConsignee = function(){
			$scope.clearMessages();
			
			if(isEdit == 'Edit'){
				var consignee = commonService.getResponse();
				
				$scope.consignee = consignee;
				commonService.setResponse(null);
				commonService.populateSbu($scope, false, 'consignee', 'sbuCode');
				$scope.isEdit = true;
			}else if(isEdit == 'Copy'){
				var exp = commonService.getResponse();
				
				$scope.exporter = exp;
				$scope.exporter.status = 'T';
				commonService.populateSbu($scope, true, 'consignee', 'sbuCode');
				commonService.setResponse(null);
			}else if(isEdit == 'New'){
				$scope.consignee = {};
				$scope.consignee.status = 'T';
				$scope.consignee.active = '1';
				$scope.consignee.reqType = 'New';
				$scope.consignee.assurance = '';
				commonService.populateSbu($scope, true, 'consignee', 'sbuCode');
				$scope.consignee.aviationMilNucEndUse = '';
				$scope.consignee.meuser = '';
				
				$scope.consignee.type = '';
				
				$scope.isEdit = false;
			}else{
				$location.path('/createSearchConsignee');
			}
			
		}
		
		$scope.saveConsignee = function(type){
			$scope.clearMessages();
 			
 			//client side validation
			var validated = $scope.validateConsignee();
			
			if(validated){
				masterFactory.saveEntity($scope.consignee, 'saveConsigneeValue')
					.then(function(response){
						if(response.data.success == true){							
							if(type == 'save'){
								$scope.consignee = response.data.data;
							}else if(type == 'return'){
								
								if(isEdit == 'Edit'){
									$scope.searchConsignee('cancel');
								}else{
									$location.path('createSearchConsignee');
								}								
								
							}							
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
			}else{
				commonService.processMsg($scope.errors, 'error');
				return;
			}
		}
		
		$scope.validateConsignee = function(){
			if(!$scope.consigneeForm.consigneeId.$valid){
 				var errorObj = $scope.consigneeForm.consigneeId.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'consigneeForm', 'input[name=consigneeId]', 'consignee id');
 				error = true;
 			}
			if(!$scope.consigneeForm.consigneeName.$valid){
 				var errorObj = $scope.consigneeForm.consigneeName.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'consigneeForm', 'input[name=consigneeName]', 'consignee name');
 				error = true;
 			}
			if(!$scope.consigneeForm.email.$valid){
 				var errorObj = $scope.consigneeForm.email.$error;
 				commonService.processNgError(errorObj, $scope.errors, 'consigneeForm', 'input[name=email]', 'consignee email');
 				error = true;
 			}
			
			if($scope.errors.length > 0){
 				return false;
 			}else{ 				
 				return true;
 			}
		}
		
		
		$scope.deleteConsignee = function(consigneeId, ev){
			/*var cnf = confirm('Are you sure you want to delete?');
			if(cnf){
				masterFactory.deletEntity(consigneeId, 'deleteConsignee')
					.then(function(response){
						if(response.data.success == true){
							$scope.consignees = $.grep($scope.consignees, function(element, index){return element.consigneeId == consigneeId}, true);
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
				});
			}*/
			
			var confirm = $scope.mdConfirm(ev);

	          $mdDialog.show(confirm).then(function() {
	       	   
	        	  masterFactory.deletEntity(consigneeId, 'deleteConsignee')
					.then(function(response){
						if(response.data.success == true){
							//$scope.consignees = $.grep($scope.consignees, function(element, index){return element.consigneeId == consigneeId}, true);
							//$scope.getConsigneeList(1, 5);
							commonService.setResponse(response);
							$route.reload();
						}else{
							$scope.errors.push({form:'', field: '', msg: response.data.message});
		 					commonService.processMsg($scope.errors, 'error');
						}
					});
	       	   
	          	}, function() {
	          		
	          });
		
		}
		
		
		
		/*$scope.copyExporter = function(expCode){
			masterFactory.findExporter(expCode)
				.then(function(response){
					if(response.data.success == true){
						isEdit = 'Copy';
						var exporter = response.data.data;
						exporter.exporterCode = '';
						exporter.type = 'New';
						commonService.setResponse(exporter);
						$location.path('createAddExporter');
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
			});
		}*/
		
		$scope.clearMessages = function(){
 			$scope.errors.length = 0;
 			$scope.warnings.length = 0;
 			$scope.infos.length = 0;
 			angular.element('.invalid-ele').removeClass('invalid-ele');
 		}
}]);


/**
 * Exporter search results controller
 */
dashboardApp.controller('exporterResultsController', ['$scope', '$location', '$http', '$compile', '$uibModal', '$log', '$document', '$route', '$rootScope', 'commonService', 'masterFactory', 'commonFactory', '$mdDialog', 'masterService',  
                                       function($scope, $location, $http, $compile, $uibModal, $log, $document, $route, $rootScope, commonService, masterFactory, commonFactory, $mdDialog, masterService) {
		'use strict';
	
		$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];
		  
		  var bookmark;
		  
		  $scope.exportersSelected = [];
		  
		  $scope.filter = {
		    options: {
		      debounce: 100
		    }
		  };

		  $scope.exporter = commonService.getResponse();
		  
		  /*$scope.exporter = {
		    filter: '',
		    limit: '5',
		    order: 'exporterCode',
		    page: 1
		  };*/

		   $scope.exporterPagination = function(){
			  $scope.exporterPromise = masterFactory.searchRecords($scope.exporter, 'searchExporterValue')
				.then(function(response){
					if(response.data.success == true){
						/*commonService.setResponse(response);
						$location.path('resultSearchExporter');*/
						$scope.exporters = response.data.data;
						//$scope.exporter.totalCount = 187;
					}else{
						$scope.errors.push({form:'', field: '', msg: response.data.message});
	 					commonService.processMsg($scope.errors, 'error');
					}
				}).$promise;
		  }
			
		  $scope.$watch('exporter.filter', function (newValue, oldValue) {
			    if(!oldValue) {
			      bookmark = $scope.exporter.page;
			    }
			    
			    if(newValue !== oldValue) {
			      $scope.exporter.page = 1;
			    }
			    
			    if(!newValue) {
			      $scope.exporter.page = bookmark;
			    }
			    
			    $scope.exporterPagination();
	    });
		
		  
		  
		  /*function success(desserts) {
		    $scope.desserts = desserts;
		  }*/
		  
		  /*$scope.addItem = function (event) {
		    $mdDialog.show({
		      clickOutsideToClose: true,
		      controller: 'addItemController',
		      controllerAs: 'ctrl',
		      focusOnOpen: false,
		      targetEvent: event,
		      templateUrl: 'templates/add-item-dialog.html',
		    }).then($scope.getDesserts);
		  };*/
		  
		  /*$scope.delete = function (event) {
		    $mdDialog.show({
		      clickOutsideToClose: true,
		      controller: 'deleteController',
		      controllerAs: 'ctrl',
		      focusOnOpen: false,
		      targetEvent: event,
		      locals: { desserts: $scope.selected },
		      templateUrl: 'templates/delete-dialog.html',
		    }).then($scope.getDesserts);
		  };*/
		  
		  /*$scope.getDesserts = function () {
		    $scope.promise = $nutrition.desserts.get($scope.query, success).$promise;
		  };*/
		  
		  $scope.removeFilter = function () {
		    $scope.filter.show = false;
		    $scope.exporter.filter = '';
		    
		    if($scope.filter.form.$dirty) {
		      $scope.filter.form.$setPristine();
		    }
		  };
		  
		  /*$scope.$watch('exporter.filter', function (newValue, oldValue) {
			    if(!oldValue) {
			      bookmark = $scope.exporter.page;
			    }
			    
			    if(newValue !== oldValue) {
			      $scope.exporter.page = 1;
			    }
			    
			    if(!newValue) {
			      $scope.exporter.page = bookmark;
			    }
			    
			    $scope.exporterPagination();
		  });*/
		
}]);