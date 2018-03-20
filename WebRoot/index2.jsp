<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Sample Spring Welcome Page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my Sample Spring Welcome page. <br/>
       
    <p>
	<div style="margin-top:10px;">
		<ul>
			<li><a href="/SampleSpring/app/employee.htm">Employee Demo Page</a></li>
			<li><a href="/SampleSpring/app/sampleCss.htm">Sample CSS Page</a></li>
			<li><a href="/SampleSpring/app/fileDownload.htm">File Download Page</a></li>
			<li><a href="/SampleSpring/app/XMLFileDemo.htm">XML File Demo Page</a></li>
			<li><a href="/SampleSpring/app/divTableDemo.htm">DIV Table Demo Page</a></li>
			<li><a href="/SampleSpring/app/employeeAjax.htm">Employee Ajax Page</a></li>
			<li><a href="/SampleSpring/app/employeeAjax.htm">Employee Third Page</a></li>
			<li><a href="/SampleSpring/app/paging.htm">Paging Example</a></li>
			<li><a href="/SampleSpring/app/uid.htm">TxEIS UID Example</a></li>
			<li><a href="/SampleSpring/app/ws-simple.htm">TxEIS-WS Simple URL Example</a></li>
			<li><a href="/SampleSpring/app/rest-ws.htm">TxEIS-WS REST Web Service Javascript Examples</a></li>
			<li><a href="/SampleSpring/app/rest-ws-java.htm">TxEIS-WS REST Web Service Java Examples</a></li>
		</ul>
	</div>
	</p>
    
  </body>
</html>
