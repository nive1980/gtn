dashboardApp.filter("trust", ['$sce', function($sce) {
  return function(htmlCode){
    return $sce.trustAsHtml(htmlCode);
  }
}]);

dashboardApp.filter("replaceNullToZero", function($sce) {
	  return function(inp){
		  
		  if(!(inp == undefined || inp == null)){
			  return inp;
		  }else{
			  return 0;
		  }
		  
	  }
});

dashboardApp.filter("shipmentStatusDescription", function($http) {
	  return function(inp){		
		  
		  	if("P" == inp){
				return "In progress"; 
			}else if("H" == inp){
				return "On Hold"; 
			}else if("A" == inp){
				return "Shipment Approved"; 
			}else if("B" == inp){
				return "Booked"; 
			}else if("N" == inp){
				return "New"; 
			}else if("S" == inp){
				return "Shipped"; 
			}else if("D" == inp){
				return "Validated"; 
			}else if("C" == inp){
				return "Shipment Cancelled"; 
			}else{
				return "N/A";
			}
		  
		  
		  /*return $http({
			    url: "getShipmentStatus?status="+inp,
			    method: "GET",
			    responseType: 'json'
			}).then(function(response){
				//console.log(response);
				alert(response.data);
				return response.data;		
			}, function(error){
				//console.log(error);
				alert(error);
				return error;
			});*/		  
	  }
});

dashboardApp.filter("dplStatus", function($http) {
	  return function(inp){		
		  
        if("T" == inp){
			return "To Be Screened"; 
		}else if("A" == inp){
			return "Approved"; 
		}else if("D" == inp){
			return "Denied"; 
		}else if("O" == inp){
			return "Overriden"; 
		}else if("H" == inp){
			return "On Hold";  	  
		}
	  }
});

dashboardApp.filter("aesStatus", function($http) {
	  return function(inp){		
		  		  
      if("N" == inp){
			return "To Be Reported"; 
		}else if("O" == inp){
			return "Open"; 
		}else if("C" == inp){
			return "Cancelled"; 
		}else if("D" == inp){
			return "Deleted"; 
		}else if("I" == inp){
			return "In Progress";  	  
		}else if("J" == inp){
			return "Rejected";  	  
		}else if("Y" == inp){
			return "Reported";  	  
		}else if("E" == inp){
			return "Released";  	  
		}else if("T" == inp){
			return "Not To Be Reported";  	  
		}else{
			return inp;
		}
	  }
});

dashboardApp.filter("liceReqFilter", function($http){
	return function(inp){
		if(inp == "N"  ){
			return "No";
		}else if(inp == "Y" || inp == "E" || inp == "P"){
			return "Yes";
		}else if(inp == "NA"){
			return "NA";
		}
	}
});

