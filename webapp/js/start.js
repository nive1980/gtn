//var host = "http://localhost:51920/Jumpstart-web-new/"
var host = " "


 function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

function getAbout() {

    //alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getAbout",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#aboutdesc").html(msg.d[0]);
                $("#fulldesc").html(msg.d[1]);

            }
            else {
                $("#aboutdesc").html("");
                return false;
            }
        }
    });

}


function getSheduleh() {

    //alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSheduleh",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tabs-lv1").html(msg.d);

            }
            else {
                $("#tabs-lv1").html("");
                return false;
            }
        }
    });
}

function getShedulehArchive(year) {

    //alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getShedulehArchive",
        data: "{year:'" + year + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tabs-lv1").html(msg.d);

            }
            else {
                $("#tabs-lv1").html("");
                return false;
            }
        }
    });
}

function getShedulebArchive(year) {

   /// alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getShedulebArchive",
        data: "{year:'" + year + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-first").html(msg.d);

            }
            else {
                $("#tab-first").html("");
                return false;
            }
        }
    });
}

function getSheduleb() {

    /// alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSheduleb",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-first").html(msg.d);

            }
            else {
                $("#tab-first").html("");
                return false;
            }
        }
    });
}

function getSheduleArchive(year) {

    //alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSheduleArchive",
        data: "{year:'" + year + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-second").html(msg.d);

            }
            else {
                $("#tab-second").html("");
                return false;
            }
        }
    });
}

function getShedule() {

    //alert("Hello");
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getShedule",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-second").html(msg.d);

            }
            else {
                $("#tab-second").html("");
                return false;
            }
        }
    });
}

function getSpeakers() {
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSpeakers",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

               
                $("#Div_speakers").html(msg.d);

            }
            else {
                $("#Div_speakers").html("");
                return false;
            }
        }
    });
}


function getSpeakersArchive(year) {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSpeakersArchive",
        data: "{year:'" + year + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#Div_speakers").html(msg.d);

            }
            else {
                $("#Div_speakers").html("");
                return false;
            }
        }
    });
}


function getSpeakersArchiveYear() {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSpeakersArchiveYear",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-price-b").html(msg.d);

            }
            else {
                $("#tab-price-b").html("");
                return false;
            }
        }
    });
}

function getScheduleArchiveYear() {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/getScheduleArchiveYear",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#tab-price-d").html(msg.d);

            }
            else {
                $("#tab-price-d").html("");
                return false;
            }
        }
    });
}

function getUrlVars() {
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}


function speakers_detail() {

   // alert("hello");
    
    
    var id = getUrlVars()["id"];

    //alert(id);
    
    
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getSpeakers_detail",
        data: "{Sid:'" + id + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#content").html(msg.d);

            }
            else {
                $("#content").html("");
                return false;
            }
        }
    });
}


function calulateAmountPaidDelhi()
{

    var seats = $("#ddlSeats").val();
//    if(seats == 'No of seats')
//    {
//       return false;
//    }
    
     $.ajax({
        type: "POST",
        url: host+"service.aspx/calulateAmountPaidDelhi",
        data: "{seats:'" + seats + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
             if (msg.d != "error") { 
                  
               
                 $("#divAmountPaid").html("Total Amount to be paid INR " + msg.d + " (including service tax)");
               
            }
            else {
                   $("#divAmountPaid").html("");
                   return false;
            }
        }
    });
    
}


function calulateAmountPaidBangloru()
{

    var seats = $("#ddlSeats").val();
//    if(seats == 'No of seats')
//    {
//       return false;
//    }

     var price=0;
     
    
     if($("#Radio1").is(":checked"))
     {
       price = 3070; 
     }
     else  if($("#Radio2").is(":checked"))
     {
       price = 3070; 
     }
     else  if($("#Radio3").is(":checked"))
     {
       price = 2350; 
     }
      else  if($("#Radio4").is(":checked"))
     {
       price = 3070; 
     }
      else  if($("#Chkday1").is(":checked"))
     {
       price = 2000; 
     }
      else  if($("#Radiowriter").is(":checked"))
     {
       price = 2000; 
     }
      else  if($("#Radioillustrator").is(":checked"))
     {
       price = 2000; 
     }
      else  if($("#RadioScreen").is(":checked"))
     {
       price = 3070; 
     }
      else  if($("#Radioanimation").is(":checked"))
     {
       price = 350; 
     }
     $.ajax({
        type: "POST",
        url: host+"service.aspx/calulateAmountPaidBangloru",
        data: "{seats:'" + seats + "',price:'" + price + "',promocode:'" + $("#txtPromocode").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
             if (msg.d != "error") { 
                
               
                 $("#divAmountPaid").html("Total Amount to be paid INR " + msg.d + " (including service tax)");
               
            }
            else {
                   $("#divAmountPaid").html("");
                   return false;
            }
        }
    });
    
}

function Register() {

 
    if ($("#registration-form-name").val() == 'Name and Surname') {
        //alert("Please enter name!");
        //$('#registration-form-name').focus();
        return false;
    }
    if ($("#registration-form-mail").val() == 'Your Mail Here') {
        //alert("Please enter mail!");
        //$('#registration-form-mail').focus();
        return false;
    }
    
        if ($("#registration-form-phone").val() == 'Your Phone Number') {
        //alert("Please enter phone!");
        //$('#registration-form-phone').focus();
        return false;
    }
    
     if ($("#city").val() == 'Select Your City') {
        //alert("Select city!");
        //$('#city').focus();
        return false;
    }
    
    $.ajax({
        type: "POST",
        url: host+"service.aspx/Register",
        data: "{name:'" + $("#registration-form-name").val().replace("'", "`") + "',email:'" + $("#registration-form-mail").val().replace("'", "`") + "',phone:'" + $("#registration-form-phone").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
             if (msg.d != "error") { 
                if($("#city").val()=="Register for New Delhi")
	            {
	                window.location="register_delhi.aspx";
	            }
	            else 
	            {
	                window.location="register_bengaluru.aspx";
	            }
            }
            else {
                   alert('Somwthing went wrong! Please try again!');
                   return false;
            }
        }
    });
}

