


var webservices = [
    "BatchInfo",
    "IDAssignmentV2",
    "NearMatchListV2",
    "NearMatchResolutionV2",
    "StudentSearch"
];

function getUIDResults(id, op) {
    var wsData = "";
    switch ($("input[name=rbRequestSource]:checked").val())
    {
        case "1":
            setRequestData_DataIsland(id, op);
            showOperationExecuteScreen(id, op);
            break;
        case "2":
            getUserEnteredOperationData(id, op);
            break;
        case "3":
            showOperationExecuteScreen(id, op);
            setRequestData_WebService(id, op, '080808'); // fake student id
            break;
        case "4":
            showAbstractionExecuteScreen(id, op);
            break;
        case "5":
            showProxyExecuteScreen(id, op);
            break;
        default:
            break;
    };
}

function CallUIDWebService(soapMessage, id, op) {
    var webServiceURI = getWebServiceURI(id, true);
    var soapAction = webServiceURI + '/' + op;

    if (($("input[name=cbUseProxy]:checked")).size() > 0) {

        webServiceURI = getWebServiceProxyURI(id, op);
        soapAction = 'http://tccwebserviceproxy.net/' + getWebServiceName(id) + '_' + op;

    }

    $.ajax({
        url: webServiceURI,
        type: "POST",
        dataType: "text",
        headers: { SOAPAction: soapAction },
        data: soapMessage,
        processData: false,
        contentType: "text/xml; charset=\"utf-8\"",
        success: function (data, status) {
            OnWSSuccess(data, status, webServiceURI, soapMessage, id, op);
        },
        error: OnWSError
    });

    return false;
}

function CallUIDWebServiceCustom(id, op) {
    var uid = $('#hdnTEALUID').val();
    var pw = $('#hdnTEALPW').val();

    var valList = [];
    $("input[name*='tsdsuid_']").each(
        function () {
            valList.push($(this).val());
        }
     );

    var wsData = generateWebServiceRequestData(id, op, uid, pw, valList);
    CallUIDWebService(wsData, id, op);
}

function CallUIDWebServiceAbstract(id, op) {
    var webServiceURI = 'WS/TSDSUIDPassthrough.asmx';
    var soapAction = '';
    var soapMessageHead = '<SOAP-ENV:Envelope xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"> <SOAP-ENV:Body> ';
    var soapMessageBody = '<' + getWebServiceName(id) + '_' + op + '></' + getWebServiceName(id) + '_' + op + '>';
    var soapMessageFoot = '</SOAP-ENV:Body></SOAP-ENV:Envelope>';
    var soapMessage = soapMessageHead + soapMessageBody + soapMessageFoot;

    soapAction = 'http://txeis.net/' + getWebServiceName(id) + '_' + op;

    $.ajax({
        url: webServiceURI,
        type: "POST",
        dataType: "text",
        headers: { SOAPAction: soapAction },
        data: soapMessage,
        processData: false,
        contentType: "text/xml; charset=\"utf-8\"",
        success: function (data, status) {
            OnWSSuccess(data, status, webServiceURI, soapMessage, id, op);
        },
        error: OnWSError
    });

    return false;
}

/*
function CallUIDWebServiceAbstract(id, op) {
    //var webServiceURI = 'WS/TSDSUIDPassthrough.asmx?op=' + getWebServiceName(id) + '_' + op;
    var webServiceURI = 'WS/TSDSUIDPassthrough.asmx/' + getWebServiceName(id) + '_' + op;

    $.ajax({
        url: webServiceURI,
        type: "POST",
        dataType: "text",
        processData: false,
        contentType: "text/xml; charset=\"utf-8\"",
        success: function (data, status) {
            OnWSSuccess(data, status, webServiceURI, '', id, op);
        },
        error: OnWSError
    });

    return false;
}
*/

/*
function CallUIDWebServiceProxy(soapMessage, id, op) {
    //var webServiceURI = 'http://txSuiteRD2/TSDSUID/WS/TSDSUID.asmx/' + getWebServiceName(id) + '_' + op;
    var webServiceURI = 'http://localhost:2704/WS/TSDSUID.asmx/' + getWebServiceName(id) + '_' + op;

    $.ajax({
        url: webServiceURI,
        type: "POST",
        dataType: "text",
        data: 'envelope=' + soapMessage,
        processData: false,
        success: function (data, status) {
            OnWSSuccess(data, status, webServiceURI, '', id, op);
        },
        error: OnWSError
    });

    //data: 'envelope=' + soapMessage.replace(/\'/g, '"'),
    //contentType: "application/json; charset=utf-8",
    //data: "{ 'envelope': '" + soapMessage.replace(/\'/g, '"') + "' }",

    return false;
}
*/

