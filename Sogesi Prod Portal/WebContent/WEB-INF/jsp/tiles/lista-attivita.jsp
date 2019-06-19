<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="it.sogesispa.prod.web.utils.ListaAttivitaSessionFilter"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>




<div align="left" class="navFilter">Stabilimento: ${stabilimento}
</div>

<c:set var="listaAttivita" value="${paginaAttivita.objectList}" />

<div style="float: left; width: 86%;">

	<table style="width: 100%;" border="1">
		<thead>
			<tr>
			<th class="manutenzioneTitle">
					<div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.dataPrevistaSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('dataPrevistaAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('dataPrevistaNone');
								document.getElementById('lista-attivita-form').submit();">Data
						prevista</div>

					<div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.dataPrevistaSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('dataPrevistaDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
				</th>
				<th class="manutenzioneTitle">
					<div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.lineaSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
								test="${listaAttivitaSessionFilter.lineaSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
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
								test="${listaAttivitaSessionFilter.macchinaSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
								test="${listaAttivitaSessionFilter.macchinaSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
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
								test="${listaAttivitaSessionFilter.impiantoSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
								test="${listaAttivitaSessionFilter.impiantoSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
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
								test="${listaAttivitaSessionFilter.prioritaSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
								test="${listaAttivitaSessionFilter.prioritaSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
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
								test="${listaAttivitaSessionFilter.pianificazioneSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
						Tipo pianificazione
					</div>

					<div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.pianificazioneSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
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
								test="${listaAttivitaSessionFilter.interventoSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
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
								test="${listaAttivitaSessionFilter.interventoSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('interventoDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle"><div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.eseguitaSort ==  ListaAttivitaSessionFilter.SORT_ASC}">
								<img class="arrowUpRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowUpGreenImg"
									onclick="$('#sortingValue').val('eseguitaAsc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="clickable"
						onclick="$('#sortingValue').val('eseguitaNone');
								document.getElementById('lista-attivita-form').submit();">Eseguita</div>

					<div>
						<c:choose>
							<c:when
								test="${listaAttivitaSessionFilter.eseguitaSort ==  ListaAttivitaSessionFilter.SORT_DESC}">
								<img class="arrowDownRedImg">
							</c:when>
							<c:otherwise>
								<img class="arrowDownGreenImg"
									onclick="$('#sortingValue').val('eseguitaDesc');
								document.getElementById('lista-attivita-form').submit();">
							</c:otherwise>
						</c:choose>
					</div></th>
				<th class="manutenzioneTitle">&nbsp;</th>
				<th class="manutenzioneTitle">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listaAttivita}" var="att">
				<tr>
				<td class="manutenzioneTxt"><fmt:formatDate
							value="${att.dataPrevista}" pattern="dd-MM-yyyy" /></td>
					<td class="manutenzioneTxt">${att.linea}</td>
					<td class="manutenzioneTxt">${att.macchina}</td>
					<td class="manutenzioneTxt">${att.impianto}</td>
					
					<td class="manutenzioneTxt">${att.priorita}</td>
					<td class="manutenzioneTxt">${att.tipoPianificazione}</td>
					<td class="manutenzioneTxt">${att.intervento}</td>
					<td class="manutenzioneTxt"><c:choose>
							<c:when test="${att.eseguita}">
								<img
									src="${pageContext.request.contextPath}/static/images/success.png" />
							</c:when>
							<c:otherwise>
								<img
									src="${pageContext.request.contextPath}/static/images/wrong.png" />
							</c:otherwise>
						</c:choose></td>
					<td class="manutenzioneTxt">
						<form action="lista-attivita" method="POST">
							<input type="hidden" name="idAttivita" id="idAttivita"
								value="${att.idAttivita}" />
							<button name="action" value="paginaEseguiAction" type="submit">${att.eseguita ? 'Visualizza' : 'Esegui'}</button>
						</form>
					</td>
					<td class="manutenzioneTxt">
						<form action="lista-attivita" method="POST">
							<input type="hidden" name="idAttivita" id="idAttivita"
								value="${att.idAttivita}" />
								<c:if test="${empty att.tipoPianificazione or  att.tipoPianificazione == '-'}">
							<button name="action" value="paginaRimandaAction" type="submit"
								${att.eseguita ? 'disabled="disabled"' : ''}>Rimanda</button>
								</c:if>
						</form>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<!-- PAGINATORE -->
	<div style="clear: both">


		<c:if
			test="${ not empty paginaAttivita and paginaAttivita.size > 0  }">

			<table class="PagerContainerTable">
				<tbody>
					<tr>
						<td class="PagerInfoCell">
							<div>Pagina ${ paginaAttivita.pageNumber } di ${ paginaAttivita.totalPages }
							</div>
						</td>

						<c:if test="${ not empty paginaAttivita.prima }">
							<td class="PagerSSCCells ">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaAttivita.prima }');
								document.getElementById('lista-attivita-form').submit();">Prima</div>
							</td>
						</c:if>
						<c:if test="${ not empty paginaAttivita.indietro }">
							<td class="PagerSSCCells">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaAttivita.indietro }');
								document.getElementById('lista-attivita-form').submit();">Indietro</div>

							</td>
						</c:if>

						<c:forEach items="${ paginaAttivita.numeriPagine }"
							var="numeroPagina">
							<c:choose>
								<c:when test="${ numeroPagina ==  paginaAttivita.pageNumber }">
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


						<c:if test="${ not empty paginaAttivita.avanti}">
							<td class="PagerSSCCells  ">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaAttivita.avanti }');
								document.getElementById('lista-attivita-form').submit();">Avanti</div>
							</td>
						</c:if>
						<c:if test="${ not empty paginaAttivita.ultima}">
							<td class="PagerSSCCells">
								<div class="clickable"
									onclick="$('#pageNumber').val('${ paginaAttivita.ultima }');
								document.getElementById('lista-attivita-form').submit();">Ultima</div>
							</td>
						</c:if>

					</tr>
				</tbody>
			</table>
		</c:if>

	</div>