//function Register1() {

//    //alert($("#dname").find('.child') val());

//   // var d = $("#dname").find("#registration-form-name1").val();

//    //var OriginalFiled = $('#dname input[name="' + fname + '"]');

//    var d =  $("#dname").find("input").val();

//    alert(d);

//    if ($("#registration-form-name1").text() == 'Name and Surname') {
//        //alert("Please enter name!");
//        //$('#registration-form-name').focus();
//        return false;
//    }

//    if ($("#registration-form-mail1").text() == 'Your Mail Here') {
//        //alert("Please enter mail!");
//        //$('#registration-form-mail').focus();
//        return false;
//    }

//    if ($("#registration-form-phone1").text() == 'Your Phone Number') {
//        //alert("Please enter phone!");
//        //$('#registration-form-phone').focus();
//        return false;
//    }

//    alert($("#topcity option:selected").text());
//    
//    if ($("#topcity").val() == 'Select Your City') {
//        //alert("Select city!");
//        //$('#city').focus();
//        return false;
//    }
//    alert('Hi');
//    $.ajax({
//        type: "POST",
//        url: host + "service.aspx/Register",
//        data: "{name:'" + $("#registration-form-name1").val() + "',email:'" + $("#registration-form-mail1").val() + "',phone:'" + $("#registration-form-phone1").val() + "'}",
//        contentType: "application/json; charset=utf-8",
//        dataType: "json",
//        async: true,
//        cache: false,
//        success: function(msg) {
//            if (msg.d != "error") {
//                if ($("#cityTOP").val() == "Register for New Delhi") {
//                    window.location = "register_delhi.aspx";
//                }
//                else {
//                    window.location = "register_bengaluru.aspx";
//                }
//            }
//            else {
//                alert('Somwthing went wrong! Please try again!');
//                return false;
//            }
//        }
//    });
//}



function Register1() {  
// alert('Hi');
//alert(document.getElementById("registrationNameValue").value);
    if (document.getElementById("registrationNameValue").value == 'Name and Surname') {
//        alert("Please enter name!");
//        $('#registration-form-name1').focus();
        return false;
    }
    //alert(document.getElementById("registrationMailValue").value);
    if (document.getElementById("registrationMailValue").value == 'Your Mail Here') {
//        alert("Please enter mail!");
//        $('#registration-form-mail1').focus();
        return false;
    }
   //alert(document.getElementById("registrationPhoneValue").value);
   if (document.getElementById("registrationPhoneValue").value == 'Your Phone Number') {
//        alert("Please enter phone!");
//        $('#registration-form-phone1').focus();
       return false;
    }
    //alert(document.getElementById("registrationCityValue").value);
     if (document.getElementById("registrationCityValue").value == 'Select Your City') {
//        alert("Please select city!");
//        $('#city_slide').focus();
       return false;
    }
    
//     //var pcity_slide = $('#city_slide :selected').val();
//     var txtcity=1;
//     var txtvalcity="";
//     $('.btn-group').each(function(){
//     if(txtcity==1)
//     {
//     txtvalcity=$('.filter-option', this).text();
//        alert($('.filter-option', this).text());
//        }else{
//        }
//        txtcity++
//    });
//     //alert($('.btn').('.filter-option').text());
//     if (txtvalcity == 'Select Your City') {
//        //alert("Select city!");
//        //$('#city').focus();
//        return false;
//    }

    $.ajax({
        type: "POST",
        //url: host+"service.aspx/Register",
        data: "{name:'" + document.getElementById("registrationNameValue").value.replace("'", "`") + "',email:'" + document.getElementById("registrationMailValue").value.replace("'", "`") + "',phone:'" + document.getElementById("registrationPhoneValue").value + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
             if (msg.d != "error") { 
                if(document.getElementById("registrationCityValue").value=="Register for New Delhi")
	            {
	                window.location="register_delhi.aspx";
	            }
	            else 
	            {
	                window.location="register_bengaluru.aspx";
	            }
            }
            else {
                   alert('Somwthing went wrong! Please try again!');
                   return false;
            }
        }
    });
}

function EmailThis(){  
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/EmailThis",
        data: "{toname:'" + $("#to_name").val() + "',toemail:'" + $("#to_email").val() + "',fromname:'" + $("#from_name").val() + "',fromemail:'" + $("#from_email").val() + "',messsage:'" + $("#email_msg").val() + "',headerId:'" + $("#emailthisid").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error" && msg.d != "success") {
                 alert(msg.d);
                 return false;
            }
            if (msg.d != "error") {
                $("#divMsgEmailThis").show();
                  $("#divMsgEmailThis").text("Mail has been sent successfully!");   
            }
            else {
                   $("#divMsgEmailThis").show();
                  $("#divMsgEmailThis").text("Somwthing went wrong! Please try again!");   
                   return false;
            }
        }
    });
}

function SendRegisterMail() {  
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/SendRegisterMail",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "success") {
                 alert('Session has expired. Please register again!');
                 return false;
            }
            else {
                   alert('Mail has been sent successfully!');
                   return false;
            }
        }
    });
}

