<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script src="${pageContext.request.contextPath}/static/script/jquery-ui.js"></script>


<form  action="lista-pianificazioni" name="filterBox" id="filterBox" method="get"  >
	Stabilimento<br> <select name="cmbStab" id="cmbStab"
		style="width: 160px" class="txtSmall"
		onchange="onChangeGroups();onChangeMachines();">
		<option value="999">Tutti...</option>
	</select> <br> <br> Area<br> <select name="cmbGroup"
		id="cmbGroup" style="width: 160px" class="txtSmall"
		onchange="onChangeMachines()">
		<option value="999">Tutti...</option>

	</select> 
	<br> <br> Macchina<br> <select name="cmbMach"
		id="cmbMach" class="txtSmall" style="width: 160px">
		<option value="999+999">Tutte...</option>

	</select>   
	<br> <br> 
	
	 Periodicit&agrave;<br> <select name="periodicita"
		id="periodicita" class="txtSmall" style="width: 160px">
		<!--option value="0">Tutte...</option>
		<option value="1" ${sessionScope.listaPianificazioniSessionFilter.periodicita == 1 ?"selected='true'":"" }>Giornaliera</option>
		<option value="2" ${sessionScope.listaPianificazioniSessionFilter.periodicita == 2 ?"selected='true'":"" }>Settimanale</option>
		<option value="3" ${sessionScope.listaPianificazioniSessionFilter.periodicita == 3 ?"selected='true'":"" }>Mensile</option>
		<option value="4" ${sessionScope.listaPianificazioniSessionFilter.periodicita == 4 ?"selected='true'":"" }>Annuale</option-->
	</select>   
	
	<br> <br> 
	<input type="button"
		name="btnSubmit" id="idBtnSubmit" value="Seleziona"
		onclick="validate()">
</form>
<script>

	function validate() {

		document.getElementById("filterBox").submit();
	}

	function listMachines(data) {
		var mac = document.getElementById("cmbMach");
		mac.length = 0;

		var comboitem = document.createElement("option");
		comboitem.value = "999+999";
		comboitem.text = "Tutte...";

		mac.options.add(comboitem);

		for (i = 0; i < data.machines.length; i++) {
			var machine = data.machines[i];

			if(parseInt(${user.authLevel}))
			{	
				var comboitem = document.createElement("option");
	
				comboitem.value = machine.id;
				comboitem.text = machine.name;
	
				if (comboitem.value == "<c:out value="${sessionScope.listaPianificazioniSessionFilter.cmbMachString}" />") {
					comboitem.selected = true;
				}
			}
			mac.options.add(comboitem);
		}
	}

	function updateMachines() {
		
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = document.getElementById("cmbStab").value;
		
		if (!isNaN(<c:out value="${sessionScope.listaPianificazioniSessionFilter.groupId}" />)) {
			grpSelected = "<c:out value="${sessionScope.listaPianificazioniSessionFilter.groupId}" />";
		}
		
		if (!isNaN(<c:out value="${sessionScope.listaPianificazioniSessionFilter.stabId}" />)) {
			plantSelected = "<c:out value="${sessionScope.listaPianificazioniSessionFilter.stabId}" />";
		}
		
		var data1 = {
			group : grpSelected,
			plant : plantSelected
		}

		$.getJSON("<c:url value="/gettipimacchina" />", data1, listMachines);

	}
	
	function onChangeMachines() {
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = document.getElementById("cmbStab").value;
		
		var data1 = {
			group : grpSelected,
			plant : plantSelected
		}

		$.getJSON("<c:url value="/gettipimacchina" />", data1, listMachines);

	}

	function listGroups(data) {
		var grp = document.getElementById("cmbGroup");
		grp.length = 0;

		var comboitem = document.createElement("option");
		comboitem.value = "999";
		comboitem.text = "Tutti...";

		grp.options.add(comboitem);

		for (i = 0; i < data.groups.length; i++) {
			var group = data.groups[i];
			
			if(parseInt(${user.authLevel}) )
			{
				var comboitem = document.createElement("option");
	
				comboitem.value = group.id;
				comboitem.text = group.name;
	
				if (comboitem.value == "<c:out value="${sessionScope.listaPianificazioniSessionFilter.groupId}" />") {
					comboitem.selected = true;
				}
	
				grp.options.add(comboitem);
			}
		}
	}

	function updateGroups() {
		var plantSelected = document.getElementById("cmbStab").value;

		if (!isNaN(<c:out value="${sessionScope.listaPianificazioniSessionFilter.stabId}" />)) {
			plantSelected = "<c:out value="${sessionScope.listaPianificazioniSessionFilter.stabId}" />";
		}
		
		var data1 = {
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getlinee" />", data1, listGroups);

	}
	
	function onChangeGroups() {
		var plantSelected = document.getElementById("cmbStab").value;

		var data1 = {
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getlinee" />", data1, listGroups);

	}

	function listStabs(data) {
		var stb = document.getElementById("cmbStab");
		stb.length = 0;

		var comboitem = document.createElement("option");
		comboitem.value = "999";
		comboitem.text = "Tutti...";

		stb.options.add(comboitem);

		for (i = 0; i < data.plants.length; i++) {
			var plant = data.plants[i];
			
			if(parseInt(${user.authLevel}) & plant.id)
			{
				var comboitem = document.createElement("option");
	
				comboitem.value = plant.id;
				comboitem.text = plant.name;
	
				if (comboitem.value == "<c:out value="${sessionScope.listaPianificazioniSessionFilter.stabId}" />") {
					comboitem.selected = true;
				}
	
				stb.options.add(comboitem);
			}
		}
	}

	function updateStabs() {

		$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

	}
	function aggiornaPeriodicita(data){
		var stb = document.getElementById("periodicita");
		stb.length = 0;
		var comboitem1 = document.createElement("option");
		comboitem1.value = 0;
		comboitem1.text = "Tutte...";

		if (comboitem1.value == "<c:out value="${sessionScope.listaPianificazioniSessionFilter.periodicita}" />") {
			comboitem1.selected = true;
		}

		stb.options.add(comboitem1);
		
		for (i = 0; i < data.periodicita.length; i++) {
			var plant = data.periodicita[i];
				var comboitem = document.createElement("option");
				comboitem.value = plant.id;
				comboitem.text = plant.name;

				if (comboitem.value == "<c:out value="${sessionScope.listaPianificazioniSessionFilter.periodicita}" />") {
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
	$(document).ready(updatePeriodicita);
	
	$(document).ready(function() {
		$("#detList").autoNumeric('init', {
			aSep : '.',
			mDec : '0',
			vMax : '99999999999999999999999999'
		});
	});
	
</script>