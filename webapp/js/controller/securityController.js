 restControllers.controller('SecurityCtrl', ['$http', '$scope', '$rootScope', '$location', '$window', 'md5', '$cookies', 'validationService', '$mdDialog', 
     function($http, $scope,
         $rootScope, $location, $window, md5,
         $cookies, validationService, $mdDialog) {
         $scope.errors = [];
         $scope.warnings = [];
         $scope.infos = [];
         
         
         $scope.securityInit = function(){
        	 usrmgtLhs('security-li');
         }
         
         $scope.clearMessages = function() {
             $scope.errors.length = 0;
             $scope.warnings.length = 0;
             $scope.infos.length = 0;
         };
         $scope.validatePasswordForm = function() {
             $scope.errors.length = 0;

             if ($scope.user == undefined || validationService.isEmpty($scope.user.oldPassword)) {
                 $scope.errors.push({
                     form: 'user-form',
                     field: 'input[name=oldPassword]',
                     msg: "Old Password cannot be empty"
                 });
             }
             if (validationService.isEmpty($scope.user.newPassword)) {
                 $scope.errors.push({
                     form: 'user-form',
                     field: 'input[name=newPassword]',
                     msg: "New Password cannot be empty"
                 });
             }
             
             if ($scope.user.oldPassword == $scope.user.newPassword) {
                 $scope.errors.push({
                     form: '',
                     field: '',
                     msg: 'Old and new Passwords cannot be same.'
                 });
             }
             
             if ($scope.errors.length > 0) {
                 return false;
             } else {
                 return true;
             }
         }


         $scope.changePassword = function(ev) {
             var validated = $scope.validatePasswordForm();
             if (validated) {
            	 
            	//var sure = confirm('Are you sure you want to change the password?'); 
            	           	 
            	 
            	 var confirm = $mdDialog.confirm()
	                 .title('Password Change')
	                 .textContent('Are you sure you want to change the password?')
	                 .ariaLabel('')
	                 .targetEvent(ev)
	                 .ok('OK')
	                 .cancel('Cancel');
	
		           $mdDialog.show(confirm).then(function() {
		        	   
		        	   if(true) {
		                     
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
		                             $('#security-errors').text("Password Changed Successfully.You are being logged out,please login with the new password").addClass('alert alert-error').css('color', 'green');
		                             // re-enable the submit button:
		                             // $('#submitBtn').prop('disabled', false);
		                             $cookies.remove("username");
		                             $cookies.remove("password");
		                             $window.location.href = '/gtn/index.html';

		                         },
		                         function(error) {
		                             $scope.errors.push({
		                                 form: '',
		                                 field: '',
		                                 msg: error.data.message
		                             });
		                             return;

		                         });

		                 }
		        	   
		        	   //$scope.status = 'You decided to get rid of your debt.';
		           }, function() {
		        	   //alert('canceled1');
		               //$scope.status = 'You decided to keep your debt.';
		           });
            	 
                
             }
         };


     }
 ]);