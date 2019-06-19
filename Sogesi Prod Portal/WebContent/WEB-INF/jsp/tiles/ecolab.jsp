<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${filter.stabsName != null }">
	<div align="left" class="navFilter">
		Stabilimento:
		<c:forEach items="${filter.stabsName}" var="stab">
		<c:out value="${stab}" />;&nbsp;
		</c:forEach>
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
</c:if>
<c:if test="${stabPon > 0}">
	<c:if test="${fn:contains(sessionScope.ecolabSessionFilter.stabId, '1')||(sessionScope.ecolabSessionFilter.stabId == '999')}">
		<div align="center" style="margin:auto; margin-bottom: 15px;">
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
						<fmt:formatNumber value="${PonTot}" groupingUsed="true" type="number" />
					</td>
					<td width="140px" class="tdTotals">
						Ore Lavorate:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${PonOreTot}" groupingUsed="true" type="number" />
					</td>
					<td width="150px" class="tdTotals">
						Produttivit&agrave; kg/h:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${PonProdutt}" groupingUsed="true" type="number" />
					</td>
				</tr>
			</table>
			</div>
		<c:if test="${(filter.groupId == 2) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">Lavacontinue</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${PonLco}" var="rSum">
							<c:set var="totPonLco" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">
								<!--  Elemento che fa apprire il pop-up -->
									<button id="test-id" hidden="true">Dettaglio</button>
								<!-- Contenuto del pop-up -->
									<div id="dettaglio_popup" style="display:none;">
									Dettaglio produzione
									</div>
								<!-- SCRIPT per la gestione del popup -->
									<script>
										$(function(){
    										$('#test-id').click(function(){
        									$('#dettaglio_popup').bPopup({
        										speed:450,
        										transition:'slideDown', //fadeIn - slideDown - slideUp - slideBack
        										opacity:0.4,
        										positionstyle:'fixed'
        									}   									
        									);
    										});
										});
									</script>
								</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dPonLco}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totPonLco}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td class="title2">Lavacentrifughe</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${PonLce}" var="rSum">
							<c:set var="totPonLce" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dPonLce}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totPonLce}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>
<div style="width: 100%; float: left;">&nbsp;</div>
<c:if test="${stabPsg > 0}">
	<c:if test="${fn:contains(sessionScope.ecolabSessionFilter.stabId, '2')|| (sessionScope.ecolabSessionFilter.stabId == '999')}">
	&nbsp;
	<!-- <div style="width:100%; float:center; margin-bottom: 15px;"> -->
	<div align="center" style="margin-bottom: 15px;"> 
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
						<fmt:formatNumber value="${PsgTot}" groupingUsed="true" type="number" />
					</td>
					<td width="140px" class="tdTotals">
						Ore Lavorate:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${PsgOreTot}" groupingUsed="true" type="number" />
					</td>
					<td width="150px" class="tdTotals">
						Produttivit&agrave; kg/h:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${PsgProdutt}" groupingUsed="true" type="number" />
					</td>
				</tr>
			</table>
			</div>
		<c:if test="${(filter.groupId == 6) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">Lavacontinue</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${PsgLco}" var="rSum">
							<c:set var="totPsgLco" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dPsgLco}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totPsgLco}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td class="title2">Lavacentrifughe</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${PsgLce}" var="rSum">
							<c:set var="totPsgLce" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dPsgLce}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totPsgLce}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td class="title2">VOSS</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${PsgVos}" var="rSum">
							<c:set var="totPsgVos" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dPsgVos}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totPsgVos}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>
