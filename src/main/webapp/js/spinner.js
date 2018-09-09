// $Id: spinner.js,v 1.4 2016/05/10 16:10:34 a0199948 Exp $   //
//****************************************************************//
// Creates a <div> that covers the document and shows a spinner   //
// icon while the server is processing a request.                 //
//                                                                //
// To use:                                                        //
// - include jquery and this script in your <head> element        //
// - call spinner.show(xxx) where xxx is a delay in ms (optional) //
//                                                                //
// Paul Morken                                                    //
//****************************************************************//
var spinnerTimer = null;
var spinner = {
    init: function() {
        // Create the modal backdrop so all modals can use it
        $('<div id="blanket" class="ui-widget-overlay"/>').css({
             position: 'fixed',
             top: $(document).scrollTop(),
             left: 0,
             height: '100%',
             'min-height': '100%',
             width: '100%',
             zIndex: 5000
          }).appendTo(document.body).hide();

        // Create a screen level busy spinner
        $('<img id="spinner" src="images/spinner.gif" alt="Please wait..."/>').css({
             position: 'fixed',
             top: '48%',
             left: '48%',
             zIndex: 5001
          }).appendTo(document.body).hide();
    },
    show: function(delay) {
        delay = typeof(delay) !== 'undefined' ? delay : 100;
        if (spinnerTimer !== null) {
            clearTimeout(spinnerTimer);
        }
        spinnerTimer = setTimeout("$('#blanket,#spinner').show()", delay);
    },
    hide: function() {
        if (spinnerTimer !== null) {
            clearTimeout(spinnerTimer);
        }
        $('#blanket,#spinner').hide();
    }
};

/* Install when window is loaded */
$(window).load(function(){spinner.init();});

