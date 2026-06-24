<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/morris.css" type="text/css">
<script	src="${pageContext.request.contextPath}/static/script/jquery-latest.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/raphael.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/script/morris.js-0.5.1/morris.min.js" type="text/javascript"></script>
<br><br>
<h1>CHARTS_REALTIME</h1>
<c:if test="${(stabPon == 1)}">
	<table style="width: 100%;" border="1">
					<thead>
						<tr>
							<td colspan="6" class="title2">Stabilimento di Ponsacco</td>
						</tr>
					</thead>
					<tr>
						<td width="180px" class="tdTotals">
							Produzione Totale kg:
						</td>
						<td class="tdTotals">
							<fmt:formatNumber value="${ponTot}" groupingUsed="true" type="number" />
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
	
	<div id="PonsaccoChart" style="height: 250px;"></div>
	<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'PonsaccoChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
			{ hours: '06:00', <c:out value="${pon06}" /> prev: 0 },
		    { hours: '07:00', <c:out value="${pon07}" /> prev: 1500 },
		    { hours: '08:00', <c:out value="${pon08}" /> prev: 3000 },
		    { hours: '09:00', <c:out value="${pon09}" /> prev: 4500 },
		    { hours: '10:00', <c:out value="${pon10}" /> prev: 6000 },
		    { hours: '11:00', <c:out value="${pon11}" /> prev: 7500 },
		    { hours: '12:00', <c:out value="${pon12}" /> prev: 9000 },
		    { hours: '13:00', <c:out value="${pon13}" /> prev: 10500 },
		    { hours: '14:00', <c:out value="${pon14}" /> prev: 12000 },
		    { hours: '15:00', <c:out value="${pon15}" /> prev: 13500 },
		    { hours: '16:00', <c:out value="${pon16}" /> prev: 15000 },
		    { hours: '17:00', <c:out value="${pon17}" /> prev: 16500 },
		    { hours: '18:00', <c:out value="${pon18}" /> prev: 18000 },
		    { hours: '19:00', <c:out value="${pon19}" /> prev: 19500 },
		    { hours: '20:00', <c:out value="${pon20}" /> prev: 21000 },
		    { hours: '21:00', <c:out value="${pon21}" /> prev: 22500 },
		    { hours: '22:00', <c:out value="${pon22}" /> prev: 24000 },
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: 'hours',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['eff','prev'],
		  lineColors: ['#00ae86','#FF0000'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['eff','prev']
		});
	</script>
	<br><br>
</c:if>
<c:if test="${(stabPsg == 1)}">
	
	<table style="width: 100%;" border="1">
					<thead>
						<tr>
							<td colspan="6" class="title2">Stabilimento di Ponte San Giovanni</td>
						</tr>
					</thead>
					<tr>
						<td width="180px" class="tdTotals">
							Produzione Totale kg:
						</td>
						<td class="tdTotals">
							<fmt:formatNumber value="${psgTot}" groupingUsed="true" type="number" /></td>
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
	
	<div id="PerugiaChart" style="height: 250px;"></div>
	<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'PerugiaChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
			{ hours: '06:00', <c:out value="${psg06}" /> prev: 0 },
		    { hours: '07:00', <c:out value="${psg07}" /> prev: 1200 },
		    { hours: '08:00', <c:out value="${psg08}" /> prev: 2400 },
		    { hours: '09:00', <c:out value="${psg09}" /> prev: 4800 },
		    { hours: '10:00', <c:out value="${psg10}" /> prev: 7200 },
		    { hours: '11:00', <c:out value="${psg11}" /> prev: 9600 },
		    { hours: '12:00', <c:out value="${psg12}" /> prev: 12000 },
		    { hours: '13:00', <c:out value="${psg13}" /> prev: 14400 },
		    { hours: '14:00', <c:out value="${psg14}" /> prev: 16800 },
		    { hours: '15:00', <c:out value="${psg15}" /> prev: 19200 },
		    { hours: '16:00', <c:out value="${psg16}" /> prev: 21600 },
		    { hours: '17:00', <c:out value="${psg17}" /> prev: 24000 },
		    { hours: '18:00', <c:out value="${psg18}" /> prev: 26400 },
		    { hours: '19:00', <c:out value="${psg19}" /> prev: 28800 },
		    { hours: '20:00', <c:out value="${psg20}" /> prev: 31200 },
		    { hours: '21:00', <c:out value="${psg21}" /> prev: 33600 },
		    { hours: '22:00', <c:out value="${psg22}" /> prev: 36000 },
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: 'hours',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['eff','prev'],
		  lineColors: ['#00ae86','#FF0000'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['eff','prev']
		});
	</script>
	<br><br>
</c:if>
<c:if test="${(stabTer == 1)}">
	
<table style="width: 100%;" border="1">
				<thead>
					<tr>
						<td colspan="6" class="title2">Stabilimento di Stroncone</td>
					</tr>
				</thead>
				<tr>
					<td width="180px" class="tdTotals">
						Produzione Totale kg:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${terTot}" groupingUsed="true" type="number" /></td>
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

