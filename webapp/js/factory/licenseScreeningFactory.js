dashboardApp.factory('licenseScreeningFactory', ['$http', '$location', 'commonService', '$resource', function($http, $location, commonService, $resource) {
	
	var factory = {}; 

	factory.doLicenseScreening = function(licenseScreeningInput) {
		
		return $http({
		    url: "doLicenseScreening",
		    responseType:"json",
		    method: "POST",
		    data: licenseScreeningInput,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			//alert('success');
			console.log(response);
			return response;
			//commonService.setResponse(response);
			//angule.element('#preloader').delay(10).fadeOut(10);
			//$location.path("/resultLicenseScreening");
		}, function(error){
			console.log(error);
			return error;
			//angule.element('#preloader').delay(10).fadeOut(10);
			//alert('error');
		});
	
	}

	return factory;
}]);


/*dashboardApp.factory('entityScreening', ['$resource',
     function($resource){
         return $resource('doWatchListScreening', {}, {
             query: {
                 method: 'GET', 
                 params: { format: 'json'}, 
                 isArray: false
             }
         });
 }]);*/