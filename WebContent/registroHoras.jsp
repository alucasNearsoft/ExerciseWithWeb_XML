<html>
<head><title>Registro de Horas</title></head>
<body>
	<form method="post" action="guardarHoras.jsp">
		<h1>Registro de Horas</h1>
		<table>
			<tr>
				<td align="right">Correo-e:</td>
				<td><input type="text" name="email" alt="email address"></td>
			</tr>
			<tr>
				<td align="right">Semana:</td>
				<td><input type="text" name="semanaFecha"
					alt="selecciona semana del calendario"></td>
			</tr>
			<tr>
				<td align="right">Horas:</td>
				<td><input name=horasFacturables alt="Horas facturables"></td>
			</tr>
			<tr>
				<td align="right">PTO:</td>
				<td><input name=horasNoFacturables alt="Horas no facturables"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Guardar"></td>
			</tr>
		</table>
		<a href="index.jsp">Inicio</a>
	</form>
</body>
</html>