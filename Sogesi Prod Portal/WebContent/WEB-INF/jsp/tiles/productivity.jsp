<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${stabPon > 0}">
<div style="width: 100%; float: left;">&nbsp;</div>
<span class="titleProd">Stabilimento di Ponsacco</span><br/>
<table border="1">
  <tr>
    <th style="text-align:left" width="200px">Produzione</th>
    <th style="text-align:right" width="80px">kg lavorati</th>
    <th style="text-align:right" width="60px">%</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">Ore totali</th>
    <th style="text-align:right" width="60px">% Tot.</th>
    <th style="text-align:right" width="80px">Ore Dip</th>
    <th style="text-align:right" width="60px">% Dip</th>
    <th style="text-align:right" width="80px">Ore Som</th>
    <th style="text-align:right" width="60px">% Som</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">kg/ora</th>
  </tr>
  <tr>
    <td>Biancheria piana</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgPiana}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percPiana}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.orePiana}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.orePianaDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.orePianaSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>

  </tr>
  <tr>
    <td>Biancheria sagomata cotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgCotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percCotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePon.oreCotone}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePon.oreCotoneDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePon.oreCotoneSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
  </tr>
  <tr>
    <td>Biancheria sagomata policotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgPolicotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percPolicotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
    <!-- <td style="text-align:right">--</td> -->
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
  </tr>
  <tr>
    <td>Indumenti H.V.</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgHv}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percHv}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreHv}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreHvDip
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreHvSom
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>Materassi-Cuscini</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percMaterassi}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreMaterassiDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
     <td style="text-align:right"><fmt:formatNumber value="${orePon.oreMaterassiSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>TTR (Lavato)</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePon.kgTtr}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePon.percTtr}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreTtr}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreTtrDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreTtrSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
