var restControllers = angular.module('restControllers', ['ngCookies', 'angularPayments', 'angular-md5', 'gtnDashboard', 'ngMaterial', 'mdDataTable']);
var lhsFlag = false;

restControllers.controller('HomeCtrl', [
    '$http',
    '$scope',
    '$rootScope',
    '$location',
    '$window', 'md5', '$cookies', 'subscriptionService','publicationService', 'userService', 'alertService', 'messageService', 'commonService', 'DataService', '$mdDialog', 
    function($http, $scope, $rootScope, $location, $window, md5, $cookies, subscriptionService,publicationService, userService, alertService, messageService, commonService, DataService, $mdDialog) {
        $scope.message = false;
        $scope.username = localStorage.getItem("UserId");
        $scope.alertCount = "";
        $scope.alerts = [];
        $scope.obj = {};
        $scope.LoggedInUser = {};
        $scope.obj.messageCount = "";
        $scope.freeTrailEmail = "";
        $scope.cart = DataService.cart;
        $scope.errors = [];
        $scope.infos = [];
        
        $scope.$on('$routeChangeSuccess', function() {
			$('.selectpicker').selectpicker();
			angular.element('html,body').animate({ scrollTop: 0 }, 250);
			//$scope.clearMessages();
		});
        
        
        $scope.mdConfirm = function(ev){

			var confirm = $mdDialog.confirm()
	            .title('Delete')
	            .textContent('Are you sure you want to delete?')
	            .ariaLabel('')
	            .targetEvent(ev)
	            .ok('OK')
	            .cancel('Cancel');
			
			return confirm;
 		}

        $scope.validateUserSignUp = function() {
            $scope.errors.length = 0;

            if (!$scope.signupform.name.$valid) {
                var errorObj = $scope.signupform.name.$error;
                commonService.processNgError(errorObj, $scope.errors, 'signupform', 'input[name=name]', 'First Name');
                error = true;
            }
            if (!$scope.signupform.name2.$valid) {
                var errorObj = $scope.signupform.name2.$error;
                commonService.processNgError(errorObj, $scope.errors, 'signupform', 'input[name=name2]', 'Last Name');
                error = true;
            }
            if (!$scope.signupform.email.$valid) {
                var errorObj = $scope.signupform.email.$error;
                commonService.processNgError(errorObj, $scope.errors, 'signupform', 'input[name=email]', 'Business Email Id');
                error = true;
            }
            if (!$scope.signupform.phone.$valid) {
                var errorObj = $scope.signupform.phone.$error;
                commonService.processNgError(errorObj, $scope.errors, 'signupform', 'input[name=phone]', 'Phone');
                error = true;
            }
            if (!$scope.signupform.passwordfield2.$valid) {
                var errorObj = $scope.signupform.passwordfield2.$error;
                commonService.processNgError(errorObj, $scope.errors, 'signupform', 'input[name=passwordfield2]', 'Password');
                error = true;
            }
            if ($scope.errors.length > 0) {
                return false;
            } else {
                return true;
            }
        }
        
        $scope.loginInit = function(){
        	$scope.showDiv = true;
        	$scope.login = {};
        }
        $scope.showRegisterationDiv = function(){
        	$scope.showDiv = false;
        	$scope.clearMessages();
        	$scope.user = {};
        }
        $scope.showLoginDiv = function(){
        	$scope.showDiv = true;
        	$scope.clearMessages();
        	$scope.login = {};
        }
        
        $scope.clearMessages = function(){
        	$scope.errors = [];
            $scope.infos = [];
            angular.element('.invalid-ele').removeClass('invalid-ele');
        }
        
        $scope.signUp = function() {
            var validated = $scope.validateUserSignUp();
            if (validated) {
                var hashedPassword = md5.createHash($scope.user.password);
                userService.signUp($scope.user.name, $scope.user.name2, $scope.username, hashedPassword, $scope.user.phone)
                    .then(function(success) {
                        if (success.data.data.Status == 'Success') {
                            $cookies.put('username', success.data.data.Username);
                            $cookies.put('password', hashedPassword);
                            userService.login($cookies.get('username'), $cookies.get('password'))
                                .then(function(success) {
                                    window.localStorage['LoggedInUser'] = angular.toJson(success.data.LoggedInUser);
                                    // localStorage.setItem('LoggedInUser',success.data.LoggedInUser);

                                    $scope.LoggedInUser = success.data.LoggedInUser;

                                    var callCreatePostLogin = localStorage.getItem('callCreatePostLogin');
                                    if (callCreatePostLogin != undefined && callCreatePostLogin != null && callCreatePostLogin == "yes") {
                                        localStorage.removeItem('callCreatePostLogin');
                                        subscriptionService.createSubscription(angular.fromJson(localStorage.getItem('cartItems')), localStorage.getItem('carTotal'), localStorage.getItem('carTotalCurrency'), localStorage.getItem('noOfUsers'), localStorage.getItem('noOfCountries'))
                                            .then(function(success) {
                                                localStorage.removeItem('cartItems');
                                                localStorage.removeItem('carTotal');
                                                localStorage.removeItem('carTotalCurrency');
                                                localStorage.removeItem('noOfUsers');
                                                localStorage.removeItem('noOfCountries');
                                                $cookies.put('subscriptionId', success.data.data.SubId);
                                                localStorage.setItem('subscriptionIdGenerated', success.data.data.SubId);
                                                localStorage.setItem('billingamount', success.data.data.SubTotal);
                                                if (localStorage.getItem('FROM_SUB_LIST') == 'TRUE') {
                                                    $window.location.href = '/gtn/usermgt-index.html#/subscriptionDetails';
                                                    $window.location.reload();
                                                } else
                                                    $window.location.href = '/gtn/usermgt-index.html#/payment';
                                            }, function(error) {

                                            });
                                    } else {
                                    	//changed by naveen 01-Aug-17
                                    	$window.location.href = '/gtn/usermgt-index.html#/subscriptionDetails';                                    	
                                        //$window.location.href = '/gtn/usermgt-index.html#/subscription';
                                    }
                                }, function(error) {

                                });

                        } else {
                        	//alert(success.data.data.Message);
                            //$scope.message = success.data.data.Message;
                            
                            $scope.errors.push({
                                form: '',
                                field: '',
                                msg: success.data.data.Message
                            });
                            commonService.processMsg($scope.errors, 'error');
                        }
                    }, function(error) {
                        if (error.status == 417) {
                            var msgs = error.data.data;
                            angular.forEach(msgs, function(index, value) {
                                $scope.errors.push({
                                    form: '',
                                    field: '',
                                    msg: index
                                });
                            });
                        } else {
                            $scope.errors.push({
                                form: '',
                                field: '',
                                msg: 'Server encountered error, please try later.'
                            });
                        }
                    });
            } else {
                commonService.processMsg($scope.errors, 'error');
            }
        };

        $scope.validateUsername = function() {
            $scope.errors.length = 0;
            if (!$scope.loginform.email.$valid) {
                var errorObj = $scope.loginform.email.$error;
                commonService.processNgError(errorObj, $scope.errors, 'loginform', 'input[name=email]', 'Business Email Id');
                error = true;
            }
            if (!$scope.loginform.pass.$valid) {
                var errorObj = $scope.loginform.pass.$error;
                commonService.processNgError(errorObj, $scope.errors, 'loginform', 'input[name=pass]', 'Password');
                error = true;
            }
            
            if ($scope.errors.length > 0) {
                return false;
            } else {
                return true;
            }
        }
        $scope.loginFn = function(validateReq) {
        	if(validateReq){
        		var validated = $scope.validateUsername();
        	}else{
        		var validated = true;
        	}
            
            if (validated || !validateReq) {
                var hashedPassword = md5.createHash($scope.login.password);
                userService.login($scope.login.username, hashedPassword)
                    .then(function(success) {
                        // localStorage.setItem('LoggedInUser',success.data.LoggedInUser);
                        window.localStorage['LoggedInUser'] = angular.toJson(success.data.LoggedInUser);
                        $scope.LoggedInUser = success.data.LoggedInUser;

                        $cookies.put('username', $scope.login.username);
                        $cookies.put('password', hashedPassword);

                        var callCreatePostLogin = localStorage.getItem('callCreatePostLogin');
                        if (callCreatePostLogin != undefined && callCreatePostLogin != null && callCreatePostLogin == "yes") {
                            localStorage.removeItem('callCreatePostLogin');

                            // save subscriptions without login
                            subscriptionService.createSubscription(angular.fromJson(localStorage.getItem('cartItems')), localStorage.getItem('carTotal'), localStorage.getItem('carTotalCurrency'), localStorage.getItem('noOfUsers'), localStorage.getItem('noOfCountries'))
                                .then(function(success) {
                                    localStorage.removeItem('cartItems');
                                    localStorage.removeItem('carTotal');
                                    localStorage.removeItem('carTotalCurrency');
                                    localStorage.removeItem('noOfUsers');
                                    localStorage.removeItem('noOfCountries');
									var doesSubscriptionExist = success.data.data.message;
                                    
                                    if(doesSubscriptionExist!=null && doesSubscriptionExist!=undefined && doesSubscriptionExist=='ERROR_CODE_EXISTS') {
                                   $scope.errors.push({
                            form: '',
                            field: '',
                            msg: 'Subscription already exists.Redirecting to Subscriptions page'
                        });	
                                   	$window.location.href = '/gtn/usermgt-index.html#/subscriptionDetails';
                                   	 
                                   
                                   }
                                    else {
                                    $cookies.put('subscriptionId', success.data.data.SubId);
                                    localStorage.setItem('subscriptionIdGenerated', success.data.data.SubId);
                                    localStorage.setItem('billingamount', success.data.data.SubTotal);
                                    $window.location.href = '/gtn/usermgt-index.html#/payment';
								}

                                }, function(error) {

                                });
                        } else {
                            $window.location.href = '/gtn/dashboard-index.html';
                        }
                    }, function(error) {
                        $scope.errors.push({
                            form: '',
                            field: '',
                            msg: 'Invalid Username or Password.'
                        });
                        commonService.processMsg($scope.errors, 'error');
                        console.log('error: data = ', error);
                    });
            } else {
                commonService.processMsg($scope.errors, 'error');
            }
        };

        $scope.logout = function() {
            $cookies.remove("username");
            $cookies.remove("password");
            window.localStorage.clear();
            var cookies = $cookies.getAll();
            angular.forEach(cookies, function(v, k) {
                $cookies.remove(k);
            });
            $window.location.href = '/gtn/index.html';
        };
        
        $scope.forgotPasswordInit = function(){
        	if(!$scope.username){
        		$location.path('/login');
        	}
        	$scope.fp = {};
        }

        $scope.forgotPassword = function() {
        	$scope.clearMessages();
            userService.forgotPassword($scope.login.username)
                .then(function(success) {
                    // $scope.message = success.data.data.Message;
                    $scope.infos.push({
                        form: '',
                        field: '',
                        msg: success.data.data.Message
                    });
                    localStorage.setItem("UserId", success.data.data.UserId);
                    $window.location.href = '/gtn/index.html#/forgotpassword';

                }, function(error) {
                    if (error.status == 417) {
                        var msgs = error.data.data.Message;
                        $scope.errors.push({
                            form: '',
                            field: '',
                            msg: error.data.data.Message
                        });
                        $scope.login.username = error.data.data.UserId;
                    } else {
                        $scope.errors.push({
                            form: '',
                            field: '',
                            msg: 'Server encountered error, please try later.'
                        });
                    }
                });
        };

        $scope.resetPassword = function() {
            localStorage.removeItem("UserId");
            $scope.clearMessages();
            
            if(commonService.isEmpty($scope.username)){
            	$scope.errors.push({
                    form: '',
                    field: '',
                    msg: 'Email Id is required.'
                });
            }
            if(commonService.isEmpty($scope.fp.otp)){
            	$scope.errors.push({
                    form: '',
                    field: '',
                    msg: 'Otp is required.'
                });
            }
            if(commonService.isEmpty($scope.fp.newpwd)){
            	$scope.errors.push({
                    form: '',
                    field: '',
                    msg: 'New password is required.'
                });
            }
            if(commonService.isEmpty($scope.fp.confirmpwd)){
            	$scope.errors.push({
                    form: '',
                    field: '',
                    msg: 'Confirm password is required.'
                });
            }
            if($scope.fp.newpwd != $scope.fp.confirmpwd){
            	$scope.errors.push({
                    form: '',
                    field: '',
                    msg: 'Password do not match.'
                });
            }
            
            if($scope.errors.length > 0){
            	commonService.processMsg($scope.errors, 'error');
            	return;
            }
            
            var hashedNewpwd = md5.createHash($scope.fp.newpwd);
            
            userService.resetPassword($scope.username, hashedNewpwd, $scope.fp.otp)
                .then(function(success) {
                    // if (success.data.data.Status == 'Success') {
                	$scope.login = {};
                    $scope.login.username = $scope.username;
                    $scope.login.password = $scope.fp.newpwd;
                    $scope.loginFn(false);
                    /*
                     * } else { $scope.message = success.data.data.Message;
                     * $window.location.href =
                     * '/gtn/index.html#!/forgotpassword'; }
                     */

                }, function(error) {
                    if (error.status == 417) {
                        var msgs = error.data.data;
                        angular.forEach(msgs, function(index, value) {
                            $scope.errors.push({
                                form: '',
                                field: '',
                                msg: index
                            });
                        });
                        //$window.location.href = '/gtn/index.html#/forgotpassword';
                    } else {
                        $scope.errors.push({
                            form: '',
                            field: '',
                            msg: 'Server encountered error, please try later.'
                        });
                    }
                });
        };

        $scope.startFreeTrail = function() {
            localStorage.setItem('freeTrail', "yes");
            // $scope.login.username = $scope.freetrial.email;
            // $scope.user.email = $scope.freetrial.email;
            $scope.freeTrailEmail = $scope.email;

            $scope.freeTrailEmail = $('#freeTrialEmail').val();

            $window.location.href = '/gtn/index.html#/login';
        };
        
        
        //init function of dashboard page
        $scope.homeinit = function() {
            alertService.getAlerts()
                .then(function(success) {
                    $scope.alertCount = success.data.data.length;
                    $scope.alerts = success.data.data;
                }, function(error) {

                });
            messageService.getUnreadMessageCount()
                .then(function(successMessage) {
                    $scope.obj.messageCount = successMessage.data.data;
                }, function(errorMessage) {});
            $scope.LoggedInUser = angular.fromJson($window.localStorage['LoggedInUser']);
            
            userService.getDashBoardLHSMenu()
            	.then(function(response) {
            			$scope.menuItems = response.data.data;
            			lhsFlag = true;
            			//createLhsMenu();
            			console.log(response);
            		}, function(error) {
            			$scope.menuItems = [];
            			console.log(error);
			});
            
            $scope.refreshCart();
            
        }

        $scope.refreshCart = function(updateAmount){
        
        	
        	 userService.getCartInfo()
	        	.then(function(response) {
	        	
	        			if(response.data.success == true){
	        				$scope.userCart = {};
	        				$scope.userCart.getTotalCount = response.data.data.noOfUsers;
	        				$scope.userCart.getTotalPrice = response.data.data.totalCost;
	        	
	        			publicationService.getPublicationCartInfo()
				        	.then(function(response) {
				        	
				        	
				        	if(response.data.success == true){
		        				$scope.publicationCart={};	 
	        					$scope.publicationCart.getTotalCount = response.data.data.totalCount;
		        				$scope.publicationCart.getTotalPrice = response.data.data.totalCost;
		        				
	       
	        				
        					if(response.data.data.publications!=null){
    							$scope.publicationCart.cartItems=[];
    							for (j = 0; j < response.data.data.publications.length; j++) {
    								if($scope.publicationCart.cartItems.indexOf(response.data.data.publications[j])==-1) {
	        							$scope.publicationCart.cartItems.push(response.data.data.publications[j]);
	        							
	        							//$scope.cart.addItem(id,title,'Publication', description,price, 1);
	        							$scope.cart.addItem(response.data.data.publications[j].id,response.data.data.publications[j].title,'Publication',response.data.data.publications[j].description,response.data.data.publications[j].price,1, response.data.data.publications[j].currency);
	        						}
    							}
        					}	
		
						//$scope.$broadcast('publicationCart', $scope.publicationCart);
	        				 $scope.finalCart={};
        		
						
	        			$scope.finalCart.getTotalCount = parseInt($scope.userCart.getTotalCount )+parseInt($scope.publicationCart.getTotalCount );
			$scope.finalCart.getTotalPrice = parseFloat($scope.userCart.getTotalPrice )+parseFloat($scope.publicationCart.getTotalPrice );
	        	
	        	if(updateAmount)
				$scope.$broadcast('updateAmountOnPayment', $scope.finalCart.getTotalPrice);
	        			
	        			}
	        			//createLhsMenu();
	        			console.log(response);
	        		}, function(error) {
			
	        	
			$scope.finalCart={};
        		
			$scope.publicationCart = {};
        	 $scope.publicationCart.cartItems=[];		
					$scope.publicationCart.getTotalCount = 0;
					$scope.publicationCart.getTotalPrice = 0.0;
				$scope.finalCart.getTotalCount = parseInt($scope.userCart.getTotalCount )+parseInt($scope.publicationCart.getTotalCount );
			$scope.finalCart.getTotalPrice = parseFloat($scope.userCart.getTotalPrice )+parseFloat($scope.publicationCart.getTotalPrice );
					
	        	if(updateAmount)
				$scope.$broadcast('updateAmountOnPayment', $scope.finalCart.getTotalPrice);
	        		
	        			console.log(error);
			});
			
	        				
	        			}
	        			//createLhsMenu();
	        			console.log(response);
	        		}, function(error) {
	        		
	        			$scope.userCart = {};
					$scope.userCart.getTotalCount = 0;
					$scope.userCart.getTotalPrice = 0.0;
				publicationService.getPublicationCartInfo()
	        	.then(function(response) {
	       
	        	if(response.data.success == true){
	         $scope.publicationCart = {};
        	 $scope.publicationCart.cartItems=[];	
	        				;
	        				$scope.publicationCart.getTotalCount = response.data.data.totalCount;
	        				$scope.publicationCart.getTotalPrice = response.data.data.totalCost;
	        		 $scope.finalCart={};
        		
	        	$scope.finalCart.getTotalCount = parseInt($scope.userCart.getTotalCount )+parseInt($scope.publicationCart.getTotalCount );
			$scope.finalCart.getTotalPrice = parseFloat($scope.userCart.getTotalPrice )+parseFloat($scope.publicationCart.getTotalPrice );
				if(response.data.data.publications!=null){
			for (j = 0; j < response.data.data.publications.length; j++) {
	        					if($scope.publicationCart.cartItems.indexOf(response.data.data.publications[j])==-1) {
	        							$scope.publicationCart.cartItems.push(response.data.data.publications[j]);
	        							$scope.cart.addItem(response.data.data.publications[j].id,response.data.data.publications[j].title,'Publication',response.data.data.publications[j].description,response.data.data.publications[j].price,1, response.data.data.publications[j].currency);
	        					}
						
	        	
						}
							
		}
	        	if(updateAmount)
				$scope.$broadcast('updateAmountOnPayment', $scope.finalCart.getTotalPrice);
	        		
	        		
	        		}
	        			//createLhsMenu();
	        			console.log(response);
	        		}, function(error) {
				$scope.publicationCart = {};
        			$scope.publicationCart.cartItems=[];	
	        		$scope.finalCart={};
        				
					
					$scope.publicationCart.getTotalCount = 0;
					$scope.publicationCart.getTotalPrice = 0.0;
	        			console.log(error);
			$scope.finalCart.getTotalCount = parseInt($scope.userCart.getTotalCount )+parseInt($scope.publicationCart.getTotalCount );
						$scope.finalCart.getTotalPrice = parseFloat($scope.userCart.getTotalPrice )+parseFloat($scope.publicationCart.getTotalPrice );
	        	if(updateAmount)
							$scope.$broadcast('updateAmountOnPayment', $scope.finalCart.getTotalPrice);
	        	
			});
				
	        			console.log(error);
			});
				
        				
        }
    
    }
]);

