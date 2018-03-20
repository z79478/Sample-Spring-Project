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
	
	<title>XML File Demo Page</title>

	<c:import url="../jsp/resource-includes.jsp"></c:import>		
</head>
<base href="<%=basePath%>">

<body>
<p>
<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a>
</div>
</p>

<p>XML File Demo</p>

<form:form action="XMLFileDemo.htm" method="POST" commandName="XMLModel">
	<button onclick="$('#success').html('');">Execute</button>

<c:if test="${XMLModel.finished}">
	<p id="success">The file was exported successfully.</p>
	<script type="text/javascript">
		window.open("DownloadServlet?filename=Courses.xml", "_self");
	</script>
	
</c:if>
</form:form>

</body>
</html>