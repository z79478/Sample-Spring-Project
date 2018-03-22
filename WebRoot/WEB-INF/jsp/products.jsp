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
	th {
		padding-left: 5px;
		padding-right: 5px;
	}
	
	.column {
		display: table;
		clear: both;
		padding-top: 5px;
	}
	
	tr:nth-child(even) {
		background: #FFFF99;
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
	<div style="font-size: larger; margin-left: 10px;">
		Please enter your search criteria for the product list
	</div>
	
	<div style="display: inline-block; border-style:solid; border-width: 2px; margin: 5px 5px 5px 5px; padding: 5px 5px 5px 5px;">
		<div class="column">
			ID: <form:input path="prodFilter.id" size="5" maxlength="7" tabindex="1"/>
			Description: <form:input path="prodFilter.description" size="15" maxlength="20" tabindex="1"/>
			Date Last Sold(Greater than): <form:input path="dateFilter" size="10" id="datepicker" tabindex="1"/>
		</div>
		<div class="column">
			Shelf Life: <form:input path="prodFilter.shelflife" size="5" maxlength="4" tabindex="1"/>
			Department: <form:input path="prodFilter.department" size="5" maxlength="8" tabindex="1"/>
			Price(Greater than): <form:input path="prodFilter.price" size="5" maxlength="4" tabindex="1"/>
		</div>
		<div class="column">
			Unit: <form:input path="prodFilter.unit" size="5" maxlength="4" tabindex="1"/>
			For Each(Equals): <form:input path="prodFilter.xfor" size="5" maxlength="1" tabindex="1"/>
			Cost(Greater than): <form:input path="prodFilter.cost" size="5" maxlength="4" tabindex="1"/>
		</div>
	</div>
	
	<p/>
	<input type="button" value="Clear" class="clear" tabindex="1"/>
	<input type="reset" value="Reset" tabindex="1"/>
	<input type="submit" value="Retrieve Products" tabindex="1"/>
	<p/>
	
	
	<c:if test="${fn:length(ProductsModel.products) > 0}" >
		<div  style="font-size: larger; width:60%; text-align: center;">List of Products</div>
		<table id="tabledata" border="1" style="border: 2px solid black; border-collapse: collapse; ">
			<thead style="background: #dc2a27;">
				<tr>
					<th onclick="sortTable(0)" >ID</th>
					<th onclick="sortTable(1)">Description</th>
					<th onclick="sortTable(2)">Date Last Sold</th>
					<th onclick="sortTable(3)">Shelf Life</th>
					<th onclick="sortTable(4)">Department</th>
					<th onclick="sortTable(5)">Price</th>
					<th onclick="sortTable(6)">Unit</th>
					<th onclick="sortTable(7)">For Each</th>
					<th onclick="sortTable(8)">Total Cost</th>
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
	</c:if>


</form:form>

<script>
	$( "#datepicker" ).datepicker();
    $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
    $( "#datepicker" ).datepicker("setDate", "${ProductsModel.dateFilter}");
    
    $(".clear").bind("click", function() {
  		$("input[type=text]").val("");
	});
	
	function sortTable(n) {
		  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		  table = document.getElementById("tabledata");
		  switching = true;
		  // Set the sorting direction to ascending:
		  dir = "asc"; 
		  /* Make a loop that will continue until
		  no switching has been done: */
		  while (switching) {
		    // Start by saying: no switching is done:
		    switching = false;
		    rows = table.getElementsByTagName("TR");
		    /* Loop through all table rows (except the first, which contains table headers): */
		    for (i = 1; i < (rows.length - 1); i++) {
		      // Start by saying there should be no switching:
		      shouldSwitch = false;
		      /* Get the two elements you want to compare, one from current row and one from the next: */
		      x = rows[i].getElementsByTagName("TD")[n];
		      y = rows[i + 1].getElementsByTagName("TD")[n];
		      /* Check if the two rows should switch place, based on the direction, asc or desc: */
		      if (dir == "asc") {
		        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
		          // If so, mark as a switch and break the loop:
		          shouldSwitch= true;
		          break;
		        }
		      } else if (dir == "desc") {
		        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
		          // If so, mark as a switch and break the loop:
		          shouldSwitch= true;
		          break;
		        }
		      }
		    }
		    if (shouldSwitch) {
		      /* If a switch has been marked, make the switch and mark that a switch has been done: */
		      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		      switching = true;
		      // Each time a switch is done, increase this count by 1:
		      switchcount ++; 
		    } else {
		      /* If no switching has been done AND the direction is "asc", set the direction to "desc" and run the while loop again. */
		      if (switchcount == 0 && dir == "asc") {
		        dir = "desc";
		        switching = true;
		      }
		    }
		  }
		}
</script>

</body>


</html>