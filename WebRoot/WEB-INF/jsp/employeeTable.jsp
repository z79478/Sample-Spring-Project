<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../jsp/resource-includes.jsp"></c:import>

<form:form id="theform" action="employeeAjax" method="POST" commandName="EmployeeModel">

	<c:set var="SexOpts" value="${sexOptions}"/>

	<div id="div1" class="displayFields">
		<h2>Employee Information</h2>
		<div id="div2" class="newline">
			<span class="label">Employee Number:</span>
			<span id="emp_nbr" class="field"> ${EmployeeModel.employee.emp_nbr}</span>
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
			<span id="fullName" class="field">${EmployeeModel.employee.fullName}</span>
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
		
		<div id="ajaxOutput" style="clear:both;">
		
		</div>
	</div>
	
</form:form>
