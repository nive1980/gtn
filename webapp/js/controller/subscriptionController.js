restControllers
		.controller(
				'SubCtrl',
				function($scope, $http, $rootScope, $cookies, $location,
						$window, subscriptionService, productService,DataService, commonService) {
					// ,DataService
					$scope.isFirstSub='no';
					$scope.productsData = [];
					$scope.cartItems = [];
					$scope.carTotal = 0;
					$scope.amountAlreadyPaid = 0;
					$scope.carTotalCurrency = "";
					$scope.subscription = {};
					$scope.isFromUsers = localStorage.getItem('FROM_USERS');
					$scope.errors = [];
					$scope.warnings = [];
					
				
					$scope.cart = DataService.cart;
					$scope.pubCartItems = $scope.cart .items;
					$scope.getSubscription = function() {
						
						usrmgtLhs('subscription-li');
						
						var user1 = angular
								.fromJson($window.localStorage['LoggedInUser']);
						productService
								.getAllProducts()
								.then(
										function(success) {
											$scope.productsData = success.data.data;
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
															function(success1) {
																$scope.subscription = success1.data;
																$scope.subscription.isPaymentDone=success1.data.isPaymentDone;
																if($scope.subscription.isPaymentDone=='false') {
																	$scope.warnings.push('Payment Pending');
																	commonService.processMsg($scope.warnings, 'warning');																
																}
																// $scope.carTotalCurrency
																// =$scope.subscription.totalCurrencyCode;
																// $scope.carTotal=$scope.subscription.totalCost;
																$scope.sub = {};
																$scope.sub.users = $scope.subscription.noOfUsers;
																$scope.sub.countries = $scope.subscription.noOfCountries;
																for (i = 0; i < $scope.productsData.length; i++) {

																	for (k = 0; k < $scope.productsData[i].subproduct.length; k++) {

																		for (j = 0; j < $scope.subscription.products.length; j++) {
																			if ($scope.productsData[i].subproduct[k].productId === $scope.subscription.products[j].productId) {

																				//$scope.productsData[i].subproduct[k].style = "color:green";
																				if($scope.productsData[i].subproduct[k].active == 'A'){
																					/*$scope.productsData[i].subproduct[k].labelText = "REMOVE FROM SUBSCRIPTION";
																					$scope.productsData[i].subproduct[k].addStatus = "0";*/
																				
																					$scope.productsData[i].subproduct[k].labelText = "REMOVE";
																					$scope.productsData[i].subproduct[k].addStatus = "-1";
																				}else if($scope.productsData[i].subproduct[k].active == 'P'){
																					$scope.productsData[i].subproduct[k].labelText = "REMOVE";
																					$scope.productsData[i].subproduct[k].addStatus = "-1";
																				}																				
																				

																				$scope.cartItems
																						.push($scope.productsData[i].subproduct[k]);
/*																				$scope.carTotal = $scope.carTotal
																						+ ($scope.productsData[i].subproduct[k].cost
																								*

																								$scope.sub.users * $scope.sub.countries);*/
																				
																				$scope.carTotal = $scope.carTotal
																				+ ($scope.productsData[i].subproduct[k].cost
																						*
																						$scope.sub.users);
																				
																			}
																		}
																	}
																}
																$scope.carTotalCurrency = $scope.subscription.totalCurrencyCode;
																$scope.amountAlreadyPaid=$scope.subscription.totalCost;
																if($scope.subscription.isPaymentDone=='false')
																 $scope.amountAlreadyPaid=0;
															}, function(error) {
																$scope.isFirstSub='yes';
																var msg = error.data.message;
																if(msg.trim() == 'Subscription Not found'){
																	$scope.sub = {};
																	$scope.sub.users = 1;
																}
																console.log(error);
															})
										}, function(error) {
											console.log(error);
										});

					};

					$scope.addSubscription = function() {
						localStorage.setItem('FROM_SUB_LIST', 'TRUE');
						$window.location.href = '/gtn/usermgt-index.html#/subscription';

					};

					$scope.deleteSubscription = function() {
						if (confirm('Are you sure you want to delete this subscription?')) {
							localStorage.setItem('FROM_SUB_LIST', 'TRUE');
							var user1 = angular
									.fromJson($window.localStorage['LoggedInUser']);
							$http(
									{
										method : 'POST',
										url : '/gtn/deleteSubscription',
										data : {
											"email" : $cookies.get('username'),
											"id" : user1.id,
											"subscriptionId" : $scope.subscription.subscriptionId
										},
										headers : {
											'Content-Type' : 'application/json',
											'Authorization' : 'Basic '
													+ btoa($cookies
															.get('username')
															+ ':'
															+ $cookies.get

															('password'))
										}

									}).then(function(success1) {

								$scope.subscription = success1.data;
								// DataService.cart.removeItem($scope.subscription.subscriptionId,$scope.subscription.subscriptionId,'Subscription',
								// $scope.subscription.subscriptionId,$scope.subscription.subscriptionId,
								// 1);

							}, function(error) {

							});

							$window.location.reload();
						}
					};

					$scope.getProducts = function() {
						var isFreeTrial = localStorage.getItem('freeTrail');
						if (isFreeTrial != undefined && isFreeTrial != null
								&& isFreeTrial == "yes") {
							$scope.freeTrail = true;
						}
						productService.getAllProducts().then(function(success) {
							$scope.productsData = success.data.data;
						}, function(error) {

						});
					};
					
					$scope.getSubscriptionTotal  = function(){
					    var total = 0;
					    for(var i = 0; i < $scope.cartItems.length; i++){
					        var product = $scope.cartItems[i];
					        total += (product.cost * $scope.sub.users);
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
					   	total += $scope.getSubscriptionTotal()+$scope.getPublicationTotal();
					    
					    return total;
					}

					$scope.addToCart = function(subproducts) {
						$scope.errors = [];
						$scope.carTotalCurrency = subproducts.currencyCode;
						/*$scope.carTotal = $scope.carTotal
								+ (subproducts.cost * $scope.sub.users * $scope.sub.countries);*/
						
						if(!$scope.sub){
							$scope.sub = {};
							$scope.sub.users = '';
							/*$scope.errors.push({
								form : '',
								field : '',
								msg : 'Please enter number of users.'
							});
							return;*/
						}
						
						if(subproducts.addStatus == '1'){
							$scope.carTotal = $scope.carTotal + (subproducts.cost * $scope.sub.users);
							$scope.cartItems.push(subproducts);
							subproducts.labelText = 'REMOVE';
							subproducts.addStatus = '-1';
						}else if(subproducts.addStatus == '-1'){
							$scope.carTotal = $scope.carTotal - (subproducts.cost * $scope.sub.users);
							
							var itemIndex = $scope.cartItems.indexOf(subproducts);
							$scope.cartItems.splice(itemIndex, 1);
							  
							//$scope.cartItems.remove(subproducts);
							subproducts.labelText = 'ADD TO CART';
							subproducts.addStatus = '1';
						}else if(subproducts.addStatus == '0'){
							
						}
						
					};

					$scope.removeFromCart = function(item, index) {
						for (i = 0; i < $scope.productsData.length; i++) {

							for (k = 0; k < $scope.productsData[i].subproduct.length; k++) {
								if ($scope.productsData[i].subproduct[k].productId === item.productId) {

									$scope.productsData[i].subproduct[k].style = "";

								}
							}
						}
						$scope.cartItems.splice(index, 1);
						$scope.carTotalCurrency = item.currencyCode;
						/*$scope.carTotal = $scope.carTotal
								- (item.cost * $scope.sub.users * $scope.sub.countries);*/
						$scope.carTotal = $scope.carTotal
						- (item.cost * $scope.sub.users);

					};

					$scope.checkout = function(cartItems, carTotal,
							carTotalCurrency, fromDetails) {

						$scope.errors = [];
						console.log(cartItems);
						if(!$scope.sub || ($scope.sub.users==null || $scope.sub.users=='')){
							/*$scope.sub = {};
							$scope.sub.users = '';*/
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'Please enter number of users.'
							});
							
						}else if($scope.sub.users == 0){
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'Number of users can not be 0.'
							});
						}
						
						if(cartItems.length == 0){
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'Please select at least one item.'
							});
						}
						
						if($scope.errors.length > 0){
						var homeScope = angular.element("#home").scope();
				
								var pubTotalPriceToPass=homeScope.publicationCart.getTotalPrice;
						
									
							if(pubTotalPriceToPass!=0) {
								localStorage.setItem('billingamount',pubTotalPriceToPass);
								localStorage.setItem('pubTotalPriceToPass',pubTotalPriceToPass);
								
								//commonService.setResponse({'requestType': 'checkout'});
								localStorage.setItem('from', 'checkout');
								
								$window.location.href = '/gtn/usermgt-index.html#/payment';

							}
							else
								return;
						}
						
						if ($cookies.get('username') == undefined) {
							localStorage.setItem('callCreatePostLogin', "yes");
							localStorage.setItem('cartItems', angular
									.toJson(cartItems));
							localStorage.setItem('carTotal', carTotal);
							localStorage.setItem('carTotalCurrency',
									carTotalCurrency);
							localStorage.setItem('noOfUsers', $scope.sub.users);
							localStorage.setItem('noOfCountries',
									1);

							$window.location.href = '/gtn/index.html#/login';
						} else {
						if(pubTotalPriceToPass >0){
						
								localStorage.setItem('pubTotalPriceToPass',pubTotalPriceToPass);
								window.location.href = '/gtn/usermgt-index.html#/payment';
																			
						}
						else {

							subscriptionService
									.createSubscription(cartItems, carTotal, carTotalCurrency,
											$scope.sub.users,
											$scope.sub.countries,
											$scope.subscription.subscriptionId)
									.then(
										function(success) {
											//alert(JSON.stringify(success));
											
											if(success.data.Status == 'Error'){
												
												$scope.errors.push({
													form : '',
													field : '',
													msg : success.data.msg
												});
												commonService.processMsg($scope.errors, 'error');
												return;
											}
											
											var homeScope = angular.element("#home").scope();
				
											var pubTotalPriceToPass=homeScope.publicationCart.getTotalPrice;
										
										
											if (fromDetails != 'TRUE') {
												$cookies
														.put(
																'subscriptionId',
																success.data.data.SubId);
												// DataService.cart.addItem(success.data.data.SubId,success.data.data.SubId,'Subscription',
												// success.data.data.SubId,carTotal,
												// 1);
												localStorage
														.setItem(
																'subscriptionIdGenerated',
																success.data.data.SubId);
												var totprice =JSON.stringify(success.data.data.SubTotal);
												totprice=parseInt(success.data.data.SubTotal);
												totprice=totprice+pubTotalPriceToPass;
											
											
												localStorage.setItem('billingamount', totprice);
												//	localStorage.setItem('NUMBER_BILLINGS',0);
													
													//commonService.setResponse({'requestType': 'checkout'});
													localStorage.setItem('from', 'checkout');
													
													$window.location.href = '/gtn/usermgt-index.html#/payment';
												}
												else{
													var subid=success.data.SubId;
													if(subid==undefined)
														subid=success.data.data.SubId;
													localStorage.setItem('subscriptionIdGenerated',subid);
													
													var subt=success.data.SubTotal;
													if(subt==undefined)
														subt=success.data.data.SubTotal;
													
													var diff =subt-$scope.amountAlreadyPaid;
													if(diff>0 && pubTotalPriceToPass!=0) {
													
															localStorage.setItem('billingamount',diff+pubTotalPriceToPass);
															localStorage.setItem('pubTotalPriceToPass',pubTotalPriceToPass);
													
													
													}
													else if(diff>0 && pubTotalPriceToPass==0){
															localStorage.setItem('billingamount',diff);
													
															}
													else if(diff<0 && pubTotalPriceToPass!=0) {
															localStorage.setItem('billingamount',pubTotalPriceToPass);
															localStorage.setItem('pubTotalPriceToPass',pubTotalPriceToPass);
													
													
													}
													else if(diff==0 && pubTotalPriceToPass!=0) {
														localStorage.setItem('billingamount',pubTotalPriceToPass);
														localStorage.setItem('pubTotalPriceToPass',pubTotalPriceToPass);
													
													}
																								
													else if(diff<0 && pubTotalPriceToPass==0) {
														
													}
														
													//$window.location.href = '/gtn/usermgt-index.html#/payment';	
													var nextPage = "";
													
													if(success.data.hasOwnProperty('data')){
														nextPage = success.data.data.nextPage;
													} else if(diff==0 && pubTotalPriceToPass!=0) {
													nextPage='payment';
													}else{
														nextPage = success.data.nextPage;
													}
													
													if(nextPage == 'payment'){
														//commonService.setResponse({'requestType': 'checkout'});
														localStorage.setItem('from', 'checkout');
														$location.path("/payment");
													}else{
														$location.path("/billing");
													}
													
										}
												
									}, function(error) {
										alert(JSON.stringify(error));
									});
						}}
					};

					$scope.validateNewUser = function(){

						if($scope.user.firstname == ''){
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'First name is required.'
							});
						}
						if($scope.user.lastname == ''){	
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'Last name is required.'
							});
						}
						if($scope.user.email == ''){
							$scope.errors.push({
								form : '',
								field : '',
								msg : 'Email is required.'
							});
						}
					
					}
					
					$scope.addUser = function(fromListPage) {
						$scope.errors.length = 0;
						$scope.validateNewUser();
						if($scope.errors.length > 0){
							return;
						}
						
						subscriptionService
								.addAdditionalUsers($scope.user.firstname,
										$scope.user.lastname, $scope.user.email)
								.then(
										function(success) {
											$scope.message = success.data.data.Message;
											if (fromListPage) {
												$window.location.href = '/gtn/usermgt-index.html#/usersList';
												$window.location.reload();
											}
										},
										function(error) {
											if (error.status == 417) {
												var msgs = error.data.data;
												angular.forEach(msgs, function(
														index, value) {
													$scope.errors.push({
														form : '',
														field : '',
														msg : index
													});
												});
											} else {
												$scope.errors
														.push({
															form : '',
															field : '',
															msg : 'Server encountered error, please try later.'
														});
											}
										});
					};

					$scope.doFreeTrial = function() {
						localStorage.removeItem('freeTrail');
						localStorage.removeItem("UserId");
						subscriptionService
								.createFreeSubscription()
								.then(
										function(success) {
											$window.location.href = '/gtn/dashboard-index.html';

										}, function(error) {

										});
					};

				});