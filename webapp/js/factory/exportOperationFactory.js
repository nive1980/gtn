dashboardApp.factory('exportOperationFactory', ['$http', '$location', 'commonService', '$resource', function($http, $location, commonService, $resource) {
	
	var factory = {};
	
	factory.saveShipment = function(shipment) {
		
		return $http({
		    url: "saveShipment",
		    responseType:"json",
		    method: "POST",
		    data: shipment,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response			
		}, function(error){
			console.log(error);
			return error;
		});
	
	}
	
	factory.getAllShipments = function(){
		return $http({
		    url: "getShipments",
		    responseType:"json",
		    method: "POST",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
			//commonService.setResponse(response);
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}

	factory.removeShipment = function(shipmentId){
		return $http({
		    url: "removeShipment?shipmentId="+shipmentId,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.searchShipment = function(shipmentView){
		return $http({
		    url: "searchShipments",
		    responseType:"json",
		    method: "POST",
		    data: shipmentView,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.findShipment = function(id, mode){
		return $http({
		    url: "findShipment?id="+id+"&mode="+mode,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.findShipmentByNo = function(shipmentNo, mode){
		return $http({
		    url: "findShipmentByNo?shipmentNo="+shipmentNo+"&mode="+mode,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.cancelShipment = function(id){
		return $http({
		    url: "cancelShipment?id="+id,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.getShipmentFromSession = function(){
		return $http({
		    url: "getShipmentFromSession",
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	
	factory.saveGenericShipment = function(shipment, url) {
		return $http({
		    url: url,
		    responseType:"json",
		    method: "POST",
		    data: shipment,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	
	factory.getShipmentItems = function(id) {
		return $http({
		    url: "getShipmentItems?id="+id,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.getShipmentCartons = function(id) {
		return $http({
		    url: "getShipmentCartons?id="+id,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	
	factory.saveItem = function(item) {
		return $http({
		    url: 'saveItem',
		    responseType:"json",
		    method: "POST",
		    data: item,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getNextItemNo = function(shipmentId){
		return $http({
		    url: 'getNextItemNo?shipmentId='+shipmentId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.deleteItem = function(itemId){
		return $http({
		    url: 'deleteItem?itemId='+itemId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.findItem = function(itemId){
		return $http({
		    url: 'findItem?itemId='+itemId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	//////// carton start /////////
	
	factory.getNextCartonNo = function(shipmentId){
		return $http({
		    url: 'getNextCartonNo?shipmentId='+shipmentId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}	
	
	factory.findCarton = function(cartonId){
		return $http({
		    url: 'findCarton?cartonId='+cartonId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.deleteCarton = function(cartonId){
		return $http({
		    url: 'deleteCarton?cartonId='+cartonId,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.saveCarton = function(carton) {
		return $http({
		    url: 'saveCarton',
		    responseType:"json",
		    method: "POST",
		    data: carton,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getShipmentDocs = function(id) {
		return $http({
		    url: "getShipmentDocs?id="+id,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.uploadFileToUrl = function(file, uploadUrl, shipmentId){
        var fd = new FormData();
        fd.append('file', file);
        fd.append('shipmentId', shipmentId);     
        
        return $http({
		    url: uploadUrl,
		    responseType:"json",
		    method: "POST",
		    data: fd,
		    transformRequest: angular.identity,
		    headers: {
		        "Content-Type": undefined
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
        
     }
		
	factory.removeDocument = function(id){
		return $http({
		    url: 'removeDocument?id='+id,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.updateFileType = function(id, fileType){
		return $http({
		    url: 'updateDocFileType?id='+id+'&fileType='+fileType,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getDropDown = function(countryCode) {
		return $http({
		    url: 'getDropDown?countryCode='+countryCode,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getStates = function(countryCode, query) {
		return $http({
		    url: 'getStates?countryCode='+countryCode+'&qry='+query,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.validateShipment = function(shipmentId){
		return $http({
			url: 'validateShipment?shipmentId='+shipmentId,
			responseType: 'json',
			method: 'GET',
			header: {
				"Content-Type": "application/json"
			}
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getCurrency = function(req){
		return $http({
			url: 'getCurrency?req='+req,
			responseType: 'json',
			method: 'GET',
			header: {
				"Content-Type": "application/json"
			}
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getMot = function(req){
		return $http({
			url: 'getMot?req='+req,
			responseType: 'json',
			method: 'GET',
			header: {
				"Content-Type": "application/json"
			}
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getShipmentAesStatus = function(id) {
		return $http({
		    url: "getShipmentAesStatus?id="+id,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	}
	
	factory.createShipmentInEase = function(id){
		return $http({
		    url: 'createShipmentInEase?id='+id,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getShipmentAesStatusHistory = function(id){
		return $http({
		    url: 'getShipmentAesStatusHistory?id='+id,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getShipmentReport = function(shipId, type){
		return $http({
		    url: 'getShipmentReport?shipId='+shipId+'&type='+type,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.sendDocEmail = function(docObj){
		return $http({
		    url: 'sendDocEmail',
		    responseType:"json",
		    method: "POST",
		    data: docObj,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.performWlsScreening = function(shipmentId, type){
		return $http({
		    url: 'performWlsScreening?shipmentId='+shipmentId+'&type='+type,
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.updateShipmentDplAuditStatus = function(obj){
		return $http({
		    url: 'updateShipmentDplAuditStatus',
		    responseType:"json",
		    method: "POST",
		    data: obj,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	
	/*factory.downloadDocument = function(id){
		return $http({
		    url: 'downloadDocument?id='+id,
		    method: "POST"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}*/
	
	return factory;
	
}]);