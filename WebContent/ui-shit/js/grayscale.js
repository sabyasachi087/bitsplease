(function($) {
  "use strict"; // Start of use strict

  //Text-to-Speech
     $(function () {
         $("#btnPOst").click(function () {
             var username= "555b516f-47bd-4bec-b2ca-f926e84f7b09";  
             var password = "B6pwx0mSjRzE";
             var request = $("input[name=speech-text]").val();
             var url = "https://stream.watsonplatform.net/text-to-speech/api/v1/synthesize?voice=en-US_MichaelVoice&accept=audio/wav&text=" + request;

             $.ajax
               ({
                   type: "POST",
                   url: url,
                   dataType: "application/json; charset=utf-8",
                   headers: {
                       'Content-Type': 'application/json',
                       'Accept': 'audio/wav',
                       'Authorization': ("Basic " + btoa(username + ":" + password))
                   },
                  
                   success: function (content) {
                       alert('ok');
                   },

               });
           
             $("#speech").attr("src", url)[0].play();
           
         });
     });

  // Smooth scrolling using jQuery easing
  $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: (target.offset().top - 48)
        }, 1000, "easeInOutExpo");
        return false;
      }
    }
  });

  // Closes responsive menu when a scroll trigger link is clicked
  $('.js-scroll-trigger').click(function() {
    $('.navbar-collapse').collapse('hide');
  });

  // Activate scrollspy to add active class to navbar items on scroll
  $('body').scrollspy({
    target: '#mainNav',
    offset: 54
  });

  // Collapse Navbar
  var navbarCollapse = function() {
    if ($("#mainNav").offset().top > 100) {
      $("#mainNav").addClass("navbar-shrink");
    } else {
      $("#mainNav").removeClass("navbar-shrink");
    }
  };
  // Collapse now if page is not at top
  navbarCollapse();
  // Collapse the navbar when page is scrolled
  $(window).scroll(navbarCollapse);

})(jQuery); // End of use strict
