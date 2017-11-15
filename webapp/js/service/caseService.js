restControllers.service('caseService',function($http,$cookies){
	this.getCasesToDisplay = function(){
		return $http({
			method : 'POST',
			url : '/gtn/getCasesToDisplay',
			data : {
				"userId" : $cookies.get('username')								
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}
		})
	};
	
	this.saveCase = function(userId,description,priority,ticketId,status,category,newMessage) {
		return $http({
			method : 'POST',
			url : '/gtn/saveCase',
			data : {
				"userId" : userId,
				"description" : description,
				"priority" : priority,
				"ticketId" : ticketId,
				"status" : status,
				"category":category,
				"newMessage" :newMessage
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}
		})
	};
	this.getActivityData = function(){
		return $http({
			method : 'POST',
			url : '/gtn/getActivityDataForCase',
			data : {
				"userId" : $cookies.get('username'),
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}
		})
	}
	
});