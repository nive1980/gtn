restControllers.service('alertService',function($http,$cookies){
	this.getAlerts = function() {
		return $http({
			method : 'POST',
			url : '/gtn/getAlerts',
			data : {
				"userId" : $cookies.get('username')								
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}				
       })
	};
	
});