screeningController.controller('entityScreeningCtlr', ['$scope', '$location', '$http', '$compile', 'entityScreeningFactory', '$sce', 'commonService',
    function($scope, $location, $http, $compile, entityScreeningFactory, $sce, commonService) {
		
		$scope.entities = [];
		
		/*$scope.errors = [];
		$scope.warnings = [];
		$scope.infos = [];*/
	
		$scope.init = function(){
			/*$scope.$on('$routeChangeSuccess', function() {
				$('.selectpicker').selectpicker();
				$scope.reset();
			});*/
			$scope.reset();
			/*$scope.entities.push({
					entityType:'Carrier', orgType:'Individual', entityId:'12345', city:'Mumbai', name:'Naveen', 
						state:'Maharashtra', addressLine1:'Poonam nagar', addressLine2:'Jogeshwari east', addressLine3:'400096', 
						country:'IN', zip:'400096', edit: $sce.trustAsHtml("<a href='javascript:void(0)'>Edit</a>"),
						remove: $sce.trustAsHtml("<a href='javascript:void(0)' class='delete-entity'>Delete</a>")
			});
			
			var tbody = angular.element('#entity-table tbody');
			
			$compile(tbody)($scope);*/
		}
	
		$scope.addEntity = function(){
			
			//var tr = this.prepareEntityRow($scope.entity);
			
			//$('#entity-table tbody').append(tr);
			$scope.clearMessages();
			
			//$scope.entity.$index = $scope.entities.length;
			
			//$scope.entity.entityType = $scope.entityTypes[$scope.entity.entityType];
			
			/*var $el = "<a ng-click='deleteEntity("+$scope.entity.$index+")'>Delete</a>";
			$scope.entity.edit = $sce.trustAsHtml("<a ng-click='editEntity("+$scope.entity.$index+")'>Edit</a>");
			$scope.entity.remove = $sce.trustAsHtml($el);*/
			
			console.log($scope.entity);
			
			//alert($scope.entity.name);
			
			if($scope.entity.name!=''){
				if ($scope.entity.$index === undefined) {
					$scope.entities.push($scope.entity);
					$scope.entity.$index = $scope.entities.length;
				}else{
					//$scope.reset();
					//$scope.entities[$scope.entity.$index] = $scope.entity;
				}
				$scope.reset();
			}else{
				$scope.errors.push({form:'', field: '', msg: 'Please enter entity name.'});
				commonService.processMsg($scope.errors, 'error');
			}
			
			
			/*var tbody = angular.element('#entity-table tbody');
			
			$compile(tbody)($scope);*/
		}
		
		$scope.searchWlsEntity = function(){
			$scope.clearMessages();
			
			if($scope.entity.entityType == ''){
				$scope.errors.push({form:'', field: '', msg: 'Please select a entity type.'});
				return;
			}
			
			if($scope.entity.entityType == 'Exporter'){
				//$scope.searchExporterPopup();
				commonService.searchExporterPopup('wls', $scope.entity);
			}else if($scope.entity.entityType == 'Consignee'){
				//$scope.searchConsigneePopup('ultCons');
				commonService.searchConsigneePopup('wls', $scope.entity);
			}else{
				$scope.errors.push({form:'', field: '', msg: 'Master not found.'});
				return;
			}
		}
		
		/*$scope.prepareEntityRow = function(entityObj){
	    	
	    	var rowNum = 1;
	    	
	    	if($('#entity-table tbody tr').length > 0){
	    		rowNum = $('#entity-table tbody tr').length + 1;
	    	}
	    	    	
	    	//var selectedEntity = document.getElementById('entity_sl').options[document.getElementById('entity_sl').selectedIndex].text;
	    	
	    	var selectedEntity;
	    	
	    	if(entityObj.entityType == ''){
	    		selectedEntity = '';
	    	}else{
	    		selectedEntity = $scope.entityType[entityObj.entityType];
	    	}
	    		
	    	var tr = "<tr class='cart_item'>";
	  	    	    	
	    	tr += "<td>"+rowNum+"</td>";
	    	tr += "<td>"+selectedEntity+"</td>";
	    	tr += "<td>"+entityObj.orgType+"</td>";    	
	    	tr += "<td>"+entityObj.entityId+"</td>";
	    	tr += "<td>"+entityObj.name+"</td>";
	    	tr += "<td>"+entityObj.addressLine1+"</td>";
	    	tr += "<td>"+entityObj.city+"</td>";
	    	tr += "<td>"+entityObj.state+"</td>";
	    	tr += "<td>"+entityObj.zip+"</td>";
	    	tr += "<td><a href='javascript:void(0)'>Edit</a></td>";
	    	tr += "<td><a href='javascript:void(0)' class='delete-entity'>Delete</a><input type='text' value='"+JSON.stringify(entityObj)+"' class='hidden entity' /></td>";
	    	
	    	tr += "</tr>";
	    	
	    	return tr;
	    }*/
		
		/*$scope.entityTypes = {
				"": "",
				"CON": "Consignee",
				"FF": "Freight Forwarder",
				"C": "Carrier",
				"E": "Exporter"
		}*/
	
		$scope.reset = function(){
			$scope.entity = {
					entityType:'', orgType:'', entityId:'', city:'', name:'', state:'', addressLine1:'', addressLine2:'', addressLine3:'', country:'', zip:'' , entityCode: ''
			}
			//$('.selectpicker').selectpicker('refresh');
			$scope.clearMessages();
		}
		
		/**
		 * call factory method for screening
		 */
		$scope.screenEntity = function(){
			console.log($scope.entities);
			$scope.clearMessages();
			
			if($scope.entities.length == 0){
			//if(false){
				$scope.errors.push({form:'', field: '', msg: 'Please enter at least one entity to screen.'});
				commonService.processMsg($scope.errors, 'error');
			}else{
				var promise = entityScreeningFactory.doWatchListScreening($scope.entities);
				
				promise.then(function(response){
					
					if(response.status == 401){
						$scope.errors.push({form:'', field: '', msg: 'Session expired'});
						commonService.processMsg($scope.errors, 'error');
						return;
					}else if(response.status == -1){
						$scope.errors.push({form:'', field: '', msg: 'Server encountered an error. Please try later.'});
						commonService.processMsg($scope.errors, 'error');
						return;
					}
					
					if(response.data.success == false){
						$scope.errors.push({form:'', field: '', msg: response.data.msg});
						commonService.processMsg($scope.errors, 'error');
					}else{
						commonService.setResponse(response);
						$location.path("/resultEntityScreening");
					}					
				});
			}
					
		}
		
		$scope.deleteEntity = function(idx){
			$scope.entities.splice(idx, 1);
		}
		
		$scope.editEntity = function(idx){
			var entityObj = $scope.entities[idx];
			$scope.entity = entityObj;
			//$('.selectpicker').selectpicker('refresh');
			//$scope.entity.entityType = Object.keys($scope.entityTypes)[Object.values($scope.entityTypes).indexOf($scope.entity.entityType)];
		}
		

		$scope.wlsFormChanged = function(){
			$scope.entity.entityCode = '';
		}
    }
]);


