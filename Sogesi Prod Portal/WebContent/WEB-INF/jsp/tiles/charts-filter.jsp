<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form action="charts" method="get" id="filterBox" name="filterBox">
	Stabilimenti <br>
	<div id="stabilimenti" style="margin-left: 10px">
		<div></div>
	</div>
	<br> <br> <br> <span id="dataInizioSpan">Data</span> <br>

	<script>
  
  $(function() {
    $( "#dateFrom" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  $(function() {
    $( "#dateTo" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  </script>
	<fmt:formatDate type="date"
		value="${sessionScope.chartSessionFilter.txtDateFrom}"
		pattern="dd-MM-yyyy" var="fmtDTfrom" />
	<input type="text" name="dateFrom" id="dateFrom" width="140"
		value="${fmtDTfrom}" required> <br>
	<br> 
	
	<span id="dataFineSpan" style="display: hidden;">Data fine</span> <br>
	
	<fmt:formatDate type="date"
		value="${sessionScope.chartSessionFilter.txtDateTo}"
		pattern="dd-MM-yyyy" var="fmtDTto" />
	<input type="text"
	style="display: hidden";
		 name="dateTo" id="dateTo" width="140"
		value="${fmtDTto}" required> <br>
	<br>
		Unit&agrave; di misura
		<br>
		<select id="unitaMisura" name="unitaMisura">
			<option value="0" ${sessionScope.chartSessionFilter.unitaMisura==0?"selected='true'":""}>Ore</option> 
			<option value="1" ${sessionScope.chartSessionFilter.unitaMisura==1?"selected='true'":""}>Giorni</option>
			<option value="2" ${sessionScope.chartSessionFilter.unitaMisura==2?"selected='true'":""}>Settimane</option>
			<option value="3" ${sessionScope.chartSessionFilter.unitaMisura==3?"selected='true'":""}>Mesi</option>
			<option value="4" ${sessionScope.chartSessionFilter.unitaMisura==4?"selected='true'":""}>Anni</option>
		</select>
		<br>
	<br> <input type="button" name="btnSubmit" id="idBtnSubmit"
		value="Seleziona" onclick="validate()">
</form>
<script>

	function validate() {
		var startDt = document.getElementById("dateFrom").value;
		var endDt = document.getElementById("dateTo").value;
		if (new Date(startDt).getTime() > new Date(endDt).getTime()) {
			alert("La data di fine  non deve essere MINORE della data di inizio!");
			return;
		} else if (!($('[name="stab"]:checked').length > 0)) {
			alert("Selezionare almeno uno stabilimento");
			return;
		}
		document.getElementById("filterBox").submit();
	}

	function listStabs(data) {
		var stabilimenti= document.getElementById("stabilimenti");
		var stabSelected = "${sessionScope.chartSessionFilter.stabId}".split(",");
		var righe = '';
		for (i = 0; i < data.plants.length; i++) {
			var plant = data.plants[i];
			
			if(parseInt(${user.authLevel}) & plant.stabId)
			{
				righe = righe+('<input type="checkbox" name="stab" '+(stabSelected.indexOf(plant.stabId.toString())>-1||stabSelected=='all'?'checked="true"':'')+' value="'+plant.stabId+'"><label for="stab">'+plant.stabName+'</label><br>');
			}
		}
		$('#stabilimenti > div').replaceWith("<div>"+righe+"</div>");
	}

	function updateStabs() {

		$.getJSON("<c:url value="/getstabs" />", listStabs);

	}

	$(document).ready(updateStabs);
	
	$( "#unitaMisura" ).change( initDateInput );
	 
	
	function initDateInput() {
		if($("#unitaMisura option:selected" ).val() == 0){
			$( "#dataInizioSpan" ).html('Data');
			$( "#dataFineSpan" ).hide();
			$( "#dateTo" ).hide();
		} else{ 
			$( "#dataInizioSpan" ).html('Data Inizio');
			$( "#dataFineSpan" ).show();
			$( "#dateTo" ).show(); 
		}
	}

	$(document).ready(initDateInput);
	
</script>