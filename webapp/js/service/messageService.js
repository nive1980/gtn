restControllers.service('messageService',function($http,$cookies){
	this.getUnreadMessageCount = function() {
		return $http({
			method : 'POST',
			url : '/gtn/getUnreadMessageCount',
			data : {
				"userId" : $cookies.get('username')								
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}				
       })
	};
	
	this.getUnreadMessageCountForCases = function() {
		return $http({
			method : 'POST',
			url : '/gtn/getUnreadMessageCountForCases',
			data : {
				"userId" : $cookies.get('username')								
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}				
       })
	};
	
	this.markMessageAsReadForCases = function(ticketId) {
		return $http({
			method : 'POST',
			url : '/gtn/markMessageAsReadForCases',
			data : {
				"userId" : $cookies.get('username'),
				"ticketId":ticketId
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
			}				
       })
	};
	
});