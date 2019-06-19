<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/static/css/sogesi-ecolab.css"
	rel="stylesheet" type="text/css">
	<script
	src="${pageContext.request.contextPath}/static/script/jquery-latest.js"
	type="text/javascript">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div class="loginPage">
		<div align="center">
			<img src="${pageContext.request.contextPath}/static/images/logo_login.png"
				alt="So.Ge.Si. SpA" />
		</div>
		<br>
		<br>
		<div align="center" class="title"><tiles:insertAttribute name="message" /></div>
		<br>
		<tiles:insertAttribute name="login" />
	</div>
</body>
</html>