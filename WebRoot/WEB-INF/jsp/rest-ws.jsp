<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/SampleSpring/app/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid_support.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/vkbeautify.0.00.00.beta.js"></script>
	
	<head><title>REST Examples</title></head>
	<base href="<%=basePath%>">
	<body style="padding:30px">
	<p>
	<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a></div>
	</p>
	
	<p>List of Users (GET-XML):
	<button type="button" onclick="GetUsersXML();">Send</button>
	</p>
	<script type="">
		function GetUsersXML() {
			$.ajax({ method: "GET", url: "https://localhost:8443/ws/rest/UserService/users", dataType: "xml"})
				.done(function(xml) {
					var output = "";
                    $(xml).find('user').each(function (index) {
						output = output + $(this).find("id").text() + " - " + $(this).find("name").text() + " - " + $(this).find("profession").text() + "<br/>";
					});
					$("#output").html(output);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				});  
		}
	</script>
	
	<p>List of Users (GET-JSON):
	<button type="button" onclick="GetUsersJSON();">Send</button>
	</p>
	<script type="">
		function GetUsersJSON() {
			$.ajax({ method: "GET", url: "https://localhost:8443/ws/rest/UserService/usersjson", dataType: "json"})
				.done(function(users) {
					var output = "";
					for (var i in users) {
						output = output + users[i].id + " - " + users[i].name + " - " + users[i].profession + "<br/>";
					};
					$("#output").html(output);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				});  
		}
	</script>


	<p>Get Specific User (GET-XML):
	<input id="aText" type="text" size="3">
	<button type="button" onclick="GetUserXML();">Send</button>
	</p>
	<script type="">
		function GetUserXML() {
			var userID = $('#aText').val();
			$.ajax({ method: "GET", url: "https://localhost:8443/ws/rest/UserService/users/" + userID, dataType: "xml"})
				.done(function(xml) {
					var output = "";
                    $(xml).find('user').each(function (index) {
						output = output + $(this).find("id").text() + " - " + $(this).find("name").text() + " - " + $(this).find("profession").text() + "<br/>";
					});
					$("#output").html(output);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				});  
		}
	</script>

	<p>Add a User (PUT):</p>
	<div>
	ID:<input id="aID" type="text" size="10">
	Name:<input id="aName" type="text" size="10">
	Profession:<input id="aProf" type="text" size="10">
	</div>
	<button type="button" onclick="putMethod();">Send</button>
	
	<script type="">
		function putMethod() {
			var aID = $("#aID").val();
			var aname = $("#aName").val();
			var aprof = $("#aProf").val();
			$.ajax({ method: "PUT", url: "https://localhost:8443/ws/rest/UserService/users", data:{id: aID, name: aname, profession: aprof}})
				.done(function(msg) {
					$("#output").html(msg);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				});  
		}
	</script>

	<p>Update an existing User (POST):</p>
	<div>
	ID:<input id="aID2" type="text" size="10">
	Name:<input id="aName2" type="text" size="10">
	Profession:<input id="aProf2" type="text" size="10">
	</div>
	<button type="button" onclick="postMethod();">Send</button>
	
	<script type="">
		function postMethod() {
			var aID = $("#aID2").val();
			var aname = $("#aName2").val();
			var aprof = $("#aProf2").val();
			$.ajax({ method: "POST", url: "https://localhost:8443/ws/rest/UserService/users", data:{id: aID, name: aname, profession: aprof}})
				.done(function(msg) {
					$("#output").html(msg);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				}); 
		}
	</script>
	
	<p>Delete an existing User (DELETE):</p>
	<div>
	ID:<input id="aID3" type="text" size="10">
	</div>
	<button type="button" onclick="postMethod();">Send</button>
	
	<script type="">
		function postMethod() {
			var aID = $("#aID3").val();
			$.ajax({ method: "DELETE", url: "https://localhost:8443/ws/rest/UserService/users/" + aID})
				.done(function(msg) {
					$("#output").html(msg);
				})
				.fail(function(jqXHR, textStatus) {
					$("#output").html(textStatus);
				}); 
		}
	</script>	
	<p/>
	
	<div id="output" style="border: 2px solid black; height:300px; overflow-y: scroll;" >
	
	
	</div>
	
</body>

</html>