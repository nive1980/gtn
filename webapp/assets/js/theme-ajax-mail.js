

// Content login Form
// ---------------------------------------------------------------------------------------
$(function () {
    $("#af-login .form-control").tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    $('#af-login .form-control').blur(function () {
        $(this).tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    });

    $("#af-login #submit_btn").click(function () {
        // validate and process form
        // first hide any error messages
        $('#af-login .error').hide();        
		
        var email = $("#af-login input#email").val();
        //var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
        var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
        //console.log(filter.test(email));
        if (!filter.test(email)) {
            $("#af-login input#email").tooltip({placement: 'bottom', trigger: 'manual'}).tooltip('show');
            $("#af-login input#email").focus();
            return false;
        }
		
		var password = $("#af-login #passwordfield").val();
        if (password == "" || password == "" || password == "Password" || password == "Password *" || password == "Type Your Password...") {
            $("#af-login #passwordfield").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-login #passwordfield").focus();
            return false;
        }
		

        /*var message = $("#af-login #input-message").val();
        if (message == "" || message == "Message...." || message == "Message" || message == "Message *" || message == "Type Your Message...") {
            $("#af-login #input-message").tooltip({placement: 'bottom', trigger: 'manual'}).tooltip('show');
            $("#af-login #input-message").focus();
            return false;
        }*/

        var dataString = '&email=' + email + '&password=' + password;
        //alert (dataString);return false;

        $.ajax({
            type:"POST",
            url:"assets/php/login-form.php",
            data:dataString,
            success:function () {
                $('#af-login').prepend("<div class=\"alert alert-success fade in\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">&times;</button><strong>Login Form Submitted!</strong> </div>");
                $('#af-login')[0].reset();
            }
        });
        return false;
    });
});
//End login

// Content signup Form
// ---------------------------------------------------------------------------------------
$(function () {
    $("#af-signup .form-control").tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    $('#af-signup .form-control').blur(function () {
        $(this).tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    });

    $("#af-signup #submit_btn").click(function () {
        // validate and process form
        // first hide any error messages
        $('#af-signup .error').hide();

        var name = $("#af-signup input#name").val();
        if (name == "" || name == "Name...." || name == "Name" || name == "Name *" || name == "Type Your Name...") {
            $("#af-signup input#name").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-signup input#name").focus();
            return false;
        }
		
		var name2 = $("#af-signup input#name2").val();
        if (name2 == "" || name2 == "Name...." || name2 == "Name" || name2 == "Name *" || name2 == "Type Your Last Name...") {
            $("#af-signup input#name2").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-signup input#name2").focus();
            return false;
        }
		
        var email = $("#af-signup input#email").val();
        //var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
        var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
        //console.log(filter.test(email));
        if (!filter.test(email)) {
            $("#af-signup input#email").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-signup input#email").focus();
            return false;
        }
		
		var password = $("#af-signup #passwordfield2").val();
        if (password == "" || password == "" || password == "Password" || password == "Password *" || password == "Type Your Password...") {
            $("#af-signup #passwordfield2").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-signup #passwordfield2").focus();
            return false;
        }

		
        var message = $("#af-signup input#phone").val();
        if (message == "" || message == "Message...." || message == "Phone" || message == "Phone *" || message == "Type Your Phone...") {
            $("#af-signup input#phone").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-signup input#phone").focus();
            return false;
        }

        var dataString = 'name=' + name + '&email=' + email + '&message=' + message;
        //alert (dataString);return false;

        $.ajax({
            type:"POST",
            url:"assets/php/contact-form.php",
            data:dataString,
            success:function () {
                $('#af-signup').prepend("<div class=\"alert alert-success fade in\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">&times;</button><strong>Contact Form Submitted!</strong> We will be in touch soon.</div>");
                $('#af-signup')[0].reset();
            }
        });
        return false;
    });
});
//End signup

// Entity Form
// ---------------------------------------------------------------------------------------
$(function () {
    var $form = $('#entity-form');
    $form.find('.form-control').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    $form.find('.form-control').on('blur', function(){
        $(this).tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    });

    // validate and process form
    $form.find('#entity_btn').on('click', function () {
		//alert('hh');		
		// Price list
        var entity = $form.find('.input-entity').val();
        if (entity == '' || entity == 'Select Your Entity') {
            $form.find('.input-entity').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-entity').focus();
            return false;
        }
        else {
            $form.find('.input-entity').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }
        // Id
        //var id_en = $("#af-entity input#id_en").val();
		var id_en = $form.find('input#id_en').val();
        if (id_en == "" || id_en == "id" || id_en == "id" || id_en == "id *" || id_en == "Type Your id...") {
            $form.find("input#id_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#id_en").focus();
            return false;
        }
		//Name en
		var name = $form.find("input#name_en").val();
        if (name == "" || name == "Name...." || name == "Name" || name == "Name *" || name == "Type Your Name...") {
            $form.find("input#name_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#name_en").focus();
            return false;
        }
		//Address en
		var addres_en = $form.find("input#addres_en").val();
        if (addres_en == "" || addres_en == "Address" || addres_en == "Address" || addres_en == "Address *" || addres_en == "Address Line 1") {
            $form.find("input#addres_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#addres_en").focus();
            return false;
        }
		
		//Type en
		var type = $form.find("input#org_en").val();
        if (type == "" || type == "Type" || type == "Type" || type == "Type *" || type == "Type") {
            $form.find("input#org_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#org_en").focus();
            return false;
        }
		
		// City  list
        var city = $form.find('.input-city').val();
        if (city == '' || city == 'Select Your City') {
            $form.find('.input-city').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-city').focus();
            return false;
        }
        else {
            $form.find('.input-city').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }

		//state en
		var state = $form.find("input#state_en").val();
        if (state == "" || state == "state" || state == "state" || state == "state *" || state == "state") {
            $form.find("input#state_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#state_en").focus();
            return false;
        }

		//country en
		var country = $form.find("input#country_en").val();
        if (country == "" || country == "country" || country == "country" || country == "country *" || country == "country") {
            $form.find("input#country_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#country_en").focus();
            return false;
        }		

		//Zip en
		var zip = $form.find("input#zip_en").val();
        if (zip == "" || zip == "zip" || zip == "zip" || zip == "zip *" || zip == "zip") {
            $form.find("input#zip_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#zip_en").focus();
            return false;
        }
						
        // Email address
        var email = $form.find('.input-email').val();
        //var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
        var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
        //console.log(filter.test(email));
        if (!filter.test(email)) {
            $form.find('.input-email').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-email').focus();
            return false;
        }

        // Phone number
        var phone = $form.find('.input-phone').val();
        if (phone == 'Your Phone Number') {
            $form.find('.input-phone').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-phone').focus();
            return false;
        }        

       // var dataString = 'name=' + name + '&email=' + email + '&phone=' + phone + '&price=' + price;
	   var dataString = 'Entity Type=' + entity + '&ID=' + id_en + '&Name=' + name + '&Address=' + addres_en; + '&Type=' + type; + '&City=' + city; + '&State=' + state; + '&Country=' + country; + '&Zip=' + zip;
        //alert(dataString); return false;

        $.ajax({
            type: 'POST',
            url: 'assets/php/entity-form.php',
            data: dataString,
            success: function () {
                $form.find('.form-alert').append('' +
                '<div class=\"alert alert-success registration-form-alert fade in\">' +
                '<button class=\"close\" data-dismiss=\"alert\" type=\"button\">&times;</button>' +
                '<strong>Entity Form Submitted!</strong> We will be in touch soon.' +
                '</div>' +
                '');
                $form[0].reset();
                $form.find('.form-control').focus().blur();
            }
        });
        return false;
    });

});


// License Form
// ---------------------------------------------------------------------------------------
$(function () {
    var $form = $('#license-form');
    $form.find('.form-control').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    $form.find('.form-control').on('blur', function(){
        $(this).tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    });

    // validate and process form
    $form.find('#license_btn').on('click', function () {
		//alert('hh');		
		// exp lic
        var exp_li = $form.find('#exp_li').val();
        if (exp_li == '' || exp_li == 'Select Your Type') {
            $form.find('#exp_li').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('#exp_li').focus();
            return false;
        }
        else {
            $form.find('#exp_li').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }
        // reference lic
        //var id_en = $("#af-entity input#id_en").val();
		var reference = $form.find('input#lic_ref').val();
        if (reference == "" || reference == "reference" || reference == "reference" || reference == "reference *" || reference == "Type Your reference...") {
            $form.find("input#lic_ref").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#lic_ref").focus();
            return false;
        }
		//Country of Export
        var exp_count = $form.find('.input-export').val();
        if (exp_count == '' || exp_count == 'Select Your Country') {
            $form.find('.input-export').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-export').focus();
            return false;
        }
        else {
            $form.find('.input-export').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }

		//Port lic
		var port = $form.find("input#port_lic").val();
        if (port == "" || port == "port...." || port == "port" || port == "port *" || port == "Type Your port...") {
            $form.find("input#port_lic").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#port_lic").focus();
            return false;
        }
		//Description lic
		var descrip = $form.find(".input-description").val();
        if (descrip == "" || descrip == "description" || descrip == "description" || descrip == "description *" || descrip == "description") {
            $form.find(".input-description").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find(".input-description").focus();
            return false;
        }
		
		//SUB lic
		var sbu_lic = $form.find("input#sbu_lic").val();
        if (sbu_lic == "" || sbu_lic == "sub" || sbu_lic == "sub" || sbu_lic == "sub *" || sbu_lic == "sub") {
            $form.find("input#sbu_lic").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#sbu_lic").focus();
            return false;
        }
		
		// import  list
        var count_import = $form.find('.input-import').val();
        if (count_import == '' || count_import == 'Select Your City') {
            $form.find('.input-import').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-import').focus();
            return false;
        }
        else {
            $form.find('.input-import').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }

		//ECN en
		var ecn = $form.find("input#ecn_lic").val();
        if (ecn == "" || ecn == "ecn" || ecn == "ecn" || ecn == "ecn *" || ecn == "ecn") {
            $form.find("input#ecn_lic").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find("input#ecn_lic").focus();
            return false;
        }

        var dataString = 'Type=' + exp_li + '&Reference=' + reference + '&Country of Export=' + exp_count + '&port No.=' + port; + '&Description=' + descrip; + '&SBU=' + sbu_lic; + '&Country of Import=' + count_import; + '&ECN=' + ecn;
        //alert(dataString); return false;

        $.ajax({
            type: 'POST',
            url: 'assets/php/license-form.php',
            data: dataString,
            success: function () {
                $form.find('.form-alert').append('' +
                '<div class=\"alert alert-success registration-form-alert fade in\">' +
                '<button class=\"close\" data-dismiss=\"alert\" type=\"button\">&times;</button>' +
                '<strong>Entity Form Submitted!</strong> We will be in touch soon.' +
                '</div>' +
                '');
                $form[0].reset();
                $form.find('.form-control').focus().blur();
            }
        });
        return false;
    });

});
/*
// Content entity Form
// ---------------------------------------------------------------------------------------
$(function () {
    $("#af-entity .form-control").tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    $('#af-entity .form-control').blur(function () {
        $(this).tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
    });

    $("#af-entity #entity_btn").click(function () {
        // validate and process form
        // first hide any error messages
        $('#af-entity .error').hide();

        // entity list
        var price = $form.find('.input-entity').val();
        if (price == '' || price == 'Select Your Entity') {
            $form.find('.input-entity').tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $form.find('.input-entity').focus();
            return false;
        }
        else {
            $form.find('.input-entity').tooltip({placement: 'top', trigger: 'manual'}).tooltip('hide');
        }
		
		var id_en = $("#af-entity input#id_en").val();
        if (id_en == "" || id_en == "id" || id_en == "id" || id_en == "id *" || id_en == "Type Your id...") {
            $("#af-entity input#id_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity input#id_en").focus();
            return false;
        }
		
		var name = $("#af-entity input#name_en").val();
        if (name == "" || name == "Name...." || name == "Name" || name == "Name *" || name == "Type Your Name...") {
            $("#af-entity input#name_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity input#name_en").focus();
            return false;
        }
		var addres_en = $("#af-entity input#addres_en").val();
        if (addres_en == "" || addres_en == "Address" || addres_en == "Address" || addres_en == "Address *" || addres_en == "Address Line 1") {
            $("#af-entity input#addres_en").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity input#addres_en").focus();
            return false;
        }
		
		
        var email = $("#af-entity input#email").val();
        //var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
        var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
        //console.log(filter.test(email));
        if (!filter.test(email)) {
            $("#af-entity input#email").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity input#email").focus();
            return false;
        }
		
		var password = $("#af-entity #passwordfield2").val();
        if (password == "" || password == "" || password == "Password" || password == "Password *" || password == "Type Your Password...") {
            $("#af-entity #passwordfield2").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity #passwordfield2").focus();
            return false;
        }

		
        var message = $("#af-entity input#phone").val();
        if (message == "" || message == "Message...." || message == "Phone" || message == "Phone *" || message == "Type Your Phone...") {
            $("#af-entity input#phone").tooltip({placement: 'top', trigger: 'manual'}).tooltip('show');
            $("#af-entity input#phone").focus();
            return false;
        }

        var dataString = 'name=' + name + '&email=' + email + '&message=' + message;
        //alert (dataString);return false;

        $.ajax({
            type:"POST",
            url:"assets/php/contact-form.php",
            data:dataString,
            success:function () {
                $('#af-entity').prepend("<div class=\"alert alert-success fade in\"><button class=\"close\" data-dismiss=\"alert\" type=\"button\">&times;</button><strong>Contact Form Submitted!</strong> We will be in touch soon.</div>");
                $('#af-entity')[0].reset();
            }
        });
        return false;
    });
});*/
//End entity