screeningController.controller('entityScreeningResultCtlr', ['$scope', '$location', '$http', '$compile', 'entityScreeningFactory', 'commonService', 'masterService',  
       function($scope, $location, $http, $compile, entityScreeningFactory, commonService, masterService) {
			
			//$scope.hits = 0;
			$scope.wlsScreenings = [];

			$scope.init = function(){
				var response = commonService.getResponse();
				
				if($.isEmptyObject(response)){
					$location.path("/createEntityScreening");
				}
				
				console.log("-------------------------");
				console.log(response);
				
				$scope.resultType = false;
				
				//$scope.hits = response.data[0].httsReturned.hits;
				//$scope.wlsScreenings = response.data[0].httsReturned.wlsList.wlsScreenings;
				
				$scope.wlsScreenings = response.data;
				$scope.clearMessages();
				
				
				var color = d3.scale.category20();
				$scope.wlsGraphOption = {
			            chart: {
			                type: 'forceDirectedGraph',
			                height: 600,
			                 width: (function(){ return nv.utils.windowSize().width - 350 })(),
			                margin:{top: 20, right: 20, bottom: 20, left: 20},
			                color: function(d){
			                    return color(d.group)
			                },
			                charge: -300,
			                tooltip: {
	 		                      contentGenerator: function (key, x, y, e, graph) {
	 		                    	 //console.log(key);
	 		                    	 //console.log(x);
	 		                    	 var bgcolor;
	 		                    	  if(key.series && key.series.length > 0){
	 		                    		 bgcolor = key.series[0].color;
	 		                    	  }else{
	 		                    		 bgcolor = '#000';
	 		                    	  }
	 		                    	 
	 		                    	var ttContent = $scope.getTooltilContent(key);
	 		                    		 		                    	 
	 		                        return '<div class="nvd3-tooltip-wls">'+ttContent+'</div>';
	 		                      }
 		                    },
			                nodeExtras: function(node) {
			                    node && node
			                    .append("text")
			                      /*.append("circle")
			                      .attr('r', function(d){
			                    	  if(d.ntype == 'root'){
			                    		  return 8;
			                    	  }else{
			                    		  //return 3;
			                    	  }
			                      })*/
			                      .text(function(d) { return d.name })
			                      .style('font-size', '9px');
			                },
			                linkStrength: 0.7,
			                gravity: 0.2,
			                linkDist: 90,
			                callback: function(){
			                	var svg = d3.select('#wlsGraphPlot');
			                	var nodes = svg.selectAll(".nv-force-node");
			                	
			                	
			                }
			            }
		        };

				//console.log(response.data.graphData);
				
				$scope.fromHits = 1;
				$scope.toHits = 50;
					
				
				
				$scope.wlsGraphData = response.data.graphData;
				
				$scope.wlsGraphData1 = {
			            "nodes":[
			                {"name":"root-1","group":0},    
			                {"name":"l-1","group":1},
			                {"name":"l-2","group":1},
			                {"name":"l-3","group":1},
			                {"name":"l-4","group":1},
			                {"name":"l-5","group":1},
			                
			                {"name":"root-2","group":2},
			                {"name":"l-6","group":3},
			                {"name":"l-7","group":3},
			                {"name":"l-8","group":3},
			                {"name":"l-9","group":3},
			                {"name":"l-10","group":3}
			                
			            ],
			            "links":[
			                {"source":0,"target":1,"value":2},
			                {"source":0,"target":2,"value":12},
			                {"source":0,"target":3,"value":12},
			                {"source":0,"target":4,"value":12},
			                {"source":0,"target":5,"value":12},
			                
			                {"source":6,"target":7,"value":12},
			                {"source":6,"target":8,"value":12},
			                {"source":6,"target":9,"value":12},
			                {"source":6,"target":10,"value":12},
			                {"source":6,"target":11,"value":12}
			              
			            ]
			        }
				
				
				commonService.setResponse(null);
			}
			
			$scope.getTooltilContent = function(key){
				 var ttContent = '<div class="toolTip-div"><b>Name</b> : '+commonService.upperCase(key.fullName)+'</div>';
             	 
             	 if(key.dplScore){
             		ttContent = ttContent + '<div class="toolTip-div"><b>DPL Score</b> : '+key.dplScore+'</div>';
             	 }else{
             		ttContent = ttContent + '<div class="toolTip-div"><b>DPL Score</b> : '+'-'+'</div>';
             	 }
             	 
             	 ttContent = ttContent + '<div class="toolTip-div"><b>Group</b> : '+key.group+'</div>';
             	 
             	if(key.country){
             		ttContent = ttContent + '<div class="toolTip-div"><b>Country</b> : '+key.country+'</div>';
             	 }else{
             		ttContent = ttContent + '<div class="toolTip-div"><b>Country</b> : '+'-'+'</div>';
             	 }
             	if(key.agency){
             		ttContent = ttContent + '<div class="toolTip-div"><b>Agency</b> : '+key.agency+'</div>';
             	 }else{
             		ttContent = ttContent + '<div class="toolTip-div"><b>Agency</b> : '+'-'+'</div>';
             	 }
             	if(key.category){
             		ttContent = ttContent + '<div class="toolTip-div"><b>Category</b> : '+key.category+'</div>';
             	 }else{
             		ttContent = ttContent + '<div class="toolTip-div"><b>Category</b> : '+'-'+'</div>';
             	 }
             	return ttContent;
			}
			
			$scope.showAudit = function(entityType, entityCode){
				if(entityType == 'Exporter'){
					masterService.wlsAuditHistory(entityCode, 'EXPORTER');
				}else if(entityType == 'Consignee'){
					masterService.wlsAuditHistory(entityCode, 'CONSIGNEE');
				}
				//alert(entityType+" - "+entityCode);
			}
			
				
}]);