/**
 * call LHS Menu code
 */
function createLhsMenu(){

	ddaccordion.urlparamselect(config.headerclass)
	var persistedheaders=ddaccordion.getCookie(config.headerclass)
	ddaccordion.headergroup[config.headerclass]=$('.'+config.headerclass) //remember header group for this accordion
	ddaccordion.contentgroup[config.headerclass]=$('.'+config.contentclass) //remember content group for this accordion
	ddaccordion.$docbody=(window.opera)? (document.compatMode=="CSS1Compat"? jQuery('html') : jQuery('body')) : jQuery('html,body')
	var $headers=ddaccordion.headergroup[config.headerclass]
	var $subcontents=ddaccordion.contentgroup[config.headerclass]
	config.cssclass={collapse: config.toggleclass[0], expand: config.toggleclass[1]} //store expand and contract CSS classes as object properties
	config.revealtype=config.revealtype || "click"
	config.revealtype=config.revealtype.replace(/mouseover/i, "mouseenter")
	if (config.revealtype=="clickgo"){
		config.postreveal="gotourl" //remember added action
		config.revealtype="click" //overwrite revealtype to "click" keyword
	}
	if (typeof config.togglehtml=="undefined")
		config.htmlsetting={location: "none"}
	else
		config.htmlsetting={location: config.togglehtml[0], collapse: config.togglehtml[1], expand: config.togglehtml[2]} //store HTML settings as object properties
	config.oninit=(typeof config.oninit=="undefined")? function(){} : config.oninit //attach custom "oninit" event handler
	config.onopenclose=(typeof config.onopenclose=="undefined")? function(){} : config.onopenclose //attach custom "onopenclose" event handler
	var lastexpanded={} //object to hold reference to last expanded header and content (jquery objects)
	var expandedindices=ddaccordion.urlparamselect(config.headerclass) || ((config.persiststate && persistedheaders!=null)? persistedheaders : config.defaultexpanded)
	if (typeof expandedindices=='string') //test for string value (exception is config.defaultexpanded, which is an array)
		expandedindices=expandedindices.replace(/c/ig, '').split(',') //transform string value to an array (ie: "c1,c2,c3" becomes [1,2,3]
	if (expandedindices.length==1 && expandedindices[0]=="-1") //check for expandedindices value of [-1], indicating persistence is on and no content expanded
		expandedindices=[]
	if (config["collapseprev"] && expandedindices.length>1) //only allow one content open?
		expandedindices=[expandedindices.pop()] //return last array element as an array (for sake of jQuery.inArray())
	if (config["onemustopen"] && expandedindices.length==0) //if at least one content should be open at all times and none are, open 1st header
		expandedindices=[0]
	$headers.each(function(index){ //loop through all headers
		var $header=$(this)
		if (/(prefix)|(suffix)/i.test(config.htmlsetting.location) && $header.html()!=""){ //add a SPAN element to header depending on user setting and if header is a container tag
			$('<span class="accordprefix"></span>').prependTo(this)
			$('<span class="accordsuffix"></span>').appendTo(this)
		}
		$header.attr('headerindex', index+'h') //store position of this header relative to its peers
		$subcontents.eq(index).attr('contentindex', index+'c') //store position of this content relative to its peers
		var $subcontent=$subcontents.eq(index)
		var $hiddenajaxlink=$subcontent.find('a.hiddenajaxlink:eq(0)') //see if this content should be loaded via ajax
		if ($hiddenajaxlink.length==1){
			$header.data('ajaxinfo', {url:$hiddenajaxlink.attr('href'), cacheddata:null, status:'none'}) //store info about this ajax content inside header
		}
		var needle=(typeof expandedindices[0]=="number")? index : index+'' //check for data type within expandedindices array- index should match that type
		if (jQuery.inArray(needle, expandedindices)!=-1){ //check for headers that should be expanded automatically (convert index to string first)
			ddaccordion.expandit($header, $subcontent, config, false, false, !config.animatedefault) //3rd last param sets 'isuseractivated' parameter, 2nd last sets isdirectclick, last sets skipanimation param
			lastexpanded={$header:$header, $content:$subcontent}
		}  //end check
		else{
			$subcontent.hide()
			config.onopenclose($header.get(0), parseInt($header.attr('headerindex')), $subcontent.css('display'), false) //Last Boolean value sets 'isuseractivated' parameter
			ddaccordion.transformHeader($header, config, "collapse")
		}
	})
	//if (config["scrolltoheader"] && lastexpanded.$header)
		//ddaccordion.scrollToHeader(lastexpanded.$header)
	$headers.bind("evt_accordion", function(e, isdirectclick, scrolltoheader){ //assign CUSTOM event handler that expands/ contacts a header
			var $subcontent=$subcontents.eq(parseInt($(this).attr('headerindex'))) //get subcontent that should be expanded/collapsed
			if ($subcontent.css('display')=="none"){
				ddaccordion.expandit($(this), $subcontent, config, true, isdirectclick, false, scrolltoheader) //2nd last param sets 'isuseractivated' parameter
				if (config["collapseprev"] && lastexpanded.$header && $(this).get(0)!=lastexpanded.$header.get(0)){ //collapse previous content?
					ddaccordion.collapseit(lastexpanded.$header, lastexpanded.$content, config, true) //Last Boolean value sets 'isuseractivated' parameter
				}
				lastexpanded={$header:$(this), $content:$subcontent}
			}
			else if (!config["onemustopen"] || config["onemustopen"] && lastexpanded.$header && $(this).get(0)!=lastexpanded.$header.get(0)){
				ddaccordion.collapseit($(this), $subcontent, config, true) //Last Boolean value sets 'isuseractivated' parameter
			}
		})
	$headers.bind(config.revealtype, function(){
		if (config.revealtype=="mouseenter"){
			clearTimeout(config.revealdelay)
			var headerindex=parseInt($(this).attr("headerindex"))
			config.revealdelay=setTimeout(function(){ddaccordion.expandone(config["headerclass"], headerindex, config.scrolltoheader)}, config.mouseoverdelay || 0)
		}
		else{
			$(this).trigger("evt_accordion", [true, config.scrolltoheader]) //last parameter indicates this is a direct click on the header
			return false //cancel default click behavior
		}
	})
	$headers.bind("mouseleave", function(){
		clearTimeout(config.revealdelay)
	})
	config.oninit($headers.get(), expandedindices)
	$(window).bind('unload', function(){ //clean up and persist on page unload
		$headers.unbind()
		var expandedindices=[]
		$subcontents.filter(':visible').each(function(index){ //get indices of expanded headers
			expandedindices.push($(this).attr('contentindex'))
		})
		if (config.persiststate==true && $headers.length>0){ //persist state?
			expandedindices=(expandedindices.length==0)? '-1c' : expandedindices //No contents expanded, indicate that with dummy '-1c' value?
			ddaccordion.setCookie(config.headerclass, expandedindices)
		}
	})
}






