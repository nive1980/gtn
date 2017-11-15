restControllers.service('userService',function($http,$cookies){
	
	this.signUp = function(firstname,lastname,email,hashedPassword,phone){
		return $http({
			method : 'POST',
			responseType:'json',
			url : '/gtn/signup',
			data : {
				"firstName" : firstname,
				"lastName" : lastname,
				"email" : email,
				"password" : hashedPassword,
				"phone" : phone
			},
			headers : {
				'Content-Type' : 'application/json'
			}
		})
	};
	
	this.login = function(username,hashedPassword) {
		return $http(
				{
					method : 'POST',
					url : '/gtn/loginUser',
					data : {},
					headers : {
						'Authorization' : 'Basic ' + btoa(username + ':' + hashedPassword)
					}
				})
	};
	this.forgotPassword = function(username){
		return $http({
			method : 'POST',
			url : 'http://localhost:8080/gtn/forgotPassword',
			data : {
				"email" : username
			},
			headers : {
				'Content-Type' : 'application/json'
			}
		})
	};
	this.resetPassword = function(username,newPassword,otp){
		return $http({
			method : 'POST',
			url : 'http://localhost:8080/gtn/resetPassword',
			data : {
				"email" : username,
				"password" : newPassword,
				"otp" : otp
			},
			headers : {
				'Content-Type' : 'application/json'
			}
		})
	}
	
	this.getDashBoardLHSMenu = function(){
		return $http({
		    url: "getDashBoardLHSMenu",
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		});
	}
	
	this.getCartInfo = function(){
		return $http({
		    url: "getCartInfo",
		    responseType:"json",
		    method: "GET",
		    headers: {
		        "Content-Type": "application/json"
		    }
		});
	}
	
	
});