function getUserEnteredOperationData(id, op) {
    var uri = 'WSOPDataEntry/' + getWebServiceName(id) + '_' + op + '_de.html';

    $.ajax({
        'url': uri,
        'dataType': "text",
        'type': 'GET',
        'success': function (data) {
            showOperationExecuteScreenForFormEntry(data, id, op);
        },
        'error': function (jqXHR, textStatus, errorThrown) {
            var data = textStatus + ' :\n ' + errorThrown;
            showWebServiceInformation(data);
        }
    });
}

function OnWSSuccess(data, status, webServiceURI, requestData, id, op) {
    var title = "<div class='bold'>Response from: " + webServiceURI + "</div>";

    var uidp = new TSDSUIDWSResultProcessor();
    var parsed = uidp.parseResponse(id, op, data);

    // these, below, are all support functions for testing and should be changed or rmeoved in production
    $('#divResponseStatusResult').html(title + '<br/><div class="">&bull; Response status: ' +
            status + ' <span onclick="toggleRawRequestDisplay">(show raw request)<span></div>' + 
            '<div><xmp id="divRawXMPRequest" style="display:none;" class="prettyprint lang-xml">' + requestData + '</xmp></div>');
    //style="display:none;"
    $('#divWebServiceResult').html(parsed);

    prettyPrint();
    $('#divWelcomeMessage').hide();
}

function OnWSError(request, status, error) {
    // these, below, are all support functions for testing and should be changed or rmeoved in production
    alert(status + ' : ' + error);
    $('#divWelcomeMessage').hide();
}


function toggleRawRequestDisplay() {

}

function showOperationExecuteScreen(id, op) {
    $('#divWelcomeMessage').hide();
    closeResults();

    var post = "";

    $('#divResponseStatusResult').html("<span class='bold'>" + getWebServiceName(id) + "." + op + "</span>");

    post += "<br/><br/>";
    post += "<input type='button' value='Call " + getWebServiceName(id) + "." + op + "' onclick='CallUIDWebService($(\"#hdnRequestDataHold\").val(), " + id + ", \"" + op + "\")' />";

    $('#divWebServiceResult').html(post);
}

function showOperationExecuteScreenForFormEntry(data, id, op) {
    $('#divWelcomeMessage').hide();
    closeResults();

    var post = "";

    $('#divResponseStatusResult').html("<span class='bold'>" + getWebServiceName(id) + "." + op + "</span>");

    post += "<br/>";
    post += "<input type='button' value='Call " + getWebServiceName(id) + "." + op + "' onclick='CallUIDWebServiceCustom(" + id + ", \"" + op + "\")' />";

    $('#divWebServiceResult').html(data + post);
}

function showAbstractionExecuteScreen(id, op) {
    $('#divWelcomeMessage').hide();
    closeResults();

    var post = "";

    $('#divResponseStatusResult').html("<span class='bold'>" + getWebServiceName(id) + "." + op + "</span>");

    post += "<br/>";
    post += "<p>I will call the host webserver so that it may make my WebService request to TEA</p>";
    post += "<br/>";
    post += "<input type='button' value='Call " + getWebServiceName(id) + "." + op + "' onclick='CallUIDWebServiceAbstract(" + id + ", \"" + op + "\")' />";

    $('#divWebServiceResult').html(post);
}

/**************************************************************/

function getWebServiceName(id) {
    return webservices[id - 1];
}

function getWebServiceURI(id, isTraining) {
    var uri = '';
    var subPath = (isTraining ? 'uidtraining' : 'uid');

    uri = 'https://tea4avdplbprod.tea.state.tx.us/' + subPath + '/services/' + getWebServiceName(id);

    return uri;
}

function getWebServiceProxyURI(id, op) {
    var uri = '';

    uri = 'http://txsuiterd2/TSDSUIDProxy/WS/TSDSUID.asmx';

    return uri;
}

/**************************************************************/

