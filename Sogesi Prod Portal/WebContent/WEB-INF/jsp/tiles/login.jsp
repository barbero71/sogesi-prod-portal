<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		document.f.j_username.focus();
	}

	);
</script>
<div align="center">
	<form name='f'
		action="${pageContext.request.contextPath}/j_spring_security_check"
		method="post">
		<table border="1"
			style="border-collapse: collapse; width: 400px; border-spacing: 0px; padding: 0px;">
			<tbody>
				<tr>
					<td align="center">Username:<input type="text"
						name="j_username">
					</td>
				</tr>
				<tr>
					<td align="center">Password:<input type="password"
						name="j_password">
					</td>
				</tr>
				<!--
						<tr>
							<td align="center">Ricordami su questo dispositivo:<input type="checkbox"
								name="_spring_security_remember_me">
							</td>
						</tr>
						-->
				<tr>
					<td align="center"><input type="submit" Value="Effettua Login"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div align="center">
	<c:if test="${param.error}">
		<b style="color: #FF0000">Le credenziali inserite non sono
			corrette.<br>Si prega di riprovare
		</b>
		<b>${username}</b>
		<br />
		<br />
	</c:if>
</div>