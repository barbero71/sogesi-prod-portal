<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="ore-lavorate" method="get" id="filterBox" name="filterBox">
	Stabilimenti <br>  
    <div id="stabilimenti" style="margin-left:10px">
    <div>
      
      </div>
    </div>
    <br>
	<br>
    Tipologia ore <br>  
    <div  style="margin-left:10px">
    <div>
      <input type="checkbox" name="tipoore" value="lavorate" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'lavorate')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Lavorate</label><br>
      <input type="checkbox" name="tipoore" value="straordinario" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'straordinario')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Straordinario</label><br>
      <input type="checkbox" name="tipoore" value="ferie" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'ferie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Ferie</label><br>
      <input type="checkbox" name="tipoore" value="maternita" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'maternita')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Maternita</label><br>
      <input type="checkbox" name="tipoore" value="malattie" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'malattie')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Malattie</label><br>
      <input type="checkbox" name="tipoore" value="infortuni" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'infortuni')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Infortuni</label><br>
      <input type="checkbox" name="tipoore" value="l104" ${fn:contains(sessionScope.listaOreLavorateSessionFilter.tipoore, 'l104')||sessionScope.listaOreLavorateSessionFilter.tipoore=='all'?"checked='true'":""}><label for="stab">Legge 104</label><br>
      </div>
    </div>
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
  <fmt:formatDate type="date" value="${sessionScope.listaOreLavorateSessionFilter.txtDateFrom}" pattern="dd-MM-yyyy" var="fmtDTfrom" />
		 <input type="text" name="dateFrom" id="dateFrom" width="140" value="${fmtDTfrom}" required>
	<br><br> Data fine <br> 
  <fmt:formatDate type="date" value="${sessionScope.listaOreLavorateSessionFilter.txtDateTo}" pattern="dd-MM-yyyy" var="fmtDTto" />
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
		} else if (!($('[name="tipoore"]:checked').length > 0)) {
			alert("Selezionare almeno una tipologia oraria");
			return;
		} else if (!($('[name="stab"]:checked').length > 0)) {
			alert("Selezionare almeno uno stabilimento");
			return;
		}

		document.getElementById("filterBox").submit();
	}

	function listStabs(data) {
		var stabilimenti= document.getElementById("stabilimenti");
		var stabSelected = "${sessionScope.listaOreLavorateSessionFilter.stabId}".split(",");
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

		$.getJSON("<c:url value="/getstabilimenti" />", listStabs);

	}

	$(document).ready(updateStabs);
</script>