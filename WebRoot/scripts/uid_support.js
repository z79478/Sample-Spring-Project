
function getWSDL(id) {

    var uri = getWebServiceURI(id, true) + '?WSDL';

    $.ajax({
        'url': uri,
        'dataType': "text",
        'type': 'GET',
        'success': function (data) {
            WSDLSuccess(data);
        },
        'error': function (jqXHR, textStatus, errorThrown) {
            $('#divWSRawXMLResult').html(textStatus + ' :\n ' + errorThrown);
            openWSDLResults();
        }
    });

}

function WSDLSuccess(data) {

    window.open("data:text/xml,"+data, "wsdl", "");

    /*
    var xmlDoc = $.parseXML(data);
    $xml = $(xmlDoc);
    $OpNode = $xml.find('portType');

    var xml_pp = vkbeautify.xml(data);
    $('#divWSRawXMLResult').html('<xmp class="prettyprint lang-xml">' + xml_pp + '</xmp>');

    //$OpNode.find("operation")[0].attributes.item("name")
    var wsTitle = 'WebService: ' + $OpNode.attr("name") + ' - Operations(' + $OpNode.children().length + '): ';
    $OpNode.find("operation").each(
        function () {
            wsTitle += $(this).attr("name") + ', ';
        }
    );

    $('#divWSRawHeaderResult').html(wsTitle); // 
            
    openWSDLResults();
    prettyPrint();
    */
}

function openWSDLResults() {
    $('#divWelcomeMessage').hide();
    $('#divWSRawResultBox').removeClass('hidden nodisplay');
    $('#divWSRawResultBox').addClass('wsdlVisible');
}

function closeResults() {
    $('#divWSRawResultBox').removeClass('wsdlVisible');
    $('#divWSRawResultBox').addClass('hidden nodisplay');
    //$('#divResponseStatusResult').html('');
}

function openWebServiceSupport(openURL) {
    var frame = document.getElementById("ifr")
    frame.src = openURL;

    loadPopupBox();

    $('#divWebServiceSupportPopupClose').click(function () {
        unloadPopupBox();
    });
}

function unloadPopupBox() {
    activeUploadColId = "";
    $('#divWebServiceSupportPopup').fadeOut("slow");
    $('#popup_mask').fadeOut("fast");
}

function loadPopupBox() {
    loadPopupMask();
    $('#divWebServiceSupportPopup').fadeIn("slow");
}

function loadPopupMask() {
    $('#popup_mask').fadeIn(100);
    $('#popup_mask').fadeTo("slow", 0.5);
}

function getWebServiceInformation(id) {
    var uri = 'FormattedSupport/' + getWebServiceName(id) + '_information.html';

    $.ajax({
        'url': uri,
        'dataType': "text",
        'type': 'GET',
        'success': function (data) {
            showWebServiceInformation(data);
        },
        'error': function (jqXHR, textStatus, errorThrown) {
            var data = textStatus + ' :\n ' + errorThrown;
            showWebServiceInformation(data);
        }
    });
}

function showWebServiceInformation(data) {
    $('#divWelcomeMessage').hide();
    $('#divWebServiceResult').html(data);
}

/* ----------------- generic ------------------- */

function getCookieValue(key) {
    var cookieValues = document.cookie.split(";");
    for (var i = 0; i < cookieValues.length; i++) {
        var pair = cookieValues[i].split("=");
        if (key == trimString(pair[0])) {
            return trimString(pair[1]);
        }
    }
}

function setCookieValue(key, value) {
    document.cookie = key + "=" + value;
}

function trimString(input) {
    while (input.charAt(0) == ' ') {
        input = input.substring(1, input.length);
    }

    while (input.charAt(input.length - 1) == ' ') {
        input = keyinputsubstring(0, key.length - 1);
    }

    return input;
}