restControllers
		.controller(
				'caseMgtCtrl',
				function($scope, $http, $rootScope, $cookies, $location,
						$window, productService, caseService, messageService,
						commonService) {
					$scope.cases = [];
					$scope.products = [];
					$scope.errors = [];
					$scope.getAllCasesByUser = function() {
						usrmgtLhs('support-li');
						
						$scope.supportTicketColumns =[
						                              'ticketId','createdOn','createdByName',
						                              'assignedToName','statusDesc','description',
						                              ];
						
						caseService
								.getCasesToDisplay()
								.then(
										function(success) {
											$scope.cases = success.data.data;
											
											/*$(document).on(".payAdd_table_ticket tbody tr", 'click', function() {
											    var selected = $(this).hasClass("highlight");
											    $(".payAdd_table_ticket tr").removeClass("highlight");
											    if(!selected)
											            $(this).addClass("highlight");
											});*/
											
											productService
													.getAllProducts()
													.then(
															function(success) {
																$scope.products = success.data.data;
															})
										},
										function(error) {
											console
													.log('error: data = ',
															error);
										});

					};
					$scope.validateCase = function() {
						$scope.errors.length = 0;

						if (!$scope.supportTicket.description.$valid) {
							var errorObj = $scope.supportTicket.description.$error;
							commonService.processNgError(errorObj,
									$scope.errors, 'supportTicket',
									'input[name=description]', 'Description');
							error = true;
						}
						if (!$scope.supportTicket.priority.$valid) {
							var errorObj = $scope.supportTicket.priority.$error;
							commonService.processNgError(errorObj,
									$scope.errors, 'supportTicket',
									'input[name=priority]', 'Priority');
							error = true;
						}
						if (!$scope.supportTicket.newMessage.$valid) {
							var errorObj = $scope.supportTicket.newMessage.$error;
							commonService.processNgError(errorObj,
									$scope.errors, 'supportTicket',
									'input[name=newMessage]', 'Message');
							error = true;
						}
						if ($scope.errors.length > 0) {
							return false;
						} else {
							return true;
						}
					}
					$scope.saveCase = function() {
						var validated = $scope.validateCase();
						if (validated) {
							caseService
									.saveCase(
											$cookies.get('username'),
											$scope.caseItem.description,
											$scope.caseItem.priority,
											$scope.caseItem.ticketId,
											"O",
											$scope.caseItem.selectedSubProduct.productId,
											$scope.caseItem.newMessage)
									.then(
											function(success) {
												$scope.cases = success.data.data;
												$scope.caseItem = {
													description : '',
													priority : '',
													ticketId : '',
													categoryDesc : ''
												};
												$scope.message = "Case added/updated.";
											},
											function(error) {
												if (error.status == 417) {
													var msgs = error.data.data;
													angular
															.forEach(
																	msgs,
																	function(
																			index,
																			value) {
																		$scope.errors
																				.push({
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
						} else {
							commonService.processMsg($scope.errors, 'error');
						}
					};
					
					function getCaseFromNo(caseList, ticketId){
						var obj;
						angular.forEach(caseList, function(value, key){
						     if(value.ticketId == ticketId){
						    	 obj = value;
						     }
						});
						return obj;
					}

					$scope.editCase = function(selectedCI) {
						var validated = $scope.validateCase();
						if (validated) {
							
							messageService
							.markMessageAsReadForCases(
									selectedCI)
							.then(
									function(success) {
										$scope.caseItem = getCaseFromNo($scope.cases, selectedCI);
										$scope.obj.messageCount = success.data.data;
										$scope.message = "";
									},
									function(error) {
										if (error.status == 417) {
											var msgs = error.data.data;
											angular
													.forEach(
															msgs,
															function(
																	index,
																	value) {
																$scope.errors
																		.push({
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
							
							
							/** code without table lib **/
							/*messageService
									.markMessageAsReadForCases(
											selectedCI.ticketId)
									.then(
											function(success) {
												$scope.caseItem = selectedCI;
												$scope.obj.messageCount = success.data.data;
												$scope.message = "";
											},
											function(error) {
												if (error.status == 417) {
													var msgs = error.data.data;
													angular
															.forEach(
																	msgs,
																	function(
																			index,
																			value) {
																		$scope.errors
																				.push({
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
											});*/
						} else {
							commonService.processMsg($scope.errors, 'error');
						}

					};

					$scope.closeCase = function(status) {
						var validated = $scope.validateCase();
						if (validated) {
							caseService
									.saveCase(
											$cookies.get('username'),
											$scope.caseItem.description,
											$scope.caseItem.priority,
											$scope.caseItem.ticketId,
											status,
											$scope.caseItem.selectedSubProduct.productId,
											$scope.caseItem.newMessage)
									.then(
											function(success) {
												$scope.cases = success.data.data;
												$scope.caseItem = {
													description : '',
													priority : '',
													ticketId : '',
													categoryDesc : ''
												};
												if(status == 'C'){
													$scope.message = "Case Closed.";
												}else if(status == 'O'){
													$scope.message = "Case Open.";
												}
												
											},
											function(error) {
												if (error.status == 417) {
													var msgs = error.data.data;
													angular
															.forEach(
																	msgs,
																	function(
																			index,
																			value) {
																		$scope.errors
																				.push({
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
						} else {
							commonService.processMsg($scope.errors, 'error');
						}

					};

					$scope.reset = function() {
						$scope.caseItem = {
							description : '',
							priority : '',
							ticketId : '',
							categoryDesc : ''
						};
						$scope.message = "";
					};

				});