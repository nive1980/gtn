restControllers.controller('ProfileCtrl', [
    '$http',
    '$scope',
    '$rootScope',
    '$location',
    '$window', '$cookies', 'subscriptionService', 'validationService',
    function($http, $scope, $rootScope, $location, $window, $cookies, subscriptionService, validationService) {
        $scope.errors = [];
        $scope.warnings = [];
        $scope.infos = [];


        $scope.loadUser = function() {
            // var user1=localStorage.getItem('LoggedInUser');
            var user1 = angular.fromJson($window.localStorage['LoggedInUser']);

            $scope.user = user1;
            usrmgtLhs('profile-li');
        };
        $scope.clearMessages = function() {
            $scope.errors.length = 0;
            $scope.warnings.length = 0;
            $scope.infos.length = 0;
        };

        $scope.validateUserForm = function() {
            $scope.errors.length = 0;

            if (validationService.isEmpty($scope.user.companyName)) {
                $scope.errors.push({
                    form: 'user-form',
                    field: 'input[name=Company]',
                    msg: "Company name is required"
                });
            }
            if (validationService.isEmpty($scope.user.phone)) {
                $scope.errors.push({
                    form: 'user-form',
                    field: 'input[name=Phone No]',
                    msg: "Phone Number is required"
                });
            }
            if (validationService.isEmpty($scope.user.firstName)) {
                $scope.errors.push({
                    form: 'user-form',
                    field: 'input[name=firstname]',
                    msg: "First Name is required"
                });
            }
            if (validationService.isEmpty($scope.user.title)) {
                $scope.errors.push({
                    form: 'user-form',
                    field: 'input[name=title]',
                    msg: "Title is required"
                });
            }
            if (validationService.isEmpty($scope.user.lastName)) {
                $scope.errors.push({
                    form: 'user-form',
                    field: 'select[name=lastname]',
                    msg: "Last Name is required"
                });
            }

            if ($scope.errors.length > 0) {
                return false;
            } else {
                return true;
            }
        }

        $scope.modifyUserDetails = function() {
            var validated = $scope.validateUserForm();
            
            if (validated) {
                var fd = new FormData();
                // var file=$form.get(0).fileInput.value;
                var file = $scope.myFile;
                
                
                $scope.errors.length = 0;
                
                
                if(file){
                	
                	var validImg = validateFile(file.name);
                	
                	if(!validImg){
                		$scope.errors.push({
                            form: 'user-form',
                            field: '',
                            msg: "Please enter a valid image file. Allowed extension - 'jpeg', 'jpg', 'png', 'gif', 'bmp'."
                        });
                    	
                	}
                	
                	if(file.size > 1000000){
                		$scope.errors.push({
                            form: 'user-form',
                            field: '',
                            msg: "Profile image size exceeds maximum allowed limit."
                        });
                    	
                    }
                	
                	if($scope.errors.length > 0){
                		return;
                	}
                }
                

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
                        // alert("success");


                        // localStorage.setItem('LoggedInUser',success.data.LoggedInUser);
                        window.localStorage['LoggedInUser'] = angular.toJson(success.data.LoggedInUser);
                        $scope.user.image = success.data.LoggedInUser.image;
                        $('#profile-errors').text("Profile Changed Successfully").addClass(
                            'alert alert-error').css('color', 'green');

                        $window.location.reload();
                    }).then(function(error) {

                        $('#profile-errors').text(error.data.message).addClass(
                            'alert alert-error').css('color', 'red');

                    });
            }

        };
    }
]);


function validateFile(fileName) 
{
	var allowedExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
    var fileExtension = fileName.split('.').pop().toLowerCase();
    var isValidFile = false;

        for(var index in allowedExtension) {

            if(fileExtension === allowedExtension[index]) {
                isValidFile = true; 
                break;
            }
        }

        /*if(!isValidFile) {
            alert('Allowed Extensions are : *.' + allowedExtension.join(', *.'));
        }*/

        return isValidFile;
}