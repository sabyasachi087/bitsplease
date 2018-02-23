var TEXT_CONVERTED = true;
$(document).ready(function() {
	var textData = function() {
		$.ajax({
			url : "./gesture",
			type : 'GET',
			cache : false,
			success : function(text) {
				if (text.length > 0) {
					console.log(text);
					$("input[name=speech-text]").val(text);
					TEXT_CONVERTED = false;
					playText();
				}
			}
		});
	}

	var invokeTextData = function() {
		if (TEXT_CONVERTED) {
			textData();
		}
	}
	setInterval(invokeTextData, 1000);

	/** Text to speech */

	var playText = function() {
		$('#btnPOst').click();
		setTimeout(function() {
			TEXT_CONVERTED = true;
		}, 1000);
	}

});