<div style="width: 100%; float: left;">&nbsp;</div>
<c:if test="${stabTer > 0}">
	<c:if test="${fn:contains(sessionScope.ecolabSessionFilter.stabId, '4') || (sessionScope.ecolabSessionFilter.stabId == 999)}">
	&nbsp;
	<div align="center" style="margin-bottom: 15px;">
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
						<fmt:formatNumber value="${TerTot}" groupingUsed="true" type="number" />
					</td>
					<td width="140px" class="tdTotals">
						Ore Lavorate:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${TerOreTot}" groupingUsed="true" type="number" />
					</td>
					<td width="150px" class="tdTotals">
						Produttivit&agrave; kg/h:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${TerProdutt}" groupingUsed="true" type="number" />
					</td>
				</tr>
			</table>
			</div>
		<c:if test="${(filter.groupId == 10) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">Lavacontinue Stroncone</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${TerLco}" var="rSum">
							<c:set var="totTerLco" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dTerLco}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
							    <td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totTerLco}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${TerLce}" var="rSum">
							<c:set var="totTerLce" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dTerLce}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totTerLce}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		
		<c:if test="${(filter.groupId == 12) || (filter.groupId == 999)}">
			<div align="left" style="float: right; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">Lavacontinua KGS 10K Stroncone</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${TerKgs10}" var="rSum">
							<c:set var="totTerKgs10" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dTerKgs10}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totTerKgs10}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${TerSte}" var="rSum">
							<c:set var="totTerSte" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dTerSte}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totTerSte}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>
<!-- INIZIO MODIFICHE CANNARA -->

<c:if test="${stabCan > 0}">
	<c:if test="${fn:contains(sessionScope.ecolabSessionFilter.stabId, '16')||(sessionScope.ecolabSessionFilter.stabId == '999')}">
		<div align="center" style="margin:auto; margin-bottom: 15px;">
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
						<fmt:formatNumber value="${CanTot}" groupingUsed="true" type="number" />
					</td>
					<td width="140px" class="tdTotals">
						Ore Lavorate:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${CanOreTot}" groupingUsed="true" type="number" />
					</td>
					<td width="150px" class="tdTotals">
						Produttivit&agrave; kg/h:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${CanProdutt}" groupingUsed="true" type="number" />
					</td>
				</tr>
			</table>
			</div>
		<c:if test="${(filter.groupId == 2) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">Lavacontinue</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${CanLco}" var="rSum">
							<c:set var="totCanLco" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">
								<!--  Elemento che fa apprire il pop-up -->
									<button id="test-id" hidden="true">Dettaglio</button>
								<!-- Contenuto del pop-up -->
									<div id="dettaglio_popup" style="display:none;">
									Dettaglio produzione
									</div>
								<!-- SCRIPT per la gestione del popup -->
									<script>
										$(function(){
    										$('#test-id').click(function(){
        									$('#dettaglio_popup').bPopup({
        										speed:450,
        										transition:'slideDown', //fadeIn - slideDown - slideUp - slideBack
        										opacity:0.4,
        										positionstyle:'fixed'
        									}   									
        									);
    										});
										});
									</script>
								</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dCanLco}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totCanLco}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
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
							<td class="title2">Lavacentrifughe</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${CanLce}" var="rSum">
							<c:set var="totCanLce" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dCanLce}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totCanLce}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>
