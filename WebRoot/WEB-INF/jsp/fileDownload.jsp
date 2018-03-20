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
	
	<title>File Download Page</title>
	<c:import url="../jsp/resource-includes.jsp"></c:import>
</head>
<base href="<%=basePath%>">

<body>
<p>
<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a>
</div>
</p>

	<p>
		<h1>${FileModel.name}</h1>
	</p>
	                   
	<form:form id="theForm" action="fileDownload.htm" method="POST" commandName="FileModel">
	
		<c:if test="${FileModel.startProcess}">
			<button name="startProcess" type="submit">Start Process</button>
		</c:if>
	
	
		<c:if test="${FileModel.filesWritten}">
			<p>The files were written and being downloaded.</p>
			<script type="text/javascript">
				window.open("DownloadServlet?filename=file1.txt", "_self");
				setTimeout(function myDownload(){window.open("DownloadServlet?filename=file2.txt", "_self");}, 1000);
			</script>
		</c:if>
	
	</form:form>

</body>

</html>
