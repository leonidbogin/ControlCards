$(document).ready(function(){
	if ($('#error-text').html() != ''){
		$("#error-box").slideDown('fast'); 
		setTimeout(function() { $("#error-box").slideUp('slow');   }, 2500);
	}
	//$('#error-box').removeClass('hide');
});