function setRequestData_DataIsland(id, op) {
    var request = '';
    var element = 'hdn' + getWebServiceName(id) + '_' + op + '_Data';

    request = $('#' + element).val();
    $('#hdnRequestDataHold').val(request); 
}

function setRequestData_WebService(id, op, studentid) {
    var uri = 'WS/TSDSUIDPassthrough.asmx/GetRequestEnvelope';
    var operation = getWebServiceName(id) + '_' + op;
    var xmlData = '';

    $.ajax({
        url: uri,
        type: "POST",
        dataType: "json",
        data: "{ 'operation': '" + operation + "', 'studentid': '" + studentid + "' }",
        processData: false,
        contentType: "application/json; charset=utf-8",
        success: function (data, status) {
            $('#hdnRequestDataHold').val(data.d);
        },
        error: function (result) {
            $('#hdnRequestDataHold').val('');
            alert('ERROR:  There was a system problem while trying to access the local webservice.' + result.d);
        }
    });
}

/*****************************************************************/
/* 
    WebService Request Data 
*/
/*****************************************************************/

// using arguments array: [0] = webserviceId, [1] = operation, [n] = required values for operation
function generateWebServiceRequestData() {
    var header = "";
    header += "<soapenv:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' ";
    header += "   xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' ";

    if (arguments[0] != '5')
        header += "   xmlns:q0='http://ws.escholar.com' >";
    else
        header += "   xmlns:wsv2='http://wsv2.escholar.com' >";

    header += "  <soapenv:Body>";

    var footer = "";
    footer += "</soapenv:Body>";
    footer += "</soapenv:Envelope>";

    var body = getSOAPBody(arguments);

    return header + body + footer;
}

// using arguments array: see calling function
function getSOAPBody(args) {
    var ws = getWebServiceName(args[0]);
    var wsop = args[1];
    var functionName = "get" + ws + "_" + wsop + "_SOAPBody(args)";
    var body = eval(functionName);

    return body;
}

// using arguments array: see calling function
function getBatchInfo_getBatchInformation_SOAPBody(args) {
    var body = "";

    body += "<ns0:getBatchInformation xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    body += "    <batchNumber xsi:type='xsd:long'>" + args[4][0] + "</batchNumber>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:getBatchInformation>";

    return body;
}

// using arguments array: see calling function
function getStudentSearch_searchStudent_SOAPBody(args) {
    var body = "";

    body += "<wsv2:searchStudent  xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    //body += "    <student xsi:type='q0:Student'>";
    body += "    <student xsi:type='tns1:StudentV2'>";
    body += "        <SSN xsi:type='xsd:string'>" + args[4][0] + "</SSN>";
    body += "        <birthDate xsi:type='xsd:string'>" + args[4][1] + "</birthDate>";
    body += "        <districtCode xsi:type='xsd:string'>" + args[4][2] + "</districtCode>";
    body += "        <gender xsi:type='xsd:string'>" + args[4][3] + "</gender>";
    body += "        <gradeLevel xsi:type='xsd:string'>" + args[4][4] + "</gradeLevel>";
    body += "        <localStudentId xsi:type='xsd:string'>" + args[4][5] + "</localStudentId>";
    body += "        <raceEthnicCode xsi:type='xsd:string'>" + args[4][6] + "</raceEthnicCode>";
    body += "        <schoolCode xsi:type='xsd:string'>" + args[4][7] + "</schoolCode>";
    body += "        <schoolYear xsi:type='xsd:string'>" + args[4][8] + "</schoolYear>";
    body += "        <studentFirstName xsi:type='xsd:string'>" + args[4][9] + "</studentFirstName>";
    body += "        <studentLastName xsi:type='xsd:string'>" + args[4][10] + "</studentLastName>";
    body += "        <sourceSISCode xsi:type='xsd:string'>" + args[4][11] + "</sourceSISCode>";
    body += "        <ethnicityIndicator xsi:type='xsd:string'>" + args[4][12] + "</ethnicityIndicator>";

    /*
    body += "        <alternateId xsi:type='xsd:string'>" + args[4][13] + "</alternateId>";
    body += "        <alternateSource xsi:type='xsd:string'>" + args[4][14] + "</alternateSource>";
    body += "        <residentDistrictCode xsi:type='xsd:string'>" + args[4][15] + "</residentDistrictCode>";
    body += "        <stateStudentId xsi:type='xsd:string'>" + args[4][16] + "</stateStudentId>";
    body += "        <studentSuffix xsi:type='xsd:string'>" + args[4][17] + "</studentsuffix>";
    body += "        <studentMiddleName xsi:type='xsd:string'>" + args[4][18] + "</studentMiddleName>";
    body += "        <previousLastName xsi:type='xsd:string'>" + args[4][19] + "</previousLastName>";

    body += "        <race2Code xsi:type='xsd:string'>" + args[4][20] + "</race2Code>";
    body += "        <race3Code xsi:type='xsd:string'>" + args[4][21] + "</race3Code>";
    body += "        <race4Code xsi:type='xsd:string'>" + args[4][22] + "</race4Code>";
    body += "        <race5Code xsi:type='xsd:string'>" + args[4][23] + "</race5Code>";
    body += "        <cdfNumbersArray xsi:type='xsd:string'>" + args[4][24] + "</cdfNumbersArray>";
    body += "        <cdfValuesArray xsi:type='xsd:string'>" + args[4][25] + "</cdfValuesArray>";
    */

    body += "    </student>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</wsv2:searchStudent >";

    return body;
}

