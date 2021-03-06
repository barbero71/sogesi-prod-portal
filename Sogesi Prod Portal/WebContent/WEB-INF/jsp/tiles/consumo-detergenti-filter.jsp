<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="consumo-detergenti" method="get" id="filterBox" name="filterBox">
	Stabilimenti <br>  
    <div id="stabilimenti" style="margin-left:10px">
    <div>
      <input type="checkbox" name="stab" value="1" ${fn:contains(sessionScope.consumoDetergentiSessionFilter.stabId, '1')||sessionScope.consumoDetergentiSessionFilter.stabId=='all'?"checked='true'":""}><label for="stab">Perugia</label><br>
      <input type="checkbox" name="stab" value="2" ${fn:contains(sessionScope.consumoDetergentiSessionFilter.stabId, '2')||sessionScope.consumoDetergentiSessionFilter.stabId=='all'?"checked='true'":""}><label for="stab">Ponsacco</label><br>
      <input type="checkbox" name="stab" value="3" ${fn:contains(sessionScope.consumoDetergentiSessionFilter.stabId, '3')||sessionScope.consumoDetergentiSessionFilter.stabId=='all'?"checked='true'":""}><label for="stab">Stroncone</label><br>
      <input type="checkbox" name="stab" value="4" ${fn:contains(sessionScope.consumoDetergentiSessionFilter.stabId, '4')||sessionScope.consumoDetergentiSessionFilter.stabId=='all'?"checked='true'":""}><label for="stab">Cannara</label><br>
      <input type="checkbox" name="stab" value="5" ${fn:contains(sessionScope.consumoDetergentiSessionFilter.stabId, '5')||sessionScope.consumoDetergentiSessionFilter.stabId=='all'?"checked='true'":""}><label for="stab">Erbusco</label><br>
      </div>
    </div>
    <br>
	<br>
    
	<br> Data inizio <br>
  		 
  <script>
  
  $(function() {
    $( "#dateFrom" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  $(function() {
    $( "#dateTo" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  </script>
  <fmt:formatDate type="date" value="${sessionScope.consumoDetergentiSessionFilter.txtDateFrom}" pattern="dd-MM-yyyy" var="fmtDTfrom" />
		 <input type="text" name="dateFrom" id="dateFrom" width="140" value="${fmtDTfrom}" required>
	<br><br> Data fine <br> 
  <fmt:formatDate type="date" value="${sessionScope.consumoDetergentiSessionFilter.txtDateTo}" pattern="dd-MM-yyyy" var="fmtDTto" />
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
		} else if (!($('[name="stab"]:checked').length > 0)) {
			alert("Selezionare almeno uno stabilimento");
			return;
		}

		document.getElementById("filterBox").submit();
	}

	function listStabs(data) {
		var stabilimenti= document.getElementById("stabilimenti");
		var stabSelected = "${sessionScope.consumoDetergentiSessionFilter.stabId}".split(",");
		var righe = '';
		for (i = 0; i < data.plants.length; i++) {
			var plant = data.plants[i];
			
			if(parseInt(${user.authLevel}) & plant.id)
			{
				righe = righe+('<input type="checkbox" name="stab" '+(stabSelected.indexOf(plant.id.toString())>-1||stabSelected=='all'?'checked="true"':'')+' value="'+plant.id+'"><label for="stab">'+plant.name+'</label><br>');
			}
		}
		$('#stabilimenti > div').replaceWith("<div>"+righe+"</div>");
	}

	function updateStabs() {

		//$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

	}

	$(document).ready(updateStabs);
</script>