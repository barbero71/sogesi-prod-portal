<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page
	import="it.sogesispa.prod.web.utils.ListaOreLavorateSessionFilter"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/morris.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/raphael.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/script/morris.js-0.5.1/morris.min.js"
	type="text/javascript"></script>


<div style="float: left; width: 86%;">
	<c:forEach items="${charts}" var="ch">
	
		<h3>${ch.stabilimento}</h3>
		<div id="${ch.idStabilimento}" style="height: 300px;"></div>
		<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: '${ch.idStabilimento}',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
			<c:forEach items="${ch.datap}" var="ch2">
			{ 
				<c:forEach items="${ch2}" var="ch3">
				${ch3[0]}:${ch3[1]},
				</c:forEach>
			},
			</c:forEach>
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: ${ch.xkey},
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ${ch.ykeys},
		  lineColors: ${ch.lineColors},
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ${ch.labels}
		});
	</script>
	</c:forEach>

</div>