// using arguments array: see calling function
function getIDAssignmentV2_assignId_SOAPBody(args) {
    var body = "";

    body += "<ns0:assignId  xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    //body += "    <student xsi:type='q0:Student'>";
    body += "    <student xsi:type='q0:Student'>";
    body += "        <SSN xsi:type='xsd:string'>" + args[4][0] + "</SSN>";
    body += "        <birthDate xsi:type='xsd:string'>" + args[4][1] + "</birthDate>";
    body += "        <districtCode xsi:type='xsd:string'>" + args[4][2] + "</districtCode>";
    body += "        <gender xsi:type='xsd:string'>" + args[4][3] + "</gender>";
    body += "        <gradeLevel xsi:type='xsd:string'>" + args[4][4] + "</gradeLevel>";
    body += "        <localStudentId xsi:type='xsd:string'>" + args[4][5] + "</localStudentId>";
    body += "        <raceEthnicCode xsi:type='xsd:string'>" + args[4][6] + "</raceEthnicCode>";
    body += "        <schoolCode xsi:type='xsd:string'>" + args[4][7] + "</schoolCode>";
    body += "        <schoolYear xsi:type='xsd:string'>" + args[4][8] + "</schoolYear>";
    body += "        <studentFirstName xsi:type='xsd:string'>" + args[4][9] + "</studentFirstName>";
    body += "        <studentLastName xsi:type='xsd:string'>" + args[4][10] + "</studentLastName>";
    body += "        <sourceSISCode xsi:type='xsd:string'>" + args[4][11] + "</sourceSISCode>";
    body += "        <ethnicityIndicator xsi:type='xsd:string'>" + args[4][12] + "</ethnicityIndicator>";

    /*
    body += "        <alternateId xsi:type='xsd:string'>" + args[4][13] + "</alternateId>";
    body += "        <alternateSource xsi:type='xsd:string'>" + args[4][14] + "</alternateSource>";
    body += "        <residentDistrictCode xsi:type='xsd:string'>" + args[4][15] + "</residentDistrictCode>";
    body += "        <stateStudentId xsi:type='xsd:string'>" + args[4][16] + "</stateStudentId>";
    body += "        <studentSuffix xsi:type='xsd:string'>" + args[4][17] + "</studentsuffix>";
    body += "        <studentMiddleName xsi:type='xsd:string'>" + args[4][18] + "</studentMiddleName>";
    body += "        <previousLastName xsi:type='xsd:string'>" + args[4][19] + "</previousLastName>";

    body += "        <race2Code xsi:type='xsd:string'>" + args[4][20] + "</race2Code>";
    body += "        <race3Code xsi:type='xsd:string'>" + args[4][21] + "</race3Code>";
    body += "        <race4Code xsi:type='xsd:string'>" + args[4][22] + "</race4Code>";
    body += "        <race5Code xsi:type='xsd:string'>" + args[4][23] + "</race5Code>";
    body += "        <cdfNumbersArray xsi:type='xsd:string'>" + args[4][24] + "</cdfNumbersArray>";
    body += "        <cdfValuesArray xsi:type='xsd:string'>" + args[4][25] + "</cdfValuesArray>";
    */

    body += "    </student>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:assignId >";

    return body;
}

