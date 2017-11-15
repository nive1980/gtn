restControllers
		.controller(
				'PaymentGatewayCtrl',
				function($http, $scope, $window, $cookies, paymentService,
						validationService, DataService, userService, commonService, $timeout) {
					$scope.errors = [];
					$scope.userBilling = [];
					$scope.warnings = [];
					$scope.infos = [];
					$scope.isFirstBilling = 'off';
					$scope.cart = DataService.cart;
					
					if ((localStorage.getItem('NUMBER_BILLINGS') == 0 && localStorage.getItem('from')=='addPayment') || (localStorage.getItem('NUMBER_BILLINGS') == 1 && localStorage.getItem('from')=='editPayment')) {
					$scope.same_as_primary = true;
						$scope.isFirstBilling = 'on';
						$scope.is_disabled=true;
						

					} else{
					$scope.is_disabled=false;
						$scope.same_as_primary = false;
						}
					
					$scope.clearMessages = function() {
						$scope.errors.length = 0;
						$scope.warnings.length = 0;
						$scope.infos.length = 0;
					};
					$scope.validatePaymentForm = function() {

						$scope.errors.length = 0;
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.firstName)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=billingname]',
								msg : "Billing name is required"
							});
						}

						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.cardNo)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=cc]',
								msg : "Card Number is required"
							});

						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.exp_date)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=expiry]',
								msg : "Expiry Date is required"
							});
						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.cvv)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=cvv]',
								msg : "CVV is required"
							});
						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.add_1)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=add_1]',
								msg : "Address1 is required"
							});
						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.city)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=card_city]',
								msg : "City is required"
							});
						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.state)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=card_state]',
								msg : "State is required"
							});
						}
						if ($scope.userBilling == undefined
								|| validationService
										.isEmpty($scope.userBilling.zipcode)) {
							$scope.errors.push({
								form : 'user-form',
								field : 'input[name=card_zip]',
								msg : "Zip Code is required"
							});
						}

						if ($scope.errors.length > 0) {
							return false;
						} else {
							return true;
						}

					}

					$scope.loadPaymentMethod = function() {
						$scope.userBilling = angular
								.fromJson($window.localStorage['USER_BILLING']);
						
						if (localStorage.getItem('FROM_BILLING') != 'TRUE') {
							if ($scope.userBilling == undefined) {
								paymentService.loadPaymentMethod().then(
										function(result) {
											$scope.userBilling = result.data;
											if($scope.userBilling.primary=='Y') {
												$scope.same_as_primary = true;
												}
					
										}, function(error) {

										});
							}
						}
						
						//$scope.billingamount = localStorage.getItem('billingamount');
						
						/*userService.getCartInfo()
				        	.then(function(response) {
					        			if(response.data.success == true){
					        				$scope.userCart = {};
					        				$scope.userCart.getTotalCount = response.data.data.noOfUsers;
					        				$scope.userCart.getTotalPrice = response.data.data.totalCost;
					        			}
					        			//createLhsMenu();
					        			console.log(response);
					        		}, function(error) {
					        			$scope.errors.push({
				                            form: '',
				                            field: '',
				                            msg: 'Server encountered error, please try later.'
				                        });
					        			console.log(error);
							});*/
						//var reqType = commonService.getResponse();
						var reqType = localStorage.getItem('from');
						$scope.fillCountries();
						if(reqType){
							/*var requestType = reqType.requestType;
							if(requestType == 'editPayment'){
								$scope.fromReq = reqType.requestType;
							}else if(requestType == 'checkout'){
								
							}*/
							console.log(reqType);
							//alert(reqType);
							$scope.fromReq = reqType;
							$scope.refreshCart('updateAmount');
							//commonService.setResponse(null);
						}
						
					};
					
					$scope.$on('updateAmountOnPayment', function(event, data){
						$scope.billingamount = data;
					});

					$scope.fillCountries = function() {

						paymentService
								.fillCountries()
								.then(
										function(result) {
											$scope.countries = result.data;
											var len = result.data.length;
											//$timeout(angular.element('#card_country').val($scope.userBilling.country_code),1000); 
											
											setTimeout(
											    function() {
											    	angular.element('#card_country').val($scope.userBilling.country_code)
												      
										    }, 500);
											
											//angular.element('#card_country').val($scope.userBilling.country_code);
											//alert($scope.userBilling.country_code);
											//console.log("----------AAA ----------");
											//alert($scope.userBilling.country_code);
											//console.log($scope.userBilling);
											/*$("#card_country").empty();

											for (var i = 0; i < len; i++) {
												var id = result.data[i]['countryCode'];
												var name = result.data[i]['countryName'];
												$("#card_country").append(
														"<option value='" + id
																+ "'>" + name
																+ "</option>");

											}*/
											
										},
										function(error) {
											window
													.alert("errorssssssssss11111111111");
										});
					};
					// Calling the function to load the data on pageload
					

					$scope.billingamount = localStorage
							.getItem('billingamount');

					$scope.userBilling = angular
							.fromJson($window.localStorage['USER_BILLING']);
											
					if($scope.userBilling!=undefined && $scope.userBilling.primary=='Y') {
						$scope.same_as_primary = true;
					}			
					$scope.reportError = function(msg) {
						// Show the error in the form:
						$('#payment-errors').text(msg).addClass(
								'alert alert-error').css('color', 'red');
						// re-enable the submit button:
						// $('#submitBtn').prop('disabled', false);
						return false;
					};

					$scope.stripeCallback = function() {
						var validated = $scope.validatePaymentForm();
						if (validated) {
							var $form = $('#user-form');
							var ccNum = $form.get(0).card_num.value;

							var cvcNum = $form.get(0).card_cvv.value;
							var card_expiry = $form.get(0).card_expiry.value;
							var card_month;
							var idx = card_expiry.indexOf('/');
							var month = card_expiry.substring(0, idx);
							var year = card_expiry.substring(idx + 1);
							var error = false;
							var zip = $form.get(0).card_zip.value;
							var add_1 = $form.get(0).add_1.value;
							var add_2 = $form.get(0).add_2.value;
							var add_city = $form.get(0).card_city.value;
							var add_state = $form.get(0).card_state.value;
							var add_country = $form.get(0).card_country.value;
							var add_name = $form.get(0).billingname.value;
							if (!Stripe.card.validateCardNumber(ccNum)) {
								error = true;
								$scope.errors
										.push({
											form : 'user-form',
											field : 'input[name=cc]',
											msg : 'The credit card number appears to be invalid.'
										});
								commonService.processMsg($scope.errors, 'error');
								return;
							}
							if (!Stripe.card.validateCVC(cvcNum)) {
								error = true;
								$scope.errors
										.push({
											form : 'user-form',
											field : 'input[name=cvv]',
											msg : 'The CVC number appears to be invalid.'
										});
								commonService.processMsg($scope.errors, 'error');
								return;
							}
							if (!Stripe.card.validateExpiry(month, year)) {
								error = true;
								$scope.errors
										.push({
											form : 'user-form',
											field : 'input[name=expiry]',
											msg : 'The expiration date appears to be invalid.'
										});
								commonService.processMsg($scope.errors, 'error');
								return;

							}
							if (!error) {

								// Get the Stripe token:
								Stripe.card.createToken({
									number : ccNum,
									cvc : cvcNum,
									exp_month : month,
									exp_year : year,
									name : add_name,
									address_zip : add_name,
									address_line1 : add_1,
									address_line2 : add_2,
									address_city : add_city,
									address_state : add_state,
									address_country : add_country

								}, 100, stripeCallback2);

							}
						} else {
							commonService.processMsg($scope.errors, 'error');
						}

					};

					$scope.submitToken = function(code, result) {

						paymentService.submitToken().then(function(data) {
						}, function(error) {
						});
					};

				});
