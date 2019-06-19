<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${pageContext.request.contextPath}/static/script/jquery-ui.js"></script>

<form action="dettaglio-pianificazione" method="POST" id="formpian">

	Stabilimento: <select name="stabilimento" id="stabilimento" onchange="onChangeGroups();onChangeMachines();onChangeImpianti()" ${empty mode?'disabled="disabled"':""} >

		</select>
	<table style="width: 100%;" border="1">
		<tbody>
			<tr>
					<td class="">Area</td>
					<td class="">
					<select name="linea" id="linea"  onchange="onChangeMachines();onChangeImpianti();">
							
					</select>
					</td>
				</tr>

				<tr>
					<td class="">Macchina</td>
					<td class=""><select name="macchina" onchange="onChangeImpianti();aggiornaRiepilogo();" id="macchina">
					
					</select></td>
				</tr>

				<tr>
					<td class="">Impianto</td>
					<td class=""><select name="impianto" id="impianto">
					</td>
				</tr>
			<tr id="riepilogoContainer" >
				<td class="">Riepilogo</td>
				<td class="">
				<div style="text-align:right"><button type="button" onclick="$('#riepilogoContainer' ).hide()">Nascondi</button>
					<table style="width: 100%; margin: 5px;" border="1" id="riepilogo">
						<thead>
							<tr>
							<th class="manutenzioneTitle">Impianto</th>
								<th class="manutenzioneTitle">Priorit&agrave;</th>
								<th class="manutenzioneTitle">Tipo<br /> pianificazione
								</th>
								<th class="manutenzioneTitle">Intervento</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td class="manutenzioneTxt"></td>
								<td class="manutenzioneTxt"></td>
								<td class="manutenzioneTxt"></td>
								<td class="manutenzioneTxt"></td>
							</tr>
						</tbody>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="">Periodicit&agrave;</td>
				<td class=""><select name="periodicita" id="periodicita">
						<!-- <option value="1"
							${patt.codPeriodicita==1?"selected='selected'":""}>Giornaliera</option>
						<option value="2"
							${patt.codPeriodicita==2?"selected='selected'":""}>Settimanale</option>
						<option value="3"
							${patt.codPeriodicita==3?"selected='selected'":""}>Mensile</option>
						<option value="4"
							${patt.codPeriodicita==4?"selected='selected'":""}>Annuale</option>-->
				</select></td>
			</tr>

			<tr>
				<td class="">Data inizio</td>
				<td class="">
				<fmt:formatDate type="date" 
							value="${patt.dataInizio}" pattern="dd-MM-yyyy" var="fmtDT"/>
							<input type="text" name="data" id="data"
					width="140" value="${fmtDT}"> <script>
						$(function() {
							$("#data").datepicker({
								dateFormat : "dd-mm-yy",
								showAnim : "fold"
							});
						});
					</script></td>
			</tr>

			<tr>
				<td class="">Priorit&agrave;</td>
				<td class=""><select name="priorita" id="priorita">
						<option value="1" ${patt.codPriorita==1?"selected='selected'":""}>Bassa</option>
						<option value="2" ${patt.codPriorita==2?"selected='selected'":""}>Media</option>
						<option value="3" ${patt.codPriorita==3?"selected='selected'":""}>Alta</option>
				</select></td>
			</tr>
			<tr>
				<td class="">Nome</td>
				<td class=""><input type="text" name="nome" id="nome" width="140"
					value="${patt.nome}" size="100"></td>
			</tr>
			<tr>
				<td class="">Descrizione</td>
				<td class=""><textarea rows="3" cols="100" name="descrizione" id="descrizione">${patt.descrizione}</textarea>

				</td>
			</tr>
			<tr>
				<td class="">
				<input type="hidden" name="id" value="${patt.id}" id="id">
				<input type="hidden" name="action" value="annulla" id="action">
					<button type="button" onclick="validate()">Salva</button>
				</td>
				<td class="">
					<button onclick="$('#action').val('annulla');document.getElementById('formpian').submit();" type="button">Annulla</button>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
	<div></div>
