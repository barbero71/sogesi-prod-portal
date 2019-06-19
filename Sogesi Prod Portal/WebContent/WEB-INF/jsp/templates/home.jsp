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
$(document).ready(chkUrl);

function chkUrl() {
	
	var pgurl = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
	
	$("#nav li a").each(function(){
          if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
          $(this).addClass("active");
     });
}
</script>
<tiles:insertAttribute name="content-script" />
</head>
<body>
	<table border="1" style="width: 1280px; border-collapse: collapse;">
		<tbody style="font-size: small;">
			<tr>
				<td width="240" align="center"><img
					src="${pageContext.request.contextPath}/static/images/sogesi_logo_new.png"
					width="138" height="60" alt="" style="v-align: center;" /></td>
				<td align="right" class="txtSmall">Benvenuto,
					${user.firstName}&nbsp;${user.lastName}<br> <a
					href="<c:url value='${pageContext.request.contextPath}/j_spring_security_logout' />">Disconnetti</a>
				</td>
			</tr>
			<tr>
				<td align="center" style="vertical-align: middle"><tiles:insertAttribute name="area-logo" /></td>
				<td>
					<menu id="nav">
							<li><a href="main">Home</a></li>
							<li><a href="ecolab">Produzione</a></li>
							<li><a href="charts">Grafici Produzione</a></li>
							<li><a href="productivity">Produttivit&agrave;</a></li>
							<li><a href="lista-attivita">Lista Attivit&agrave;</a></li>
							<li><a href="lista-pianificazioni">Pianificazione Attivit&agrave;</a></li>
							<li><a href="ore-lavorate">Ore</a></li>
							<li><a href="ore-lavorate-charts">Grafici Ore</a></li>
							<li><a href="consumo-detergenti">Consumi</a></li>
							<li><a href="consumo-detergenti-charts">Grafici Consumi</a></li>
					</menu>
				</td>
			</tr>
			<tr>
				<td style="font-size: small; vertical-align: top;"
					style="padding:5px;" class="txtSmall,mainTable">
					<tiles:insertAttribute name="filter" />
					</td>
				<td><tiles:insertAttribute name="main-content" /></td>
			</tr>
			<tr>
				<td><span style="font-size: xx-small;">&nbsp;<tiles:insertAttribute
							name="version" /></span></td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</body>
</html>