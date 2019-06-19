<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="ecolab" method="post" id="filterBox" name="filterBox">
	Stabilimento
	<div id="stabilimenti" style="margin-left:10px"><div>
      
      </div>
    </div>
    
    <br> <!-- select name="cmbStab" id="cmbStab"
		style="width: 160px" class="txtSmall"
		onchange="onChangeGroups();onChangeMachines();">
		<option value="999">Tutti...</option>
	</select-->
	 <br> <br> Gruppo<br> <select name="cmbGroup"
		id="cmbGroup" style="width: 160px" class="txtSmall"
		onchange="onChangeMachines()">
		<option value="999">Tutti...</option>

	</select> <br> <br> Macchina<br> <select name="cmbMach"
		id="cmbMach" class="txtSmall" style="width: 160px">
		<option value="999+999">Tutte...</option>

	</select> <br> <br> Inizio periodo
	
		 <br>
  		
  <script>
  $(function() {
    $( "#dateFrom" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  $(function() {
    $( "#dateTo" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  </script>
  <fmt:formatDate type="date"
		value="${sessionScope.ecolabSessionFilter.txtDateFrom}"
		pattern="dd-MM-yyyy" var="fmtDTfrom" />
		 <input type="text" name="dateFrom" id="dateFrom" width="140" value="${fmtDTfrom}" required>
	<br><br> Fine periodo<br> 
	<fmt:formatDate type="date"
		value="${sessionScope.ecolabSessionFilter.txtDateTo}"
		pattern="dd-MM-yyyy" var="fmtDTto" />
	  <input type="text" name="dateTo" id="dateTo" width="140" value="${fmtDTto}" required>
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
		} else if (startDt == "" || endDt == "") {
			alert("Il campo data non può essere vuoto!");
			return;
		}

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

			if(parseInt(${user.authLevel}) & machine.stabId)
			{	
				var comboitem = document.createElement("option");
	
				comboitem.value = machine.groupId + "\+" + machine.machineId;
				comboitem.text = machine.machineName;
	
				if (comboitem.value == "<c:out value="${param.cmbMach}" />") {
					comboitem.selected = true;
				}
			}
			mac.options.add(comboitem);
		}
	}

	function updateMachines() {
		
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = "";// document.getElementById("cmbStab").value;
		$('[name="stab"]:checked').each(function() {
			plantSelected+=$(this).val()+", ";
		     });
		plantSelected= plantSelected==""?'999':plantSelected;
		if (!isNaN(<c:out value="${param.cmbGroup}" />)) {
			grpSelected = "<c:out value="${param.cmbGroup}" />";
		}
		
		/*if (!isNaN(<c:out value="${param.cmbStab}" />)) {
			plantSelected = "<c:out value="${param.cmbStab}" />";
		}*/
		
		var data1 = {
			group : grpSelected,
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getmachines" />", data1, listMachines);

	}
	
	function onChangeMachines() {
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = "";// document.getElementById("cmbStab").value;
		$('[name="stab"]:checked').each(function() {
			plantSelected+=$(this).val()+", ";
		     });
		plantSelected= plantSelected==""?'999':plantSelected;
		var data1 = {
			group : grpSelected,
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getmachines" />", data1, listMachines);

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
			
			if(parseInt(${user.authLevel}) & group.stabId)
			{
				var comboitem = document.createElement("option");
	
				comboitem.value = group.groupId;
				comboitem.text = group.groupName;
	
				if (comboitem.value == "<c:out value="${param.cmbGroup}" />") {
					comboitem.selected = true;
				}
	
				grp.options.add(comboitem);
			}
		}
	}

	function updateGroups() {
		var plantSelected = "";// document.getElementById("cmbStab").value;
		$('[name="stab"]:checked').each(function() {
			plantSelected+=$(this).val()+", ";
		     });
		/*if (!isNaN(<c:out value="${param.cmbStab}" />)) {
			plantSelected = "<c:out value="${param.cmbStab}" />";
		}*/
		plantSelected= plantSelected==""?'999':plantSelected;
		var data1 = {
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getgroups" />", data1, listGroups);

	}
	
	function onChangeGroups() {
		var plantSelected = "";// document.getElementById("cmbStab").value;
		$('[name="stab"]:checked').each(function() {
			plantSelected+=$(this).val()+", ";
		     });
		plantSelected= plantSelected==""?'999':plantSelected;
		var data1 = {
			plant : plantSelected
		}

		$.getJSON("<c:url value="/getgroups" />", data1, listGroups);

	}

	function listStabs(data) {
		var stabilimenti= document.getElementById("stabilimenti");
		var stabSelected = "${sessionScope.ecolabSessionFilter.stabId}".split(",");
		var righe = '';
		for (i = 0; i < data.plants.length; i++) {
			var plant = data.plants[i];
			
			if(parseInt(${user.authLevel}) & plant.stabId)
			{
				righe = righe+('<input type="checkbox" onchange="onChangeGroups();onChangeMachines();" name="stab" '+(stabSelected.indexOf(plant.stabId.toString())>-1||stabSelected=='999'?'checked="true"':'')+' value="'+plant.stabId+'"><label for="stab">'+plant.stabName+'</label><br>');
			}
		}
		$('#stabilimenti > div').replaceWith("<div>"+righe+"</div>");
		
		/*var stb = document.getElementById("cmbStab");
		stb.length = 0;

		var comboitem = document.createElement("option");
		comboitem.value = "999";
		comboitem.text = "Tutti...";

		stb.options.add(comboitem);

		for (i = 0; i < data.plants.length; i++) {
			var plant = data.plants[i];
			
			if(parseInt(${user.authLevel}) & plant.stabId)
			{
				var comboitem = document.createElement("option");
	
				comboitem.value = plant.stabId;
				comboitem.text = plant.stabName;
	
				if (comboitem.value == "<c:out value="${param.cmbStab}" />") {
					comboitem.selected = true;
				}
	
				stb.options.add(comboitem);
			}
		}*/
	}

	function updateStabs() {

		$.getJSON("<c:url value="/getstabs" />", listStabs);

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