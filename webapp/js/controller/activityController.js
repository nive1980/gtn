restControllers.controller('activityCtlr', ['$scope', '$location', '$http', '$compile', 'caseService','messageService',
      function($scope, $location, $http, $compile,caseService,messageService) {
 		
 		$scope.init = function(){
 			var height = 200;
 			var width = 300;
 			
 			usrmgtLhs('activity-li');
 			
 			 //Cases 
 	         $scope.optionsCases = {
 	                chart: {
 	                    type: 'discreteBarChart',
 	                    height: height,
		                width: width,
 	                    margin : {
 	                        top: 20,
 	                        right: 20,
 	                        bottom: 50,
 	                        left: 55
 	                    },
 	                    x: function(d){return d.label;},
 	                    y: function(d){return d.value;},
 	                    showValues: true,
 	                    valueFormat: function(d){
 	                        return d3.format(',f')(d);
 	                    },
 	                    duration: 500,
 	                    xAxis: {
 	                        axisLabel: 'Status'
 	                    },
 	                    yAxis: {
 	                        axisLabel: 'No of Cases',
 	                        axisLabelDistance: -10
 	                    }
 	                }
 	            };
 	       
 	       $scope.dataForCases = [];
 	       $scope.getCaseData();
 	       $scope.messageCountForCases = "";
 	       $scope.getUnreadMessageCountForCasesByUser();
 	        
		}

 		$scope.getCaseData = function(){
 			caseService.getActivityData()
 			.then(function (success){
 				$scope.dataForCases = [{
 					key: "Cases",
 				    values: success.data.data }]
		       },function (error){
		     	           
		       });
 		};

 		$scope.getUnreadMessageCountForCasesByUser = function(){
 			messageService.getUnreadMessageCountForCases()
 			.then(function (success){
 				$scope.messageCountForCases = success.data.data;
		       },function (error){
		     	           
		       });
 		};
}]);