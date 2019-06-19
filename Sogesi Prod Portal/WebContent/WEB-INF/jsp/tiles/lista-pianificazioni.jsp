<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page
	import="it.sogesispa.prod.web.utils.ListaPianificazioniSessionFilter"%>

<c:set var="listaPianificazioni"
	value="${paginaPianificazioni.objectList}" />
<div>
	<form action="dettaglio-pianificazione" method="POST">
		<button name="action" value="newAttPianificata" type="submit">Nuova
			attivit&agrave; pianificata</button>
			
		<button style="float: right;" name="action" value="forzaEsecuzionePianificazione" type="submit">Forza esecuzione pianificazione</button>			
			
	</form>
</div>
<div align="left" class="navFilter">Stabilimento: ${stabilimento}
</div>
<div style="width: 100%;">
	<table style="width: 100%;" border="1">
		<thead>
			<tr>
				<th class="manutenzioneTitle">
					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.lineaSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('lineaAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('lineaNone');
								document.getElementById('lista-attivita-form').submit();">Area</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.lineaSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('lineaDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
				</th>
				<th class="manutenzioneTitle"><div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.macchinaSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('macchinaAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('macchinaNone');
								document.getElementById('lista-attivita-form').submit();">Macchina</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.macchinaSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('macchinaDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle"><div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.impiantoSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('impiantoAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('impiantoNone');
								document.getElementById('lista-attivita-form').submit();">Impianto</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.impiantoSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('impiantoDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle">
					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.prioritaSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('prioritaAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('prioritaNone');
								document.getElementById('lista-attivita-form').submit();">Priorit&agrave;</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.prioritaSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('prioritaDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
				</th>
				<th class="manutenzioneTitle"><div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.pianificazioneSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('pianificazioneAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('pianificazioneNone');
								document.getElementById('lista-attivita-form').submit();">
						Tipo<br /> pianificazione
					</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.pianificazioneSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('pianificazioneDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle"><div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.interventoSort ==  ListaPianificazioniSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('interventoAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('interventoNone');
								document.getElementById('lista-attivita-form').submit();">Intervento</div>

					<div>
						<c:choose>
							<c:when
								test="${listaPianificazioniSessionFilter.interventoSort ==  ListaPianificazioniSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('interventoDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle">&nbsp;</th>
				<th class="manutenzioneTitle">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaPianificazioni}" var="p">
				<tr>
					<td class="manutenzioneTxt">${p.linea}</td>
					<td class="manutenzioneTxt">${p.macchina}</td>
					<td class="manutenzioneTxt">${p.impianto}</td>
					<td class="manutenzioneTxt">${p.priorita}</td>
					<td class="manutenzioneTxt">${p.tipoPianificazione}</td>
					<td class="manutenzioneTxt">${p.intervento}</td>
					<td class="manutenzioneTxt">
						<form action="dettaglio-pianificazione" method="POST">
							<input type="hidden" name="id" id="id" value="${p.id}" />
							<button name="action" value="paginaDettaglioPianificazioneAction"
								type="submit">Modifica</button>
						</form>
					</td>
					<td class="manutenzioneTxt">
						<form action="dettaglio-pianificazione" method="POST">
							<input type="hidden" name="id" id="id" value="${p.id}" />
							<button name="action" value="eliminaPianificazioneAction"
								type="submit"
								onclick="return confirm('Sei sicuro di voler eliminare la pianificazione ?')">Elimina</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- PAGINATORE -->
	<div style="clear: both">


		<c:if
			test="${ not empty paginaPianificazioni and paginaPianificazioni.size > 0  }">

			<table class="PagerContainerTable">
				<tbody>
					<tr>
						<td class="PagerInfoCell">
							<div>Pagina ${ paginaPianificazioni.pageNumber } di ${ paginaPianificazioni.totalPages }
							</div>
						</td>

						<c:if test="${ not empty paginaPianificazioni.prima }">
							<td class="PagerSSCCells ">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaPianificazioni.prima }');
								document.getElementById('lista-attivita-form').submit();">Prima</div>
							</td>
						</c:if>
						<c:if test="${ not empty paginaPianificazioni.indietro }">
							<td class="PagerSSCCells">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaAttivita.indietro }');
								document.getElementById('lista-attivita-form').submit();">Indietro</div>

							</td>
						</c:if>

						<c:forEach items="${ paginaPianificazioni.numeriPagine }"
							var="numeroPagina">
							<c:choose>
								<c:when test="${ numeroPagina ==  paginaPianificazioni.pageNumber }">
									<td class="PagerCurrentPageCell"><span
										class="PagerHyperlinkStyle"><strong> ${ numeroPagina  }
										</strong></span></td>
								</c:when>
								<c:otherwise>
									<td class="PagerOtherPageCells">

										<div class="clickable"
											onclick="$('#pageNumber').val('${ numeroPagina }');
								document.getElementById('lista-attivita-form').submit();">${ numeroPagina  }
										</div>

									</td>
								</c:otherwise>
							</c:choose>

						</c:forEach>


						<c:if test="${ not empty paginaPianificazioni.avanti}">
							<td class="PagerSSCCells  ">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaPianificazioni.avanti }');
								document.getElementById('lista-attivita-form').submit();">Avanti</div>
							</td>
						</c:if>
						<c:if test="${ not empty paginaPianificazioni.ultima}">
							<td class="PagerSSCCells">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaPianificazioni.ultima }');
								document.getElementById('lista-attivita-form').submit();">Ultima</div>
							</td>
						</c:if>

					</tr>
				</tbody>
			</table>
		</c:if>

	</div>
</div>

<form action="lista-pianificazioni" method="get"
	id="lista-attivita-form">
	<input type="hidden" name="sorting" value="true"> <input
		type="hidden" id="sortingValue" name="sortingValue" value="">
	<input type="hidden" id="pageNumber" name="pageNumber" value="">
</form>