<!-- INIZIO RIGHE DA COPIARE -->
  <tr>
    <td>&nbsp;</td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
  </tr>
  <tr>
    <td>Ore non ripartite&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<!--  Elemento che fa apparire il pop-up -->
			<button id="pon-id" class="btnDetails">Dettaglio</button>
		<!-- Contenuto del pop-up -->
			<div id="pon_dettaglio_popup" class="dettaglio_popup" align="center" style="display:none;width:600px;">
				<span class="titleProd">Stabilimento di Ponsacco</span>
					<table border="1" style="width:500px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "right" width="70px">Ore Dip.</th>
							<th align="right" width="70px">Ore Som.</th>
						</tr>
						<tr>
							<td>100050001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td align="right"><fmt:formatNumber value="${orePon.oreDipCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePon.oreSomCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100050007</td>
							<td>PIEGATURA MANUALE</td>
							<td align="right"><fmt:formatNumber value="${orePon.oreDipCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePon.oreSomCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100050014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td align="right"><fmt:formatNumber value="${orePon.oreDipCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePon.oreSomCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100050015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td align="right"><fmt:formatNumber value="${orePon.oreDipCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePon.oreSomCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100050016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td align="right"><fmt:formatNumber value="${orePon.oreDipCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePon.oreSomCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td class="tdTotals">TOTALE</td>
							<td class="tdTotals">&nbsp;</td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${orePon.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${orePon.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#pon-id').click(function(){
  						$('#pon_dettaglio_popup').bPopup({
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
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreGen}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePon.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><button id="pon-leg" class="btnDetails">Legenda Ore</button>
    				<div id="pon_legenda_popup" class="dettaglio_popup" align="center" style="display:none;width:800px;">
				<span class="titleProd">LEGENDA ORE - Stabilimento di Ponsacco</span>
					<table border="1" style="width:700px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "left" width="170px">Gruppo</th>
						</tr>
						<tr>
							<td>100050001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050002</td>
							<td>CONTEGGIO E CERNITA SAGOMATA</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100050004</td>
							<td>STIRO PIANA E CONFEZIONAMENTO</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100050005</td>
							<td>STIRO TRADIZIONALE SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100050006</td>
							<td>STIRO A TUNNEL SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100050007</td>
							<td>PIEGATURA MANUALE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050009</td>
							<td>SFODERATURA E RIFODERATURA MATERASSI</td>
							<td>MATERASSI E CUSCINI</td>
						</tr>
						<tr>
							<td>100050014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050017</td>
							<td>ASSEGNAZIONE E SOSTITUZ. BIANCH. SAGOM.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#pon-leg').click(function(){
  						$('#pon_legenda_popup').bPopup({
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
<!-- FINE RIGHE DA COPIARE -->
  <tr>
    <td class="tdTotals">TOTALE</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber value="${orePon.kgLavorati}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePon.oreTotali}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePon.oreTotaliDip}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePon.oreTotaliSom}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePon.prodIndex}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
  </tr>
</table>
<br />
</c:if>
<c:if test="${stabPsg > 0}">
<div style="width: 100%; float: left;">&nbsp;</div>
<span class="titleProd">Stabilimento di Ponte San Giovanni</span><br/>
<table border="1">
  <tr>
    <th style="text-align:left" width="200px">Produzione</th>
    <th style="text-align:right" width="80px">kg lavorati</th>
    <th style="text-align:right" width="60px">%</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">Ore totali</th>
    <th style="text-align:right" width="60px">% Tot.</th>
    <th style="text-align:right" width="80px">Ore Dip</th>
    <th style="text-align:right" width="60px">% Dip</th>
    <th style="text-align:right" width="80px">Ore Som</th>
    <th style="text-align:right" width="60px">% Som</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">kg/ora</th>
  </tr>
  <tr>
    <td>Biancheria piana</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgPiana}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percPiana}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.orePiana}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.orePianaDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.orePianaSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>

  </tr>
  <tr>
    <td>Biancheria sagomata cotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgCotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percCotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePsg.oreCotone}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePsg.oreCotoneDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${orePsg.oreCotoneSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
  </tr>
  <tr>
    <td>Biancheria sagomata policotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgPolicotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percPolicotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
    <!-- <td style="text-align:right">--</td> -->
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
  </tr>
  <tr>
    <td>Indumenti H.V.</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgHv}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percHv}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreHv}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreHvDip
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreHvSom
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>Materassi-Cuscini</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percMaterassi}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreMaterassiDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
     <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreMaterassiSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>TTR (Lavato)</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.kgTtr}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${orePsg.percTtr}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreTtr}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreTtrDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreTtrSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
