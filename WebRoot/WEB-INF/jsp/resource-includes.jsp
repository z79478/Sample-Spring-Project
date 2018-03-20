<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%String commonWeb = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/CommonWeb";%>
<c:set var="commonWeb" value="<%=commonWeb%>"/>

<link rel="stylesheet" href="${commonWeb}/styles/jquery-ui/jquery-ui.min.css">
<script type="text/javascript" src="${commonWeb}/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${commonWeb}/scripts/jquery-ui.min.js"></script>
