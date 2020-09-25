$(document).ready(function(){
   
});

document.addEventListener('DOMContentLoaded', function () {
	window.onload = function () {
		setTimeout(function(){window.print();},500);
		window.onfocus = setTimeout(function(){history.back();},1000);
	  };
//window.print(); setTimeout(history.back(), 6000);
});