function Login() {
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/login",
        data: "{email:'" + $("#log_email").val() + "',password:'" + $("#log_passwd").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "success") {
                 $("#divMsgLoginPopUp").show();
                 $("#divMsgLoginPopUp").text(msg.d);  
                return false;   
            }
            else
            {
              window.location.href = host + "index.aspx";
             
            }
        }
    });
}




function EditProfile(userid) {
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/EditProfile",
        data: "{name:'" + $("#profile_name").val() + "',institution:'" + $("#profile-org").val() + "',email:'" + $("#profile-email").val() + "',userId:'" + userid + "',notification:'" + $("#reg_Notification").is(":checked") + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                  $("#divMsgS").show();
                  $("#divMsgS").text("Profile Updated Successfully!");    
            }
            else
            {
              $("#divMsgS").show();
              $("#divMsgS").text("Somwthing went wrong! Please try again!");    
              return false;
            }
        }
    });
}


function ChangePwd(userid) {
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/ChangePwd",
        data: "{Password:'" + $("#old_passwd").val() + "',NewPassword:'" + $("#new_passwd").val() + "',userId:'" + userid + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                  $("#divMsgS").show();
                  $("#divMsgS").text("Password Changed Successfully!");    
            }
            else
            {
              $("#divMsgS").show();
              $("#divMsgS").text("'Please enter correct old password'!");    
              return false;
            }
        }
    });
}

function DeleteAccount(userid) {
var resonfordeleetion ="";
if(del_option1.checked == true)
     resonfordeleetion = $("#del_option1").val()
else if(del_option2.checked == true)
     resonfordeleetion = $("#del_option2").val()
else if(del_option3.checked == true)
     resonfordeleetion = $("#del_option3").val()
else if(del_option4.checked == true)
     resonfordeleetion = $("#del_option4").val()
else if(del_option5.checked == true)
     resonfordeleetion = $("#delete_msg").val()
       
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/DeleteAccount",
        data: "{reason:'" + resonfordeleetion + "',userId:'" + userid + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                  $("#divMsgDeleteAccount").show();
                  $("#divMsgDeleteAccount").text("Account Deleted Successfully!");  
                  //sleep(10000);
                  setTimeout(function(){ window.location.href = host + "index.aspx?Logout=true"; }, 2000);
                   
            }
            else
            {
              $("#divMsgDeleteAccount").show();
              $("#divMsgDeleteAccount").text("Somwthing went wrong! Please try again!");    
              return false;
            }
        }
    });
}

function ContactUsEnquiry() {  
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/ContactUsEnquiry",
        data: "{name:'" + $("#enq_name").val() + "',Subject:'" + $("#enq_sub").val() + "',email:'" + $("#enq_email").val() + "',Contactno:'" + $("#enq_mobile").val() + "',Comments:'" + $("#enq_comments").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error" && msg.d != "success") {
                 //alert(msg.d);
                 
                  $("#divMsgContactUsEnquiry").show();
                  $("#divMsgContactUsEnquiry").text(msg.d);  
                 return false;
            }
            if (msg.d != "error") {
               //alert('Quick Enquiry has been submitted successfully!');
               $("#divMsgContactUsEnquiry").show();
               $("#divMsgContactUsEnquiry").text("Quick Enquiry has been submitted successfully!");  
               $("#enq_name").val('');
               $("#enq_sub").val('');
               $("#enq_email").val('');
               $("#enq_mobile").val('');
               $("#enq_comments").val('');
            }
            else {
                   alert('Somwthing went wrong! Please try again!');
                   return false;
            }
        }
    });
}


function Search() { 
//    if($("#search").val()=='')
//    {
//        alert('Please Enter Search Text');
//        $("#search").focus();
//        return;
//    }
    if($("#reftype").val() == null)
    {
        alert('Please Select Reference Type');
        $("#reftype").focus();
        return;
    }
    
     window.location.href= host+'search-result.aspx?searchText=' + $("#search").val() + '&captionName=' + $("#reftype").val() + '';
}

function SearchTop() { 
    if($("#top_search").val()=='')
    {
        alert('Please Enter Search Text');
        $("#top_search").focus();
        return;
    }
    
     window.location.href= host+'search-result.aspx?searchText=' + $("#top_search").val() + '&captionName=Book,Book Section,Catalogue Essay,Periodical Article,Newspaper Article,Thesis';
}


function AdvancedsearchValue() { 
//alert($('select[name=adv_language]').val());
//alert($("#adv_reftype").val());

//if($("#adv_author_editor").val()=='' && $("#pub_place").val()=='' && $("#adv_artist").val()=='' && $("#adv_periodicals").val()==''
//&& $("#adv_publisher").val()=='' && $("#adv_language").val() == null && $("#adv_reftype").val() == null)
//{
//alert('Please Enter Text');
//return;
//}
    
    var Editor = $("#adv_author_editor").val(); 
    var refrenceType = $("#adv_reftype").val();
    var lang = $("#adv_language").val();
    var PlacePublished = $("#pub_place").val(); 
    var artist = $("#adv_artist").val(); 
    var Periodicals = $("#adv_periodicals").val();
    var Publisher = $("#adv_publisher").val();
    var fromDate = $("#txtFromDate").val();
    var todate = $("#txtToDate").val();
    
   
    $.ajax({
        type: "POST",
        //url: host+"service.aspx/AdvancedsearchRValues",
       data: "{Editor:'" + Editor + "',refrenceType:'" + refrenceType + "',lang:'" + lang + "',PlacePublished:'" + PlacePublished + "',artist:'" + artist + "',Periodicals:'" + Periodicals + "',Publisher:'" + Publisher + "',fromDate:'" + fromDate + "',todate:'" + todate + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                window.location.href= host+ 'search-result.aspx';
               
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });
}

