<%@ page import="it.sogesispa.prod.web.models.User"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<form action="attivita" method="POST">

	<div align="left" class="navFilter">Stabilimento:
		${attivita.stabilimento}</div>

	<div>

		<table style="width: 100%;" border="1">
			<tbody>
				<tr>
					<td class="">Linea</td>
					<td class=""><select disabled="disabled">
							<option value="" selected="selected">${attivita.linea}</option>
					</select></td>
				</tr>

				<tr>
					<td class="">Macchina</td>
					<td class=""><select disabled="disabled">
							<option value="" selected="selected">${attivita.macchina}</option>
					</select></td>
				</tr>

				<tr>
					<td class="">Impianto</td>
					<td class=""><select disabled="disabled">
							<option value="" selected="selected">${attivita.impianto}</option>
					</select></td>
				</tr>
<tr>
					<td class="">Prevista per</td>
					<td class=""><fmt:formatDate
							value="${attivita.dataPrevista}" pattern="dd-MM-yyyy" />
					</td>
				</tr>

				<tr>
					<td class="">Nuova Data</td>
					<td class=""><input type="text" name="dataPrevista"
						id="dataPrevista" width="140"
						value="">

						<script>
							$(function() {
								$("#dataPrevista").datepicker({
									dateFormat : "dd-mm-yy",
									showAnim : "fold"
								});
							});
						</script></td>
				</tr>
				<tr>
					<td class=""><input type="hidden" id="idAttivita"
						name="idAttivita" value="${attivita.idAttivita}" />
						<button name="action" value="salvaAttivitaAction" type="submit">Salva</button>
					</td>
					<td class="">
						<button name="action" value="annullaAttivitaAction" type="submit">Annulla</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>


	<div></div>

</form>