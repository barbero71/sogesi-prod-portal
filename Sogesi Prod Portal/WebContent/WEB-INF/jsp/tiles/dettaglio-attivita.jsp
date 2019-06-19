<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script
	src="${pageContext.request.contextPath}/static/script/jquery-ui.js"></script>

<form action="attivita" method="post" id="formpian">

	Stabilimento: <select name="stabilimento" id="stabilimento"
		onchange="onChangeGroups();onChangeMachines();onChangeImpianti()"
		${empty mode?'disabled="disabled"':""}>

	</select>


	<div>
		<table style="width: 100%;" border="1">
			<tbody>
				<tr>
					<td class="">Intervento</td>
					<td class=""><input name="intervento"
						${empty mode?'disabled="disabled"':""} type="text" id="intervento"
						value="${attivita.intervento}" /></td>
				</tr>
				<tr>
					<td class="">Area</td>
					<td class=""><select name="linea" id="linea"
						${empty mode?'disabled="disabled"':""}
						onchange="onChangeMachines();onChangeImpianti();">

					</select></td>
				</tr>

				<tr>
					<td class="">Macchina</td>
					<td class="">
					<select name="macchina"
						${empty mode?'disabled="disabled"':""}
						onchange="onChangeImpianti();" id="macchina">

					</select></td>
				</tr>

				<tr>
					<td class="">Impianto</td>
					<td class=""><select name="impianto"
						${empty mode?'disabled="disabled"':""} id="impianto"></td>
				</tr>
				<tr>
					<td class="">Data prevista</td>
					<td class=""><fmt:formatDate type="date"
							value="${attivita.dataPrevista}" pattern="dd-MM-yyyy" var="fmtDT" />
						<input type="text" name="dataPrevista" id="dataPrevista"
						width="140" ${empty mode?'disabled="disabled"':""}
						value="${fmtDT}"> </input> <script>
							$(function() {
								$("#dataPrevista").datepicker({
									dateFormat : "dd-mm-yy",
									showAnim : "fold"
								});
							});
						</script></td>
				</tr>
				<tr>
					<td class="">Data esecuzione</td>
					<td class=""><fmt:formatDate type="date"
							value="${attivita.dataEsecuzione}" pattern="dd-MM-yyyy"
							var="fmtDTes" /> <input type="text" name="dataEsecuzione"
						id="dataEsecuzione" width="140" value="${fmtDTes}"> <script>
							$(function() {
								$("#dataEsecuzione").datepicker({
									dateFormat : "dd-mm-yy",
									showAnim : "fold"
								});
							});
						</script></td>
				</tr>

				<tr>
					<td class="">Ore impiegate</td>
					<td class=""><input type="text" name="oreImpiegate"
						id="oreImpiegate" value="${attivita.oreImpiegate}"></input></td>
				</tr>


				<tr>
					<td class="">Costi</td>
					<td class=""><input type="text" name="costi" id="costi"
						value="${attivita.costi}"></input></td>
				</tr>

				<tr>
					<td class="">Operatore</td>
					<td class=""><select name="operatore"
						id="operatore">
							<option value="${user.userId}" selected="selected">${user.firstName} &nbsp; ${user.lastName}</option>
					</select></td>
				</tr>

				<tr>
					<td class="">Note</td>
					<td class=""><textarea rows="3" cols="30" name="note"
							id="note">${attivita.note}</textarea></td>
				</tr>

				<tr>
					<td class=""><input type="hidden" id="idAttivita"
						name="idAttivita" value="${attivita.idAttivita}" />
						<input type="hidden" name="action" value="annulla" id="action">
						<button  onclick="validate()"
							type="button" ${attivita.eseguita?'disabled="disabled"':""}>Salva</button>

					</td>
					<td class="">
						<button onclick="$('#action').val('annullaAttivitaAction');document.getElementById('formpian').submit();" type="button">Annulla</button>
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
	var intervento = document.getElementById("intervento").value;
	var dataPrevista = document.getElementById("dataPrevista").value;
	var dataEsecuzione = document.getElementById("dataEsecuzione").value;
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
	if (intervento == "") {
		alert("Il campo intervento non può essere vuoto!");
		return;
	}
	if (dataPrevista == "") {
		alert("Il campo dataPrevista non può essere vuoto!");
		return;
	}
	if (dataEsecuzione!=""){
	var dt1   = parseInt(dataEsecuzione.substring(0,2));
	var mon1  = parseInt(dataEsecuzione.substring(3,5));
	var yr1   = parseInt(dataEsecuzione.substring(6,10));
	var date1 = new Date(yr1, mon1-1, dt1);
	if (date1.getTime() > new Date().getTime()) {
		alert("La data di esecuzione non può essere maggiore di oggi!");
		return;
	}
}
	
	$('#action').val('salvaAttivitaAction');

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

			if (comboitem.value == "<c:out value="${attivita.idImpianto}" />") {
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

	
	if (!isNaN(<c:out value="${attivita.idLinea}" />)) {
		grpSelected = "<c:out value="${attivita.idLinea}" />";
	}
	
	if (!isNaN(<c:out value="${attivita.idStabilimento}" />)) {
		plantSelected = "<c:out value="${attivita.idStabilimento}" />";
	}
	if (!isNaN(<c:out value="${attivita.idMacchina}" />)) {
		machinaSelected = "<c:out value="${attivita.idMacchina}" />";
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

			if (comboitem.value == "<c:out value="${attivita.idMacchina}" />") {
				comboitem.selected = true;
			}
		}
		mac.options.add(comboitem);
	}
}

function updateMachines() {
	
	var grpSelected = document.getElementById("linea").value;
	var plantSelected = document.getElementById("stabilimento").value;
	
	if (!isNaN(<c:out value="${attivita.idLinea}" />)) {
		grpSelected = "<c:out value="${attivita.idLinea}" />";
	}
	
	if (!isNaN(<c:out value="${attivita.idStabilimento}" />)) {
		plantSelected = "<c:out value="${attivita.idStabilimento}" />";
	}
	
	var data1 = {
		group : grpSelected,
		plant : plantSelected
	}

	$.getJSON("<c:url value="/getmacchine" />", data1, listMachines);

}

function onChangeMachines() {
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

			if (comboitem.value == "<c:out value="${attivita.idLinea}" />") {
				comboitem.selected = true;
			}

			grp.options.add(comboitem);
		}
	}
}

function updateGroups() {
	var plantSelected = document.getElementById("stabilimento").value;

	plantSelected = "<c:out value="${attivita.idStabilimento}" />";
	
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

			if (comboitem.value == "<c:out value="${attivita.idStabilimento}" />") {
				comboitem.selected = true;
			}

			stb.options.add(comboitem);

	}
}

function updateStabs() {

	$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

}

$(document).ready(updateStabs);
$(document).ready(updateGroups);
$(document).ready(updateMachines);
$(document).ready(updateImpianti);
</script>