function allLang(lang) { 
 $.ajax({
        type: "POST",
        //url: host+"service.aspx/allLang",
       data: "{lang:'" + lang + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                //alert("Something went wrong! Please try again.");
               
            }
            else {
                //alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

function addLang(lang) { 

   $.ajax({
        type: "POST",
        //url: host+"service.aspx/addLang",
       data: "{lang:'" + lang + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                //alert("Something went wrong! Please try again.");
               
            }
            else {
                //alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}
function subLang(lang) { 

   $.ajax({
        type: "POST",
        //url: host+"service.aspx/subLang",
       data: "{lang:'" + lang + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
               //alert("Something went wrong! Please try again.");
               
            }
            else {
               // alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}


function allRefernce(reference) { 
 $.ajax({
        type: "POST",
        //url: host+"service.aspx/allRefernce",
       data: "{reference:'" + reference + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                //alert("Something went wrong! Please try again.");
               
            }
            else {
                //alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

function addRefernce(reference) { 

   $.ajax({
        type: "POST",
        //url: host+"service.aspx/addRefernce",
       data: "{reference:'" + reference + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                //alert("Something went wrong! Please try again.");
               
            }
            else {
                //alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}
function subRefernce(reference) { 

   $.ajax({
        type: "POST",
        //url: host+"service.aspx/subRefernce",
       data: "{reference:'" + reference + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
               //alert("Something went wrong! Please try again.");
               
            }
            else {
               // alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

function searchBy(searchText,captionName,captionvalue,searchby) { 
//alert(searchText);
//alert(captionName);
//alert(searchValue);
//alert(searchText);
window.location.href= host+'search-result.aspx?searchText=' + searchText + '&captionName=' + captionName + '&captionvalue=' + captionvalue + '&searchby=' + searchby;
}


function searchByAdv(captionvalue,searchby) { 
//alert(searchText);
//alert(captionName);
//alert(searchValue);
//alert(searchText);
window.location.href= host+'search-result.aspx?captionvalue=' + captionvalue + '&searchby=' + searchby;
}



function searchValue() { 

    
    var Editor = ''; 
    var refrenceType = '';
    var lang = '';
    var PlacePublished = ''; 
    var artist = ''; 
    var Periodicals = '';
    var Publisher = '';
    var fromDate = $("#year_from").val();
    var todate = $("#year_to").val();
    if(fromDate == 'Select Year')
      fromDate = '';
    if(todate == 'Select Year')
      todate = '';
   
    $.ajax({
        type: "POST",
        url: host+"service.aspx/AdvancedsearchRValues",
       data: "{Editor:'" + Editor + "',refrenceType:'" + refrenceType + "',lang:'" + lang + "',PlacePublished:'" + PlacePublished + "',artist:'" + artist + "',Periodicals:'" + Periodicals + "',Publisher:'" + Publisher + "',fromDate:'" + fromDate + "',todate:'" + todate + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                window.location.href= host+ 'search-result.aspx';
               
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });
}




function ForgotPwd() {
    $.ajax({
        type: "POST",
        url: host+"service.aspx/ForgotPwd",
        data: "{email:'" + $("#log_email1").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                $("#log_email1").val("");
                $("#divMsgForgotPwd").show();
                $("#divMsgForgotPwd").text("Password sent! Please check your inbox.");
                return;
            }
            else {
                $("#log_email1").focus();
                $("#divMsgForgotPwd").show();                
                $("#divMsgForgotPwd").text("Invalid Email Id!");
                return;
            }
        }
    });
}


function addToBookMarks(headerid,type) { 
 $.ajax({
        type: "POST",
        url: host+"service.aspx/AddBookMarks",
        data: "{headerid:'" + headerid + "',type:'" + type + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") 
            {
                 if (msg.d != "deleted") 
                 {
                    alert('Added to bookmarks successfully!'); 
                      window.location.reload();
                    return;
                 }
                 else
                 {
                    alert('deleted from bookmarks successfully!'); 
                    window.location.href= host+ 'bookmarks.aspx';
                    return;
                 }
               
            }
            else {
               
               alert('Already added to bookmarks!'); return;
            }
        }
    });
}


function deleteBookMarks() { 
 $.ajax({
        type: "POST",
        url: host+"service.aspx/AddBookMarks",
       data: "{headerid:'" + $("#emailthisid").val() + "',type:'0'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") 
            {
                 if (msg.d != "deleted") 
                 {
                     alert('Added to bookmarks successfully!'); 
                     window.location.reload();
                    return;
                 }
                 else
                 {
                     
                    alert('deleted from bookmarks successfully!'); 
                    window.location.href= host+ 'bookmarks.aspx';
                    return;
                 }
               
            }
            else {
               
               alert('Already added to bookmarks!'); return;
            }
        }
    });

}


function CiteThis(headerId) {
    $.ajax({
        type: "POST",
        url: host+"service.aspx/CiteThis",
        data: "{headerId:'" + headerId + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function (msg) {
            if (msg.d != "error") {
                  $("#divCitethis").html(msg.d);    
            }
            else
            {
              $("#divCitethis").text("Somwthing went wrong! Please try again!");    
              return false;
            }
        }
    });
}




//////////////////////----------------------Admin--------------------------/////////////////////////

function RegisterAdmin() {


   

   
    if ($("#user_type").val() == 'Select') {
        alert("Please select user type!");
        $('#user_type').focus();
        return false;
    }

    
    if ($("#user_name").val() == '') {
        alert("User name cannot be blank!");
        $('#user_name').focus();
        return false;
    }

   
    if ($("#user_id").val() == '') {
        alert("User id cannot be blank!");
        $('#user_id').focus();
        return false;
    }

    
    if ($("#user_pwd").val() == '') {
        alert("Password cannot be blank!");
        $('#user_pwd').focus();
        return false;
    }
    
    
    $.ajax({
        type: "POST",
        url: host + "service.aspx/RegisterAdmin",
        data: "{uid:'" + $("#user_Admin_id").val() + "', user_type:'" + $("#user_type option:selected").text() + "',user_name:'" + $("#user_name").val() + "' ,user_id:'" + $("#user_id").val() + "',user_pwd:'" + $("#user_pwd").val() + "',active:'" + $("input[name=checkme]:checked").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {
                $("#user_type option:selected").text("Select");
                $("#user_name").val("");
                $("#user_id").val("");
                $("#user_pwd").val("");

                alert(msg.d);
                getAdminUser();
                $("#btnsubmitu").val("Save");

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}




function getAdminUser() {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/getAdminUser",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(collapse4).show();
                $(collapse4).html(msg.d);
                

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

function ChildClick(CheckBox,headerId) {
var ischecked;
if(CheckBox.checked)
     ischecked =1;
 else
      ischecked=0;

    $.ajax({
        type: "POST",
        url: host + "service.aspx/ChildClick",
        data: "{ischecked:'" + ischecked + "',headerId:'" + headerId + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

               
                

            }
            else {
               
                return;
            }
        }
    });

}




//function deleteAdminUser(id) {


//    $('#btndelete').click(function() {
//        deleteAdminUserA(id);
//    });

//    $("#userDelete").show();


//    $("#userDelete").attr("class", "modal fade in");

//}


function deleteAdminUserA() {
  
    $.ajax({
        type: "POST",
        url: host + "service.aspx/deleteAdminUser",
        data: "{id:'" + $("#user_Admin_id").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {


                $("#user_Admin_id").val("0");
                getAdminUser();
               

              
                

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

    $("#userDelete").hide();
    $("#userDelete").attr("class", "modal fade");

}






function editAdminUser() {


   
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/editAdminUser",
        data: "{id:'" + $("#user_Admin_id").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $("#user_Admin_id").val(msg.d[0]);
                $("#user_id_Popup").val(msg.d[1]);
                $("#user_name_Popup").val(msg.d[2]);
                $("#user_pwd_Popup").val(msg.d[3]);
                //$("#user_type_Popup option:selected").val(msg.d[4]);
                $("#user_type_Popup").val(msg.d[4]);
               
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

    $('#btnSave').click(function() {
    
    if ($("#user_type_Popup").val() == 'Select') {
        alert("Please select user type!");
        $('#user_type_Popup').focus();
        return false;
    }

    
    if ($("#user_name_Popup").val() == '') {
        alert("User name cannot be blank!");
        $('#user_name_Popup').focus();
        return false;
    }

   
    if ($("#user_id_Popup").val() == '') {
        alert("User id cannot be blank!");
        $('#user_id_Popup').focus();
        return false;
    }

    
    if ($("#user_pwd_Popup").val() == '') {
        alert("Password cannot be blank!");
        $('#user_pwd_Popup').focus();
        return false;
    }
    
    UpdateAdminUserA($("#user_Admin_id").val(), $("#user_name_Popup").val(), $("#user_id_Popup").val(), $("#user_pwd_Popup").val(), $("#user_type_Popup option:selected").val());
    });
    
    $("#usereditModal").show();


    $("#usereditModal").attr("class", "modal fade in");
}

function UpdateAdminUserA(id, username, userid, password, usertype) {




    $.ajax({
        type: "POST",
        url: host + "service.aspx/RegisterAdmin",
        data: "{uid:'" + id + "', user_type:'" + usertype + "',user_name:'" + username + "' ,user_id:'" + userid + "',user_pwd:'" + password + "',active:'" + $("input[name=checkme]:checked").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {
                $("#user_Admin_id").val("0");
               
                getAdminUser();

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

    $("#usereditModal").hide();


    $("#usereditModal").attr("class", "modal fade");

}



//////////////////////////////////-------------Register User----------------/////////////////////////////////


function nextPageUser() {
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/nextPageUser",
        data: "{NoR:'" + $("#NOR option:selected").text() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(dataTable).show();
                $(dataTable).html(msg.d[0]);
                $(dataTable_paginate).show();
                $(dataTable_paginate).html(msg.d[1]);
                $(dataTable_info).show();
                $(dataTable_info).html(msg.d[2]);
                return;
            }
            else {
                alert("Somwthing went wrong! Please try again.");
                return;
            }
        }
    });
}
function prePageUser() {
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/prePageUser",
        data: "{NoR:'" + $("#NOR option:selected").text() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(dataTable).show();
                $(dataTable).html(msg.d[0]);
                $(dataTable_paginate).show();
                $(dataTable_paginate).html(msg.d[1]);
                $(dataTable_info).show();
                $(dataTable_info).html(msg.d[2]);
                return;
            }
            else {
                alert("Somwthing went wrong! Please try again.");
                return;
            }
        }
    });
}

function getAdminRUser() {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/getAdminRUser",
        data: "{NoR:'" + $("#NOR option:selected").text() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(dataTable).show();
                $(dataTable).html(msg.d[0]);
                $(dataTable_paginate).show();
                $(dataTable_paginate).html(msg.d[1]);
                $(dataTable_info).show();
                $(dataTable_info).html(msg.d[2]);


            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

//function deleteAdminRUser(id) {


//    $('#btndelete').click(function() {
//        deleteAdminRUserA(id);
//    });

//    $("#userDelete").show();
//   

//    $("#userDelete").attr("class", "modal fade in");

//}

function deleteAdminRUserA() {
    
  $.ajax({
        type: "POST",
        url: host + "service.aspx/deleteAdminRUser",
        data: "{id:'" + $("#user_Admin_id").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $("#user_Admin_id").val("0");
                getAdminRUser();
               
               
                
                
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });
    $("#userDelete").hide();


    $("#userDelete").attr("class", "modal fade");

}

//////////////////////////////////-----------End Register User----------------/////////////////////////////////
//////////////////////////////////-----------Up comming----------------/////////////////////////////////

function insertUpcomming() {





    if ($("#utitle1").val() == '') {
        alert("title1 cannot be blank!");
        $('#utitle1').focus();
        return false;
    }


    if ($("#udescription1").val() == '') {
        alert("Description1 cannot be blank!");
        $('#udescription1').focus();
        return false;
    }

    if ($("#utitle2").val() == '') {
        alert("Title2 cannot be blank!");
        $('#utitle2').focus();
        return false;
    }


    if ($("#udescription2").val() == '') {
        alert("Description2 cannot be blank!");
        $('#udescription2').focus();
        return false;
    }

    if ($("#utitle3").val() == '') {
        alert("Title3 cannot be blank!");
        $('#utitle3').focus();
        return false;
    }


    if ($("#udescription3").val() == '') {
        alert("Description3 cannot be blank!");
        $('#udescription3').focus();
        return false;
    }

    var mytitle = new Array();
    mytitle[0] = $("#utitle1").val();
    mytitle[1] = $("#utitle2").val();
    mytitle[2] = $("#utitle3").val();

    var mydescription = new Array();
    mydescription[0] = $("#udescription1").val();
    mydescription[1] = $("#udescription2").val();
    mydescription[2] = $("#udescription3").val();


    $.ajax({
        type: "POST",
        url: host + "service.aspx/insertUpcomming",
        data: JSON.stringify({ tittle: mytitle, description: mydescription }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                alert(msg.d);
                getUpcomming();
                $("#utitle1").val("");
                $("#utitle2").val("");
                $("#utitle3").val("");

               
               $("#udescription1").val("");
               $("#udescription2").val("");
               $("#udescription3").val("");
                
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}

function getUpcomming() {


    $.ajax({
        type: "POST",
        url: host + "service.aspx/getUpcomming",
        data: JSON.stringify({}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

               
                 $("#utitle1").val(msg.d[0][0]);
                $("#utitle2").val(msg.d[0][1]);
                $("#utitle3").val(msg.d[0][2]);


                $("#udescription1").val(msg.d[1][0]);
                $("#udescription2").val(msg.d[1][1]);
                $("#udescription3").val(msg.d[1][2]);
                
//                $("#utitle1").attr('placeholder',msg.d[0][0]);
//                $("#utitle2").attr('placeholder',msg.d[0][1]);
//                $("#utitle3").attr('placeholder',msg.d[0][2]);


//                $("#udescription1").attr('placeholder', msg.d[1][0]);
//                $("#udescription2").attr('placeholder', msg.d[1][1]);
//                $("#udescription3").attr('placeholder', msg.d[1][2]);

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}


function addTagList() {

    if ($("#title1").val() == '') {
        alert("Tag Title cannot be blank!");
        $('#title1').focus();
        return false;
    }


    if ($("#url1").val() == 'http://') {
        alert("Tag Link Url cannot be blank!");
        $('#url1').focus();
        return false;
    }

 



    $.ajax({
        type: "POST",
        url: host + "service.aspx/addTagList",
         data: "{title:'" + $("#title1").val() + "',link:'" + $("#url1").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {
                $("#title1").val("");
                $("#url1").val("http://");
                
                var cur_val = $('#tags').val();alert(cur_val);
if(cur_val)
  $('#tags').val(cur_val + "," + msg.d);
else
  $('#tags').val(msg.d);
  
  var elem = document.getElementById ("tags_tagsinput");
            var message =  elem.innerHTML;
            alert(message);
            alert( message + "<span class='tag'><span>" + msg.d + "&nbsp;&nbsp;</span><a href='#' title='Removing tag'>x</a></span>");
            
             $('#tags_tagsinput').innerHTML = message + "<span class='tag'><span>" + msg.d + "&nbsp;&nbsp;</span><a href='#' title='Removing tag'>x</a></span>";
  
 
  
  

                
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}


function addTagListFinal() {

//    var elem = document.getElementById ("tags_tagsinput");
//            var message =  elem.innerHTML;
//            
   var message =  $("#tags_tagsinput").text(); 

    $.ajax({
        type: "POST",
        url: host + "service.aspx/addTagListFinal",
         data: "{taglist:'" + message + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {
               
                   alert("Tag list Saved successfully!.");
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}



//////////////////////////////////-----------End Up comming----------------/////////////////////////////////


//////////////////////////////////-----------Up Recent----------------/////////////////////////////////

function insertRecently() {





    if ($("#rtitle1").val() == '') {
        alert("title1 cannot be blank!");
        $('#rtitle1').focus();
        return false;
    }


    if ($("#rdescription1").val() == '') {
        alert("Description1 cannot be blank!");
        $('#rdescription1').focus();
        return false;
    }

    if ($("#rurl1").val() == 'http://') {
        alert("Link URL1 cannot be blank!");
        $('#rurl1').focus();
        return false;
    }

    if ($("#rtitle2").val() == '') {
        alert("Title2 cannot be blank!");
        $('#rtitle2').focus();
        return false;
    }

    if ($("#rdescription2").val() == '') {
        alert("Description2 cannot be blank!");
        $('#rdescription2').focus();
        return false;
    }

    if ($("#rurl2").val() == 'http://') {
        alert("Link URL2 cannot be blank!");
        $('#rurl2').focus();
        return false;
    }


   

    if ($("#rtitle3").val() == '') {
        alert("Title3 cannot be blank!");
        $('#rtitle3').focus();
        return false;
    }


    if ($("#rdescription3").val() == '') {
        alert("Description3 cannot be blank!");
        $('#rdescription3').focus();
        return false;
    }

    if ($("#rurl3").val() == 'http://') {
        alert("Link URL3 cannot be blank!");
        $('#rurl3').focus();
        return false;
    }


    

    var mytitle = new Array();
    mytitle[0] = $("#rtitle1").val();
    mytitle[1] = $("#rtitle2").val();
    mytitle[2] = $("#rtitle3").val();

    var mydescription = new Array();
    mydescription[0] = $("#rdescription1").val();
    mydescription[1] = $("#rdescription2").val();
    mydescription[2] = $("#rdescription3").val();

    var link = new Array();
    link[0] = $("#rurl1").val();
    link[1] = $("#rurl2").val();
    link[2] = $("#rurl3").val();


    $.ajax({
        type: "POST",
        url: host + "service.aspx/insertRecently",
        data: JSON.stringify({ tittle: mytitle, description: mydescription,link:link }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                alert(msg.d);
                 getRecently();
                $("#rtitle1").val("");
                $("#rtitle2").val("");
                $("#rtitle3").val("");


                $("#rdescription1").val("");
                $("#rdescription2").val("");
                $("#rdescription3").val("");


                $("#rurl1").val("");
                $("#rurl2").val("");
                $("#rurl3").val("");

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}

function getRecently() {


    $.ajax({
        type: "POST",
        url: host + "service.aspx/getRecently",
        data: JSON.stringify({}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

               
                 $("#rtitle1").val(msg.d[0][0]);
                $("#rtitle2").val(msg.d[0][1]);
                $("#rtitle3").val(msg.d[0][2]);


                $("#rdescription1").val(msg.d[1][0]);
                $("#rdescription2").val(msg.d[1][1]);
                $("#rdescription3").val(msg.d[1][2]);

                $("#rurl1").val(msg.d[2][0]);
                $("#rurl2").val(msg.d[2][1]);
                $("#rurl3").val(msg.d[2][2]);

               

//                $("#rtitle1").attr('placeholder', msg.d[0][0]);
//                $("#rtitle2").attr('placeholder', msg.d[0][1]);
//                $("#rtitle3").attr('placeholder', msg.d[0][2]);


//                $("#rdescription1").attr('placeholder', msg.d[1][0]);
//                $("#rdescription2").attr('placeholder', msg.d[1][1]);
//                $("#rdescription3").attr('placeholder', msg.d[1][2]);

//                $("#rurl1").attr('placeholder', msg.d[2][0]);
//                $("#rurl2").attr('placeholder', msg.d[2][1]);
//                $("#rurl3").attr('placeholder', msg.d[2][2]);

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}


//////////////////////////////////-----------End Recent----------------/////////////////////////////////

//////////////////////////////////-----------Tag list----------------/////////////////////////////////
function getTaglist() {


    $.ajax({
        type: "POST",
        url: host + "service.aspx/getTaglist",
        data: JSON.stringify({}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") { alert(msg.d);

               
                 $("#tags").val(msg.d);
               

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}

//////////////////////////////////-----------End Tag list----------------/////////////////////////////////

/////////////////////////////////------------Data listing-------------//////////////////////////////////


function deletedatalist(id) {

    $.ajax({
        type: "POST",
        url: host + "service.aspx/deletedatalist",
        data: "{id:'" + id + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

              window.location.reload();
//               
//                getDataListing();
// alert(msg.d);



            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}

 function deleteRegisterdUsers(cId)
 {
    $.ajax({
                type: "POST",
                url: host + "service.aspx/deleteRegisterdUsers",
                data: JSON.stringify({cId:cId}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function(msg) {
                    if (msg.d != "error") {

                        location.reload();


                    }
                    else {
                        alert("Something went wrong! Please try again.");
                        return;
                    }
                }
            });
 }


  function bulkUpdate(cId)
  {
    $.ajax({
                type: "POST",
                url: host + "service.aspx/bulkUpdate",
                data: JSON.stringify({cId:cId}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function(msg) {
                    if (msg.d != "error") {

                         window.location.href = host + "Admin/edit-bulk-data-listing.aspx";


                    }
                    else {
                        alert("Something went wrong! Please try again.");
                        return;
                    }
                }
            });
  }

 function variousFunction(cId,flag)
 {
    $.ajax({
                type: "POST",
                url: host + "service.aspx/variousFunction",
                data: JSON.stringify({cId:cId, flag:flag}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function(msg) {
                    if (msg.d != "error") {

                        location.reload();


                    }
                    else {
                        alert("Something went wrong! Please try again.");
                        return;
                    }
                }
            });
 }


function addbulkdatalist(cId,cname, cvalue) {

//            alert(cname);
//            alert(cvalue);
//            alert(sno);
//            alert(active);

            $.ajax({
                type: "POST",
                url: host + "service.aspx/addbulkdatalist",
                data: JSON.stringify({cId:cId, cname: cname, cvalue: cvalue}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function(msg) {
                    if (msg.d != "error") {

                        alert("Updated successfully!.");


                    }
                    else {
                        alert("Something went wrong! Please try again.");
                        return;
                    }
                }
            });

}


function adddatalist(headerId,cId,cname, cvalue, sno, active) {

//            alert(cname);
//            alert(cvalue);
//            alert(sno);
//            alert(active);

            $.ajax({
                type: "POST",
                url: host + "service.aspx/adddatalist",
                data: JSON.stringify({hid:headerId,cId:cId, cname: cname, cvalue: cvalue, sno: sno, active: active }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                async: true,
                cache: false,
                success: function(msg) {
                    if (msg.d != "error") {

                        location.reload();


                    }
                    else {
                        alert("Something went wrong! Please try again.");
                        return;
                    }
                }
            });

}



function getDataListing() {
   
    
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getDataListing",
        data: "{Title:'" + $("#ctl00_ContentPlaceHolder1_txttitle").val() + "',Author:'" + $("#ctl00_ContentPlaceHolder1_txtauthor").val() + "',ReferenceType:'" + $("#ctl00_ContentPlaceHolder1_ddlreference option:selected").val() + "',Language:'" + $("#ctl00_ContentPlaceHolder1_ddllanguage option:selected").val() + "',From:'" + $("#ctl00_ContentPlaceHolder1_txtfrom").val() + "',txtTo:'" + $("#ctl00_ContentPlaceHolder1_txtto").val() + "',User:'" + $("#ctl00_ContentPlaceHolder1_ddluser option:selected").val() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
        if (msg.d != "error") {

               
                $(ctl00_ContentPlaceHolder1_dvdatalist).show();
                $(ctl00_ContentPlaceHolder1_dvdatalist).html(msg.d);
                

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

}




/////////////////////////////////---------End data listing------------//////////////////////////////////

////////////////////////////////-----------footer--------------------/////////////////////////////////
function getUpcommingF() {

   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/getUpcommingF",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

//                $(#UpcommingF).show();
//                $(#UpcommingF).html(msg.d);
               
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}


function getRecentlyF() {


    $.ajax({
        type: "POST",
        url: host + "service.aspx/getRecentlyF",
        data: JSON.stringify({}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(recentlyF).show();
                $(recentlyF).html(msg.d[0]);
            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });



}

///////////////////////////////---------------End Fotter------------//////////////////////////////////

///////////////////////////////---------------bookmark--------------/////////////////////////////////
function userBookmark(id) {

    alert(id);
    $.ajax({
        type: "POST",
        url: host + "service.aspx/userBookmark",
        data: JSON.stringify({}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

               
                $(tr_userBookmark).show();
                $(tr_userBookmark).html(msg.d[0]);

                $(husername).show();
                $(husername).html(msg.d[1]);

                $("#userBookmark").show();
                

            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

    $(function() { $(userBookmark).load(pageload).show(); });
    
    $("#userBookmark").attr("class", "modal fade in");
   
   
   
}
//////////////////////////////----------------End bookmark----------/////////////////////////////////

function searchTableB(inputVal) {
   
    var table = $('#bookmarkdataTable');
    table.find('tr').each(function(index, row) {
        var allCells = $(row).find('td');
        if (allCells.length > 0) {
            var found = false;
            allCells.each(function(index, td) {
                var regExp = new RegExp(inputVal, 'i');
                if (regExp.test($(td).text())) {
                    found = true;
                    return false;
                }
            });
            if (found == true) $(row).show(); else $(row).hide();
        }
    });
}

function getBookMark(id) {
    
    $.ajax({
        type: "POST",
        url: host + "service.aspx/userBookmarkB",
        data: "{NoR:'" + $("#NORB option:selected").text() + "',Id:'"+id+"'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(tr_userBookmark).show();
                $(tr_userBookmark).html(msg.d[0]);

                $(husername).show();
                $(husername).html(msg.d[1]);

                $(bookmarkdataTable_paginateB).show();
                $(bookmarkdataTable_paginateB).html(msg.d[2]);

                $(bookmarkdataTable_infoB).show();
                $(bookmarkdataTable_infoB).html(msg.d[3]);
                

                $("#userBookmark").show();


            }
            else {
                alert("Something went wrong! Please try again.");
                return;
            }
        }
    });

    $(function() { $(userBookmark).load(pageload).show(); });

    $("#userBookmark").attr("class", "modal fade in");

}


function nextPageUserB() {
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/nextPageUserB",
        data: "{NoR:'" + $("#NORB option:selected").text() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(tr_userBookmark).show();
                $(tr_userBookmark).html(msg.d[0]);

                $(husername).show();
                $(husername).html(msg.d[1]);

                $(bookmarkdataTable_paginateB).show();
                $(bookmarkdataTable_paginateB).html(msg.d[2]);
                $(bookmarkdataTable_infoB).show();
                $(bookmarkdataTable_infoB).html(msg.d[3]);


                $("#userBookmark").show();

                return;
            }
            else {
                alert("Somwthing went wrong! Please try again.");
                return;
            }
        }
    });

    $(function() { $(userBookmark).load(pageload).show(); });

    $("#userBookmark").attr("class", "modal fade in");
}
function prePageUserB() {
   
    $.ajax({
        type: "POST",
        url: host + "service.aspx/prePageUserB",
        data: "{NoR:'" + $("#NORB option:selected").text() + "'}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: true,
        cache: false,
        success: function(msg) {
            if (msg.d != "error") {

                $(tr_userBookmark).show();
                $(tr_userBookmark).html(msg.d[0]);

                $(husername).show();
                $(husername).html(msg.d[1]);

                $(bookmarkdataTable_paginateB).show();
                $(bookmarkdataTable_paginateB).html(msg.d[2]);
                $(bookmarkdataTable_infoB).show();
                $(bookmarkdataTable_infoB).html(msg.d[3]);


                $("#userBookmark").show();

                return;
            }
            else {
                alert("Somwthing went wrong! Please try again.");
                return;
            }
        }
    });

    $(function() { $(userBookmark).load(pageload).show(); });

    $("#userBookmark").attr("class", "modal fade in");
}