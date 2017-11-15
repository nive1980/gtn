dashboardApp.factory('entityScreeningFactory', ['$http', '$location', 'commonService', '$resource', function($http, $location, commonService, $resource) {
	
	var factory = {}; 

	factory.doWatchListScreening = function(entities) {
		
		//angule.element('#preloader').delay(10).fadeIn(10);
		
		$.each(entities, function(k, v){
			delete v['$$hashKey'];
			delete v['edit'];
			delete v['remove'];
		});
			
		
		/*var dummyArr = [];
		var dummy = {};
		
		dummy.name="naveen";
		dummyArr.push(dummy);
		
		console.log(dummyArr);*/
		
		console.log(entities);
		
		/*return $resource('doWatchListScreening', entities, {
            get: { method: 'GET', isArray: false }
        })*/
		
		return $http({
		    url: "doWatchListScreening",
		    responseType:"json",
		    method: "POST",
		    data: entities,
		    headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function(response){
			//alert('success');
			console.log(response);
			return response;
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