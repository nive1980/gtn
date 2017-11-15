restControllers.controller('UsersCtrl', function($scope, $http, $rootScope,
		$cookies, $location, $window, commonService, subscriptionService, $mdDialog) {
	localStorage.setItem('FROM_USERS', 'TRUE');

	$scope.errors = [];
	$scope.warnings = [];
	$scope.infos = [];
	$scope.isPaymentDone={};
	
	
	$scope.getListOfUsers = function() {
		var user1 = angular.fromJson($window.localStorage['LoggedInUser']);
		usrmgtLhs('user-li');
		// if($cookies.get('subscriptionId')==undefined) {
		// $('#billing-errors').text('Please create a subscription and then add
		// users').addClass(
		// 'alert alert-error').css('color', 'red');

		// }
		$scope.user = {firstname: '', lastname: '', email: ''};
		
		$scope.userColumns = [
		                      	'firstName', 'email', 'id'
		                      ];
		
		$http(
				{
					method : 'POST',
					url : '/gtn/getSubscriptionFromUser',
					data : {
						"email" : $cookies.get('username'),
						"id" : user1.id,
						"subscriptionId" : $cookies.get('subscriptionId')
					},
					headers : {
						'Content-Type' : 'application/json',
						'Authorization' : 'Basic '
								+ btoa($cookies.get('username') + ':'
										+ $cookies.get

										('password'))
					}

				}).then(
				function(success1) {

					$scope.NoOfUsersAllowed = success1.data.noOfUsers;
					$scope.isPaymentDone=success1.data.isPaymentDone;
					if($scope.isPaymentDone=='false') {
						$('#billing-errors').text("Subscription Payment pending").addClass(
							'alert alert-error').css('color', 'red');
							}
					$scope.NoOfUsersAllowed = $scope.NoOfUsersAllowed - 1;
					localStorage.setItem('subscriptionIdFromListPAge',
							success1.data.subscriptionId);

					$http(
							{
								method : 'POST',
								url : '/gtn/getAdditionalUsers',
								data : {
									"email" : $cookies.get('username'),
									"id" : user1.id
								},
								headers : {
									'Content-Type' : 'application/json',
									'Authorization' : 'Basic '
											+ btoa($cookies.get('username')
													+ ':' + $cookies.get

													('password'))
								}

							}).then(function(success) {

								$scope.users = [];
								$scope.users = success.data;
								if ($scope.users.length < $scope.NoOfUsersAllowed) {
									$scope.allowMoreUsers = true;
								} else {
									$scope.allowMoreUsers = false;
								}

					}, function(error) {
					});

				},
				function(error1) {
					$('#billing-errors').text(error1.data.message).addClass(
							'alert alert-error').css('color', 'red');
				});

	};

	$scope.addUser = function() {
		localStorage.setItem('FROM_USERS', 'TRUE');

		window.location.href = '/gtn/usermgt-index.html#/adduser';
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
	
	$scope.addUserNew = function(fromListPage) {
		$scope.errors.length = 0;
		$scope.validateNewUser();
		if($scope.errors.length > 0){
			scroll();
			return;
		}
		
		subscriptionService
				.addAdditionalUsers($scope.user.firstname,
						$scope.user.lastname, $scope.user.email)
				.then(
						function(success) {
							//$scope.message = success.data.data.Message;
													
							if (fromListPage) {
								
								//$scope.users.push(success.data.data.user);
								$scope.user = {firstname: '', lastname: '', email: ''};
								$scope.errors.length = 0;
								
								var user1 = angular.fromJson($window.localStorage['LoggedInUser']);
								$http(
										{
											method : 'POST',
											url : '/gtn/getAdditionalUsers',
											data : {
												"email" : $cookies.get('username'),
												"id" : user1.id
											},
											headers : {
												'Content-Type' : 'application/json',
												'Authorization' : 'Basic '
														+ btoa($cookies.get('username')
																+ ':' + $cookies.get

																('password'))
											}

										}).then(function(success) {

											$scope.users = [];
											$scope.users = success.data;
											if ($scope.users.length < $scope.NoOfUsersAllowed) {
												$scope.allowMoreUsers = true;
											} else {
												$scope.allowMoreUsers = false;
											}

								}, function(error) {
									$scope.errors
										.push({
											form : '',
											field : '',
											msg : 'Error getting additional users.'
										});
									//alert(error);
									console.log(error);
								});
								
								//$window.location.href = '/gtn/usermgt-index.html#/usersList';
								//$window.location.reload();
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
							}
							else if (error.status == 400) {
								var msgs = error.data.data;
								$scope.errors
									.push({
										form : '',
										field : '',
										msg : error.data.data.Message
									});
							}
							else {
								$scope.errors
										.push({
											form : '',
											field : '',
											msg : 'Server encountered error, please try later.'
										});
							}
						});
	};
	
	
	
	$scope.deleteUser = function(user, ev) {
		
		var confirm = $mdDialog.confirm()
	        .title('Delete')
	        .textContent('Are you sure you want to delete?')
	        .ariaLabel('')
	        .targetEvent(ev)
	        .ok('OK')
	        .cancel('Cancel');

        $mdDialog.show(confirm).then(function() {
     	   
      			blockScroll();
	 			
	 			var user1 = angular.fromJson(window.localStorage['LoggedInUser']);

				var dataTobeSent = {
					"id" : user,

				}
				var headerTobeSent = {
					'Authorization' : 'Basic '
							+ btoa($.cookie('username') + ':'
									+ $.cookie('password'))
				}
				$http(
						{
							method : 'POST',
							url : '/gtn/deleteUser',
							data : JSON.stringify(dataTobeSent),
							responseType : 'json',

							headers : {
								'Content-Type' : 'application/json',
								'Authorization' : 'Basic '
										+ btoa($cookies.get('username') + ':'
												+ $cookies.get

												('password'))
							}

						}).then(
						function(success) {
							
							//$scope.users = $.grep($scope.users, function(element, index){return element.id == user.id}, true);
							$scope.user = {firstname: '', lastname: '', email: ''};
							$scope.errors.length = 0;
							
							var user1 = angular.fromJson($window.localStorage['LoggedInUser']);
							$http(
									{
										method : 'POST',
										url : '/gtn/getAdditionalUsers',
										data : {
											"email" : $cookies.get('username'),
											"id" : user1.id
										},
										headers : {
											'Content-Type' : 'application/json',
											'Authorization' : 'Basic '
													+ btoa($cookies.get('username')
															+ ':' + $cookies.get

															('password'))
										}

									}).then(function(success) {

										$scope.users = [];
										$scope.users = success.data;
										if ($scope.users.length < $scope.NoOfUsersAllowed) {
											$scope.allowMoreUsers = true;
										} else {
											$scope.allowMoreUsers = false;
										}

							}, function(error) {
								$scope.errors
									.push({
										form : '',
										field : '',
										msg : 'Error getting additional users.'
									});
								//alert(error);
								console.log(error);
							});
							
							
							//window.location.reload();
							/*$('#billing-errors').text("User  deleted successfully")
									.addClass('alert alert-error').css('color',
											'green');*/

						},
						function(error) {
							$('#billing-errors').text(error.data.message).addClass(
									'alert alert-error').css('color', 'red');
						});
     	   
        	}, function() {
        		
        });
		
		
			

		

	}
	
	
	
	$scope.clearMessages = function(){
			$scope.errors.length = 0;
			$scope.warnings.length = 0;
			$scope.infos.length = 0;
	}
});