</form>
<script>
function validate() {
	var stabilimento = document.getElementById("stabilimento").value;
	var linea = document.getElementById("linea").value;
	var macchina = document.getElementById("macchina").value;
	var impianto = document.getElementById("impianto").value;
	var periodicita = document.getElementById("periodicita").value;
	var datainizio = document.getElementById("data").value;
	var priorita = document.getElementById("priorita").value;
	var nome = document.getElementById("nome").value;
	var descrizione = document.getElementById("descrizione").value;
	if (stabilimento == "") {
		alert("Il campo stabilimento non può essere vuoto!");
		return;
	}
	if (linea == "") {
		alert("Il campo linea non può essere vuoto!");
		return;
	}
	if (macchina == "") {
		alert("Il campo macchina non può essere vuoto!");
		return;
	}
	if (impianto == "") {
		alert("Il campo impianto non può essere vuoto!");
		return;
	}
	if (periodicita == "") {
		alert("Il campo periodicita non può essere vuoto!");
		return;
	}
	if (datainizio == "") {
		alert("Il campo datainizio non può essere vuoto!");
		return;
	}
	if (priorita == "") {
		alert("Il campo priorita non può essere vuoto!");
		return;
	}
	if (nome == "") {
		alert("Il campo nome non può essere vuoto!");
		return;
	}
	if (descrizione == "") {
		alert("Il campo descrizione non può essere vuoto!");
		return;
	}
	$('#action').val('salvaPianificazioneAction');

	document.getElementById("formpian").submit();
}
function listImpianti(data) {
	var mac = document.getElementById("impianto");
	mac.length = 0;
	var comboitem = document.createElement("option");
	comboitem.value = "";
	comboitem.text = "";
	mac.options.add(comboitem);
	for (i = 0; i < data.impianti.length; i++) {
		var machine = data.impianti[i];

		if(parseInt(${user.authLevel}))
		{	
			var comboitem = document.createElement("option");

			comboitem.value = machine.id;
			comboitem.text = machine.name;

			if (comboitem.value == "<c:out value="${patt.idImpianto}" />") {
				comboitem.selected = true;
			}
		}
		mac.options.add(comboitem);
	}
}

function updateImpianti() {
	
	var grpSelected = document.getElementById("linea").value;
	var plantSelected = document.getElementById("stabilimento").value;
	var machinaSelected = document.getElementById("macchina").value;

	
	if (!isNaN(<c:out value="${patt.idLinea}" />)) {
		grpSelected = "<c:out value="${patt.idLinea}" />";
	}
	
	if (!isNaN(<c:out value="${patt.idStabilimento}" />)) {
		plantSelected = "<c:out value="${patt.idStabilimento}" />";
	}
	if (!isNaN(<c:out value="${patt.idMacchina}" />)) {
		machinaSelected = "<c:out value="${patt.idMacchina}" />";
	}
	var data1 = {
		macchina : machinaSelected,
		group : grpSelected,
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getimpianti" />", data1, listImpianti);

}

function onChangeImpianti() {
	var grpSelected = document.getElementById("linea").value;
	var plantSelected = document.getElementById("stabilimento").value;
	var machinaSelected = document.getElementById("macchina").value;
	
	var data1 = {
		macchina : machinaSelected,
		group : grpSelected,
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getimpianti" />", data1, listImpianti);

}
///-----
function listMachines(data) {
	var mac = document.getElementById("macchina");
	mac.length = 0;
	var comboitem = document.createElement("option");
	comboitem.value = "";
	comboitem.text = "";
	mac.options.add(comboitem);
	for (i = 0; i < data.machines.length; i++) {
		var machine = data.machines[i];

		if(parseInt(${user.authLevel}))
		{	
			var comboitem = document.createElement("option");

			comboitem.value = machine.id;
			comboitem.text = machine.name;

			if (comboitem.value == "<c:out value="${patt.idMacchina}" />") {
				comboitem.selected = true;
			}
		}
		mac.options.add(comboitem);
	}
}

function updateMachines() {
	
	var grpSelected = document.getElementById("linea").value;
	var plantSelected = document.getElementById("stabilimento").value;
	
	if (!isNaN(<c:out value="${patt.idLinea}" />)) {
		grpSelected = "<c:out value="${patt.idLinea}" />";
	}
	
	if (!isNaN(<c:out value="${patt.idStabilimento}" />)) {
		plantSelected = "<c:out value="${patt.idStabilimento}" />";
	}
	
	var data1 = {
		group : grpSelected,
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getmacchine" />", data1, listMachines);

}

