<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title" /></title>
<link
	href="${pageContext.request.contextPath}/static/css/sogesi-ecolab.css"
	rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery-ui.css" type="text/css">
<script	src="${pageContext.request.contextPath}/static/script/jquery-latest.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/autoNumeric-latest.js"></script>
<script	src="${pageContext.request.contextPath}/static/script/jquery.bpopup.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/script/jquery-ui.js"></script>
<script>
setTimeout(function(){
	   window.location.reload(1);
	}, 10000);
	</script>
</head>
<body>
	<tiles:insertAttribute name="main-content" />
</body>
</html>