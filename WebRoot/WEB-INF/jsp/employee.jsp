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

<p>
Employee Count: ${EmployeeModel.countCustomers}
</p>

<form:form action="employee/retrieve.htm" method="POST"  commandName="EmployeeModel">

	Employee Number: <form:input path="retrieveName" size="10" maxlength="6" title="Search Name" />

	<input type="submit" onclick="animateImage();" value="Retrieve Employee" />

</form:form>
<c:set var="SexOpts" value="${sexOptions}"/>
<c:if test="${EmployeeModel.employee != null}" >
	<form:form id="theform" action="employee/save.htm" method="POST" commandName="EmployeeModel">
		<div id="div1" class="displayFields">
			<h2>Inline Style Layout</h2>
			<div id="div2" class="newline">
				<span class="label">Employee Number:</span>
				<span class="field"> ${EmployeeModel.employee.emp_nbr}</span>
			</div>
			<div id="div3" class="newline">
				<span class="label">First Name: </span>
				<span class="field"><form:input path="employee.name_f" maxlength="17" size="15" /></span>
				<span class="label">Middle Name:</span> 
				<span class="field"><form:input path="employee.name_m" maxlength="14" size="15"/></span>
				<span class="label">Last Name: </span>
				<span class="field"><form:input path="employee.name_l" maxlength="25" size="20" /></span>
			</div>
			<div id="div3" class="newline">
				<span class="label">Full Name: </span>
				<span class="field">${EmployeeModel.employee.fullName}</span>
			</div>
			<div id="div4" class="newline">
				<span class="label">Race White: </span>
				<span class="field"><form:checkbox path="employee.race_white" value="true" /></span>	
				<span class="label">DOB: </span>
				<span class="field">
					<input id="datepicker" name="employee.dob" size="10" value="<fmt:formatDate value='${EmployeeModel.employee.dob}' pattern='MM/dd/yyyy'/>"/>
				</span>	
				<span class="label">Marital Status: </span>
				<span class="field">
					<form:select path="employee.marital_stat">
						<c:forEach items="${maritalOptions}" var="option" varStatus="status">
							<form:option value="${fn:substring(option,0,1)}">${fn:substring(option,2,10)}</form:option>
						</c:forEach>
					</form:select>
				</span>	
				<span class="label">Sex: </span>
				<div style="width:72px; display:inline-block; vertical-align:top; text-align: right;">
				<span class="field">
						<c:forEach items="${SexOpts}" var="option" varStatus="status">
							<c:if test="${status.index == 0}">
								<label for="employee.sex${status.index}">${fn:substring(option,2, 10)}</label>
								<form:radiobutton path="employee.sex" value="${fn:substring(option,0,1)}"/>
							</c:if>
							<c:if test="${status.index > 0}">
								<label for="employee.sex${status.index}">${fn:substring(option,2, 10)}</label>
								<form:radiobutton path="employee.sex" value="${fn:substring(option,0,1)}"/>
							</c:if>							 	
						</c:forEach>
				</span>
				</div>	
			</div>
			<div id="div5" class="newline">
				<span class="label">Number Field: </span>
				<c:set var="displayField">
					
				</c:set>
				<span class="field"><input name="employee.numberField" value="<fmt:formatNumber type='number' value='${EmployeeModel.employee.numberField}' pattern='###,###.00' />" /></span>				
			</div>

			<div id="div6" class="newline button" style="position: relative; width:100%">
				<input type="submit" value="Save Employee" />
			</div>

		</div>
		
		<div style="clear:both;">
		<h2>Column Style Layout</h2>
		<div style="float:left;">
			<span style="display:block;">Employee Number:</span>
			<span style="display:block;">First Name:</span>
			<span style="display:block;">Full Name:</span>
			<span style="display:block;">Race White:</span>
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">${EmployeeModel.employee.emp_nbr}</span>
			<span style="display:block;">${EmployeeModel.employee.name_f}</span>
			<span style="display:block;">${EmployeeModel.employee.fullName}</span>
			<span style="display:block;"><form:checkbox path="employee.race_white" value="true" /></span>			
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">Middle Name:</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">DOB:</span>
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">${EmployeeModel.employee.name_m}</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;"><input id="datepicker" name="employee.dob" size="10" value="<fmt:formatDate value='${EmployeeModel.employee.dob}' pattern='MM/dd/yyyy'/>"/></span>			
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">Last Name:</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">Marital Status:</span>
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">${EmployeeModel.employee.name_l}</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">
				<form:select path="employee.marital_stat">
					<c:forEach items="${maritalOptions}" var="option" varStatus="status">
						<form:option value="${fn:substring(option,0,1)}">${fn:substring(option,2,10)}</form:option>
					</c:forEach>
				</form:select>
			</span>
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">Sex:</span>
		</div>
		<div style="float:left; padding-left:10px;">
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">&nbsp</span>
			<span style="display:block;">&nbsp</span>
			<div style="width:72px; display:inline-block; vertical-align:top; text-align: right;">
				<c:forEach items="${sexOptions}" var="option" varStatus="status">
					<c:if test="${status.index == 0}">
						<label for="employee.sex${status.index}">${fn:substring(option,2, 10)}</label>
						<form:radiobutton path="employee.sex" value="${fn:substring(option,0,1)}"/>
					</c:if>
					<c:if test="${status.index > 0}">
						<label for="employee.sex${status.index}">${fn:substring(option,2, 10)}</label>
						<form:radiobutton path="employee.sex" value="${fn:substring(option,0,1)}"/>
					</c:if>							 	
				</c:forEach>
			</div>
		</div>
			
	</form:form>
</c:if>

<c:if test="${EmployeeModel.employee == null}" >
<p style="display:block;">No employee found.</p>
</c:if>

<img style="display:none;" id="image" src="../throbber.gif" />

<c:if test="${!EmployeeModel.showImage}">
	<script type="text/javascript" language="javascript">
    	$(function(){
        	  $("#image").hide();
    	});
	</script>
</c:if>

<p style="clear:both;'"/>
<h1>JQuery Output</h1>
<div id="output" style="display:block;">

</div>

<script type="text/javascript" language="javascript">
   	$(function(){
       	  $("#output").html("Hello");
       	  //$("#output").html($("#div3").html());
       	  //$("#output").html($("#div3 input").val());
   	});
</script>
	
</body>

</html>