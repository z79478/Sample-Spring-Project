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
	
	<title>Products Page Demo</title>
	
	<style type="text/css">
	.column {
		display: table;
		clear: both;
		padding-top: 5px;
	}
	
	</style>

</head>

<base href="<%=basePath%>">

<body>
<p>
<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a>
</div>
</p>

<form:form action="products/retrieve.htm" method="POST"  commandName="ProductsModel">
	<div style="font-size: larger; ">
		Please enter your search criteria for the product list
	</div>
	
	<div class="column">ID: <form:input path="prodFilter.id" size="3" /></div>
	<div class="column">Description: <form:input path="prodFilter.description" size="10" /></div>
	<div class="column">Date Last Sold: <form:input path="prodFilter.lastsold" size="5" /></div>
	<div class="column">Shelf Life: <form:input path="prodFilter.shelflife" size="5" /></div>
	<div class="column">Department: <form:input path="prodFilter.department" size="5" /></div>
	<div class="column">Price: <form:input path="prodFilter.price" size="5" /></div>
	<div class="column">Unit: <form:input path="prodFilter.unit" size="5" /></div>
	<div class="column">For Each: <form:input path="prodFilter.xfor" size="5" /></div>
	<div class="column">Cost: <form:input path="prodFilter.cost" size="5" /></div>
	
	<p/>
	
	<input type="submit" onclick="animateImage();" value="Retrieve Products" />
	<p/>
		
	<h3>List of Products</h3>
	<table id="tabledata" border="1" style="border: 2px solid black; border-collapse: collapse; ">
		<thead style="background: #FFFF99;">
			<tr>
				<th>ID</th>
				<th>Description</th>
				<th>Date Last Sold</th>
				<th>Shelf Life</th>
				<th>Department</th>
				<th>Price</th>
				<th>Unit</th>
				<th>For Each</th>
				<th>Total Cost</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ProductsModel.products}" var="prod" varStatus="status">
				<tr>
					<td>${prod.id}</td>
					<td>${prod.description}</td>
					<td>${prod.lastsold}</td>
					<td>${prod.shelflife}</td>
					<td>${prod.department}</td>
					<td>${prod.price}</td>
					<td>${prod.unit}</td>
					<td>${prod.xfor}</td>
					<td>${prod.cost}</td>
				</tr>
			</c:forEach>
		</tbody>
		
		<tfoot>
		
		</tfoot>
		
	</table>



</form:form>


</body>


</html>