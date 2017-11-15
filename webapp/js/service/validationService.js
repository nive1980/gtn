gtnApp.service('validationService', ['$http', '$window', function ($http, $window) {
    var response = {};
    var service = this;
    
    this.getResponse = function(){
    	return response;
    }
    
    this.setResponse = function(value){
    	response = value;
    }
    
    this.processMsg = function(arr, type){
    	angular.element('.invalid-ele').removeClass('invalid-ele');
    	if(arr.length > 0){
    		//if(!service.isEmpty(arr[0].form))
    			//angular.element(arr[0].form+" input", arr[0].form+" select", arr[0].form+" textarea").removeClass('invalid-ele');
    		
    		angular.forEach(arr, function(index, value){
    			if(!service.isEmpty(index.field))
    				angular.element(index.field).addClass('invalid-ele');
    		});
    		$('html,body').animate({ scrollTop: 0 }, 'slow');
    	}
    }
    
    this.isEmpty = function(val){
		if(val==null || val == '' || val == undefined){
			return true;
		}else{
			return false;
		}
	}
    
    this.processNgError = function(errorObj, arr, formName, field, customFieldName){
    	if(errorObj.hasOwnProperty('min')){
    		arr.push({form:formName, field: field, msg: "Please enter valid "+customFieldName+"."});
    	}
    	if(errorObj.hasOwnProperty('number')){
    		arr.push({form:formName, field: field, msg: "Please enter valid "+customFieldName+"."});
    	}
    	if(errorObj.hasOwnProperty('required')){
    		arr.push({form:formName, field: field, msg: service.ucfirst(customFieldName)+" is required."});
    	}
    	return arr;
    }
    this.validatePinCode=function(pincode){
    var regexp = /^\d{5}(?:[-\s]\d{4})?$/; 
   if (pincode) {
   
   return regexp.test(pincode); 
   } else { 
   return true; 
   } 

    }

    this.validateEmail = function(email){
    	if(service.isEmpty(email)){
    		return false;
    	}else {
    		var pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    		var valid = pattern.test(email);
    		return valid;
    	}
    }
    
    this.ucfirst = function(str) {
        var firstLetter = str.slice(0,1);
        return firstLetter.toUpperCase() + str.substring(1);
    }
    
    /*return {
        getResponse: function () {
            return response;
        },
        setResponse: function(value) {
        	response = value;
        }
    };*/
}]);

