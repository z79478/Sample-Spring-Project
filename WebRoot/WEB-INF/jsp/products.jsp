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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
  	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
	<style type="text/css">
	.column {
		display: table;
		clear: both;
		padding-top: 5px;
	}
	
	tr:nth-child(even) {
		background: #CCC;
	}
	
	input {
		margin-right: 5px;
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
	
	<div style="display: inline-block; border-style:solid; border-width: 2px; margin: 5px 5px 5px 5px; padding: 5px 5px 5px 5px;">
		<div class="column">
			ID: <form:input path="prodFilter.id" size="5" maxlength="7"/>
			Description: <form:input path="prodFilter.description" size="15" maxlength="20"/>
			Date Last Sold: <form:input path="prodFilter.lastsold" size="10" id="datepicker"/>
		</div>
		<div class="column">
			Shelf Life: <form:input path="prodFilter.shelflife" size="5" maxlength="4"/>
			Department: <form:input path="prodFilter.department" size="5" maxlength="8"/>
			Price(Greater than): <form:input path="prodFilter.price" size="5" maxlength="4"/>
		</div>
		<div class="column">
			Unit: <form:input path="prodFilter.unit" size="5" maxlength="4"/>
			For Each(Equals): <form:input path="prodFilter.xfor" size="5" maxlength="1"/>
			Cost(Greater than): <form:input path="prodFilter.cost" size="5" maxlength="4"/>
		</div>
	</div>
	
	<p/>
	<input type="button" value="Clear" class="clear" />
	<input type="reset" value="Reset" />
	<input type="submit" value="Retrieve Products" />
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

<script>
	$( "#datepicker" ).datepicker();
    $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
    
    $(".clear").bind("click", function() {
  		$("input[type=text]").val("");
	});
</script>

</body>


</html>