<!-- INIZIO RIGHE DA COPIARE -->
  <tr>
    <td>&nbsp;</td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
  </tr>
  <tr>
    <td>Ore non ripartite&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<!--  Elemento che fa apparire il pop-up -->
			<button id="psg-id" class="btnDetails">Dettaglio</button>
		<!-- Contenuto del pop-up -->
			<div id="psg_dettaglio_popup" class="dettaglio_popup" align="center" style="display:none;width:600px;">
				<span class="titleProd">Stabilimento di Ponte San Giovanni</span>
					<table border="1" style="width:500px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "right" width="70px">Ore Dip.</th>
							<th align="right" width="70px">Ore Som.</th>
						</tr>
						<tr>
							<td>100040001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040007</td>
							<td>PIEGATURA MANUALE</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040013</td>
							<td>MAGAZZINO</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0013}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0013}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100040027</td>
							<td>LAVAGGIO A SECCO</td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreDipCdc0027}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${orePsg.oreSomCdc0027}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td class="tdTotals">TOTALE</td>
							<td class="tdTotals">&nbsp;</td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${orePsg.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${orePsg.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#psg-id').click(function(){
  						$('#psg_dettaglio_popup').bPopup({
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
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreGen}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${orePsg.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><button id="psg-leg" class="btnDetails">Legenda Ore</button>
    				<div id="psg_legenda_popup" class="dettaglio_popup" align="center" style="display:none;width:800px;">
				<span class="titleProd">LEGENDA ORE - Stabilimento di Ponte San Giovanni</span>
					<table border="1" style="width:700px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "left" width="170px">Gruppo</th>
						</tr>
						<tr>
							<td>100040001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040003</td>
							<td>CONTEGGIO E CERNITA B. PIANA</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100040004</td>
							<td>STIRO PIANA E CONFEZIONAMENTO</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100040007</td>
							<td>PIEGATURA MANUALE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050013</td>
							<td>MAGAZZINO</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100050027</td>
							<td>LAVAGGIO A SECCO</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#psg-leg').click(function(){
  						$('#psg_legenda_popup').bPopup({
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
<!-- FINE RIGHE DA COPIARE -->
  <tr>
    <td class="tdTotals">TOTALE</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber value="${orePsg.kgLavorati}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.oreTotali}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.oreTotaliDip}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.oreTotaliSom}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${orePsg.prodIndex}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
  </tr>
</table>
<br />
</c:if>
<c:if test="${stabTer > 0}">
<div style="width: 100%; float: left;">&nbsp;</div>
<span class="titleProd">Stabilimento di Stroncone</span><br/>
<table border="1">
  <tr>
    <th style="text-align:left" width="200px">Produzione</th>
    <th style="text-align:right" width="80px">kg lavorati</th>
    <th style="text-align:right" width="60px">%</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">Ore totali</th>
    <th style="text-align:right" width="60px">% Tot.</th>
    <th style="text-align:right" width="80px">Ore Dip</th>
    <th style="text-align:right" width="60px">% Dip</th>
    <th style="text-align:right" width="80px">Ore Som</th>
    <th style="text-align:right" width="60px">% Som</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">kg/ora</th>
  </tr>
  <tr>
    <td>Biancheria piana</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgPiana}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percPiana}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.orePiana}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.orePianaDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.orePianaSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>

  </tr>
  <tr>
    <td>Biancheria sagomata cotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgCotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percCotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreTer.oreCotone}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreTer.oreCotoneDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreTer.oreCotoneSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
  </tr>
  <tr>
    <td>Biancheria sagomata policotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgPolicotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percPolicotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
    <!-- <td style="text-align:right">--</td> -->
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
  </tr>
  <tr>
    <td>Indumenti H.V.</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgHv}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percHv}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreHv}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreHvDip
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreHvSom
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>Materassi-Cuscini</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percMaterassi}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreMaterassiDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
     <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreMaterassiSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>TTR (Lavato)</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.kgTtr}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreTer.percTtr}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreTtr}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreTtrDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreTtrSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <!-- INIZIO RIGHE DA COPIARE -->
  <tr>
    <td>&nbsp;</td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
  </tr>
  <tr>
    <td>Ore non ripartite&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<!--  Elemento che fa apparire il pop-up -->
			<button id="ter-id" class="btnDetails">Dettaglio</button>
		<!-- Contenuto del pop-up -->
			<div id="ter_dettaglio_popup" class="dettaglio_popup" align="center" style="display:none;width:600px;">
				<span class="titleProd">Stabilimento di Stroncone</span>
					<table border="1" style="width:500px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "right" width="70px">Ore Dip.</th>
							<th align="right" width="70px">Ore Som.</th>
						</tr>
						<tr>
							<td>100020001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100020007</td>
							<td>PIEGATURA MANUALE</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100020008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100020014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100020015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100020016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreDipCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreTer.oreSomCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td class="tdTotals">TOTALE</td>
							<td class="tdTotals">&nbsp;</td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreTer.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreTer.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#ter-id').click(function(){
  						$('#ter_dettaglio_popup').bPopup({
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
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreGen}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreTer.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><button id="ter-leg" class="btnDetails">Legenda Ore</button>
    				<div id="ter_legenda_popup" class="dettaglio_popup" align="center" style="display:none;width:800px;">
				<span class="titleProd">LEGENDA ORE - Stabilimento di Stroncone</span>
					<table border="1" style="width:700px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "left" width="170px">Gruppo</th>
						</tr>
						<tr>
							<td>100020001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020002</td>
							<td>CONTEGGIO E CERNITA SAGOMATA</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100020003</td>
							<td>CONTEGGIO E CERNITA B. PIANA</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100020004</td>
							<td>STIRO PIANA E CONFEZIONAMENTO</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100020005</td>
							<td>STIRO TRADIZIONALE SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100020006</td>
							<td>STIRO A TUNNEL SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100020007</td>
							<td>PIEGATURA MANUALE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100020017</td>
							<td>ASSEGNAZIONE E SOSTITUZ. BIANCH. SAGOM.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100020018</td>
							<td>CONTROLLO ALTA VISIBILITA'</td>
							<td>INDUMENTI H.V.</td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#ter-leg').click(function(){
  						$('#ter_legenda_popup').bPopup({
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
<!-- FINE RIGHE DA COPIARE -->
  <tr>
    <td class="tdTotals">TOTALE</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber value="${oreTer.kgLavorati}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.oreTotali}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.oreTotaliDip}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.oreTotaliSom}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreTer.prodIndex}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
  </tr>
</table>
<br />
</c:if>
<c:if test="${stabBol > 0}">
<div style="width: 100%; float: left;">&nbsp;</div>
<span class="titleProd">Stabilimento di Bologna</span><br/>
<table border="1">
  <tr>
    <th style="text-align:left" width="200px">Produzione</th>
    <th style="text-align:right" width="80px">kg lavorati</th>
    <th style="text-align:right" width="60px">%</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">Ore totali</th>
    <th style="text-align:right" width="60px">% Tot.</th>
    <th style="text-align:right" width="80px">Ore Dip</th>
    <th style="text-align:right" width="60px">% Dip</th>
    <th style="text-align:right" width="80px">Ore Som</th>
    <th style="text-align:right" width="60px">% Som</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">kg/ora</th>
  </tr>
  <tr>
    <td>Biancheria piana</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgPiana}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percPiana}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.orePiana}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.orePianaDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.orePianaSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>

  </tr>
  <tr>
    <td>Biancheria sagomata cotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgCotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percCotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreBol.oreCotone}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreBol.oreCotoneDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreBol.oreCotoneSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
  </tr>
  <tr>
    <td>Biancheria sagomata policotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgPolicotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percPolicotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
    <!-- <td style="text-align:right">--</td> -->
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
  </tr>
  <tr>
    <td>Indumenti H.V.</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgHv}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percHv}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreHv}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreHvDip
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreHvSom
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>Materassi-Cuscini</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percMaterassi}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreMaterassiDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
     <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreMaterassiSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>TTR (Lavato)</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.kgTtr}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreBol.percTtr}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreTtr}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreTtrDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreTtrSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
