dashboardApp.factory('commonFactory', ['$http', '$location', '$resource', function($http, $location, $resource) {
	var factory = {};
	
	factory.searchSbuConfig = function(sbuConfigView){
		return $http({
		    url: "searchSbuConfig",
		    responseType:"json",
		    method: "POST",
		    data: sbuConfigView,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
			//commonService.setResponse(response);
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.saveSbuConfig = function(subConfigValue){
		return $http({
		    url: "saveSbuConfig",
		    responseType:"json",
		    method: "POST",
		    data: subConfigValue,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
			//commonService.setResponse(response);
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.deleteSbuConfig = function(id){
		return $http({
		    url: "deleteSbuConfig?id="+id,
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
		});
	}
	
	factory.findSbuConfig = function(id){
		return $http({
		    url: 'findSbuConfig?id='+id,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getSbuList = function(){
		return $http({
		    url: 'getSbuList',
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getUserThemeClass = function(){
		return $http({
		    url: 'getUserThemeClass',
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.updateUserThemeClass = function(gtnClass){
		return $http({
		    url: 'updateUserThemeClass?gtnClass='+gtnClass,
		    responseType:"json",
		    method: "POST"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.getSchedulerJob = function(jobCode){
		return $http({
		    url: 'getSchedulerJob?jobCode='+jobCode,
		    responseType:"json",
		    method: "GET"
		}).then(function(response){
			return response;
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.saveAesFilingConfig = function(aesFilingConfig){
		return $http({
		    url: "saveAesFilingConfig",
		    responseType:"json",
		    method: "POST",
		    data: aesFilingConfig,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
			//commonService.setResponse(response);
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	factory.searchScheduledJobs = function(job){
		return $http({
		    url: "searchScheduledJobs",
		    responseType:"json",
		    method: "POST",
		    data: job,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			return response;
			//commonService.setResponse(response);
		}, function(error){
			console.log(error);
			return error;
		});
	}
	
	return factory;
}]);