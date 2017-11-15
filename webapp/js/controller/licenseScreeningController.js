screeningController.controller('licenseScreeningCtlr', ['$scope', '$location', '$http', '$compile', 'licenseScreeningFactory', '$sce', 'commonService',
    function($scope, $location, $http, $compile, licenseScreeningFactory, $sce, commonService) {
		
		$scope.license = {};
	
		$scope.init = function(){
			$scope.visible = false;
			$scope.license = {};
			$scope.inputLabel='';
			
			/*$scope.license.expCountry="US";
			$scope.license.expCountryName="UNITED STATES";
			
			$scope.license.impCountry="IN";
			$scope.license.impCountryName="INDIA";*/

			//$scope.license.type="eccn";
			//$scope.license.input="NRC8(c)";
			$scope.visible = true;
			$scope.license.type="product_no";
			$scope.inputLabel = "Part No";			
		}
	
		$scope.showInput = function(){
			if($scope.license.type == 'product_no'){
				$scope.inputLabel = "Part No";
			}else if($scope.license.type == 'eccn'){
				$scope.inputLabel = "ECCN";
			}else if($scope.license.type == 'nrc'){
				$scope.inputLabel = "NRC";
			}else if($scope.license.type == 'usml'){
				$scope.inputLabel = "USML";
			}
			
			$scope.license.input='';			
			$scope.visible = true;
		}
		
		$scope.reset = function(){
			$scope.init();
		}
		
		$scope.screenLicense = function(){
			$scope.clearMessages();
			
			if(!$scope.license){
				$scope.errors.push({form:'', field: '', msg: 'Please enter a valid input.'});
				commonService.processMsg($scope.errors, 'error');
				return;
			}
			
			if(!$scope.license.impCountry || $scope.license.impCountry == ''){
				$scope.errors.push({form:'', field: 'input[name=impCountryName]', msg: 'Import country is required.'});
			}
			if(!$scope.license.expCountry || $scope.license.expCountry == ''){
				$scope.errors.push({form:'', field: 'input[name=expCountryName]', msg: 'Export country is required.'});
			}
			if($scope.license.type == ''){
				$scope.errors.push({form:'', field: '', msg: 'Please select a type.'});
			}
			if(!$scope.license.input || $scope.license.input == ''){
				$scope.errors.push({form:'', field: 'input[name=input]', msg: 'Input is required.'});
			}
			
			if($scope.errors.length > 0){
				commonService.processMsg($scope.errors, 'error');
				return;
			}
			
			var promise = licenseScreeningFactory.doLicenseScreening($scope.license);
			
			promise.then(function(response){
				
				if(response.status == 401){
					$scope.errors.push({form:'', field: '', msg: 'Session expired'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}else if(response.status == -1){
					$scope.errors.push({form:'', field: '', msg: 'Server encountered an error. Please try later.'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}else if(response.data.success == false){
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					return;
				}
				console.log(response);
				if(response.data.data.response.status == 'SUCCESS'){					
					commonService.setResponse(response);
					$location.path("/resultLicenseScreening");
				}else{
					if(response.data.data.response.hostedError){
						var errorArr = response.data.hostedError.errorTag;
						$.each(errorArr, function(k, v){
							$scope.errors.push({form:'', field: '', msg: v});
						});
						commonService.processMsg($scope.errors, 'error');
					}
					
				}					
			});
			
		}
    }
]);


screeningController.controller('licenseScreeningResultCtlr', ['$scope', '$location', '$http', '$compile', 'licenseScreeningFactory', 'commonService', 'paymentService', 
     function($scope, $location, $http, $compile, licenseScreeningFactory, commonService, paymentService) {
		
		//$scope.hits = 0;
		//$scope.wlsScreenings = [];
	
		$scope.init = function(){
			var response = commonService.getResponse();
			
			//console.log("-------------------------");
			console.log(response);
			
			if(angular.equals({}, response) || response == null){
				$location.path("/createLicenseScreening");
			}
			
			paymentService
				.fillCountries()
				.then(
					function(result) {
						$scope.countries = result.data;					
					},
					function(error) {
						$scope.errors.push({form:'', field: '', msg: 'Error getting countries.'});
					});
			
			$scope.licenseScreeningResponse = response.data.data;			
			$scope.lastLicenseInp = response.data.data.input;
			
			//$scope.hits = response.data[0].httsReturned.hits;
			//$scope.wlsScreenings = response.data[0].httsReturned.wlsList.wlsScreenings;
				
			//$scope.wlsScreenings = response.data;
			
			commonService.setResponse(null);
			$('.selectpicker').selectpicker();	
		}
		
		/**
		 * Refresh the results on change of country
		 */
		$scope.screenLicenseCtry = function(){
			//$scope.lastLicenseInp.expCountry = $scope.countryVal;
			$scope.clearMessages();
			//licenseScreeningResponse.input.impCountry
			
			//ajax call
			//licenseScreeningFactory.doLicenseScreening($scope.lastLicenseInp);
			$scope.lastLicenseInp.impCountry = $scope.licenseScreeningResponse.input.impCountry;
			$scope.lastLicenseInp.impCountryName = $("#screenLicenseCtry option:selected").html();
			
			
			var promise = licenseScreeningFactory.doLicenseScreening($scope.lastLicenseInp);
			
			promise.then(function(response){
				
				if(response.status == 401){
					$scope.errors.push({form:'', field: '', msg: 'Session expired'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}else if(response.status == -1){
					$scope.errors.push({form:'', field: '', msg: 'Server encountered an error. Please try later.'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}else if(response.data.success == false){
					$scope.errors.push({form:'', field: '', msg: response.data.message});
					commonService.processMsg($scope.errors, 'error');
					return;
				}
				console.log(response);
				if(response.data.data.response.status == 'SUCCESS'){					
					//commonService.setResponse(response);
					$scope.licenseScreeningResponse = response.data.data;
					//$location.path("/resultLicenseScreening");
				}else{
					if(response.data.data.response.hostedError){
						var errorArr = response.data.hostedError.errorTag;
						$.each(errorArr, function(k, v){
							$scope.errors.push({form:'', field: '', msg: v});
						});
						commonService.processMsg($scope.errors, 'error');
					}
					
				}					
			});		
			
			
			//execute after ajax call is finished
			/*var response = commonService.getResponse();			
			$scope.licenseScreeningResponse = response.data;
			$scope.lastLicenseInp = response.data.inp;*/
		}
	  				
  }]);