<!-- INIZIO RIGHE DA COPIARE -->
  <tr>
    <td>&nbsp;</td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
  </tr>
  <tr>
    <td>Ore non ripartite&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<!--  Elemento che fa apparire il pop-up -->
			<button id="bol-id" class="btnDetails">Dettaglio</button>
		<!-- Contenuto del pop-up -->
			<div id="bol_dettaglio_popup" class="dettaglio_popup" align="center" style="display:none;width:600px;">
				<span class="titleProd">Stabilimento di Bologna</span>
					<table border="1" style="width:500px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "right" width="70px">Ore Dip.</th>
							<th align="right" width="70px">Ore Som.</th>
						</tr>
						<tr>
							<td>100030014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreDipCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreSomCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100030015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreDipCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreSomCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100030016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreDipCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreBol.oreSomCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td class="tdTotals">TOTALE</td>
							<td class="tdTotals">&nbsp;</td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreBol.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreBol.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#bol-id').click(function(){
  						$('#bol_dettaglio_popup').bPopup({
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
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreGen}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreBol.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
        <td style="text-align:right"><button id="bol-leg" class="btnDetails">Legenda Ore</button>
    				<div id="bol_legenda_popup" class="dettaglio_popup" align="center" style="display:none;width:800px;">
				<span class="titleProd">LEGENDA ORE - Stabilimento di Bologna</span>
					<table border="1" style="width:700px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "left" width="170px">Gruppo</th>
						</tr>
						<tr>
							<td>100040004</td>
							<td>STIRO PIANA E CONFEZIONAMENTO</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100040005</td>
							<td>STIRO TRADIZIONALE SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100040008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040012</td>
							<td>RIPARAZIONE E RAMMENDO CAPI</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100040014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100040017</td>
							<td>ASSEGNAZIONE E SOSTITUZ. BIANCH. SAGOM.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100040018</td>
							<td>CONTROLLO ALTA VISIBILITA'</td>
							<td>INDUMENTI H.V.</td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#bol-leg').click(function(){
  						$('#bol_legenda_popup').bPopup({
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
<!-- FINE RIGHE DA COPIARE -->
  <tr>
    <td class="tdTotals">TOTALE</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber value="${oreBol.kgLavorati}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.oreTotali}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.oreTotaliDip}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.oreTotaliSom}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreBol.prodIndex}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
  </tr>
</table>
<br />
</c:if>
<c:if test="${stabCan > 0}">
<div style="width: 100%; float: left;">&nbsp;</div>
<span class="titleProd">Stabilimento di Cannara</span><br/>
<table border="1">
  <tr>
    <th style="text-align:left" width="200px">Produzione</th>
    <th style="text-align:right" width="80px">kg lavorati</th>
    <th style="text-align:right" width="60px">%</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">Ore totali</th>
    <th style="text-align:right" width="60px">% Tot.</th>
    <th style="text-align:right" width="80px">Ore Dip</th>
    <th style="text-align:right" width="60px">% Dip</th>
    <th style="text-align:right" width="80px">Ore Som</th>
    <th style="text-align:right" width="60px">% Som</th>
    <th style="text-align:right;border-top:0px;border-bottom:0px" width="20px">&nbsp;</th>
    <th style="text-align:right" width="80px">kg/ora</th>
  </tr>
  <tr>
    <td>Biancheria piana</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgPiana}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percPiana}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.orePiana}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.orePianaDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.orePianaSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>

  </tr>
  <tr>
    <td>Biancheria sagomata cotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgCotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percCotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreCan.oreCotone}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreCan.oreCotoneDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right"><fmt:formatNumber value="${oreCan.oreCotoneSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td rowspan="2" style="vertical-align:middle;text-align:right">--</td>
  </tr>
  <tr>
    <td>Biancheria sagomata policotone</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgPolicotone}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percPolicotone}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
    <!-- <td style="text-align:right">--</td> -->
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <!-- <td style="text-align:right">--</td> -->
  </tr>
  <tr>
    <td>Indumenti H.V.</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgHv}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percHv}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreHv}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreHvDip
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreHvSom
    }" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>Materassi-Cuscini</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percMaterassi}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreMaterassi}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreMaterassiDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
     <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreMaterassiSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>TTR (Lavato)</td>
    <td style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.kgTtr}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td style="text-align:right">
    	<fmt:formatNumber value="${oreCan.percTtr}" groupingUsed="true" type="percent" />
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreTtr}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreTtrDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreTtrSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">--</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right">
    	&nbsp;
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right">&nbsp;</td>
  </tr>
  <tr>
    <td>Ore non ripartite&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<!--  Elemento che fa apparire il pop-up -->
			<button id="can-id" class="btnDetails">Dettaglio</button>
		<!-- Contenuto del pop-up -->
			<div id="can_dettaglio_popup" class="dettaglio_popup" align="center" style="display:none;width:600px;">
				<span class="titleProd">Stabilimento di Cannara</span>
					<table border="1" style="width:500px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "right" width="70px">Ore Dip.</th>
							<th align="right" width="70px">Ore Som.</th>
						</tr>
						<tr>
							<td>100010001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td align="right" ><fmt:formatNumber value="${oreCan.oreDipCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0001}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100010007</td>
							<td>PIEGATURA MANUALE</td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreDipCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0007}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100010008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreDipCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0008}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100010014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreDipCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0014}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100010015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreDipCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0015}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td>100010016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreDipCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td align="right"><fmt:formatNumber value="${oreCan.oreSomCdc0016}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
						<tr>
							<td class="tdTotals">TOTALE</td>
							<td class="tdTotals">&nbsp;</td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreCan.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
							<td class="tdTotals" align="right"><fmt:formatNumber value="${oreCan.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#can-id').click(function(){
  						$('#can_dettaglio_popup').bPopup({
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
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right">
    	--
    </td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreGen}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreGenDip}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right"><fmt:formatNumber value="${oreCan.oreGenSom}" groupingUsed="true" type="number" maxFractionDigits="2" /></td>
    <td style="text-align:right">--</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
            <td style="text-align:right"><button id="can-leg" class="btnDetails">Legenda Ore</button>
    				<div id="can_legenda_popup" class="dettaglio_popup" align="center" style="display:none;width:800px;">
				<span class="titleProd">LEGENDA ORE - Stabilimento di Cannara</span>
					<table border="1" style="width:700px;">
						<tr>					
							<th align="left">Id CdC</th>
							<th align="left">Descrizione</th>
							<th align = "left" width="170px">Gruppo</th>
						</tr>
						<tr>
							<td>100010001</td>
							<td>LAVAGGIO CON LAVACONTINUA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010002</td>
							<td>CONTEGGIO E CERNITA SAGOMATA</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100010003</td>
							<td>CONTEGGIO E CERNITA B. PIANA</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100010004</td>
							<td>STIRO PIANA E CONFEZIONAMENTO</td>
							<td>BIANCHERIA PIANA</td>
						</tr>
						<tr>
							<td>100010005</td>
							<td>STIRO TRADIZIONALE SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100010006</td>
							<td>STIRO A TUNNEL SAGOMATA E CONFEZ.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100010007</td>
							<td>PIEGATURA MANUALE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010008</td>
							<td>LAVAGGIO CON LAVACENTRIFUGA</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010009</td>
							<td>SFODERATURA E RIFODERATURA MATERASSI</td>
							<td>MATERASSI E CUSCINI</td>
						</tr>
						<tr>
							<td>100010012</td>
							<td>RIPARAZIONE E RAMMENDO CAPI</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100010014</td>
							<td>SERVIZI GENERALI DI MANUTENZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010015</td>
							<td>BOLLETTAZIONE E SPEDIZIONE</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010016</td>
							<td>SERVIZI E COSTI GENERALI</td>
							<td>ORE NON RIPARTITE</td>
						</tr>
						<tr>
							<td>100010017</td>
							<td>ASSEGNAZIONE E SOSTITUZ. BIANCH. SAGOM.</td>
							<td>BIANCHERIA SAGOMATA</td>
						</tr>
						<tr>
							<td>100010018</td>
							<td>CONTROLLO ALTA VISIBILITA'</td>
							<td>INDUMENTI H.V.</td>
						</tr>
					</table>
			</div>
		<!-- SCRIPT per la gestione del popup -->
			<script>
				$(function(){
					$('#can-leg').click(function(){
  						$('#can_legenda_popup').bPopup({
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
  <tr>
    <td class="tdTotals">TOTALE</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber value="${oreCan.kgLavorati}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.oreTotali}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.oreTotaliDip}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.oreTotaliSom}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
    <td class="tdTotals" style="text-align:right">&nbsp;</td>
    <td style="text-align:right;border-top:0px;border-bottom:0px">&nbsp;</td>
    <td class="tdTotals" style="text-align:right">
    	<fmt:formatNumber	value="${oreCan.prodIndex}" groupingUsed="true" type="number" maxFractionDigits="2" />
    </td>
  </tr>
</table>
<br />
</c:if>
<br />