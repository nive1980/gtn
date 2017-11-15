restControllers
		.controller(
				'BillingCtrl',
				[
						'$http',
						'$scope',
						'$rootScope',
						'$location',
						'$window',
						'$cookies',
						'subscriptionService', 'commonService', '$mdDialog', '$route', 
						function($http, $scope, $rootScope, $location, $window,
								$cookies, subscriptionService, commonService, $mdDialog, $route) {
							
							$scope.errors = [];
							$scope.warnings = [];
							$scope.infos = [];
							
							$scope.makePrimary = function(billing) {
								
								var id = billing;
								billing = getBillingFromId($scope.billingMethods, id);
								
								var user1 = angular
										.fromJson(window.localStorage['LoggedInUser']);
								var dataTobeSent = {
									"billingname" : billing.firstName,
									"ccNumber" : billing.cardNo,
									"expiry" : billing.exp_date,
									"add_1" : billing.add_1,
									"add_2" : billing.add_2,
									"card_city" : billing.city,
									"card_state" : billing.state,
									"card_zip" : billing.zipcode,
									"card_country" : billing.country_code,
									"primary" : 'Y',
									"subscriptionId" : angular
											.fromJson(window.localStorage['subscriptionIdGenerated']),
									"userId" : user1.id

								}

								$http(
										{
											method : 'POST',

											responseType : 'json',
											url : '/gtn/chargeCard',
											headers : {
												'Content-Type' : 'application/json',
												'Authorization' : 'Basic '
														+ btoa($cookies
																.get('username')
																+ ':'
																+

																$cookies
																		.get('password'))
											},
											data : JSON.stringify(dataTobeSent)

										}).then(function(success) {
											$route.reload();
											//$window.location.reload();
										}, function(error) {
											console.log(error);
											$window.location.reload();
		
										});

							};
							
							$scope.makePrimaryNew = function(billing){
								
								var id = billing;
								billing = getBillingFromId($scope.billingMethods, id);
								
								$http({
									method : 'POST',
									responseType : 'json',
									url : '/gtn/makeCardPrimary',
									data: billing
								}).then(function(response) {
									
									/*if(response.data == null || response.status != 200){
										$scope.errors.push({form:'', field: '', msg: 'Error modifying card data.'});
					 					commonService.processMsg($scope.errors, 'error');
									}else{
										
										var result = $.grep($scope.billingMethods, function(e){ return e.primary == 'Y'; });
										result.primary = 'N';
										
										billing.primary = 'Y';
										//$scope.$apply();
									}*/
									
									
									$route.reload();
								}, function(error) {
									
									$scope.errors.push({form:'', field: '', msg: 'Error modifying card data.'});
				 					commonService.processMsg($scope.errors, 'error');
									
									//$window.location.reload();
								});
							}
							
							
							function getBillingFromId(billingMethods, id){
								var obj;
								angular.forEach(billingMethods, function(value, key){
								     if(value.id == id){
								    	 obj = value;
								     }
								});
								return obj;
							}
							
							
							$scope.editPayment = function(billing,billingsLength) {
								
								var id = billing;
								billing = getBillingFromId($scope.billingMethods, id);
								
								localStorage.setItem('FROM_BILLING', 'TRUE');
								window.localStorage['USER_BILLING'] = angular
										.toJson(billing);
								localStorage.setItem('NUMBER_BILLINGS',
								billingsLength);
								
								localStorage.setItem('from', 'editPayment');
								
								$window.location.href = '/gtn/usermgt-index.html#/payment';
							};
							
							
					          
							$scope.deletePayment = function(billing, ev) {
								var id = billing;
								billing = getBillingFromId($scope.billingMethods, id);
								
								$scope.errors.length = 0;
								
								if(billing.primary == 'Y'){
									$scope.errors.push({form:'', field: '', msg: 'Primary payment method can not be deleted.'});
									commonService.processMsg($scope.errors, 'error');
									return;
								}
								
								var confirm = $scope.mdConfirm(ev);

						        $mdDialog.show(confirm).then(function() {
						        	
									var user1 = angular
											.fromJson(window.localStorage['LoggedInUser']);

									var dataTobeSent = {
										"ccNumber" : billing.cardNo,
										"userId" : user1.id,

									}
									var headerTobeSent = {
										'Authorization' : 'Basic '
												+ btoa($.cookie('username')
														+ ':'
														+ $.cookie('password'))
									}
									$http(
											{
												method : 'POST',
												url : '/gtn/deletePaymentMethod',
												data : JSON
														.stringify(dataTobeSent),
												responseType : 'json',

												headers : {
													'Content-Type' : 'application/json',
													'Authorization' : 'Basic '
															+ btoa($cookies
																	.get('username')
																	+ ':'
																	+ $cookies
																			.get('password'))
												}
											})
											.then(
													function(success) {

														window.location
																.reload();
														$('#billing-errors')
																.text(
																		"Payment method deleted successfully")
																.addClass(
																		'alert alert-error')
																.css('color',
																		'green');

													},
													function(error) {
														$('#billing-errors')
																.text(
																		error.data.message)
																.addClass(
																		'alert alert-error')
																.css('color',
																		'red');
													});

								
						        	
						        }, function() {
						          		
					            });
						          
								
								
								/*if (confirm('Are you sure you want to delete this?')) {}*/
							};

							$scope.addPayment = function(billingsLength) {

								localStorage.removeItem('USER_BILLING');
								localStorage.setItem('FROM_BILLING', 'TRUE');
								localStorage.setItem('NUMBER_BILLINGS',
										billingsLength);
								
								localStorage.setItem('from', 'addPayment');

								$window.location.href = '/gtn/usermgt-index.html#/payment';
							};
							
							$scope.getSubscriptionTotal  = function(){
							    var total = 0;
							    if($scope.cartItems){
								    for(var i = 0; i < $scope.cartItems.length; i++){
								        var product = $scope.cartItems[i];
								        total += (product.cost * $scope.sub.users);
								    }
							    }
							    return total;
							}
							$scope.getPublicationTotal = function(){
							    var total = 0;
							    for(var i = 0; i < $scope.pubCartItems.length; i++){
							        var product = $scope.pubCartItems[i];
							        total += (product.price * $scope.sub.users);
							    }
							    return total;
							}	
							$scope.getTotal = function(){
							    var total = 0;
							   	//total += $scope.getSubscriptionTotal()+$scope.getPublicationTotal();
							    total += $scope.getSubscriptionTotal();
							    
							    return total;
							}

							$scope.getBillingDetails = function() {

								usrmgtLhs('billing-li');
								
								$scope.billingColumns = [
								                         'method','billTo','exp_date',
								                         'primary','edit', 'delete'
								                         ];

								$http(
										{
											method : 'POST',
											url : '/gtn/getBillingDetails',
											data : {
												"email" : $cookies
														.get('username')
											},
											headers : {
												'Content-Type' : 'application/json',
												'Authorization' : 'Basic '
														+ btoa($cookies
																.get('username')
																+ ':'
																+ $cookies
																		.get('password'))
											}
										})
										.then(
												function(success) {

													$scope.billingMethods = [];
													$scope.billingMethods = success.data;
													var user1 = angular
															.fromJson(window.localStorage['LoggedInUser']);

													$http(
															{
																method : 'POST',
																url : '/gtn/getSubscriptionFromUser',
																data : {
																	"email" : $cookies
																			.get('username'),
																	"id" : user1.id,
																	"subscriptionId" : $cookies
																			.get('subscriptionId')
																},
																headers : {
																	'Content-Type' : 'application/json',
																	'Authorization' : 'Basic '
																			+ btoa($cookies
																					.get('username')
																					+ ':'
																					+ $cookies
																							.get('password'))
																}

															})
															.then(
																	function(
																			success1) {
																		//$scope.billingAmountToDisplay = success1.data.totalCost;

																	},
																	function(
																			error1) {
																		//alert(error1);
																		console.log(error1);
																	});
													
													
													// get cart details
													
													$http({
														method: 'GET',
														url: 'getPurchasedProducts',
														data : {
															"email" : $cookies
																	.get('username'),
															"id" : user1.id,
															"subscriptionId" : $cookies
																	.get('subscriptionId')
														}
													}).then(function(response){
														
														if(response.data.success == true){
															var data = response.data.data;
											 	 			$scope.sub = {};
											 	 			$scope.sub.users = data.noOfUsers;
											 	 			$scope.cartItems = data.products;
											 	 			
											 	 			var currentTotalAmt = 0;
											 	 			
											 	 			if($scope.cartItems){
											 	 				$.each($scope.cartItems, function(k, v){
											 	 					currentTotalAmt = currentTotalAmt + v.cost*$scope.sub.users;
											 	 				});											 	 				
											 	 			}
											 	 			
											 	 			$scope.billingAmountToDisplay = currentTotalAmt;
														}else{
															$scope.errors.push({form:'', field: '', msg: response.data.message});
										 					commonService.processMsg($scope.errors, 'error');
														}
														
														console.log(response);
													},
													function(error){
														console.log(error);
													});
													
													$scope.refreshCart();
													
												}, function(error) {
													console.log(error);
												});

							};

						} ]);