function getCookie(name) {
	var cookieValue = null;
	if (document.cookie && document.cookie != '') {
		var cookies = document.cookie.split(';');
		for (var i = 0; i < cookies.length; i++) {
			var cookie = jQuery.trim(cookies[i]);
			// Does this cookie string begin with the name we want?
			if (cookie.substring(0, name.length + 1) == (name + '=')) {
				cookieValue = decodeURIComponent(cookie
						.substring(name.length + 1));
				break;
			}
		}
	}
	return cookieValue;
}
function OnError(xhr, errorType, exception) {
localStorage.removeItem('NUMBER_BILLINGS');

	angular.element('#preloader').hide();
	var scope = angular.element("#user-form").scope();
	var responseText = jQuery.parseJSON(xhr.responseText);
	error = true;
	scope.errors.push({
		form : '',
		field : '',
		msg : responseText.message
	});
	scope.$apply();
	return;

}
function changeValueCheckbox(element) {

	if (element.checked) {

		element.value = 'on';
	} else {
		element.value = 'off';
	}
}
function stripeCallback2(status, result) {

	var $form = $('#user-form');
	var scope = angular.element("#user-form").scope();

	var token = result.id;

	if(result.hasOwnProperty('error')){
		scope.errors.push({
			form : '',
			field : '',
			msg : result.error.message
		});
		scope.$apply();
	}
	
	else if (result.card.address_line1_check == 'fail') {

		scope.errors.push({
			form : '',
			field : '',
			msg : 'Please provide the correct address'
		});
		scope.$apply();

	} else if (result.card.address_zip_check == 'fail') {

		scope.errors.push({
			form : '',
			field : '',
			msg : 'Please provide the correct Zip code'
		});
		scope.$apply();

	}

	else {
		var primary = null;
		
		
		
		if (!scope.same_as_primary) {
			primary = 'N';
		}
		if (scope.same_as_primary) {
			primary = 'Y';
		} else {

			primary = 'N';
		}
		var subid =window.localStorage['subscriptionIdGenerated'];
		
		var amountToBeCharged = null;
		if ($form.get(0).amount == undefined) {
			amountToBeCharged = '0';
		} else {
			amountToBeCharged = $form.get(0).amount.value * 100;
		}
		var user1 = angular.fromJson(window.localStorage['LoggedInUser']);
		var fromPage=scope.fromReq;
		
		var dataTobeSent = {
			"billingname" : $form.get(0).billingname.value,
			"ccNumber" : $form.get(0).card_num.value,
			"expiry" : $form.get(0).card_expiry.value,
			"stripeToken" : token,
			"add_1" : $form.get(0).add_1.value,
			"add_2" : $form.get(0).add_2.value,
			"card_city" : $form.get(0).card_city.value,
			"card_state" : $form.get(0).card_state.value,
			"card_zip" : $form.get(0).card_zip.value,
			"card_country" : $form.get(0).card_country.value,
			"primary" : primary,
			"subscriptionId" : subid,
			"userId" : user1.id,
			"amount" : amountToBeCharged,
			"from":fromPage

		}
		var headerTobeSent = {
			'Authorization' : 'Basic '
					+ btoa($.cookie('username') + ':' + $.cookie('password'))
		}
		localStorage.removeItem('subscriptionIdGenerated');
		var csrftoken = getCookie('XSRF-TOKEN');
		$.ajax({
			type : 'POST',
			beforeSend : function(xhr) {
				angular.element('#preloader').show();
				xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);				
			},
			url : '/gtn/chargeCard',
			responseType : 'json',
			data : JSON.stringify(dataTobeSent),
			contentType : "application/json; charset=utf-8",
			headers : JSON.stringify(headerTobeSent),
			success : function(r) {
				angular.element('#preloader').hide();
				var scope = angular.element("#user-form").scope();
				
				if (localStorage.getItem('pubTotalPriceToPass') != null) {
					
					scope.cart.updatePublicationStatus();
					localStorage.removeItem('pubTotalPriceToPass');
				}
				
				var homeScope = angular.element("#home").scope();
				homeScope.refreshCart();

				localStorage.removeItem('billingamount');
				localStorage.removeItem('NUMBER_BILLINGS');
				
				
				if (localStorage.getItem('FROM_BILLING') == 'TRUE') {
					if(fromPage == 'checkout'){
						window.location.href = '/gtn/dashboard-index.html#/';
						//window.location.reload();
					}else{
						window.location.href = '/gtn/usermgt-index.html#/billing';
						window.location.reload();
					}
					

				} else {
					window.location.href = '/gtn/usermgt-index.html#/usersList';
					localStorage.removeItem('FROM_BILLING');
				}
			},
			error : OnError
		});

	}

}
