<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page
	import="it.sogesispa.prod.web.utils.ListaOreLavorateSessionFilter"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:set var="listaOre" value="${paginaOre.objectList}" />

<div style="float: left; width: 86%;">

	<table style="width: 100%;" border="1">
		<thead>
			<tr>
				<th class="manutenzioneTitle">
					<div>
						<c:choose>
							<c:when
								test="${listaOreLavorateSessionFilter.stabilimentoSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('stabilimentoSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('stabilimentoSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Stabilimento</div>

					<div>
						<c:choose>
							<c:when
								test="${listaOreLavorateSessionFilter.stabilimentoSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('stabilimentoSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
				</th>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'lavorate')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<th class="manutenzioneTitle">
						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreLavorateOrdinarieSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('oreLavorateOrdinarieSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('oreLavorateOrdinarieSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Ore
							ordinarie</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreLavorateOrdinarieSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('oreLavorateOrdinarieSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
					</th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'straordinario')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<th class="manutenzioneTitle"><div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreStraordinarioSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('oreStraordinarioSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('oreStraordinarioSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Ore
							straordinario</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreStraordinarioSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('oreStraordinarioSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div></th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'ferie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">

					<th class="manutenzioneTitle"><div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreFerieSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('oreFerieSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('oreFerieSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Ore
							ferie</div>
						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreFerieSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('oreFerieSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div></th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'maternita')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">

					<th class="manutenzioneTitle">
						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreMaternitaSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('oreMaternitaSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('oreMaternitaSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Ore
							maternita</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.oreMaternitaSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('oreMaternitaSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
					</th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'malattie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">

					<th class="manutenzioneTitle"><div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.malattieSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('malattieSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('malattieSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Malattie</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.malattieSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('malattieSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div></th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'infortuni')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">

					<th class="manutenzioneTitle"><div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.infortuniSort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('infortuniSortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('infortuniSortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Infortuni</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.infortuniSort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('infortuniSortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div></th>
				</c:if>
				<c:if
					test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'l104')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">

					<th class="manutenzioneTitle"><div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.legge104Sort ==  ListaOreLavorateSessionFilter.SORT_ASC}">
									<img class="arrowUpRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowUpGreenImg"
										onclick="$('#sortingValue').val('legge104SortAsc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="clickable"
							onclick="$('#sortingValue').val('legge104SortNone');
								document.getElementById('lista-ore-lavorate-form').submit();">Legge
							104</div>

						<div>
							<c:choose>
								<c:when
									test="${listaOreLavorateSessionFilter.legge104Sort ==  ListaOreLavorateSessionFilter.SORT_DESC}">
									<img class="arrowDownRedImg">
								</c:when>
								<c:otherwise>
									<img class="arrowDownGreenImg"
										onclick="$('#sortingValue').val('legge104SortDesc');
								document.getElementById('lista-ore-lavorate-form').submit();">
								</c:otherwise>
							</c:choose>
						</div></th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaOre}" var="att">
				<tr>
					<td class="manutenzioneTxt" style="text-align: left">${att.stabilimento}</td>
					<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'lavorate')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##"
							value="${att.oreLavorateOrdinarie}" /></td>
							</c:if>
							<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'straordinario')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##"
							value="${att.oreStraordinario}" /></td>
							</c:if>
							<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'ferie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##" value="${att.oreFerie}" /></td>
							</c:if>
							<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'maternita')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##"
							value="${att.oreMaternita}" /></td>
							</c:if>
							<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'malattie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##" value="${att.malattie}" /></td>
							</c:if>
							<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'infortuni')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
					<td class="manutenzioneTxt"><fmt:formatNumber
							minFractionDigits="0" pattern="#,##0.##" value="${att.infortuni}" /></td>
							</c:if>
					<c:if
						test="${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'l104')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'}">
						<td class="manutenzioneTxt"><fmt:formatNumber
								minFractionDigits="0" pattern="#,##0.##" value="${att.legge104}" /></td>
					</c:if>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<form action="ore-lavorate" method="get" id="lista-ore-lavorate-form">
		<input type="hidden" name="sorting" value="true"> <input
			type="hidden" id="sortingValue" name="sortingValue" value="">
		<input type="hidden" id="pageNumber" name="pageNumber" value="">
	</form>
</div>