restControllers.controller('PaymentGatewayCtrl', [
    '$http',
    '$scope', '$window', '$cookies',
    function($http, $scope, $window, $cookies) {
        $scope.fillCountries = function() {

            $http({
                method: 'POST',
                url: '/gtn/getCountries',
                headers: {
                    'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
                },
                data: {}
            }).then(
                function(result) {
                    $scope.countries = result;
                    var len = result.data.length;

                    $("#card_country").empty();

                    for (var i = 0; i < len; i++) {
                        var id = result.data[i]['countryCode'];
                        var name = result.data[i]['countryName'];
                        $("#card_country").append(
                            "<option value='" + id + "'>" + name +
                            "</option>");

                    }

                },
                function(error) {
                    window.alert("errorssssssssss11111111111");
                });
        };
        // Calling the function to load the data on pageload
        $scope.fillCountries();
        $scope.billingamount = localStorage.getItem('billingamount');
        $scope.userBilling = [];
        $scope.userBilling = angular.fromJson($window.localStorage['USER_BILLING']);
        $scope.reportError = function(msg) {
            // Show the error in the form:
            $('#payment-errors').text(msg).addClass('alert alert-error').css('color', 'red');
            // re-enable the submit button:
            // $('#submitBtn').prop('disabled', false);
            return false;
        };

        $scope.stripeCallback = function() {
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
                var msg = 'The credit card number appears to be invalid.';
                $('#payment-errors').text(msg)
                    .addClass('alert alert-error').css('color', 'red');
                // re-enable the submit button:
                // $('#submitBtn').prop('disabled', false);
                return false;
            }
            if (!Stripe.card.validateCVC(cvcNum)) {
                error = true;
                var msg = 'The CVC number appears to be invalid.';
                $('#payment-errors').text(msg)
                    .addClass('alert alert-error').css('color', 'red');
                // re-enable the submit button:
                // $('#submitBtn').prop('disabled', false);
                return false;

            }
            if (!Stripe.card.validateExpiry(month, year)) {
                error = true;
                var msg = 'The expiration date appears to be invalid.';
                $('#payment-errors').text(msg)
                    .addClass('alert alert-error').css('color', 'red');
                // re-enable the submit button:
                // $('#submitBtn').prop('disabled', false);
                return false;

            }
            if (!error) {

                // Get the Stripe token:
                Stripe.card.createToken({
                    number: ccNum,
                    cvc: cvcNum,
                    exp_month: month,
                    exp_year: year,
                    name: add_name,
                    address_zip: add_name,
                    address_line1: add_1,
                    address_line2: add_2,
                    address_city: add_city,
                    address_state: add_state,
                    address_country: add_country

                }, 100, stripeCallback2);

            }

        };

        $scope.submitToken = function(code, result) {
            $http({
                method: 'POST',
                url: '/gtn/chargeCard',
            }).success(function(data) {
                window.alert("succ");
            }).error(function() {
                window.alert("errorssssssssss");
            });
        };

    }
]);
restControllers.controller('ProfileCtrl', [
    '$http',
    '$scope',
    '$rootScope',
    '$location',
    '$window', '$cookies', 'subscriptionService',
    function($http, $scope, $rootScope, $location, $window, $cookies, subscriptionService) {

        $scope.loadUser = function() {
            // var user1=localStorage.getItem('LoggedInUser');
            var user1 = angular.fromJson($window.localStorage['LoggedInUser']);

            $scope.user = user1;

        };
        $scope.modifyUserDetails = function() {
            var fd = new FormData();
            // var file=$form.get(0).fileInput.value;
            var file = $scope.myFile;

            fd.append("file", file);
            $scope.LoggedUser = {};
            $scope.LoggedUser.email = $scope.user.emailId;
            $scope.LoggedUser.company = $scope.user.companyName;
            $scope.LoggedUser.phone = $scope.user.phone;

            $scope.LoggedUser.firstName = $scope.user.firstName;
            $scope.LoggedUser.title = $scope.user.title;

            $scope.LoggedUser.lastName = $scope.user.lastName;
            fd.append("LoggedUser", angular.toJson($scope.LoggedUser, true));
            $http({
                    method: 'POST',
                    url: '/gtn/modifyUserDetails',
                    transformRequest: angular.identity,
                    headers: {
                        'Content-Type': undefined,
                        'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' +

                            $cookies.get('password'))
                    },
                    data: fd
                })
                .then(function(success) {
                    // alert("success")

                    // localStorage.setItem('LoggedInUser',success.data.LoggedInUser);
                    window.localStorage['LoggedInUser'] = angular.toJson(success.data.LoggedInUser);
                    $scope.user.image = success.data.LoggedInUser.image;
                    $('#profile-errors').text("Profile Changed Successfully").addClass(
                        'alert alert-error').css('color', 'green');

                    // $window.location.reload();
                }).then(function(error) {

                    $('#profile-errors').text(error.data.message).addClass(
                        'alert alert-error').css('color', 'red');

                });


        };
    }
]);
restControllers.controller('BillingCtrl', [
    '$http',
    '$scope',
    '$rootScope',
    '$location',
    '$window', '$cookies', 'subscriptionService',
    function($http, $scope, $rootScope, $location, $window, $cookies, subscriptionService) {
        $scope.makePrimary = function(billing) {

            var user1 = angular.fromJson(window.localStorage['LoggedInUser']);
            var dataTobeSent = {
                "billingname": billing.firstName,
                "ccNumber": billing.cardNo,
                "expiry": billing.exp_date,
                "add_1": billing.add_1,
                "add_2": billing.add_2,
                "card_city": billing.city,
                "card_state": billing.state,
                "card_zip": billing.zipcode,
                "card_country": billing.country_code,
                "primary": 'Y',
                "subscriptionId": angular.fromJson(window.localStorage['subscriptionIdGenerated']),
                "userId": user1.id

            }


            $http({
                method: 'POST',

                responseType: 'json',
                url: '/gtn/chargeCard',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' +

                        $cookies.get('password'))
                },
                data: JSON.stringify(dataTobeSent)

            }).then(function(success) {

                $window.location.reload();
            }, function(error) {

                $window.location.reload();

            });

        };
        $scope.editPayment = function(billing) {


            localStorage.setItem('FROM_BILLING', 'TRUE');
            window.localStorage['USER_BILLING'] = angular.toJson(billing);

            $window.location.href = '/gtn/usermgt-index.html#/payment';
        };
        $scope.deletePayment = function(billing) {
            var user1 = angular.fromJson(window.localStorage['LoggedInUser']);

            var dataTobeSent = {
                "ccNumber": billing.cardNo,
                "userId": user1.id,

            }
            var headerTobeSent = {
                'Authorization': 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
            }
            $http({
                method: 'POST',
                url: '/gtn/deletePaymentMethod',
                data: JSON.stringify(dataTobeSent),
                responseType: 'json',

                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
                }
            }).then(function(success) {

                window.location.reload();
                $('#billing-errors').text("Payment method deleted successfully").addClass('alert alert-error').css('color', 'green');

            }, function(error) {
                $('#billing-errors').text(error.data.message).addClass(
                    'alert alert-error').css('color', 'red');
            });

        };



        $scope.addPayment = function() {

            localStorage.removeItem('USER_BILLING');
            localStorage.setItem('FROM_BILLING', 'TRUE');

            $window.location.href = '/gtn/usermgt-index.html#/payment';
        };

        $scope.getBillingDetails = function() {

            $http({
                method: 'POST',
                url: '/gtn/getBillingDetails',
                data: {
                    "email": $cookies.get('username')
                },
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
                }
            }).then(function(success) {

                $scope.billingMethods = [];
                $scope.billingMethods = success.data;

            }, function(error) {
                alert(error);
            });

        };


    }
]);

