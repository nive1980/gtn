var homevisible = 0;
	$("#sign-up-m").click(function(e){//This stops the page scrolling to the top on a # link.
	e.preventDefault();
	if (homevisible ===0) {//Search is currently hidden. Slide down and show it.
		$("#signup_div").slideDown(200);
		$("#signin_div").slideUp(200);
		$("#sign-in").fadeOut(100);
		$("#sign-up").fadeIn(100);
	} else {//Search is currently showing. Slide it back up and hide it.
		$("#signin_div").slideDown(100);
		homevisible = 0;
	}
});
var singinvisible = 0;
	$("#sign-in-m").click(function(e){//This stops the page scrolling to the top on a # link.
	e.preventDefault();
	if (singinvisible ===0) {//Search is currently hidden. Slide down and show it.
		$("#signin_div").slideDown(200);
		$("#signup_div").slideUp(200);

		$("#sign-up").fadeOut(100);
		$("#sign-in").fadeIn(100);
	} else {//Search is currently showing. Slide it back up and hide it.
		$("#signup_div").slideUp(200);
		singinvisible = 0;
	}
});
//- End page login


/*-----------------------------------------------------------------------------------*/
/*	ACCORDION TOGGLES
/*-----------------------------------------------------------------------------------*/
$(document).ready(function(){
	
	$("#accordion h4").eq(2).addClass("active");
	$("#accordion .accordion_content").eq(2).show();

	$("#accordion h4").click(function(){
		$(this).next(".accordion_content").slideToggle("slow")
		.siblings(".accordion_content:visible").slideUp("slow");
		$(this).toggleClass("active");
		$(this).siblings("h4").removeClass("active");
	});

});

/*-----------------------------------------------------------------------------------*/
/*	ACCORDION TOGGLES
/*-----------------------------------------------------------------------------------*/
$(document).ready(function(){
	
	$("#accordion_m h4").eq(2).addClass("active");
	$("#accordion_m .accordion_content").eq(2).show();

	$("#accordion_m h4").click(function(){
		$(this).next(".accordion_content").slideToggle("slow")
		.siblings(".accordion_content:visible").slideUp("slow");
		$(this).toggleClass("active");
		$(this).siblings("h4").removeClass("active");
	});

});



/*-----------------------------------------------------------------------------------*/
/*	Tovar Sizes
/*-----------------------------------------------------------------------------------*/
jQuery(document).ready(function() {
    $('ul.tabs').on('click', 'li:not(.current)', function() {
		$(this).addClass('current').siblings().removeClass('current')
		.parents('div.tovar_information').find('div.box').eq($(this).index()).fadeIn(150).siblings('div.box').hide();
	})
});
