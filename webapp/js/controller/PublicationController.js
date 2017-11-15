dashboardApp
		.controller(
				'PublicationCtrl',function($scope, $http, $rootScope,$cookies,$location,$window,publicationService,DataService, commonService, $route, $timeout){
$scope.publicationsData = [];   

$scope.itemArr = [
                	{type: 'compliance', heading: 'Compliance Handbooks', icon: 'ch-icon.png'},
                	{type: 'regulatory', heading: 'Government Regulatory', icon: 'gr-icon.png'},
                	{type: 'industrySpecific', heading: 'Industry Specific Implementation', icon: 'isi-icon.png'},
                	{type: 'statistics', heading: 'Statistics & Classifications', icon: 'sc-icon.png'}
               ];


$scope.pageSize = 3;

$scope.loadPublications=function() {
		
		var type = commonService.getResponse();
		
		if(!type || $.isEmptyObject(type)){
			$location.path('publication');
		}
		
		var typeObj = $scope.itemArr.filter(function(item) {
			  return item.type === type;
		})[0];
		
		$scope.pub_heading = typeObj.heading;
		$scope.pub_url = typeObj.icon;
		$scope.msgText = 'Searching..';
		$scope.pageinationIndex = 1;
		
		publicationService.arePublicationsPresent(type).then(function(success) {
			$scope.publicationsData = success.data;
			$scope.origianlData = angular.copy(success.data);
			$scope.msgText = 'No records found.';
			//$scope.buildPagination($scope.pageinationIndex);
			//console.log($scope.publicationsData);
		});
		
		commonService.setResponse(null);
};

$scope.buildPagination = function(pageNo){
	/*if(pageNo < 0)
		return;
		
	if(pageNo > $scope.origianlData.length/$scope.pageSize){
		return;
	}	
		
	var startIndex = $scope.pageSize * (pageNo - 1);
	var originalCopy = angular.copy($scope.origianlData);
	if(startIndex == 0){
		$scope.publicationsData = originalCopy.splice(0, 3);
	}else{
		$scope.publicationsData = originalCopy.splice(startIndex, $scope.pageSize);
	}
	$scope.pageinationIndex = pageNo;*/
}

$scope.range = function(min, max, step) {
    step = step || 1;
    var input = [];
    for (var i = min; i <= max; i += step) {
        input.push(i);
    }
    return input;
};

$scope.searchSubPublication = function(){
	
	//$scope.originalCopied = angular.copy($scope.origianlData);
	$scope.publicationsData.length = 0;
	
	$scope.subPubSearchQuery = $scope.subPubSearchQuery.toLowerCase();
	
	angular.forEach($scope.origianlData, function(item) {
	    if( item.author.toLowerCase().indexOf($scope.subPubSearchQuery) >= 0 ||
	    		item.description.toLowerCase().indexOf($scope.subPubSearchQuery) >= 0 ||
	    		item.publicationType.toLowerCase().indexOf($scope.subPubSearchQuery) >= 0 ||
	    		item.title.toLowerCase().indexOf($scope.subPubSearchQuery) >= 0)
	    	$scope.publicationsData.push(item);
	});
	
	//$scope.publicationsData = $scope.originalCopied;
	
	//alert($scope.subPubSearchQuery);
}

$scope.getSubPublication = function(type){
	commonService.setResponse(type);
	$location.path('publicationSub');
}


function getCookie(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) == (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}
$scope.init = function(){
        var startDocument = "Paper";
        var $form = $.cookie('pdf_url') ;
        var idx = $form.lastIndexOf("/");
        var fileName=$form.substring(idx+1);

        startDocument =fileName;


        $http({
            url: "views/publication/UI_flowpaper_desktop_flat.html",
            responseType: 'html',
            method: "GET"
        }).then(function(toolbarData){
            //alert('success');

var bottomUrl={};
if($.cookie('isAnnotate')=='true')
	bottomUrl='views/publication/UI_flowpaper_annotations.html';
else   
bottomUrl='views/publication/UI_flowpaper_bottom.html';

//             jQuery('#documentViewer').html(toolbarData.data);

            jQuery('#documentViewer').FlowPaperViewer(
                                { config : {

                                  //  JSONFile                : 'docs/Paper.js',
                                    PDFFile                 : $form,

                                    Scale                   : 0.6,
                                    ZoomTransition          : 'easeOut',
                                    ZoomTime                : 0.5,
                                    ZoomInterval            : 0.2,
                                    FitPageOnLoad           : true,
                                    FitWidthOnLoad          : false,
                                    FullScreenAsMaxWindow   : false,
                                    ProgressiveLoading      : false,
                                    MinZoomSize             : 0.2,
                                    MaxZoomSize             : 5,
                                    SearchMatchAll          : false,
                                    StickyTools             : true,

                                    Toolbar                 : toolbarData.data,
                                    BottomToolbar           : bottomUrl,
                                    InitViewMode            : 'Portrait',
                                    RenderingOrder          : 'html5,html',
                                    StartAtPage             : '',

                                    ViewModeToolsVisible    : true,
                                    ZoomToolsVisible        : true,
                                    NavToolsVisible         : true,
                                    CursorToolsVisible      : true,
                                    SearchToolsVisible      : true,

                                    UserCollaboration       : true,
                                    CurrentUser             : $.cookie('username'),

                                    WMode                   : 'window',
                                    localeChain             : 'en_US'
                                }}
                        );
        }, function(error){
            console.log(error);
            //angule.element('#preloader').delay(10).fadeOut(10);
            alert('error');
        });

        /*$.ajax({
                url: 'http://localhost:8080/gtn/UI_flowpaper_desktop_flat.html',
                type: "GET",
                beforeSend: function() {
                    console.log('sending request.....');
                    //$('div.table-responsive').html('<div id="overlay"><img src="http://www.freshdesignweb.com/blank.gif" class="loading_circle" alt="loading" /></div>');
                },
                success: function(data, textStatus, xhr) {          
                    console.log('success');
                    alert('success');
                },
                error: function(xhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                    
                 }
           });  */
}

$scope.searchPdfs=function() {

				$http({
					method : 'POST',
					url : '/gtn/searchPublications',
					data : {
						"description" : $scope.pdfDescription,
						"author":$.cookie('username')
					},
					headers : {
						'Content-Type' : 'application/json'
					}
				}).then(function(success) {
					$scope.publicationsData =success.data;


				}, function(error) {
					console.log('error: data = ', error);
				});

};
$scope.buyNow = function(title,description,price,qty,id, publication){
		DataService.cart.addItem(id,title,'Publication', description,price, 1);
		$scope.cart.shopCart($scope.cart.getTotalPrice(),'USD');
		
		publication.buyNow = false;
		publication.hasBeenMarkedForPurchase = true;
		
		$timeout(function(){														
			$scope.refreshCart();
        }, 100);
		
		
		//$route.reload();
		//$window.location.reload();
};
$scope.openPdf= function(urlStr){

    $cookies.put('pdf_url',urlStr);
$cookies.put('isAnnotate','false');
    $window.location.href = '/gtn/dashboard-index.html#/viewPdf';

//$window.location.href = urlStr;
};
 
$scope.annotatePdf= function(urlStr){
$cookies.put('isAnnotate','true');
$cookies.put('pdf_url',urlStr);
			
//$window.location.href = urlStr;
$window.location.href = '/gtn/dashboard-index.html#/viewPdf';

};        


jQuery(function() {
  var startDocument = "Paper";
var $form = $.cookie('pdf_url') ;
if($form !=undefined) {
var idx = $form.lastIndexOf("/");

var fileName=$form.substring(idx+1);
startDocument =fileName;
}

    /**
     * Handles the event of external links getting clicked in the document.
     *
     * @example onExternalLinkClicked("http://www.google.com")
     *
     * @param String link
     */
    jQuery('#documentViewer').bind('onExternalLinkClicked',function(e,link){

        window.open(link,'_flowpaper_exturl');
    });

    /**
     * Recieves progress information about the document being loaded
     *
     * @example onProgress( 100,10000 );
     *
     * @param int loaded
     * @param int total
     */
    jQuery('#documentViewer').bind('onProgress',function(e,loadedBytes,totalBytes){

    });

    /**
     * Handles the event of a document is in progress of loading
     *
     */
    jQuery('#documentViewer').bind('onDocumentLoading',function(e){

    });

    /**
     * Handles the event of a document is in progress of loading
     *
     */
    jQuery('#documentViewer').bind('onPageLoading',function(e,pageNumber){
    });

    /**
     * Receives messages about the current page being changed
     *
     * @example onCurrentPageChanged( 10 );
     *
     * @param int pagenum
     */
    jQuery('#documentViewer').bind('onCurrentPageChanged',function(e,pagenum){

    });

    /**
     * Receives messages about the document being loaded
     *
     * @example onDocumentLoaded( 20 );
     *
     * @param int totalPages
     */
    jQuery('#documentViewer').bind('onDocumentLoaded',function(e,totalPages){
if($.cookie('isAnnotate')=='true'){

$.removeCookie("isAnnotate");
var username=$.cookie('username');
var csrftoken = getCookie('XSRF-TOKEN');
		var dataTobeSent = {
author :$.cookie('username'),
document_filename:startDocument 

}
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password')),
			}

$.ajax({
						  url: "/gtn/getAllMarks",
						   beforeSend : function(xhr) {
						  			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},	
			data : JSON.stringify(dataTobeSent),

  			  contentType: "application/json",
	headers : JSON.stringify(headerTobeSent),

  type: 'POST',
						  success: function(data){

var viewer = $FlowPaper('documentViewer');
 
 $.each(data, function(index1, element1) {

 if(element1.type=='note'){


	viewer.addMark({type:'note',id:element1.id, note: element1.note,positionX:element1.positionX,positionY:element1.positionY,width:element1.width,height:element1.height,pageIndex:element1.pageIndex,collapsed:element1.collapsed,displayFormat:'html'});

					
}
if(element1.type=='drawing') {
viewer.addMark({type:'drawing',id:element1.id, pageIndex :element1.pageIndex,collapsed:element1.collapsed,points:element1.points,color:element1.color});
}
if(element1.type=='highlight'){

var hasSelection =false;
if(element1.has_selection==1)
	hasSelection =true;
viewer.addMark({type:'highlight', selection_info: element1.selection_info,collapsed:element1.collapsed,id:element1.id,has_selection:element1.hasSelection ,color:element1.color});
}
if(element1.type=='strikeout'){
var hasSelection =false;
if(element1.Has_selection==1)
	hasSelection =true;
viewer.addMark({type:'strikeout', selection_info: element1.selection_info,id:element1.id,has_selection:element1.hasSelection ,color:element1.color,collapsed:element1.collapsed});
}
});
 

												  },
            error: function(jqXHR, textStatus, errorThrown)
{
  
}
					});

}    });

    /**
     * Receives messages about the page loaded
     *
     * @example onPageLoaded( 1 );
     *
     * @param int pageNumber
     */
    jQuery('#documentViewer').bind('onPageLoaded',function(e,pageNumber){
    });

    /**
     * Receives messages about the page loaded
     *
     * @example onErrorLoadingPage( 1 );
     *
     * @param int pageNumber
     */
    jQuery('#documentViewer').bind('onErrorLoadingPage',function(e,pageNumber){

    });

    /**
     * Receives error messages when a document is not loading properly
     *
     * @example onDocumentLoadedError( "Network error" );
     *
     * @param String errorMessage
     */
    jQuery('#documentViewer').bind('onDocumentLoadedError',function(e,errMessage){
});

    /**
     * Receives error messages when a document has finished printed
     *
     * @example onDocumentPrinted();
     *
     */
    jQuery('#documentViewer').bind('onDocumentPrinted',function(e){

    });

    /**
     * Handles the event of annotations getting clicked.
     *
     * @example onMarkClicked(object)
     *
     * @param Object mark that was clicked
     */
    jQuery('#documentViewer').bind('onMarkClicked',function(e,mark){

});

    /**
     * Handles the event of annotations getting clicked.
     *
     * @example onMarkCreated(object)
     *
     * @param Object mark that was created
     */
    jQuery('#documentViewer').bind('onMarkCreated',function(e,mark){

mark.document_filename=startDocument ;

var csrftoken = getCookie('XSRF-TOKEN');
if(mark.note && !(typeof mark.note == "string")){

           mark.note = '<notes>'+mark.note.find("note").parent().html()+'</notes>';
                    }
				
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}

	$.ajax({
						  url: "/gtn/createMark",
						   beforeSend : function(xhr) {
						  			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},	
data :JSON.stringify(mark, null, 2)
	,
			responseType : 'json',
			  contentType: "application/json;",
dataType: 'json',

			headers : JSON.stringify(headerTobeSent),

type: 'POST',
success: function(data){


							if(data=="1"){


 // mark created
								append_log(String.format('Mark updated in database (id:{0})',mark.id));
							}
							if(data=="0"){



 // failed creating mark

								append_log('Failed updating mark in database');
							}
						  },
            error: function(jqXHR, textStatus, errorThrown)
{
  
}
					});

    });

    /**
     * Handles the event of annotations getting clicked.
     *
     * @example onMarkDeleted(object)
     *
     * @param Object mark that was deleted
     */
    jQuery('#documentViewer').bind('onMarkDeleted',function(e,mark){

var csrftoken = getCookie('XSRF-TOKEN');
var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}

			$.ajax({
						  url: "/gtn/deleteMark",
						  	 beforeSend : function(xhr) {
						  			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},	
						  data : JSON.stringify(mark, null, 2),
						  
			responseType : 'json',
			  contentType: "application/json;",
			headers : JSON.stringify(headerTobeSent),

						  type: 'POST',
dataType: 'json',
						  success: function(data){


							if(data=="1"){


 // mark created
								append_log(String.format('Mark updated in database (id:{0})',mark.id));
							}
							if(data=="0"){


 // failed creating mark

								append_log('Failed updating mark in database');
							}
						  },
            error: function(jqXHR, textStatus, errorThrown)
{
  
}
					});

    });
function chainOfMethods(){
}
    /**
     * Handles the event of annotations getting clicked.
     *
     * @example onMarkChanged(object)
     *
     * @param Object mark that was changed
     */
    jQuery('#documentViewer').bind('onMarkChanged',function(e,mark){

if(mark.note) {
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}
	var csrftoken = getCookie('XSRF-TOKEN');
	
$.ajax({
						  url: "/gtn/changeMark",
						   beforeSend : function(xhr) {
						  			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},
data :JSON.stringify(mark, null, 2)
	,
			responseType : 'json',
			  contentType: "application/json;",
dataType: 'json',

			headers : JSON.stringify(headerTobeSent),

type: 'POST',
success: function(data){
if(data=="1"){
append_log(String.format('Mark updated in database (id:{0})',mark.id));
}
if(data=="0"){
append_log('Failed updating mark in database');
}
},
 error: function(jqXHR, textStatus, errorThrown)
{
  
}


});
}
 });   /**
     * Handles the event of a pdf requiring a password
     *
     * @example onPasswordNeeded(updatePassword,reason)
     *
     * @param updatePassword callback function for setting the password
     */
    jQuery('#documentViewer').bind('onPasswordNeeded',function(e,updatePassword){

    });
});

});