// using arguments array: see calling function
function getNearMatchListV2_getNearMatches_SOAPBody(args) {
    var body = "";

    body += "<ns0:getNearMatches xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    body += "    <submissionId xsi:type='xsd:string'>" + args[4][0] + "</submissionId>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:getNearMatches>";

    return body;
}

// using arguments array: see calling function
function getNearMatchResolutionV2_cancel_SOAPBody(args) {
    var body = "";

    body += "<ns0:cancel xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    body += "    <tranSerialNumber xsi:type='xsd:string'>" + args[4][0] + "</tranSerialNumber>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:cancel>";

    return body;
}

// using arguments array: see calling function
function getNearMatchResolutionV2_assignId_SOAPBody(args) {
    var body = "";

    body += "<ns0:assignId xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    body += "    <tranSerialNumber xsi:type='xsd:string'>" + args[4][0] + "</tranSerialNumber>";
    body += "    <stateStudentId xsi:type='xsd:string'>" + args[4][1] + "</stateStudentId>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:assignId>";

    return body;
}

// using arguments array: see calling function
function getNearMatchResolutionV2_createNewId_SOAPBody(args) {
    var body = "";

    body += "<ns0:createNewId xmlns:ns0='http://ws.escholar.com' soapenv:encodingstyle='http://schemas.xmlsoap.org/soap/encoding/'>";
    body += "    <tranSerialNumber xsi:type='xsd:string'>" + args[4][0] + "</tranSerialNumber>";
    body += "    <stateStudentId xsi:type='xsd:string'>" + args[4][1] + "</stateStudentId>";
    body += "    <userId xsi:type='xsd:string'>" + args[2] + "</userId>";
    body += "    <password xsi:type='xsd:string'>" + htmlEscape(args[3]) + "</password>";
    body += "</ns0:createNewId>";

    return body;
}

/*****************************************************************/
/*****************************************************************/
/*****************************************************************/



function htmlEscape(str) {
    return String(str)
            .replace(/&/g, '&amp;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;')
            .replace(/</g, '&lt;');

}

//.replace(/>/g, '&gt;');


/*****************************************************************/
/* 
    WebService Result Parsers 
*/
/*****************************************************************/

