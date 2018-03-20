<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

	<form:form action="rest-ws-java" commandName="RestModel" method="POST">	
		<p>
			Server:<form:input path="server" size="20"/>
		</p>
		<p>List of Students (GET-JSON):
			<form:button name="GetStudents" value="GetStudents">Send</form:button>
		</p>
		
		<p>List of Users (GET-XML):
		<form:button name="GetXml" value="GetXml">Send</form:button>
		</p>
		
		<p>List of Users (GET-JSON):
		<form:button name="GetJson" value="GetJson">Send</form:button>
		</p>
	
		<p>Get Specific User (GET-XML):
		<form:input path="id1" type="text" size="3"/>
		<form:button name="GetXmlUser" value="GetXmlUser">Send</form:button>
		</p>
	
		<p>Add a User (PUT):<br/>
		
			ID:<form:input path="id2" type="text" size="10"/>
			Name:<form:input path="name1" type="text" size="10"/>
			Profession:<form:input path="profession1" type="text" size="10"/>
		
			<form:button name="PutUser" value="PutUser">Send</form:button>	
		</p>
		
		<p>Update an existing User (POST):<br/>
			ID:<form:input path="id3" type="text" size="10"/>
			Name:<form:input path="name2" type="text" size="10"/>
			Profession:<form:input path="profession2" type="text" size="10"/>
			<form:button name="PostUser" value="PostUser">Send</form:button>	
		</p>
		
		<p>Delete an existing User (DELETE):<br/>
			ID:<form:input path="id4" type="text" size="10"/>
			<form:button name="DeleteUser" value="DeleteUser">Send</form:button>
		</p>
		
		<p/>
		List of Users:
		<table border="1" style="border: 2px solid black;">
		<c:forEach items="${RestModel.users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.profession}</td>
			</tr>
		</c:forEach>
		</table>
		
		<p/>
		List of Students:
		<table border="1" style="border: 2px solid black;">
		<c:forEach items="${RestModel.students}" var="stud">
			<tr>
				<td>${stud.firstName}</td>
				<td>${stud.middleName}</td>
				<td>${stud.lastName}</td>
				<td>${stud.ssn}</td>
				<td>${stud.grade}</td>
			</tr>
		</c:forEach>
		</table>
				
		<p/>
		<div id="output" style="border: 2px solid black; height:50px; overflow-y: scroll;" >
		${RestModel.outputBox}
		</div>
	</form:form>
	
	
	
</body>

</html>