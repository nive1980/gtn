dashboardApp.service('publicationService',function($http,$cookies){
	this.arePublicationsPresent = function(type) {													
	return	$http(
				{
					method : 'GET',
					responseType:'json',
					url : '/gtn/getPublications?type='+type+'&email='+$cookies.get('username'),
					
					headers : {
							'Content-Type' : 'application/json',
							'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
					}								
       })

       };	
 this.getPublicationCartInfo = function(){
       		return $http({
       		    url: "getPublicationCartInfo",
       		    responseType:"json",
       		    method: "GET",
       		    headers: {
       		        "Content-Type": "application/json"
       		    }
       		});
       	}
	
});