function onChangeMachines() {
	$( "#riepilogoContainer" ).hide();
	var grpSelected = document.getElementById("linea").value;
	var plantSelected = document.getElementById("stabilimento").value;
	
	var data1 = {
		group : grpSelected,
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getmacchine" />", data1, listMachines);

}
function listGroups(data) {
	var grp = document.getElementById("linea");
	grp.length = 0;
	var comboitem = document.createElement("option");
	comboitem.value = "";
	comboitem.text = "";
	grp.options.add(comboitem);
	for (i = 0; i < data.groups.length; i++) {
		var group = data.groups[i];
		
		if(parseInt(${user.authLevel}))
		{
			var comboitem = document.createElement("option");

			comboitem.value = group.id;
			comboitem.text = group.name;

			if (comboitem.value == "<c:out value="${patt.idLinea}" />") {
				comboitem.selected = true;
			}

			grp.options.add(comboitem);
		}
	}
}

function updateGroups() {
	var plantSelected = document.getElementById("stabilimento").value;

	plantSelected = "<c:out value="${patt.idStabilimento}" />";
	
	var data1 = {
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getlinee" />", data1, listGroups);

}

function onChangeGroups() {
	var plantSelected = document.getElementById("stabilimento").value;

	var data1 = {
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getlinee" />", data1, listGroups);

}
function listStabs(data) {
	var stb = document.getElementById("stabilimento");
	stb.length = 0;
	var comboitem = document.createElement("option");
	comboitem.value = "";
	comboitem.text = "";
	stb.options.add(comboitem);
	
	for (i = 0; i < data.plants.length; i++) {
		var plant = data.plants[i];
			var comboitem = document.createElement("option");
			comboitem.value = plant.id;
			comboitem.text = plant.name;

			if (comboitem.value == "<c:out value="${patt.idStabilimento}" />") {
				comboitem.selected = true;
			}

			stb.options.add(comboitem);

	}
}

function updateStabs() {
	$( "#riepilogoContainer" ).hide();
	$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

}

function elencaRiepilogo(data)
{
	var righe = '';
	for (i = 0; i < data.riepilogo.length; i++) {
		var r = data.riepilogo[i];

		righe = righe+('<tr><td class="manutenzioneTxt">'+r.impianto+'</td><td class="manutenzioneTxt">'+r.priorita+'</td><td class="manutenzioneTxt">'+r.tipopianificazione+'</td><td class="manutenzioneTxt">'+r.intervento+'</td></tr>');

	}
	
	$('#riepilogo > tbody').replaceWith('<tbody>'+righe+'</tbody>');
	$( "#riepilogoContainer" ).show();
}
function aggiornaRiepilogo() {
	var plantSelected = document.getElementById("stabilimento").value;
	var grpSelected = document.getElementById("linea").value;
	var macchinaSelected = document.getElementById("macchina").value;
	var data1 = {
			stabilimento : plantSelected,
			linea: grpSelected,
			macchina:macchinaSelected
		}
	$.getJSON("<c:url value="/getriepilogopianificazione" />",data1, elencaRiepilogo);

}

function aggiornaPeriodicita(data){
	var stb = document.getElementById("periodicita");
	stb.length = 0;
	
	
	for (i = 0; i < data.periodicita.length; i++) {
		var plant = data.periodicita[i];
			var comboitem = document.createElement("option");
			comboitem.value = plant.id;
			comboitem.text = plant.name;

			if (comboitem.value == "<c:out value="${patt.codPeriodicita}" />") {
				comboitem.selected = true;
			}

			stb.options.add(comboitem);

	}
}
function updatePeriodicita(){
	$.getJSON("<c:url value="/getperiodicita" />", aggiornaPeriodicita);
}

$(document).ready(updateStabs);
$(document).ready(updateGroups);
$(document).ready(updateMachines);
$(document).ready(updateImpianti);
$(document).ready(updatePeriodicita);

</script>