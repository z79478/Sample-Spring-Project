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
	<c:import url="../jsp/resource-includes.jsp"></c:import>
	<title>Creating Table Structure Using Div Tag</title>
<style type="text/css">
.Table
{
    display: table;
}
.Body
{
    display: table-row-group;
}
.Title
{
    display: table-caption;
    text-align: center;
    font-weight: bold;
    font-size: larger;
}
.Heading
{
    display: table-row;
    font-weight: bold;
    text-align: center;
}
.Row
{
    display: table-row;
}
.Cell
{
    display: table-cell;
    border: solid;
    border-width: thin;
    padding-left: 5px;
    padding-right: 5px;
}
</style>	
</head>
<body>		
<div class="Table">
    <div class="Title">
        <p>This is a Table</p>
    </div>
	<div class="Heading">
	     <div class="Cell">
	         <p>Heading 1</p>
	     </div>
	     <div class="Cell">
	         <p>Heading 2</p>
	     </div>
	     <div class="Cell">
	         <p>Heading 3</p>
	     </div>
	</div>
	<div class="Body">
	    <div class="Row">
	        <div class="Cell">
	            <p>Row 1 Column 1</p>
	        </div>
	        <div class="Cell">
	            <p>Row 1 Column 2</p>
	        </div>
	        <div class="Cell">
	            <p>Row 1 Column 3</p>
	        </div>
	    </div>
	    <div class="Row">
	        <div class="Cell">
	            <p>Row 2 Column 1</p>
	        </div>
	        <div class="Cell">
	            <p>Row 2 Column 2</p>
	        </div>
	        <div class="Cell">
	            <p>Row 2 Column 3</p>
	        </div>
	    </div>
	</div>
</div>
</body>
</html>