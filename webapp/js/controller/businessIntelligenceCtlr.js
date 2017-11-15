//var biController = angular.module('businessIntelligenceCtlr', []);

dashboardApp.controller('businessIntelligenceCtlr', ['$scope', '$location', '$http', '$compile', 'licenseScreeningFactory', 'commonService', 'paymentService', 
      function($scope, $location, $http, $compile, licenseScreeningFactory, commonService, paymentService) {

		var height = 200;
		var width = 320;
		
 		$scope.init = function(){
 			/*var response = commonService.getResponse();
 		
 			commonService.setResponse(null);*/
 			/* Chart options */
 			
 			$scope.angularChart = false;
 			
 			//shipment by status 			
 			$scope.shipStats = "gtn";
 			$scope.getShipmentByStatus();
 			
 			//shipment by country
 			$scope.getShipmentByCountry();
 	  
 	        //shipment by country - world map
 			$scope.getShipmentByCountryMap();
 			
 			//shipment by value
 			$scope.getShipmentByValue();
 			
	 		 //shipment timeline	 	     
	 	    $scope.optionsTimeline = {
	 	            chart: {
	 	                type: 'multiBarChart',
	 	                height: height,
	 	                width: width,
	 	                margin : {
	 	                    top: 20,
	 	                    right: 20,
	 	                    bottom: 45,
	 	                    left: 45
	 	                },
	 	                clipEdge: true,
	 	                //staggerLabels: true,
	 	                duration: 500,
	 	                stacked: false,
	 	                xAxis: {
	 	                    axisLabel: 'Time (Day)',
	 	                    showMaxMin: false,
	 	                    tickFormat: function(d){
	 	                        return d3.format(',f')(d);
	 	                    }
	 	                },
	 	                yAxis: {
	 	                    axisLabel: 'Shipment',
	 	                    axisLabelDistance: -20,
	 	                    tickFormat: function(d){
	 	                        return d3.format(',.1f')(d);
	 	                    }
	 	                }
	 	            }
	 	        };

 	        $scope.dataTimline = generateData();
 	       /* Random Data Generator (took from nvd3.org) */
 	        function generateData() {
 	            return stream_layers(3,50+Math.random()*50,.1).map(function(data, i) {
 	                return {
 	                    key: 'Stream' + i,
 	                    values: data
 	                };
 	            });
 	        }

 	        /* Inspired by Lee Byron's test data generator. */
 	        function stream_layers(n, m, o) {
 	            if (arguments.length < 3) o = 0;
 	            function bump(a) {
 	                var x = 1 / (.1 + Math.random()),
 	                    y = 2 * Math.random() - .5,
 	                    z = 10 / (.1 + Math.random());
 	                for (var i = 0; i < m; i++) {
 	                    var w = (i / m - y) * z;
 	                    a[i] += x * Math.exp(-w * w);
 	                }
 	            }
 	            return d3.range(n).map(function() {
 	                var a = [], i;
 	                for (i = 0; i < m; i++) a[i] = o + o * Math.random();
 	                for (i = 0; i < 5; i++) bump(a);
 	                return a.map(stream_index);
 	            });
 	        }

 	        /* Another layer generator using gamma distributions. */
 	        function stream_waves(n, m) {
 	            return d3.range(n).map(function(i) {
 	                return d3.range(m).map(function(j) {
 	                    var x = 20 * j / m - i / 3;
 	                    return 2 * x * Math.exp(-.5 * x);
 	                }).map(stream_index);
 	            });
 	        }

 	        function stream_index(d, i) {
 	            return {x: i, y: Math.max(0, d)};
 	        }
 	       
 	        
 	        
 	        //shipment and invoice
 	       $scope.shipmentByInvoice();
 			
 		}//init function
 		
 		//shipment by status fn
 		$scope.getShipmentByStatus = function(){
 			console.log($scope.shipStats);
 			//blockLoader();
 			//angular.element('#ajax_loader').show();
 			//angular.element('#graph-content').hide();
 			
 			$http({
 				url: 'getShipmentStatusGraph?type='+$scope.shipStats,
 				method: 'GET',
 				responseType: 'json'
 			}).then(function(response){
 				//unblockLoader();
 				
 				//angular.element('#ajax_loader').hide();
 	 			//angular.element('#graph-content').show();
 				
 				$scope.options = {
 	 		            chart: {
 	 		                type: 'pieChart',
 	 		                height: height,
 	 		                width: width,
 	 		                x: function(d){return d.key;},
 	 		                y: function(d){return d.y;},
 	 		                showLabels: false,
 	 		                duration: 500,
 	 		                labelThreshold: 0.01,
 	 		                labelSunbeamLayout: true,
 	 		                legend: {
 	 		                    margin: {
 	 		                        top: 5,
 	 		                        right: 0,
 	 		                        bottom: 5,
 	 		                        left: -20
 	 		                    }
 	 		                },
 	 		                margin:{
 	 		                	left: 0
 	 		                },
 	 		               useInteractiveGuideline: true,    
 	 	 	               interactive: true
 	 		            }
 	 		        };
 				
 				
 			      /* Chart data */
 	 			$scope.data = response.data;
 				
 				console.log(response);
 				return response;
 			}, function(error){
 				//unblockLoader();
 				//angular.element('#ajax_loader').hide();
 	 			//angular.element('#graph-content').show();
 	 			
 				if(error.status == 401){
					$scope.errors.push({form:'', field: '', msg: 'Session expired'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}else{
					$scope.errors.push({form:'', field: '', msg: 'Server encountered an error. Please try later.'});
					commonService.processMsg($scope.errors, 'error');
					return;
				}
 				
 				
 				console.log(error);
 				return error;
 			});
 		}
 		
 		$scope.getShipmentByCountry = function(){
 			$http({
 				url: 'getShipmentByCountryGraph',
 				method: 'GET',
 				responseType: 'json'
 			}).then(function(response){
 				
 				//shipment by country 
 		         $scope.optionsByCountry = {
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
 		                        axisLabel: 'Country'
 		                    },
 		                    yAxis: {
 		                        axisLabel: 'Shipment',
 		                        axisLabelDistance: -10
 		                    },
 		                    tooltip: {
 		                      contentGenerator: function (key, x, y, e, graph) {
 		                    	  //console.log(key);
 		                    	 //console.log(x);
 		                        return '<div class="nvd3-tooltip">'+commonService.upperCase(key.data.cname)+': '+ key.data.value+'</div>';
 		                      }
 		                    }
 		                }
 		            };
 		         
 		        $scope.dataByCountry = response.data;
 		        
 		       /* $scope.dataByCountry = [
 		     	                      {
 		     	                          key: "Cumulative Shipments",
 		     	                          values: [
 		     	                              {
 		     	                                  "label" : "Australia" ,
 		     	                                  "value" : 150
 		     	                              } ,
 		     	                              {
 		     	                                  "label" : "India" ,
 		     	                                  "value" : 250
 		     	                              } ,
 		     	                              {
 		     	                                  "label" : "Mexico" ,
 		     	                                  "value" : 80
 		     	                              } ,
 		     	                              {
 		     	                                  "label" : "England" ,
 		     	                                  "value" : 195
 		     	                              } ,
 		     	                              {
 		     	                                  "label" : "Japan" ,
 		     	                                  "value" : 20
 		     	                              } ,
 		     	                              {
 		     	                                  "label" : "China" ,
 		     	                                  "value" : 100
 		     	                              }
 		     	                          ]
 		                          }
 		                      ];*/
 				
 				console.log(response);
 				return response;
 			}, function(error){
 				console.log(error);
 				return error;
 			});

 		}
 		
 		function arr_diff (a1, a2) {

 		    var a = [], diff = [];

 		    for (var i = 0; i < a1.length; i++) {
 		        a[a1[i]] = true;
 		    }

 		    for (var i = 0; i < a2.length; i++) {
 		        if (a[a2[i]]) {
 		            delete a[a2[i]];
 		        } else {
 		            a[a2[i]] = true;
 		        }
 		    }

 		    for (var k in a) {
 		        diff.push(k);
 		    }

 		    return diff;
 		};
 		
 		function getMissingCtryMap(){
 			var missingCtryMap = {};
 			
 			missingCtryMap['BENIN (DAHOMEY)'] = 'BENIN';
 			missingCtryMap['BOLIVIA'] = 'BOLIVIA, PLURINATIONAL STATE OF';
 			//missingCtryMap['BENIN'] = 'BONAIRE, SINT EUSTATIUS AND SABA';
 			missingCtryMap['BOUVET ISLAND (BOUVETOYA)'] = 'BOUVET ISLAND';
 			missingCtryMap['CAPE VERDE, REPUBLIC'] = 'CAPE VERDE';
 			missingCtryMap['CONGO, REPUBLIC OF THE'] = 'CONGO';
 			missingCtryMap['CONGO, DEMOCRATIC REPUBLIC'] = 'CONGO, THE DEMOCRATIC REPUBLIC OF THE';
 			missingCtryMap['CURACAO'] = 'CURAÇAO';
 			//missingCtryMap['BENIN'] = 'CÔTE D'IVOIRE';
 			missingCtryMap['FALKLAND ISLANDS'] = 'FALKLAND ISLANDS (MALVINAS)';
 			missingCtryMap['GAMBIA, THE'] = 'GAMBIA';
 			missingCtryMap['GERMANY, FEDERAL REPUBLIC'] = 'GERMANY';
 			missingCtryMap['HEARD AND MCDONALD'] = 'HEARD ISLAND AND MCDONALD ISLANDS';
 			missingCtryMap['VATICAN CITY'] = 'HOLY SEE (VATICAN CITY STATE)';
 			missingCtryMap['IRAN'] = 'IRAN, ISLAMIC REPUBLIC OF';
 			missingCtryMap['NORTH KOREA'] = 'KOREA, DEMOCRATIC PEOPLE\'S REPUBLIC OF';
 			missingCtryMap['SOUTH KOREA'] = 'KOREA, REPUBLIC OF';
 			missingCtryMap['LAOS'] = 'LAO PEOPLE\'S DEMOCRATIC REPUBLIC';
 			missingCtryMap['MACAU'] = 'MACAO';
 			missingCtryMap['MICRONESIA, FEDERATED STATE OF'] = 'MICRONESIA, FEDERATED STATES OF';
 			missingCtryMap['MOLDOVA'] = 'MOLDOVA, REPUBLIC OF';
 			missingCtryMap['CYPRUS'] = 'NORTHERN CYPRUS';
 			//missingCtryMap['BENIN'] = 'NORTHERN MARIANA ISLANDS';
 			missingCtryMap['PALESTINIAN TERRITORY'] = 'PALESTINIAN TERRITORIES';
 			missingCtryMap['PITCAIRN ISLANDS'] = 'PITCAIRN';
 			missingCtryMap['REUNION'] = 'RÉUNION';
 			missingCtryMap['SAINT BARTHELEMY'] = 'SAINT BARTHÉLEMY';
 			//missingCtryMap['BENIN'] = 'SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA';
 			//missingCtryMap['BENIN'] = 'SAINT KITTS AND NEVIS';
 			//missingCtryMap['BENIN'] = 'SAINT LUCIA';
 			//missingCtryMap['SAINT MARTIN (FRENCH PART)'] = 'SAINT PIERRE AND MIQUELON';
 			//missingCtryMap['BENIN'] = 'SAINT VINCENT AND THE GRENADINES';
 			missingCtryMap['SINT MAARTEN'] = 'SINT MAARTEN (DUTCH PART)';
 			missingCtryMap['SOMALIA'] = 'SOMALILAND';
 			missingCtryMap['SOUTH GEORGIA AND SOUTH SANDWICH ISLAND'] = 'SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS';
 			missingCtryMap['SOUTH SUDAN, REPUBLIC OF'] = 'SOUTH SUDAN';
 			missingCtryMap['SVALBARD & JAN MAYEN'] = 'SVALBARD AND JAN MAYEN';
 			missingCtryMap['SYRIA'] = 'SYRIAN ARAB REPUBLIC';
 			missingCtryMap['TANZANIA'] = 'TANZANIA, UNITED REPUBLIC OF';
 			missingCtryMap['TIMOR LESTE'] = 'TIMOR-LESTE';
 			missingCtryMap['TURKS AND CAICOS IS'] = 'TURKS AND CAICOS ISLANDS';
 			missingCtryMap['UNITED STATES MISC. PACIFIC ISLANDS'] = 'UNITED STATES MINOR OUTLYING ISLANDS';
 			missingCtryMap['VENEZUELA'] = 'VENEZUELA, BOLIVARIAN REPUBLIC OF';
 			missingCtryMap['VIETNAM'] = 'VIET NAM';
 			missingCtryMap['VIRGIN ISLANDS BRITISH'] = 'VIRGIN ISLANDS, BRITISH';
 			missingCtryMap['VIRGIN ISLANDS OF US'] = 'VIRGIN ISLANDS, U.S.';
 			missingCtryMap['ALAND ISLANDS'] = 'ÅLAND ISLANDS';
 			
 			return missingCtryMap;
 		}
 		
 		$scope.getShipmentByCountryMap = function(){
 			
 			
 			/************* remove me ***************/
 			/*paymentService
			.fillCountries()
			.then(
					function(result) {
						$scope.countries = result.data;
						var len = result.data.length;
						
						var mapCtry = [];
						var dbCtry = [];
						
						var countriesMap = Datamap.prototype.worldTopo.objects.world.geometries;
		 				var countryHashMap = {};
		 				for (var i = 0, j = countriesMap.length; i < j; i++) {
		 					mapCtry.push(countriesMap[i].properties.name.toUpperCase());
		 				}
						
						for (var i = 0; i < len; i++) {
							var id = result.data[i]['countryCode'];
							var name = result.data[i]['countryName'];
							
							dbCtry.push(name);
						}
						
						console.log(' ---------- Country difference ------------- ');
						var diffCnt = 0;
						var diffArr = [];
						for(var k=0; k<mapCtry.length; k++){
							if($.inArray(mapCtry[k], dbCtry) == -1){
								diffArr.push(mapCtry[k]);
								//console.log(mapCtry[k]);
								diffCnt++;
							}
						}
						//alert(diffCnt);
						diffArr.sort();
						$.each(diffArr, function(k, v){
							console.log(v);
						});
						
						//console.log(diffArr.sort());
						//console.log(arr_diff(dbCtry, mapCtry));
					},
					function(error) {
						window
								.alert("errorssssssssss11111111111");
					});*/
 			/************* remove me ***************/
 			
 			
 			
 			
 			
 			
 			$http({
 				url: 'getShipmentByCountryGraph',
 				method: 'GET',
 				responseType: 'json'
 			}).then(function(response){
 				
 				var countriesMap = Datamap.prototype.worldTopo.objects.world.geometries;
 				var countryHashMap = {};
 				for (var i = 0, j = countriesMap.length; i < j; i++) {
 					countryHashMap[countriesMap[i].properties.name.toUpperCase()] = countriesMap[i].properties.iso;
 				  //console.log(countries[i].properties);
 				}
 				
 				
 				$('#world-map').ready(function () {
 					$('#world-map').html('');
 					var countries = response.data[0].values;
 					//console.log(' --------- response ------------ ');
 					//console.log(response);
 					var mapData = {};
 					$.each(countries, function(k, v){
 						var obj = {};
 						obj.fillKey = 'shipment';
 						obj.numberOfThings = v.value;
 						
 						var cname = v.cname;
 						
 						if(countryHashMap.hasOwnProperty(cname)){
 							mapData[countryHashMap[cname]] = obj;
 						}else{
 							var missingCtryMap = getMissingCtryMap();
 							var dataMapCtryName = missingCtryMap[cname];
 							if(dataMapCtryName){
 								mapData[countryHashMap[dataMapCtryName]] = obj;
 							}
 						}
 						/*if(v.label == 'US'){
 							v.label = 'USA';
 						}*/
 						
 					});
 					//console.log(mapData);
 	 				var basic = new Datamap({
 	 					element: document.getElementById('world-map'),
 	 					fills:{
 	 						shipment: 'blue',
 	 						defaultFill: '#ABDDA4'
 	 					},
 	 					done: function(){
 	 						console.log('done');
 	 					},
 	 					geographyConfig: {
 	 			            popupTemplate: function(geo, data) {
 	 			            	if(data){
 	 			            		return ['<div class="hoverinfo">',
 	 	 			                        '' + geo.properties.name,
 	 	 			                        ': ' + data.numberOfThings,
 	 	 			                        '</div>'].join('');
 	 			            	}else{
 	 			            		return ['<div class="hoverinfo">',
 	 	 			                        '' + geo.properties.name,
 	 	 			                        '</div>'].join('');
 	 			            	}
 	 			                
 	 			            }
 	 			        },
 	 			        data:mapData
 	 				});
 	 		    });
 				
 				
 				
 			}, function(error){
 				console.log(error);
 				return error;
 			});

 		}
 		
 		$scope.shipmentByInvoice = function(){
 			$scope.optionsInvoice = {
 	 	              chart: {
 	 	                  type: 'boxPlotChart',
 	 	                  height: height,
 	 	                  width: width,
 	 	                  margin : {
 	 	                      top: 20,
 	 	                      right: 20,
 	 	                      bottom: 30,
 	 	                      left: 50
 	 	                  },
 	 	                  color:['darkblue', 'darkorange', 'green', 'darkred', 'darkviolet'],
 	 	                  x: function(d){return d.label;},
 	 	                  //y: function(d){return d.values.Q3;},
 	 	                  maxBoxWidth: 55,
 	 	                  yDomain: [0, 500],
 	 	                  xAxis: {
 		                        axisLabel: 'Year',
 		                        axisLabelDistance: -10
 		                    },
 		                    yAxis: {
 		                        axisLabel: 'Shipment No',
 		                        axisLabelDistance: -10
 		                    }
 	 	              }
 	 	          };
 	 	        
 	 	       $scope.dataInvoice = [
 	 	                     {
 	 	                         label: "2013",
 	 	                         values: {
 	 	                             Q1: 180,
 	 	                             Q2: 200,
 	 	                             Q3: 250,
 	 	                             whisker_low: 115,
 	 	                             whisker_high: 400,
 	 	                             outliers: [50, 100, 425]
 	 	                         }
 	 	                     },
 	 	                     {
 	 	                         label: "2014",
 	 	                         values: {
 	 	                             Q1: 300,
 	 	                             Q2: 350,
 	 	                             Q3: 400,
 	 	                             whisker_low: 225,
 	 	                             whisker_high: 425,
 	 	                             outliers: [175, 450, 480]
 	 	                         }
 	 	                     },
 	 	                     {
 	 	                         label: "2015",
 	 	                         values: {
 	 	                             Q1: 100,
 	 	                             Q2: 200,
 	 	                             Q3: 300,
 	 	                             whisker_low: 25,
 	 	                             whisker_high: 400,
 	 	                             outliers: [450, 475]
 	 	                         }
 	 	                     },
 	 	                     {
 	 	                         label: "2016",
 	 	                         values: {
 	 	                             Q1: 75,
 	 	                             Q2: 100,
 	 	                             Q3: 125,
 	 	                             whisker_low: 50,
 	 	                             whisker_high: 300,
 	 	                             outliers: [450]
 	 	                         }
 	 	                     },
 	 	                     {
 	 	                         label: "2017",
 	 	                         values: {
 	 	                             Q1: 325,
 	 	                             Q2: 400,
 	 	                             Q3: 425,
 	 	                             whisker_low: 225,
 	 	                             whisker_high: 475,
 	 	                             outliers: [50, 100, 200]
 	 	                         }
 	 	                     }
 	 	                 ];
 		}
 		
 		//shipment by value
 		$scope.getShipmentByValue = function(){
 			
 			$http({
 				url: 'getShipmentByValue',
 				method: 'GET',
 				responseType: 'json'
 			}).then(function(response){
 				
 				if(response.data.success == true){
 					var valData = response.data.data; 					

 		 			$scope.optionsByValue = {
 		 		            chart: {
 		 		                type: 'lineChart',
 		 		                height: height,
 			 	                width: width,
 			 	                transitionDuration: 500,
 			 	                margin : {
 			 	                     top: 50,
 			 	                     right: 35,
 			 	                     bottom: 30,
 			 	                     left: 50
 			 	                },
 		 		                x: function(d){
 		 		                	if(d){
 		 		                		//var ts = new Date(d.x).getTime();
 		 		                		//return d3.time.format('%d/%m/%y')(new Date(ts));
 		 		                		return new Date(d.x);
 		 		                	}
 		 		                },
 		 		                y: function(d){ 
	 		                		if(d){
	 		                			return d.y;
	 		                		}
	 		                	},
 		 		                useInteractiveGuideline: true, 		 		               
 		 		                xAxis: {
 		 		                    axisLabel: 'Date',
 		 		                    tickFormat: function(d){
 		 		                    	var ts = new Date(d).getTime();
 		 		                		return d3.time.format('%d/%m/%y')(new Date(ts));
		 		                    },
 		 		                    axisLabelDistance: -10,
 		 		                    rotateLabels: 0
 		 		                },
 		 		                yAxis: {
 		 		                    axisLabel: 'Value',
 		 		                    tickFormat: function(d){
 		 		                        return d3.format('.01f')(d);
 		 		                    },
 		 		                    axisLabelDistance: -10
 		 		                }/*,
 		 		                callback: function(chart){
 		 		                    console.log("!!! lineChart callback !!!");
 		 		                }*/
 		 		            }/*,
 		 		            title: {
 		 		                enable: true,
 		 		                text: 'Title for Line Chart'
 		 		            },
 		 		            subtitle: {
 		 		                enable: true,
 		 		                text: 'Subtitle for simple line chart. Lorem ipsum dolor sit amet, at eam blandit sadipscing, vim adhuc sanctus disputando ex, cu usu affert alienum urbanitas.',
 		 		                css: {
 		 		                    'text-align': 'center',
 		 		                    'margin': '10px 13px 0px 7px'
 		 		                }
 		 		            },
 		 		            caption: {
 		 		                enable: true,
 		 		                html: '<b>Figure 1.</b> Lorem ipsum dolor sit amet, at eam blandit sadipscing, <span style="text-decoration: underline;">vim adhuc sanctus disputando ex</span>, cu usu affert alienum urbanitas. <i>Cum in purto erat, mea ne nominavi persecuti reformidans.</i> Docendi blandit abhorreant ea has, minim tantas alterum pro eu. <span style="color: darkred;">Exerci graeci ad vix, elit tacimates ea duo</span>. Id mel eruditi fuisset. Stet vidit patrioque in pro, eum ex veri verterem abhorreant, id unum oportere intellegam nec<sup>[1, <a href="https://github.com/krispo/angular-nvd3" target="_blank">2</a>, 3]</sup>.',
 		 		                css: {
 		 		                    'text-align': 'justify',
 		 		                    'margin': '10px 13px 0px 7px'
 		 		                }
 		 		            }*/
 		 		        };
 		 			
 		 			$scope.dataByValue = $scope.prepareValueData(valData);
 					
 				}else{
 					console.log(response);
 				}
 				
 			}, function(error){
 				console.log(error);
 				return error;
 			});
 			
 			
 			$scope.prepareValueData = function(valData){
 				return [
 	 	                {
 	 	                    values: valData.valueTotal,      //values - represents the array of {x,y} data points
 	 	                    key: 'Value', //key  - the name of the series.
 	 	                    color: '#7777ff',  //color - optional: choose your own line color.
 	 	                    area: false
 	 	                },
 	 	                {
 	 	                    values: valData.countTotal,      //values - represents the array of {x,y} data points
 	 	                    key: 'Number', //key  - the name of the series.
 	 	                    color: '#2ca02c',  //color - optional: choose your own line color.
 	 	                    area: true
 	 	                }
 	                ];
 			}
 			
 			 //$scope.dataByValue = sinAndCos();
 			 
 			 

 	        /*Random Data Generator */
 	        function sinAndCos() {
 	            var sin = [],sin2 = [],
 	                cos = [];

 	            //Data is represented as an array of {x,y} pairs.
 	            for (var i = 0; i < 100; i++) {
 	                sin.push({x: i, y: Math.sin(i/10)});
 	                sin2.push({x: i, y: i % 10 == 5 ? null : Math.sin(i/10) *0.25 + 0.5});
 	                cos.push({x: i, y: .5 * Math.cos(i/10+ 2) + Math.random() / 10});
 	            }

 	            //Line chart data should be sent as an array of series objects.
 	            return [
 	                {
 	                    values: sin,      //values - represents the array of {x,y} data points
 	                    key: 'Sine Wave', //key  - the name of the series.
 	                    color: '#ff7f0e'  //color - optional: choose your own line color.
 	                },
 	                {
 	                    values: cos,
 	                    key: 'Cosine Wave',
 	                    color: '#2ca02c'
 	                },
 	                {
 	                    values: sin2,
 	                    key: 'Another sine wave',
 	                    color: '#7777ff',
 	                    area: true      //area - set to true if you want this line to turn into a filled area chart.
 	                }
 	            ];
 	        };
 			
 		}
 		
 		///angular charts
 		
 		$scope.showAngularCharts = function(){
 			$scope.angularChart = true;
 		}
 		
}]);