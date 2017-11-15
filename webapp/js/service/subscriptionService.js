restControllers.service('subscriptionService', function($http, $cookies,$window) {
	
	
	this.createSubscription = function(cartItems, carTotal, carTotalCurrency,
			noOfUsers, noOfCountries, subscriptionIdGenerated) {
		
		if (subscriptionIdGenerated != null
				&& subscriptionIdGenerated != undefined) {

			return $http({
				method : 'POST',
				responseType : 'json',
				url : '/gtn/modifySubscription',
				data : {
					"totalCost" : carTotal,
					"totalCurrencyCode" : carTotalCurrency,
					"products" : cartItems,
					"noOfUsers" : noOfUsers,
					"noOfCountries" : noOfCountries,
					"username" : $cookies.get('username'),
					"subscriptionId" : subscriptionIdGenerated
				},
				headers : {
					'Content-Type' : 'application/json',
					'Authorization' : 'Basic '
							+ btoa($cookies.get('username') + ':'
									+ $cookies.get('password'))
				}
			})
		}

		else {

			return $http({
				method : 'POST',
				responseType : 'json',
				url : '/gtn/createSubscription',
				data : {
					"totalCost" : carTotal,
					"totalCurrencyCode" : carTotalCurrency,
					"products" : cartItems,
					"noOfUsers" : noOfUsers,
					"noOfCountries" : noOfCountries,
					"username" : $cookies.get('username')
				},
				headers : {
					'Content-Type' : 'application/json',
					'Authorization' : 'Basic '
							+ btoa($cookies.get('username') + ':'
									+ $cookies.get('password'))
				}
			})
		}
	};
	this.getAllProducts = function() {
		$http({
			method : 'POST',
			url : '/gtn/fetchProductList'
		})
	};
	this.createFreeSubscription = function() {
		return $http({
			method : 'POST',
			responseType : 'json',
			url : '/gtn/createFreeTrialSubscription',
			data : {
				"username" : $cookies.get('username')
			},
			headers : {
				'Content-Type' : 'application/json',
				'Authorization' : 'Basic '
						+ btoa($cookies.get('username') + ':'
								+ $cookies.get('password'))
			}
		})
	};
	
	this.addAdditionalUsers = function(firstname,lastname,email){
var subscriptionIdFromListPAge= $window.localStorage['subscriptionIdFromListPAge'];
var subId=$cookies.get('subscriptionId');
if(subId==undefined)
	subId=subscriptionIdFromListPAge ;

		
return $http(
				{
					method : 'POST',
					responseType : 'json',
					url : '/gtn/addAdditionalUser',
					data : {
						"firstName" : firstname,
						"lastName" : lastname,
						"email" : email,
						"parentUserEmail" : $cookies.get('username'),
						"subscriptionId" : subId
					},
					headers : {
						'Content-Type' : 'application/json',
						'Authorization' : 'Basic '+ btoa($cookies.get('username')+ ':'+ $cookies.get('password'))
					}
				})
	}

});