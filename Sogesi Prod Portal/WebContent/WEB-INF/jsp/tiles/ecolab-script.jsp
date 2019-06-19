<script type="text/javascript">
/*
	$(document).ready(function() {
		$("#detList").autoNumeric('init', {
			aSep : '.',
			mDec : '0',
			vMax : '99999999999999999999999999'
		});
	});
*/
</script>
<script type="text/javascript">
/*
	var httprequest = false;
	var httprequest2 = false;

	function responseMach() { 
		if (httprequest2.readyState == 4 && httprequest2.status == 200) {
			var second = document.getElementById("cmbMach");
			second.length = 0;
			var responsedata = httprequest2.responseText;

			if (responsedata.substring(responsedata.length - 1) == ",") {
				responsedata = responsedata.substring(0,
						responsedata.length - 1);
			}
			var responselist = responsedata.split(",");
			var rsText = "";
			for (i = 0; i < responselist.length; i++) {
				var comboitem = document.createElement("option");
				rsText = responselist[i].split("-");
				comboitem.text = rsText[1];
				comboitem.value = rsText[0];

				second.options.add(comboitem);
			}
		}
	}

	function responseGrp() {
		if (httprequest.readyState == 4 && httprequest.status == 200) {
			var second = document.getElementById("cmbGroup");
			second.length = 0;
			var responsedata = httprequest.responseText;

			if (responsedata.substring(responsedata.length - 1) == ",") {
				responsedata = responsedata.substring(0,
						responsedata.length - 1);
			}

			var responselist = responsedata.split(",");
			var rsText = "";
			for (i = 0; i < responselist.length; i++) {
				var comboitem = document.createElement("option");
				rsText = responselist[i].split("-");
				alert(comboitem.text = rsText[1]);
				comboitem.text = rsText[1];
				comboitem.value = rsText[0];
				second.options.add(comboitem);
			}
		}
	}

	function machineChange() {
		
		var grpSelected = document.getElementById("cmbGroup").value;
		var plantSelected = document.getElementById("cmbStab").value;
		
		if (grpSelected.length == 0) {
			alert("Please select a item");
			var second = document.getElementById("cmbMach");
			//second.length = 0; 
			return;
		}
		
		if (window.XMLHttpRequest) {
			httprequest2 = new XMLHttpRequest();
		} else {
			httprequest2 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	
		httprequest2.open("GET",
				"${pageContext.request.contextPath}/dynamic/machines.jsp?group="
						+ grpSelected + "&plant=" + plantSelected, true);
		
		httprequest2.onreadystatechange = responseMach();
		alert("${pageContext.request.contextPath}/dynamic/machines.jsp?group="
						+ grpSelected + "&plant=" + plantSelected);

		httprequest2.send();
		
	}

	function groupChange() {
		var dataselected = document.getElementById("cmbStab").value;
		if (dataselected == "999")
			machineChange();
		if (dataselected.length == 0) {
			alert("Please select a item");
			var second = document.getElementById("cmbGroup");
			second.length = 0;
			return;
		}
		if (window.XMLHttpRequest) {
			httprequest = new XMLHttpRequest();
		} else {
			httprequest = new ActiveXObject("Microsoft.XMLHTTP");
		}
		httprequest.open("GET",
				"${pageContext.request.contextPath}/dynamic/groups.jsp?params="
						+ dataselected, true);
		httprequest.onreadystatechange = responseGrp();
		httprequest.send();

		//machineChange();
	}

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
*/
</script>
