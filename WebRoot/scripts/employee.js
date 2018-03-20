$(document).ready(function() {

	$("#empAjaxRetrieve").click(function() {
		var employeeId = $("#retrieveName").val();
		$("#theform").load("employeeAjax/" + employeeId + " #theform", {empNbr: employeeId}, function(response, status, xhr){
			$("#datepicker").datepicker();
			if (status == "error") {
				alert(xhr.status + " " + xhr.statusText);
			}	
		});
	});
	
	$("#empGetRetrieve").click(function() {
		var employeeId = $("#retrieveName").val();
		$.get("employeeAjax/getJquery/" + employeeId)
			.done(function(data){
				$("#employee\\.name_f").val(data["name_f"]);
				$("#employee\\.name_l").val(data["name_l"]);
				$("#employee\\.name_m").val(data["name_m"]);
				var dateTemp = new Date(parseInt(data["dob"]));
				var month = dateTemp.getMonth() + 1;
				$("#datepicker").val(month + "/" + dateTemp.getDate() + "/" + dateTemp.getFullYear());
				$("#employee\\.sex").val(data["sex"]);
				$("#employee\\.marital_stat").val(data["marital_stat"]);
				$("#employee\\.race_white").val(data["race_white"]);
				$("#emp_nbr").html(data["emp_nbr"]);
				$("#fullName").html(data["fullName"]);
				$("#datepicker").datepicker();
			}).fail(function(jqXHR) {
				alert(jqXHR.status + " " + jqXHr.statusText);
			}).always(function(){
				document.getElementById("ajaxOutput").innerHTML = "Done.";
		});
	});
	
	
	$("#empJSONRetrieve").click(function() {
		var employeeId = $("#retrieveName").val();
		$.getJSON("employeeAjax/getJSON/" + employeeId, 
				function(data){
					$("#employee\\.name_f").val(data["name_f"]);
					$("#employee\\.name_l").val(data["name_l"]);
					$("#employee\\.name_m").val(data["name_m"]);
					var dateTemp = new Date(parseInt(data["dob"]));
					var month = dateTemp.getMonth() + 1;
					$("#datepicker").val(month + "/" + dateTemp.getDate() + "/" + dateTemp.getFullYear());
					$("#employee\\.sex").val(data["sex"]);
					$("#employee\\.marital_stat").val(data["marital_stat"]);
					$("#employee\\.race_white").val(data["race_white"]);
					$("#emp_nbr").html(data["emp_nbr"]);
					$("#fullName").html(data["fullName"]);
					$("#datepicker").datepicker();
				
			}).fail(function(jqxhr, textStatus, error){
            	var err = textStatus + ", " + error;
            	console.log( "Request Failed: " + err );
            });
	});
	
});

function jsAjaxCall() {
	var request = new XMLHttpRequest();
	var employeeId = $("#retrieveName").val();
	request.open('GET', 'employeeAjax/getJSON/' + employeeId, true);  //"true" makes the request asynchronous
	request.onreadystatechange = function() {
	    if (request.readyState == 4) { 
	        if (request.status == 200) 
	        {
	        	var data = JSON.parse(request.responseText);
				$("#employee\\.name_f").val(data["name_f"]);
				$("#employee\\.name_l").val(data["name_l"]);
				$("#employee\\.name_m").val(data["name_m"]);
				var dateTemp = new Date(parseInt(data.dob));
				var month = dateTemp.getMonth() + 1;
				$("#datepicker").val(month + "/" + dateTemp.getDate() + "/" + dateTemp.getFullYear());
				$("#employee\\.sex").val(data.sex);
				$("#employee\\.marital_stat").val(data.marital_stat);
				$("#employee\\.race_white").val(data.race_white);
				$("#emp_nbr").html(data.emp_nbr);
				$("#fullName").html(data.fullName);
				$("#datepicker").datepicker();
	            document.getElementById("ajaxOutput").innerHTML = request.responseText;
	        } 
	        else
	        {
	            alert('Something is wrong !!');
	        }
	    }
	};
	request.send(null);
}

function submitForm() {
	$("#retrieveForm").submit();
}
