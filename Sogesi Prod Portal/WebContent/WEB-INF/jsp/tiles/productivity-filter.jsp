<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="${pageContext.request.contextPath}/static/script/jquery-ui.js"></script>
<form action="productivity" method="post" id="filterBox" name="filterBox">
	Stabilimento<br> <select name="cmbStab" id="cmbStab"
		style="width: 160px" class="txtSmall">
		<option value="999">Tutti...</option>
	</select> 
	<script>
	 $(function() {
    $( "#dateFrom" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  $(function() {
    $( "#dateTo" ).datepicker({dateFormat: "dd-mm-yy", showAnim: "fold"});
  });
  </script>
	<br> <br> Inizio periodo<br> 
	<!-- <input type="date" name="dateFrom" id="dateFrom" width="140" value="<%=request.getParameter("dateFrom")%>" required> -->
	<input type="text" name="dateFrom" id="dateFrom" width="140" value="<%=request.getParameter("dateFrom")%>" required>
	<br>
	<br> Fine periodo<br>
	<!-- <input type="date" name="dateTo" id="dateTo" width="100" value="<%=request.getParameter("dateTo")%>" required>  -->
	<input type="text" name="dateTo" id="dateTo" width="100" value="<%=request.getParameter("dateTo")%>" required>
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

	function listStabs(data) {
		
		var stb = document.getElementById("cmbStab");
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
	
				comboitem.value = plant.stabCdc;
				comboitem.text = plant.stabName;
	
				if (comboitem.value == "<c:out value="${param.cmbStab}" />") {
					comboitem.selected = true;
				}
	
				stb.options.add(comboitem);
			}
		}
	}

	function updateStabs() {

		$.getJSON("<c:url value="/getprodstabs" />", listStabs);

	}

	$(document).ready(updateStabs)
	
	
</script>