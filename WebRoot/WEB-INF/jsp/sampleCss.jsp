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
	
	<title>Sample CSS Page</title>
	<c:import url="../jsp/resource-includes.jsp"></c:import>
</head>
<base href="<%=basePath%>">
<body>
	<p>
	<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a></div>
	</p>
	
	<p>
	<div style="margin-top:50px; text-align: center;"><h2>Welcome to the Sample CSS Page</h2></div>
	</p>

	<h1>Block level DIVs</h1>
	<div>
	Div Block 1
	</div>
	<div>
	Div Block 2
	</div>
	
	<h1>Inline level SPANs</h1>
	<span>
	Span 1
	</span>
	<span>
	Span 2
	</span>

	<h1>Inline level DIVs</h1>
	<div style="display:inline;">
	Div Block 1
	</div>
	<div style="display:inline;">
	Div Block 2
	</div>

	<h1>Block level SPANs</h1>
	<span style="display:block;">
	Span 1
	</span>
	<span style="display:block;">
	Span 2
	</span>

	<h1>Floating DIVs</h1>
	<div style="float: left; padding-left:20px;">
	Div Block 1
	</div>
	<div style="float: left; padding-left:20px;">
	Div Block 2
	</div>
	<div style="float: left; clear:both; padding-left:20px;">
	Div Block 3
	</div>
	<div style="float: left; padding-left:20px;">
	Div Block 4
	</div>
	<div style="float: left; padding-left:20px;">
	Div Block 5
	</div>

</body>

</html>