<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/SampleSpring/app/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/uid_support.js"></script>
	<script type="text/javascript" src="/SampleSpring/scripts/vkbeautify.0.00.00.beta.js"></script>
	
	<head><title>Simple Service Request</title></head>
	<base href="<%=basePath%>">
	<body style="padding:30px">
	<p>
	<div style="margin-top:10px; text-align: left;"><a href="/SampleSpring">Home Page</a></div>
	</p>
	
	<input id="aText" type="text" size="10">
	<button type="button" onclick="$('#myFrame').attr('src', 'https://localhost:8443/TxEIS-WS/test/greeting/' + $('#aText').val());">Send</button>
	<p/>
	<iframe id="myFrame" src="">

	</iframe>
	
</body>

</html>