restControllers.controller('SecurityCtrl', ['$http', '$scope', '$rootScope', '$location', '$window', 'md5', '$cookies',
    function($http, $scope,
        $rootScope, $location, $window, md5,
        $cookies) {

        $scope.changePassword = function() {
            if ($scope.user.oldPassword == $scope.user.newPassword) {
                $('#security-errors').text("Old and new Passwords cannot be same").addClass(
                    'alert alert-error').css('color', 'red');
                // re-enable the submit button:
                // $('#submitBtn').prop('disabled', false);
                return false;
            }
            console.log(md5.createHash($scope.user.oldPassword));
            return $http({
                method: 'POST',
                responseType: 'json',
                url: '/gtn/changePassword',
                headers: {
                    'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get('password'))
                },
                data: {
                    "email": $cookies.get('username'),
                    "oldPassword": md5.createHash($scope.user.oldPassword),
                    "newPassword": md5.createHash($scope.user.newPassword)

                },
            }).then(
                function(success) {
                    $('#security-errors').text("Password Changed Successfully.Please logout of the Application and login with the new password").addClass('alert alert-error').css('color', 'green');
                    // re-enable the submit button:
                    // $('#submitBtn').prop('disabled', false);
                    return false;

                },
                function(error) {
                    $('#security-errors').text(error.data.message).addClass(
                        'alert alert-error').css('color', 'red');
                    // re-enable the submit button:
                    $('#submitBtn').prop('disabled', false);
                    return false;

                });
        };


    }
]);


