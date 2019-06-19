<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<form action="lista-attivita" method="get" id="filterBox" name="filterBox">
	Stabilimento <br> <select name="cmbStab" id="cmbStab"
		style="width: 160px" class="txtSmall"
		onchange="onChangeGroups();onChangeMachines();">
		<option value="999">Tutti...</option>
	</select> <br> <br> Area <br> <select name="cmbGroup"
		id="cmbGroup" style="width: 160px" class="txtSmall"
		onchange="onChangeMachines()">
		<option value="999">Tutti...</option>

	</select> <br> <br> Macchina<br> <select name="cmbMach"
		id="cmbMach" class="txtSmall" style="width: 160px">
		<option value="999+999">Tutte...</option>

	</select> <br> <br> Data inizio 
 
		 <br>
  		 
  <script>
  $(function() {
    $( "#dateFrom" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  $(function() {
    $( "#dateTo" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  </script>
  <fmt:formatDate type="date" value="${sessionScope.listaAttivitaSessionFilter.txtDateFrom}" pattern="dd-MM-yyyy" var="fmtDTfrom" />
		 <input type="text" name="dateFrom" id="dateFrom" width="140" value="${fmtDTfrom}" required>
	<br><br> Data fine <br> 
<fmt:formatDate type="date"
							value="${sessionScope.listaAttivitaSessionFilter.txtDateTo}" pattern="dd-MM-yyyy" var="fmtDTto" />
	  <input type="text" name="dateTo" id="dateTo" width="140" value="${fmtDTto}" required>
		
		<br><br>
		Eseguite:
		SI<input type="radio" value="SI" name="eseguite" ${sessionScope.listaAttivitaSessionFilter.eseguite=='SI'?'checked="true"':""} />
		NO<input type="radio" value="NO" name="eseguite" ${sessionScope.listaAttivitaSessionFilter.eseguite=='NO'?'checked="true"':""} />
		Tutte<input type="radio" value="ALL" name="eseguite" ${sessionScope.listaAttivitaSessionFilter.eseguite=='ALL'?'checked="true"':""} />
		<br> <br> Elementi per pagina:
		<select name="pageSize"
		id="pageSize" class="txtSmall" style="width: 60px">
		<option value="10" ${sessionScope.listaAttivitaSessionFilter.pageSize==10?'selected="true"':""}>10</option>
<option value="20" ${sessionScope.listaAttivitaSessionFilter.pageSize==20?'selected="true"':""}>20</option>
<option value="50" ${sessionScope.listaAttivitaSessionFilter.pageSize==50?'selected="true"':""}>50</option>
<option value="100" ${sessionScope.listaAttivitaSessionFilter.pageSize==100?'selected="true"':""}>100</option>
<option value="150" ${sessionScope.listaAttivitaSessionFilter.pageSize==150?'selected="true"':""}>150</option>
<option value="200" ${sessionScope.listaAttivitaSessionFilter.pageSize==200?'selected="true"':""}>200</option>
	</select>
		<br> <br> <input type="button"
		name="btnSubmit" id="idBtnSubmit" value="Seleziona"
		onclick="validate()">
</form>
<script>

	function validate() {
		var startDt = document.getElementById("dateFrom").value;
		var endDt = document.getElementById("dateTo").value;

		if (new Date(startDt).getTime() > new Date(endDt).getTime()) {
			alert("La data di fine  non deve essere MINORE della data di inizio!");
			return;
		}/* else if (startDt == "" || endDt == "") {
			alert("Il campo data non può essere vuoto!");
			return;
		}*/

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
				if (comboitem.value == "<c:out value="${sessionScope.listaAttivitaSessionFilter.cmbMachString}" />") {
					comboitem.selected = true;
				}
			}
			mac.options.add(comboitem);
		}
	}

	function updateMachines() {
		
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = document.getElementById("cmbStab").value;
		
		if (!isNaN(${sessionScope.listaAttivitaSessionFilter.groupId})) {
			grpSelected = "${sessionScope.listaAttivitaSessionFilter.groupId}";
		}
		
		if (!isNaN(${sessionScope.listaAttivitaSessionFilter.stabId})) {
			plantSelected = "${sessionScope.listaAttivitaSessionFilter.stabId}";
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
			
			if(parseInt(${user.authLevel}))
			{
				var comboitem = document.createElement("option");
	
				comboitem.value = group.id;
				comboitem.text = group.name;
	
				if (comboitem.value == "${sessionScope.listaAttivitaSessionFilter.groupId}") {
					comboitem.selected = true;
				}
	
				grp.options.add(comboitem);
			}
		}
	}

	function updateGroups() {
		var plantSelected = document.getElementById("cmbStab").value;

		if (!isNaN(${sessionScope.listaAttivitaSessionFilter.stabId})) {
			plantSelected = "${sessionScope.listaAttivitaSessionFilter.stabId}";
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
	//alert(${sessionScope.listaAttivitaSessionFilter.stabId});
				if (comboitem.value == "${sessionScope.listaAttivitaSessionFilter.stabId}") {
					comboitem.selected = true;
				}
	
				stb.options.add(comboitem);
			}
		}
	}

	function updateStabs() {

		$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

	}

	$(document).ready(updateStabs);
	$(document).ready(updateGroups);
	$(document).ready(updateMachines);
	
	$(document).ready(function() {
		$("#detList").autoNumeric('init', {
			aSep : '.',
			mDec : '0',
			vMax : '99999999999999999999999999'
		});
	});
	
</script>