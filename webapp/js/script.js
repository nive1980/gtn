
var ww = document.body.clientWidth;

/*Read more*/
$(window).load(function(){
$(document).ready(function(){
   $(".colo_blue").click(function () {
   $('.detail_h').slideToggle("slow");
   });
});
});/*--/End Read more*/

$(document).ready(function() {
	$(".nav li a").each(function() {
		if ($(this).next().length > 0) {
			$(this).addClass("parent");
		};
	})
	
	$(".toggleMenu").click(function(e) {
		e.preventDefault();
		$(this).toggleClass("active");
		alert("hi");
		//$(".nav").toggle();
		$('.nav').slideToggle("slow");
		$('.nav2').slideUp(200);
		
	});
	adjustMenu();
	
	$(".toggleMenu2").click(function(e) {
		e.preventDefault();
		$(this).toggleClass("active");
		//$(".nav2").toggle();
		$('.nav2').slideToggle("slow");
		//$('.nav').slideUp(200);
	});
	adjustMenu2();
})


$(window).bind('resize orientationchange', function() {
	ww = document.body.clientWidth;
	adjustMenu();
});

$(window).bind('resize orientationchange', function() {
	ww = document.body.clientWidth;
	adjustMenu2();
});




	
var adjustMenu = function() {
	if (ww < 768) {
		$(".toggleMenu").css("display", "inline-block");
		if (!$(".toggleMenu").hasClass("active")) {
			//$(".nav").slideUp(200);
			
			
		} else {
			$(".nav").slideDown(200);
		}
		$(".nav li").unbind('mouseenter mouseleave');
		$(".nav li a.parent").unbind('click').bind('click', function(e) {
			// must be attached to anchor element to prevent bubbling
			e.preventDefault();
			$(this).parent("li").toggleClass("hover");
		});
	} 
	else if (ww >= 768) {
		$(".toggleMenu").css("display", "none");
		$(".nav").show();
		$(".nav li").removeClass("hover");
		$(".nav li a").unbind('click');
		$(".nav li").unbind('mouseenter mouseleave').bind('mouseenter mouseleave', function() {
		 	// must be attached to li so that mouseleave is not triggered when hover over submenu
		 	$(this).toggleClass('hover');
		});
	}
}

var adjustMenu2 = function() {
	if (ww < 768) {
		$(".toggleMenu2").css("display", "inline-block");
		
		if (!$(".toggleMenu2").hasClass("active")) {
			
			$(".nav2").hide();
			
		} else {
			$(".nav2").hide();
		}
		$(".nav2 li").unbind('mouseenter mouseleave');
		$(".nav2 li a.parent2").unbind('click').bind('click', function(e) {
			//alert("hi");
			//$(".nav2 li").hide();
			//$('.nav2 li.hover').slideUp(200);
			$(".nav2 li").removeClass("hover");			
			// must be attached to anchor element to prevent bubbling
			e.preventDefault();
			$(this).parent("li").toggleClass("hover");
		});
	} 
	else if (ww >= 768) {
		$(".toggleMenu2").css("display", "none");
		$(".nav2").show();
		
		$(".nav2 li").removeClass("hover");
		$(".nav2 li a").unbind('click');
		$(".nav2 li").unbind('mouseenter mouseleave').bind('mouseenter mouseleave', function() {
		 	// must be attached to li so that mouseleave is not triggered when hover over submenu
		 	$(this).toggleClass('hover');
		});
	}
}

//Search box top area onclick expand & hide
	$(document).ready(function(){
	var submitIcon = $('.searchbox-icon');
	var inputBox = $('.searchbox-input');
	var searchBox = $('.searchbox');
	var isOpen = false;
	submitIcon.click(function(){
		if(isOpen == false){
			searchBox.addClass('searchbox-open');
			inputBox.focus();
			isOpen = true;
		} else {
			searchBox.removeClass('searchbox-open');
			inputBox.focusout();
			isOpen = false;
		}
	});  
	 submitIcon.mouseup(function(){
			return false;
		});
	searchBox.mouseup(function(){
			return false;
		});
	$(document).mouseup(function(){
			if(isOpen == false){//true to auto hide
				$('.searchbox-icon').css('display','block');
				//submitIcon.click();
			}
		});
});
	function buttonUp(){
		var inputVal = $('.searchbox-input').val();
		inputVal = $.trim(inputVal).length;
		if( inputVal !== 0){
			$('.searchbox-icon').css('display','none');
		} else {
			$('.searchbox-input').val('');
			$('.searchbox-icon').css('display','block');
		}
	}