restControllers
    .controller(
        'storeCtrl', ['$http', '$scope', '$routeParams', 'DataService', '$rootScope', '$location', '$window', '$cookies',
            // ['$http','$scope','$routeParams','$rootScope','$location','$window','$cookies',
            // function($http, $scope, $routeParams, DataService,
            function($http, $scope, $routeParams, DataService,
                $rootScope, $location, $window, $cookies) {

                $scope.store = DataService.store;
                $scope.cart = DataService.cart;

                // use routing to pick the selected product

                if ($routeParams.productSku != null) {

                    $scope.product = $scope.store
                        .getProduct($routeParams.productSku);
                }

                $scope.shopCart = function(carTotal,
                    carTotalCurrency) {
                    return $http({
                        method: 'POST',
                        responseType: 'json',
                        url: '/gtn/shopCart',
                        headers: {
                            'Authorization': 'Basic ' + btoa($cookies.get('username') + ':' + $cookies.get

                                ('password'))
                        },
                        data: {
                            "totalCost": carTotal,
                            "totalCurrencyCode": carTotalCurrency,
                            "user": "nive"
                        },
                    }).then(
                        function(success) {
                            localStorage.setItem('billingamount', carTotal);
                            $scope.billingamount = carTotal;
                            $window.location.href = '/gtn/usermgt-index.html#/payment';
                        },
                        function(error) {
                            alert("error sa" + error);
                            localStorage.setItem(
                                'billingamount',
                                carTotal);
                            $location.url('#payment');
                        });

                };

            }
        ]);

