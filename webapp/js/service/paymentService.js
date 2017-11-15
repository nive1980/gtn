restControllers.service('paymentService', function($http,$window,$cookies)  {
	this.fillCountries = function() {

return 		$http({
				method : 'POST',
               			url: '/gtn/getCountries',
               			headers : {
							'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
						},
               			data : {}
			})
		}

this.submitToken = function(code, result) {
			$http({
				method : 'POST',
				url : '/gtn/chargeCard',
			})
}
this.loadPaymentMethod=function(){
	return $http({
				method : 'POST',
               			url: '/gtn/getPrimaryBillingDetail',
               			headers : {
							'Authorization' : 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
						},
               			data : {"email" : $cookies.get('username')}
			})
}

});