

jQuery(function() {
  var startDocument = "Paper";
var $form = $.cookie('pdf_url') ;
var idx = $form.lastIndexOf("/");

var fileName=$form.substring(idx+1);
startDocument =fileName;


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

var username=$.cookie('username');
		var dataTobeSent = {
author :$.cookie('username'),
document_filename:startDocument 

}
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password')),
			}

$.ajax({
						  url: "/gtn/getAllMarks",
			data : JSON.stringify(dataTobeSent),

  			  contentType: "application/json",
	headers : JSON.stringify(headerTobeSent),

  type: 'POST',
						  success: function(data){

var viewer = $FlowPaper('documentViewer');
 
 $.each(data, function(index1, element1) {

 if(element1.type=='note'){


	viewer.addMark({type:'note',id:element1.Id, note: element1.note,positionX:element1.positionX,positionY:element1.positionY,width:element1.width,height:element1.height,pageIndex:element1.pageIndex,collapsed:element1.collapsed,displayFormat:'html'});

					
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
  alert("Error: "+jqXHR.responseText+" , Please try again");   
}
					});

//chainOfMethods();
    });

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


if(mark.note && !(typeof mark.note == "string")){

           mark.note = '<notes>'+mark.note.find("note").parent().html()+'</notes>';
                    }
				
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}

	$.ajax({
						  url: "/gtn/createMark",
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
  alert("Error: "+jqXHR.responseText+" , Please try again");   
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

		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}

			$.ajax({
						  url: "/gtn/deleteMark",
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
  alert("Error: "+jqXHR.responseText+" , Please try again");   
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
                    
$.ajax({
						  url: "/gtn/changeMark",
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
  alert("Error: "+jqXHR.responseText+" , Please try again");   
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