<div style="width: 100%; float: left;">&nbsp;</div>
<!--  FINE MODIFICHE CANNARA -->
<!-- ------------------------------------------------------------------------------------------------------------------------------------- -->
<!--  INIZIO MODIFICHE ERBUSCO -->
<c:if test="${stabErb > 0}">
	<c:if test="${fn:contains(sessionScope.ecolabSessionFilter.stabId, '32')||(sessionScope.ecolabSessionFilter.stabId == '999')}">
		<div align="center" style="margin:auto; margin-bottom: 15px;">
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
						<fmt:formatNumber value="${ErbTot}" groupingUsed="true" type="number" />
					</td>
					<td width="140px" class="tdTotals">
						Ore Lavorate:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${ErbOreTot}" groupingUsed="true" type="number" />
					</td>
					<td width="150px" class="tdTotals">
						Produttivit&agrave; kg/h:
					</td>
					<td class="tdTotals">
						<fmt:formatNumber value="${ErbProdutt}" groupingUsed="true" type="number" />
					</td>
				</tr>
			</table>
			</div>
		<c:if test="${(filter.groupId == 201) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">FAVORIT PLUS 165</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${ErbLco}" var="rSum">
							<c:set var="totErbLco" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">
								<!--  Elemento che fa apprire il pop-up -->
									<button id="test-id" hidden="true">Dettaglio</button>
								<!-- Contenuto del pop-up -->
									<div id="dettaglio_popup" style="display:none;">
									Dettaglio produzione
									</div>
								<!-- SCRIPT per la gestione del popup -->
									<script>
										$(function(){
    										$('#test-id').click(function(){
        									$('#dettaglio_popup').bPopup({
        										speed:450,
        										transition:'slideDown', //fadeIn - slideDown - slideUp - slideBack
        										opacity:0.4,
        										positionstyle:'fixed'
        									}   									
        									);
    										});
										});
									</script>
								</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dErbLco}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totErbLco}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${(filter.groupId == 202) || (filter.groupId == 999)}">
			<div align="left" style="float: left; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">SENKING 20K</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${ErbLce}" var="rSum">
							<c:set var="totErbLce" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">
								<!--  Elemento che fa apprire il pop-up -->
									<button id="test-id" hidden="true">Dettaglio</button>
								<!-- Contenuto del pop-up -->
									<div id="dettaglio_popup" style="display:none;">
									Dettaglio produzione
									</div>
								<!-- SCRIPT per la gestione del popup -->
									<script>
										$(function(){
    										$('#test-id').click(function(){
        									$('#dettaglio_popup').bPopup({
        										speed:450,
        										transition:'slideDown', //fadeIn - slideDown - slideUp - slideBack
        										opacity:0.4,
        										positionstyle:'fixed'
        									}   									
        									);
    										});
										});
									</script>
								</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dErbLce}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totErbLce}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:if test="${(filter.groupId == 204) || (filter.groupId == 999)}">
			<div align="right" style="float: right; margin-bottom: 15px;">
				<table style="width: 500px;" border="1">
					<thead>
						<tr>
							<td class="title2">SENKING 13K</td>
							<td align="right" class="title2">kg lavati</td>
							<td colspan="3" align="right" class="title2">&nbsp;</td>
						</tr>
					</thead>
					<tbody class="txtSmall">
						<c:forEach items="${ErbLce2}" var="rSum">
							<c:set var="totErbLce2" value="${rSum.groupLoad}" />
							<tr>
								<td class="txtSmall"><c:out value="${rSum.machineName}" /></td>
								<td align="right" class="txtSmall"><fmt:formatNumber
										value="${rSum.totalLoad}" groupingUsed="true" type="number" /></td>
								<td colspan="3" class="txtSmall">&nbsp;</td>


							</tr>
						</c:forEach>
						<tr>
							<td class="title3">Dettaglio</td>
							<td class="title3" style="text-align: right;">Kg Lavati</td>
							<td class="title3" style="text-align: right;">Carico Nom.</td>
							<td class="title3" style="text-align: right;"> N. cicli</td>
							<td class="title3" style="text-align: right;">Carico %</td>
						</tr>
						<c:forEach items="${dErbLce2}" var="rDet">
							<tr>
								<td class="detList"><c:out value="${rDet.programName}" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.machineLoad}" groupingUsed="true" type="number" /></td>

								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.nomLoad}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList"><fmt:formatNumber
										value="${rDet.cicli}" groupingUsed="true" type="number" /></td>
								<td align="right" class="detList">
									<c:if test="${rDet.percLoad < 0.9}">
										<span class="txtSmallRed"><fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" /></span>
									</c:if>
									<c:if test="${rDet.percLoad >= 0.9}">
										<fmt:formatNumber value="${rDet.percLoad}" groupingUsed="true" type="percent" />
									</c:if>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td class="tdTotals">Totale</td>
							<td align="right" class="tdTotals"><fmt:formatNumber
									value="${totErbLce2}" groupingUsed="true" type="number" /></td>
							<td class="tdTotals" colspan="3">&nbsp;</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
	</c:if>
</c:if>
<div style="width: 100%; float: left;">&nbsp;</div>
<!--  FINE MODIFICHE ERBUSCO -->