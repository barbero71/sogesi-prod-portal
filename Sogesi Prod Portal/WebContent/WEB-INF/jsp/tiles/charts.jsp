<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/morris.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/raphael.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/script/morris.js-0.5.1/morris.min.js" type="text/javascript"></script>
<br><br>
<c:forEach items="${chartTableList}" var="chartTable">
	<table style="width: 100%;" border="1">
					<thead>
						<tr>
							<td colspan="6" class="title2">${chartTable.nomeStabilimento}</td>
						</tr>
					</thead>
					<tr>
						<td width="180px" class="tdTotals">
							Produzione Totale kg:
						</td>
						<td class="tdTotals">
							<fmt:formatNumber value="${chartTable.produzioneTotaleKg}" groupingUsed="true" type="number" />
							</td>
						<td width="140px" class="tdTotals">
							&nbsp;
						</td>
						<td class="tdTotals">&nbsp;
							</td>
						<td width="150px" class="tdTotals">
							&nbsp;
						</td>
						<td class="tdTotals">
							&nbsp;</td>
					</tr>
				</table>
	
<div id="${chartTable.nomeStabilimento}" style="height: 250px;"></div>

<c:set var="chartLine" value="${chartTable.chartLine}" />

<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: '${chartTable.nomeStabilimento}',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
				<c:forEach items="${chartLine.chartDataList}" var="chartData">
					{ ${chartLine.xkeyName}: '${chartData.xkey}',  ${chartLine.ykeyName}: ${chartData.ykey} },
				</c:forEach> 
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: '${chartLine.xkeyName}',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['${chartLine.ykeyName}'],
		  lineColors: ['#00ae86'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['${chartLine.ykeyName}']
		});
	</script>
<br><br>




</c:forEach>