<div id="StronconeChart" style="height: 250px;"></div>
<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'StronconeChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
		         	{ hours: '06:00', <c:out value="${ter06}" /> prev: 0 },
				    { hours: '07:00', <c:out value="${ter07}" /> prev: 1000 },
				    { hours: '08:00', <c:out value="${ter08}" /> prev: 1900 },
				    { hours: '09:00', <c:out value="${ter09}" /> prev: 3800 },
				    { hours: '10:00', <c:out value="${ter10}" /> prev: 5700 },
				    { hours: '11:00', <c:out value="${ter11}" /> prev: 7600 },
				    { hours: '12:00', <c:out value="${ter12}" /> prev: 9500 },
				    { hours: '13:00', <c:out value="${ter13}" /> prev: 11400 },
				    { hours: '14:00', <c:out value="${ter14}" /> prev: 13300 },
				    { hours: '15:00', <c:out value="${ter15}" /> prev: 15200 },
				    { hours: '16:00', <c:out value="${ter16}" /> prev: 17100 },
				    { hours: '17:00', <c:out value="${ter17}" /> prev: 19000 },
				    { hours: '18:00', <c:out value="${ter18}" /> prev: 20900 },
				    { hours: '19:00', <c:out value="${ter19}" /> prev: 22800 },
				    { hours: '20:00', <c:out value="${ter20}" /> prev: 24700 },
				    { hours: '21:00', <c:out value="${ter21}" /> prev: 26600 },
				    { hours: '22:00', <c:out value="${ter22}" /> prev: 28500 },
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: 'hours',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['eff','prev'],
		  lineColors: ['#00ae86','#FF0000'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['eff','prev']
		});
	</script>
<br><br>
</c:if>
<c:if test="${(stabCan == 1)}">
	
<table style="width: 100%;" border="1">
				<thead>
					<tr>
						<td colspan="6" class="title2">Stabilimento di Cannara</td>
					</tr>
				</thead>
				<tr>
					<td width="180px" class="tdTotals">
						Produzione Totale kg:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${canTot}" groupingUsed="true" type="number" /></td>
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

<div id="CannaraChart" style="height: 250px;"></div>
<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'CannaraChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
		         	{ hours: '06:00', <c:out value="${can06}" /> prev: 0 },
				    { hours: '07:00', <c:out value="${can07}" /> prev: 1000 },
				    { hours: '08:00', <c:out value="${can08}" /> prev: 1900 },
				    { hours: '09:00', <c:out value="${can09}" /> prev: 3800 },
				    { hours: '10:00', <c:out value="${can10}" /> prev: 5700 },
				    { hours: '11:00', <c:out value="${can11}" /> prev: 7600 },
				    { hours: '12:00', <c:out value="${can12}" /> prev: 9500 },
				    { hours: '13:00', <c:out value="${can13}" /> prev: 11400 },
				    { hours: '14:00', <c:out value="${can14}" /> prev: 13300 },
				    { hours: '15:00', <c:out value="${can15}" /> prev: 15200 },
				    { hours: '16:00', <c:out value="${can16}" /> prev: 17100 },
				    { hours: '17:00', <c:out value="${can17}" /> prev: 19000 },
				    { hours: '18:00', <c:out value="${can18}" /> prev: 20900 },
				    { hours: '19:00', <c:out value="${can19}" /> prev: 22800 },
				    { hours: '20:00', <c:out value="${can20}" /> prev: 24700 },
				    { hours: '21:00', <c:out value="${can21}" /> prev: 26600 },
				    { hours: '22:00', <c:out value="${can22}" /> prev: 28500 },
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: 'hours',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['eff','prev'],
		  lineColors: ['#00ae86','#FF0000'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['eff','prev']
		});
	</script>
<br><br>
</c:if>

<c:if test="${(stabErb == 1)}">
	
<table style="width: 100%;" border="1">
				<thead>
					<tr>
						<td colspan="6" class="title2">Stabilimento di Erbusco</td>
					</tr>
				</thead>
				<tr>
					<td width="180px" class="tdTotals">
						Produzione Totale kg:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${erbTot}" groupingUsed="true" type="number" /></td>
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

<div id="ErbuscoChart" style="height: 250px;"></div>
<script>
	new Morris.Line({
		  // ID of the element in which to draw the chart.
		  element: 'ErbuscoChart',
		  // Chart data records -- each entry in this array corresponds to a point on
		  // the chart.
		  data: [
		         	{ hours: '06:00', <c:out value="${erb06}" /> prev: 0 },
				    { hours: '07:00', <c:out value="${erb07}" /> prev: 0 },
				    { hours: '08:00', <c:out value="${erb08}" /> prev: 1000 },
				    { hours: '09:00', <c:out value="${erb09}" /> prev: 2000 },
				    { hours: '10:00', <c:out value="${erb10}" /> prev: 3000 },
				    { hours: '11:00', <c:out value="${erb11}" /> prev: 4000 },
				    { hours: '12:00', <c:out value="${erb12}" /> prev: 5000 },
				    { hours: '13:00', <c:out value="${erb13}" /> prev: 6000 },
				    { hours: '14:00', <c:out value="${erb14}" /> prev: 7000 },
				    { hours: '15:00', <c:out value="${erb15}" /> prev: 8000 },
				    { hours: '16:00', <c:out value="${erb16}" /> prev: 9000 },
				    { hours: '17:00', <c:out value="${erb17}" /> prev: 10000 },
				    { hours: '18:00', <c:out value="${erb18}" /> prev: 11000 },
				    { hours: '19:00', <c:out value="${erb19}" /> prev: 12000 },
				    { hours: '20:00', <c:out value="${erb20}" /> prev: 13000 },
				    { hours: '21:00', <c:out value="${erb21}" /> prev: 14000 },
				    { hours: '22:00', <c:out value="${erb22}" /> prev: 15000 },
		  ],
		  // The name of the data record attribute that contains x-effs.
		  xkey: 'hours',
		  parseTime: false,
		  // A list of names of data record attributes that contain y-effs.
		  ykeys: ['eff','prev'],
		  lineColors: ['#00ae86','#FF0000'],
		  // Labels for the ykeys -- will be displayed when you hover over the
		  // chart.
		  labels: ['eff','prev']
		});
	</script>
<br><br>
</c:if>


