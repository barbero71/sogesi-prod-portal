<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page
	import="it.sogesispa.prod.web.utils.ListaOreLavorateSessionFilter"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div style="margin: 2px auto; width: 86%;">

	<c:forEach items="${listaDetergenti}" var="det">

		<div align="center"
			style="position: relative; margin: auto; margin-bottom: 15px;">
			<table style="width: 100%;" border="1">
				<thead>
					<tr>
						<td colspan="6" class="title2">Stabilimento di ${det.key }</td>
					</tr>
					<tr>
						<td class="title3">Nome prodotto</td>
						<td class="title3">Quantit&agrave; utilizzata</td>
						<td class="title3">Quantit&agrave;/kg Costo</td>
						<td class="title3">Costo/kg</td>
						<td class="title3">Quantit&agrave; desiderata</td>
						<td class="title3"></td>
					</tr>
				</thead>
				<tbody>
					<c:set var="totQuantita" value="0" scope="page" />
					<c:set var="totCostoquantitakg" value="0" scope="page" />
					<c:set var="totCostokg" value="0" scope="page" />
					<c:set var="totQuantitaDesiderata" value="0" scope="page" />
					<c:forEach items="${det.value}" var="d">
						<c:set var="totQuantita" value="${totQuantita + d.quantita}"
							scope="page" />
						<c:set var="totCostoquantitakg"
							value="${totCostoquantitakg + d.costoquantitakg}" scope="page" />
						<c:set var="totCostokg" value="${totCostokg + d.costokg}"
							scope="page" />
						<c:set var="totQuantitaDesiderata"
							value="${totQuantitaDesiderata + d.quantitaDesiderata}"
							scope="page" />
						<c:set var="perc" value="${d.quantita / d.quantitaDesiderata}"
							scope="page" />

						<tr>
							<td class="txtSmall">${d.nomeProdotto}</td>
							<td class="txtSmall"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${d.quantita}" /></td>
							<td class="txtSmall"><fmt:formatNumber
									value="${d.costoquantitakg}" type="currency"  currencySymbol="&euro;" /></td>
							<td class="txtSmall"><fmt:formatNumber value="${d.costokg}"
									type="currency" /></td>
							<td class="txtSmall"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${d.quantitaDesiderata}" /></td>
							<td ${perc <=1 ?"class='txtSmall'":"class='txtSmallRed'"}><fmt:formatNumber
									type="percent" maxIntegerDigits="3" minFractionDigits="2"
									value="${perc}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td class="tdTotals">Totali:</td>
						<td class="tdTotals"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${totQuantita }" /></td>
						<td class="tdTotals"><fmt:formatNumber
								value="${totCostoquantitakg }" type="currency" />
						<td class="tdTotals"></td>
						<td class="tdTotals"><fmt:formatNumber type="number"
									maxFractionDigits="2" value="${totQuantitaDesiderata }" /></td>
						<td class="tdTotals"></td>
					</tr>
				</tbody>

			</table>
		</div>
	</c:forEach>
</div>


