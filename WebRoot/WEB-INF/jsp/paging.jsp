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
	<script type="text/javascript" src="/SampleSpring/scripts/paging.js"></script>
	
</head>
<base href="<%=basePath%>">

<body>
	<p>
	<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a>
	</div>
	</p>

	<p>
	<div style="margin-top:50px; text-align: center;"><h2>Welcome to the Paging Example Page</h2></div>
	</p>
	
	<spring:hasBindErrors name="PagingModel">
	<div>
		<p>
			<c:forEach items="${errors.allErrors}" var="error">
				<spring:message text="${error.defaultMessage}"/><br/>
			</c:forEach>
		</p>
	</div>
	</spring:hasBindErrors>

	<div id="saveMessage" style="color:green; font-weight:bold;">${saveSuccess}</div>
	
	<form:form id="theform" action="paging/save.htm" method="POST" commandName="PagingModel">

		<div id="tablediv">
		<table id="tabledata" border="1" style="border: 2px solid black; border-collapse: collapse; ">
			<thead style="background: #FFFF99;">
				<tr>
				    <th>Selected</th>
					<th>Emp Nbr</th>
					<th>First</th>
					<th>Middle</th>
					<th>Last</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${PagingModel.page.pageItems}" var="emp" varStatus="status">
				<tr>
				    <td><form:checkbox path="page.pageItems[${status.index}].selected"/></td>
					<td><form:input path="page.pageItems[${status.index}].emp_nbr" size="10" /></td>
					<td><form:input path="page.pageItems[${status.index}].name_f" size="10"/></td>
					<td><form:input path="page.pageItems[${status.index}].name_m" size="10"/></td>
					<td><form:input path="page.pageItems[${status.index}].name_l" size="10"/></td>
				</tr>			
			</c:forEach>
			</tbody>
		  <tfoot>
		    <tr style="background: #404036; ">
		      <td colspan="5">
		      		<img src="/SampleSpring/images/table_first.gif" onclick="page(1);"/>
		      		<img src="/SampleSpring/images/table_prev.gif"  onclick="page(${PagingModel.prevPage});"/>
		      		<img src="/SampleSpring/images/table_next.gif"  onclick="page(${PagingModel.nextPage});"/>
		      		<img src="/SampleSpring/images/table_last.gif"  onclick="page(${PagingModel.maxPages});"/>
		      		<span style="color: #FFFFFF;"> Page ${PagingModel.currentPage} of ${PagingModel.maxPages} </span>
		      </td>
		    </tr>
		  </tfoot>			
		</table>	
		</div>
	
	</form:form>
	
</body>
</html>