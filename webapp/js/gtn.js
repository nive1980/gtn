document.addEventListener('invalid', (function(){
      return function(e){
          //prevent the browser from showing default error bubble/ hint
          e.preventDefault();
          // optionally fire off some custom validation handler
          // myvalidationfunction();
      };
  })(), true);

$(document).on('keyup', '.invalid-ele', function(){
	if($(this).val() != ''){
		$(this).removeClass('invalid-ele');
	}
});

$(document).ready(function(){
	

});


function usrmgtLhs(className){
	$('.usermgt-lhs-ul li').removeClass('active');
	$('.usermgt-lhs-ul li.'+className).addClass('active');
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}