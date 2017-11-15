'use strict';
dashboardApp.service('masterService', ['$http', '$window', '$document', '$uibModal', 'masterFactory', 'commonService',
                                       function ($http, $window, $document, $uibModal, masterFactory, commonService) {
    var response = {};
    var shipment = {};
    var service = this;
    var errors = [];
    
    this.wlsAuditHistory = function(tableKey, type){

		var config = {
			  controller: 'masterCtlr',
		      templateUrl: 'views/masters/resultDplAudit.html',
		      windowTemplateUrl: 'views/masters/resultDplAudit.html'
		}
		
		masterFactory.getDplAudit(tableKey, type)
			.then(function (response){						
				if(response.data.success == true){
					//$scope.wplAudits = response.data.data;			
					//commonService.setResponse(null);
					
					config.resolve = {
						items: function(){
							return response.data.data;
						}
					}
					
					var uibModalInstance = commonService.gtnModal(config);
					
					var response = {
							data: response.data.data,
							instance: uibModalInstance
					}
					
					if(type == 'EXPORTER'){
						response.code = tableKey;
						response.type = 'Exporter ID';
					}else if(type == 'CONSIGNEE'){
						response.code = tableKey;
						response.type = 'Consignee ID';
					}
					
					commonService.setResponse(response);
				}else{
					errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg(errors, 'error');
					commonService.setResponse(null);
				}
		
		},function (error){
		   console.log(error);     
		});
		
	}
    
    
}]);

