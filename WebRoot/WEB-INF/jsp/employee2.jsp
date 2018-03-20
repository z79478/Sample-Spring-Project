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
<head>
	
	<title>Employee Page Demo</title>
	
	<c:import url="../jsp/resource-includes.jsp"></c:import>
	
	<link rel="stylesheet" href="/SampleSpring/styles/sample.css" type="text/css" media="all" />

	<script type="text/javascript" src="/SampleSpring/scripts/sample.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/employee.js"></script>
	
</head>
<base href="<%=basePath%>">
<body>
<p>
<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a>
</div>
</p>

<p>
<div style="margin-top:50px; text-align: center;"><h2>Welcome to the Employee Page</h2></div>
</p>

<spring:hasBindErrors name="EmployeeModel">
	<div>
		<p>
			<c:forEach items="${errors.allErrors}" var="error">
				<spring:message text="${error.defaultMessage}"/><br/>
			</c:forEach>
		</p>
	</div>
</spring:hasBindErrors>

<div id="saveMessage" style="color:green; font-weight:bold;">${saveSuccess}</div>

<form:form id="retrieveForm" action="employeeAjax" method="POST" commandName="EmployeeModel">
	<div style="clear:both;">
		Employee Number: <form:input path="retrieveName" size="10" maxlength="6" title="Search Name" />
		<input type="submit" value="Post Employee"/>
		<button id="empAjaxRetrieve" type="button">Load Employee</button>
		<button id="empJSONRetrieve" type="button">Load JSON Employee</button>
		<button id="empGetRetrieve" type="button">Jquery Get Employee</button>
		<button id="ajaxCall" type="button" onclick="jsAjaxCall();" >JS Ajax Employee</button>
		
	</div>
</form:form>

<jsp:include page="employeeTable.jsp" />


<c:if test="${EmployeeModel.employee == null}" >
	<p style="display:block;">No employee found.</p>
</c:if>
	
</body>

</html>