</div>





<div style="float: left; width: 13%;">
	<div style="margin: 10px;margin-bottom: 30px">
	<form action="dettaglio-pianificazione" method="POST">
		<button name="action" value="newAttStraordinaria" type="submit">Nuova
			attivit&agrave; straordinaria</button>
	</form>
	</div>
	<div style="margin: 10px;">
	<form method="get" action="report/stampaPianoSettimanale"
			 target="_new" id="formReport2" target="_new">
			<input
				type="hidden" id="txtdateFrom" name="txtdateFrom"
				value="">
				<input
				type="hidden" id="txtcmbStab" name="txtcmbStab"
				value="">
		<button
				onclick="if($('#cmbStab').val()==''||$('#cmbStab').val()=='999'){alert('Selezionare uno stabilimento');return false;}else{$('#txtcmbStab').val($('#cmbStab').val());$('#txtdateFrom').val($('#dateFrom').val()); document.getElementById('formReport2').submit();}">Stampa piano settimanale</button>
		</form>
	</div>
	<div style="margin: 10px;">
		<form method="get" action="report/stampaGiornoMacchina"
			 target="_new" id="formReport3" target="_new">
			 <input
				type="hidden" id="repdateFrom" name="repdateFrom"
				value="">
				<input
				type="hidden" id="repcmbStab" name="repcmbStab"
				value="">
				<input
				type="hidden" id="replinea" name="replinea"
				value="">
				<input
				type="hidden" id="repcmbMach" name="repcmbMach"
				value="">
		<button
				onclick="$('#replinea').val($('#cmbGroup').val());$('#repdateFrom').val($('#dateFrom').val());$('#repcmbStab').val($('#cmbStab').val());$('#repcmbMach').val($('#cmbMach').val());document.getElementById('formReport3').submit();">Stampa giorno macchina</button>
		</form>
	</div>
	<div style="margin: 10px;">
		<form method="get" action="report/stampaElencoAttivita"
			id="formReport1" target="_new">
			<input type="hidden" name="sortingReport" value="true"> <input
				type="hidden" id="sortingValueReport" name="sortingValueReport"
				value="">
			<button
				onclick="$('#sortingValueReport').val($('#sortingValue').val());
								document.getElementById('formReport1').submit();">Stampa
				elenco</button>

		</form>

	</div>

</div>


<form action="lista-attivita" method="get" id="lista-attivita-form">
	<input type="hidden" name="sorting" value="true"> <input
		type="hidden" id="sortingValue" name="sortingValue" value="">
	<input type="hidden" id="pageNumber" name="pageNumber" value="">
</form>