function changeValueCheckbox(element) {
    alert(element.checked);
    if (element.checked) {
        element.value = 'on';
    } else {
        element.value = 'off';
    }
};

function stripeCallback2(status, result) {
    var $form = $('#user-form');

    // alert($form.get(0).amount.value);
    var token = result.id;

    if (result.error) {
        window.alert('it failed! error: ' + result.error.message);

        // $scope.error_message=result.error.message;
    } else {
        var primary = null;
        if ($form.get(0).same_as_primary == undefined) {
            primary = 'N';
        }
        if ($form.get(0).same_as_primary.value == 'on') {
            primary = 'Y';
        } else {

            primary = 'N';
        }
        var amountToBeCharged = null;
        if ($form.get(0).amount == undefined) {
            amountToBeCharged = '0';
        } else {
            amountToBeCharged = $form.get(0).amount.value * 100;
        }
        var user1 = angular.fromJson(window.localStorage['LoggedInUser']);

        var dataTobeSent = {
            "billingname": $form.get(0).billingname.value,
            "ccNumber": $form.get(0).card_num.value,
            "expiry": $form.get(0).card_expiry.value,
            "stripeToken": token,
            "add_1": $form.get(0).add_1.value,
            "add_2": $form.get(0).add_2.value,
            "card_city": $form.get(0).card_city.value,
            "card_state": $form.get(0).card_state.value,
            "card_zip": $form.get(0).card_zip.value,
            "card_country": $form.get(0).card_country.value,
            "primary": primary,
            "subscriptionId": localStorage.getItem('subscriptionIdGenerated'),
            "userId": user1.id,
            "amount": amountToBeCharged

        }
        var headerTobeSent = {
            'Authorization': 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
        }
        localStorage.removeItem('subscriptionIdGenerated');
        $.ajax({
            type: 'POST',
            url: '/gtn/chargeCard',
            responseType: 'json',
            data: JSON.stringify(dataTobeSent),
            contentType: "application/json; charset=utf-8",
            headers: JSON.stringify(headerTobeSent),
            success: function(r) {

                if (localStorage.getItem('FROM_BILLING') == 'TRUE') {

                    window.location.href = '/gtn/usermgt-index.html#/billing';
                    window.location.reload();

                } else {
                    window.location.href = '/gtn/usermgt-index.html#/usersList';
                    localStorage.removeItem('FROM_BILLING');
                }
            },
            error: OnError
        });

    }

};

function OnError(xhr, errorType, exception) {

    var responseText = jQuery.parseJSON(JSON.stringify(xhr.responseText));
    $('#payment-errors').text(responseText.message).addClass(
        'alert alert-error').css('color', 'red');
    // re-enable the submit button:
    $('#submitBtn').prop('disabled', false);
    return false;
}

var gtnClassGlobal = '';