function TSDSUIDWSResultProcessor()
{
    this.parseResponse = function (id, op, data) {
        var ws = getWebServiceName(id);
        var wsop = op;
        var functionName = "this.parse_" + ws.toLowerCase() + "_" + wsop.toLowerCase() + "_response(data, ws, wsop)";
        var body = eval(functionName);

        return body;
    }

    /************** RAW RESULTS **************/
    this.displayRawResults = function (wsName, wsOperation, data, $HeaderNode) {
        var xml_pp = vkbeautify.xml(data);
        $('#divWSRawXMLResult').html('<xmp class="prettyprint lang-xml">' + xml_pp + '</xmp>');

        var resultList = "";
        var wsTitle = wsName + " " + wsOperation + " Results(" + $HeaderNode.children().length + "): ";
        $HeaderNode.find(wsOperation + "Return").each(
            function () {
                resultList += $(this).attr("href") + '|';
            }
        );
        wsTitle += resultList;

        $('#divWSRawHeaderResult').html(wsTitle); // 

        openWSDLResults();
        prettyPrint();
    }

    this.parse_batchinfo_getbatchinformation_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";
        pr += "batchStatus : " + $DetailNode.find("batchStatus").text() + "</br>";
        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";
        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "totalNumberRecords : " + $DetailNode.find("totalNumberRecords").text() + "</br>";
        pr += "transactionStatus : " + $DetailNode.find("transactionStatus").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    this.parse_studentsearch_searchstudent_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);


        $mainDiv = $('<DIV/>', {
            class: '',
        });

        this.displayResponseHeader($mainDiv, $DetailNode, $xml);

        //potentialNearMatches
        this.displayPotentialNearMatches($mainDiv, $DetailNode, $xml);

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return $mainDiv;
    }

    this.parse_idassignmentv2_assignid_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";

        //potentialNearMatches
        pr += "potentialNearMatches : " + $DetailNode.find("potentialNearMatches").text() + "</br>";

        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";

        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "submissionId : " + $DetailNode.find("submissionId").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    this.parse_nearmatchlistv2_getnearmatches_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";

        //potentialNearMatches
        pr += "potentialNearMatches : " + $DetailNode.find("potentialNearMatches").text() + "</br>";

        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";

        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "submissionId : " + $DetailNode.find("submissionId").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    //getNearMatchResolutionV2_createNewId_SOAPBody
    this.parse_nearmatchresolutionv2_createnewid_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";

        //potentialNearMatches
        pr += "potentialNearMatches : " + $DetailNode.find("potentialNearMatches").text() + "</br>";

        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";

        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "submissionId : " + $DetailNode.find("submissionId").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    this.parse_nearmatchresolutionv2_assignid_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";

        //potentialNearMatches
        pr += "potentialNearMatches : " + $DetailNode.find("potentialNearMatches").text() + "</br>";

        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";

        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "submissionId : " + $DetailNode.find("submissionId").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    this.parse_nearmatchresolutionv2_cancel_response = function (data, ws, wsop) {
        var xmlDoc = $.parseXML(data);
        $xml = $(xmlDoc);

        var $HeaderNode = $xml.find(wsop + 'Response');
        var mainNodeId = $HeaderNode.find(wsop + 'Return').attr('href');
        var $DetailNode = $(mainNodeId, $xml);

        var pr = "";
        pr += "<div class=''>";
        pr += "<h3>Status : " + $DetailNode.find("status").text() + "</h3>";
        pr += "batchNumber : " + $DetailNode.find("batchNumber").text() + "</br>";

        //potentialNearMatches
        pr += "potentialNearMatches : " + $DetailNode.find("potentialNearMatches").text() + "</br>";

        pr += "nearMatchCount : " + $DetailNode.find("nearMatchCount").text() + "</br>";

        pr += "stateStudentId : " + $DetailNode.find("stateStudentId").text() + "</br>";
        pr += "submissionId : " + $DetailNode.find("submissionId").text() + "</br>";
        pr += "</div>";

        /************** RAW RESULTS **************/
        this.displayRawResults(ws, wsop, data, $HeaderNode);

        return pr;
    }

    this.displayResponseHeader = function ($mainDiv, $DetailNode, $xml) {
        $('<H3/>', {
            class: '',
            text: 'Status: ' + $DetailNode.find("status").text()
        }).appendTo($mainDiv);

        $('<P/>', {
            class: '',
            text: 'batchNumber: ' + $DetailNode.find("batchNumber").text()
        }).appendTo($mainDiv);

        $('<P/>', {
            class: '',
            text: 'potentialNearMatches: ' + $DetailNode.find("potentialNearMatches").first().children().length
        }).appendTo($mainDiv);

        $('<P/>', {
            class: '',
            text: 'nearMatchCount: ' + $DetailNode.find("nearMatchCount").text()
        }).appendTo($mainDiv);

        $('<P/>', {
            class: '',
            text: 'stateStudentId: ' + $DetailNode.find("stateStudentId").text()
        }).appendTo($mainDiv);

        $('<P/>', {
            class: '',
            text: 'submissionId: ' + $DetailNode.find("submissionId").text()
        }).appendTo($mainDiv);

        //return $mainDiv;
    }

    this.displayPotentialNearMatches = function ($mainDiv, $DetailNode, $xml) {
        $DetailNode.find("potentialNearMatches").first().children().each(function (i, val) {
            $innerDiv = $('<DIV/>').appendTo($mainDiv);
            $('<H3/>', {
                class: '',
                text: 'Potential Near Match ' + (i + 1)
            }).appendTo($innerDiv);

            var nodeId = $(val).attr('href');
            if (nodeId) {
                $(nodeId, $xml).children().each(function (i2, val2) {
                    var outputText = $(val2).text();
                    if (!outputText) {
                        outputText = 'EMPTY';
                    }
                    $('<P/>', {
                        class: '',
                        text: $(val2).prop('tagName') + ': ' + outputText
                    }).appendTo($innerDiv);
                });
            }
        });
    }

    this.saveUIDAssignmentData = function (id, op) {
        var uri = 'WS/' + getWebServiceName(id) + '_' + op + '_de.html';

        $.ajax({
            'url': uri,
            'dataType': "text",
            'type': 'GET',
            'success': function (data) {
                showOperationExecuteScreenForFormEntry(data, id, op);
            },
            'error': function (jqXHR, textStatus, errorThrown) {
                var data = textStatus + ' :\n ' + errorThrown;
                showWebServiceInformation(data);
            }
        });
    }

}
/*****************************************************************/
/*****************************************************************/
/*****************************************************************/