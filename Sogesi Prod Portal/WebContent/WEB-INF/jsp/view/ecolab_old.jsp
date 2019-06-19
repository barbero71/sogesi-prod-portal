<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="javax.sql.DataSource"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Portale Produzione - So.Ge.Si. SpA</title>
<link
	href="${pageContext.request.contextPath}/static/css/sogesi-ecolab.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/static/scripts/jquery-latest.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/scripts/autoNumeric-latest.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#detList").autoNumeric('init', {
			aSep : '.',
			mDec : '0',
			vMax : '99999999999999999999999999'
		});
	});
</script>
<script type="text/javascript">
	var httprequest = false;
	var httprequest2 = false;

	function responseMach() {
		if (httprequest2.readyState == 4 && httprequest2.status == 200) {
			var second = document.getElementById("cmbMach");
			second.length = 0;
			var responsedata = httprequest2.responseText;

			if (responsedata.substring(responsedata.length - 1) == ",") {
				responsedata = responsedata.substring(0,
						responsedata.length - 1);
			}
			var responselist = responsedata.split(",");
			var rsText = "";
			for (i = 0; i < responselist.length; i++) {
				var comboitem = document.createElement("option");
				rsText = responselist[i].split("-");
				comboitem.text = rsText[1];
				comboitem.value = rsText[0];

				second.options.add(comboitem);
			}
		}
	}

	function responseGrp() {
		if (httprequest.readyState == 4 && httprequest.status == 200) {
			var second = document.getElementById("cmbGroup");
			second.length = 0;
			var responsedata = httprequest.responseText;

			if (responsedata.substring(responsedata.length - 1) == ",") {
				responsedata = responsedata.substring(0,
						responsedata.length - 1);
			}

			var responselist = responsedata.split(",");
			var rsText = "";
			for (i = 0; i < responselist.length; i++) {
				var comboitem = document.createElement("option");
				rsText = responselist[i].split("-");
				comboitem.text = rsText[1];
				comboitem.value = rsText[0];
				second.options.add(comboitem);
			}
		}
	}

	function machineChange() {
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = document.getElementById("cmbStab").value;
		if (grpSelected.length == 0) {
			alert("Please select a item");
			var second = document.getElementById("cmbMach");
			//second.length = 0; 
			return;
		}
		if (window.XMLHttpRequest) {
			httprequest2 = new XMLHttpRequest();
		} else {
			httprequest2 = new ActiveXObject("Microsoft.XMLHTTP");
		}
		httprequest2.open("GET",
				"${pageContext.request.contextPath}/dynamic/machines.jsp?group="
						+ grpSelected + "&plant=" + plantSelected, true);
		httprequest2.onreadystatechange = responseMach;
		httprequest2.send();
	}

	function groupChange() {
		var dataselected = document.getElementById("cmbStab").value;
		if (dataselected == "999")
			machineChange();
		if (dataselected.length == 0) {
			alert("Please select a item");
			var second = document.getElementById("cmbGroup");
			second.length = 0;
			return;
		}
		if (window.XMLHttpRequest) {
			httprequest = new XMLHttpRequest();
		} else {
			httprequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		httprequest.open("GET",
				"${pageContext.request.contextPath}/dynamic/groups.jsp?params="
						+ dataselected, true);
		httprequest.onreadystatechange = responseGrp;
		httprequest.send();

		//machineChange();
	}

	function validate() {
		var startDt = document.getElementById("dateFrom").value;
		var endDt = document.getElementById("dateTo").value;

		if (new Date(startDt).getTime() > new Date(endDt).getTime()) {
			alert("La data di fine  non deve essere MINORE della data di inizio!");
			return;
		} else if (startDt == "" || endDt == "") {
			alert("Il campo data non può essere vuoto!");
			return;
		}
		document.getElementById("filterBox").submit();
	}
</script>
</head>
<body>
	<table border="1" style="width: 1280px; border-collapse: collapse;">
		<tbody style="font-size: small;">
			<tr>
				<td width="240" align="center"><img
					src="${pageContext.request.contextPath}/static/images/sogesi_logo.png"
					width="138" height="60" alt="" /></td>
				<td align="right" class="txtSmall">Benvenuto,
					${user.firstName}&nbsp;${user.lastName}<br> <a
					href="<c:url value='${pageContext.request.contextPath}/j_spring_security_logout' />">Disconnetti</a>
				</td>
			</tr>
			<tr>
				<td align="center" style="vertical-align: center">Powered by <img
					src="${pageContext.request.contextPath}/static/images/ecolab_logo.png"
					width="62" height="12" alt="Powered by Ecolab" /></td>
				<td>
					<table>
						<tr>
							<td width="100px"><a class="navBarLink"
								href="${pageContext.request.contextPath}/main">Home</a></td>
							<td width="100px"><span class="navBarText">Prod.
									Ecolab</span></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="font-size: small; vertical-align: top;"
					style="padding:5px;" class="txtSmall">
					<form action="ecolab" method="post" id="filterBox" name="filterBox">
						Stabilimento<br> <select name="cmbStab" id="cmbStab"
							style="width: 160px" class="txtSmall"
							onchange="groupChange();machineChange();">
							<option value="999">Tutti...</option>
							<%
								User usr = (User) session.getAttribute("user");
								try
								{
									Context ctx = new InitialContext();
									DataSource ds = (DataSource) ctx
											.lookup("java:/comp/env/jdbc/oradb");
									Connection con = ds.getConnection();

									Statement sm = con.createStatement();

									ResultSet rs = sm.executeQuery("SELECT * FROM T_PLANTS");

									while (rs.next())
									{
										if ((Integer.parseInt(rs.getString("PLANT_ID")) & usr
												.getAuthLevel()) > 0)
										{
							%>
							<option value="<%=rs.getString("TI_NUMBER")%>"
								<%if (rs.getString("TI_NUMBER").equals(
								request.getParameter("cmbStab")))
							out.print(" selected");%>><%=rs.getString("PLANT_DESC")%></option>
							<%
								}
									}
									rs.close();
									sm.close();
									con.close();
									ctx.close();

								}
								catch (SQLException e)
								{
									e.printStackTrace();
								}
							%>
						</select> <br> <br> Gruppo<br> <select name="cmbGroup"
							id="cmbGroup" style="width: 160px" class="txtSmall"
							onchange="machineChange()">
							<option value="999">Tutti...</option>
							<%
								//User usr = (User) session.getAttribute("user");
								try
								{
									Context ctx = new InitialContext();
									DataSource ds = (DataSource) ctx
											.lookup("java:/comp/env/jdbc/oradb");
									Connection con = ds.getConnection();
									Statement sm = con.createStatement();
									ResultSet rs;
									String chkPlant = request.getParameter("cmbStab");
									if (chkPlant != null)
									{
										if (chkPlant.equals("999"))
										{
											rs = sm.executeQuery("SELECT * FROM T_GROUPS");
										}
										else
										{
											rs = sm.executeQuery("SELECT * FROM T_GROUPS WHERE TI_NUMBER = "
													+ chkPlant);
										}
									}
									else
									{
										rs = sm.executeQuery("SELECT * FROM T_GROUPS");
									}

									while (rs.next())
									{
										if ((Integer.parseInt(rs.getString("PLANT_ID")) & usr
												.getAuthLevel()) > 0)
										{
							%>
							<option value="<%=rs.getString("GR_NUMBER")%>"
								<%if (rs.getString("GR_NUMBER").equals(
								request.getParameter("cmbGroup")))
						{
							out.print(" selected");
						}%>><%=rs.getString("GR_NAME")%></option>
							<%
								}
									}
									rs.close();
									sm.close();
									con.close();
									ctx.close();

								}
								catch (SQLException e)
								{
									e.printStackTrace();
								}
							%>
						</select> <br> <br> Macchina<br> <select name="cmbMach"
							id="cmbMach" class="txtSmall" style="width: 160px">
							<option value="999+999">Tutte...</option>
							<%
								try
								{
									Context ctx = new InitialContext();
									DataSource ds = (DataSource) ctx
											.lookup("java:/comp/env/jdbc/oradb");
									Connection con = ds.getConnection();
									ResultSet rs;

									Statement sm = con.createStatement();
									String chkGroup = request.getParameter("cmbGroup");

									if (chkGroup != null)
									{
										if (chkGroup.equals("999"))
										{
											rs = sm.executeQuery("SELECT * FROM VW_MACHINES");
										}
										else
										{
											rs = sm.executeQuery("SELECT * FROM VW_MACHINES WHERE \"GR_Number\" = "
													+ request.getParameter("cmbGroup"));
										}
									}
									else
									{
										rs = sm.executeQuery("SELECT * FROM VW_MACHINES");
									}

									while (rs.next())
									{
										if ((Integer.parseInt(rs.getString("PLANT_ID")) & usr
												.getAuthLevel()) > 0)
										{
							%>
							<option
								value="<%=rs.getString("GR_Number") + "+"
								+ rs.getString("MA_InterNumber")%>"
								<%if ((rs.getString("GR_Number") + "+" + rs
								.getString("MA_InterNumber")).equals(request
								.getParameter("cmbMach")))
							out.print(" selected");%>><%=rs.getString("MA_Name")%></option>
							<%
								}
									}
									rs.close();
									sm.close();
									con.close();
									ctx.close();

								}
								catch (SQLException e)
								{
									e.printStackTrace();
								}
							%>
						</select> <br> <br> Inizio periodo<br> <input type="date"
							name="dateFrom" id="dateFrom" width="140"
							value="<%=request.getParameter("dateFrom")%>" required><br>
						<br> Fine periodo<br> <input type="date" name="dateTo"
							id="dateTo" width="100"
							value="<%=request.getParameter("dateTo")%>" required> <br>
						<br> <input type="button" name="btnSubmit" id="idBtnSubmit"
							value="Seleziona" onclick="validate()">
					</form>
				</td>
				<td><c:if test="${filter.stabName != null }">
						<div align="left" class="navFilter">
							Stabilimento:
							<c:out value="${filter.stabName }" />
							&gt; Gruppo:
							<c:out value="${filter.groupName }" />
							&gt; Macchina:
							<c:out value=" ${filter.machineName}" />
							&gt; Data: dal
							<fmt:formatDate value="${filter.dateFrom }" pattern="dd-MM-yyyy" />
							al
							<fmt:formatDate value="${filter.dateTo }" pattern="dd-MM-yyyy" />
							<br>
						</div>
						<br>
					</c:if> <c:if test="${stabPon > 0}">
						<c:if test="${(filter.stabId == 1) || (filter.stabId == 999)}">
							<c:if test="${(filter.groupId == 2) || (filter.groupId == 999)}">
								<div align="left" style="float: left; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacontinue Ponsacco</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${PonLco}" var="rSum">
												<c:set var="totPonLco" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dPonLco}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totPonLco}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${(filter.groupId == 3) || (filter.groupId == 999)}">
								<div align="right" style="float: right; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacentrifughe Ponsacco</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${PonLce}" var="rSum">
												<c:set var="totPonLce" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dPonLce}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totPonLce}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
						</c:if>
					</c:if>
					<div style="width: 100%; float: left;">&nbsp;</div> <c:if
						test="${stabPsg > 0}">
						<c:if test="${(filter.stabId == 2) || (filter.stabId == 999)}">
							<c:if test="${(filter.groupId == 6) || (filter.groupId == 999)}">
								<div align="left" style="float: left; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacontinue Perugia</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${PsgLco}" var="rSum">
												<c:set var="totPsgLco" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dPsgLco}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totPsgLco}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${(filter.groupId == 7) || (filter.groupId == 999)}">
								<div align="left" style="float: right; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacentrifughe Perugia</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${PsgLce}" var="rSum">
												<c:set var="totPsgLce" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dPsgLce}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totPsgLce}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${(filter.groupId == 8) || (filter.groupId == 999)}">
								<div style="width: 100%; float: left;">&nbsp;</div>
								<div align="left" style="float: left; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">VOSS Perugia</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${PsgVos}" var="rSum">
												<c:set var="totPsgVos" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dPsgVos}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totPsgVos}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
						</c:if>
					</c:if>
					<div style="width: 100%; float: left;">&nbsp;</div> <c:if
						test="${stabTer > 0}">
						<c:if test="${(filter.stabId == 4) || (filter.stabId == 999)}">
							<c:if test="${(filter.groupId == 10) || (filter.groupId == 999)}">
								<div align="left" style="float: left; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacontinue Stroncone</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${TerLco}" var="rSum">
												<c:set var="totTerLco" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dTerLco}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totTerLco}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${(filter.groupId == 11) || (filter.groupId == 999)}">
								<div align="left" style="float: right; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Lavacentrifughe Stroncone</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${TerLce}" var="rSum">
												<c:set var="totTerLce" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dTerLce}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totTerLce}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
							<c:if test="${(filter.groupId == 13) || (filter.groupId == 999)}">
								<div style="width: 30%; float: left;">&nbsp;</div>
								<div align="left" style="float: left; margin-bottom: 15px;">
									<table style="width: 500px;" border="1">
										<thead>
											<tr>
												<td class="title2">Sterilizzazione Stroncone</td>
												<td align="right" class="title2">kg lavati</td>
												<td  colspan="2" align="right" class="title2">&nbsp;</td>
											</tr>
										</thead>
										<tbody class="txtSmall">
											<c:forEach items="${TerSte}" var="rSum">
												<c:set var="totTerSte" value="${rSum.groupLoad}" />
												<tr>
													<td class="txtSmall"><c:out
															value="${rSum.machineName}" /></td>
													<td align="right" class="txtSmall"><fmt:formatNumber
															value="${rSum.totalLoad}" groupingUsed="true"
															type="number" /></td>
													<td colspan="2" class="txtSmall">&nbsp;</td>
													

												</tr>
											</c:forEach>
											<tr>
												<td class="title3">Dettaglio</td>
												<td class="title3" style="text-align:right;">Kg Lavati</td>
												<td class="title3" style="text-align:right;">Carico Nom.</td>
												<td class="title3" style="text-align:right;">Carico %</td>
											</tr>
											<c:forEach items="${dTerSte}" var="rDet">
												<tr>
													<td class="detList"><c:out value="${rDet.programName}" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.machineLoad}" groupingUsed="true"
															type="number" /></td>

													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
													<td align="right" class="detList"><fmt:formatNumber
															value="${rDet.percLoad}" groupingUsed="true" 
															type="percent" /></td>
												</tr>
											</c:forEach>
											<tr>
												<td class="tdTotals">Totale</td>
												<td  align="right" class="tdTotals"><fmt:formatNumber
														value="${totTerSte}" groupingUsed="true" type="number" /></td>
												<td class="tdTotals" colspan="2">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</div>
							</c:if>
						</c:if>
					</c:if></td>
			</tr>
			<tr>
				<td><span style="font-size: xx-small;">&nbsp;Rel. 2.0.0
						del 27/02/2015</span></td>
				<td align="right"><span style="font-size: xx-small;">Developed
						by: Marco Barberini for So.Ge.Si. SpA.&nbsp;</span></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
