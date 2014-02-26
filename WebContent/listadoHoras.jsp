<%@page	import="com.companyname.timesheet.interactor.ListadoHorasInteractor"%>
<%@page	import="com.companyname.timesheet.requestmodel.RequestModelListaHoras"%>
<%@page	import="com.companyname.timesheet.responsemodel.ResponseModelListaHoras"%>
<%@page	import="com.companyname.timesheet.repository.TimesheetRepositoryImplDB"%>
<%@page import="com.companyname.timesheet.entity.Timesheet" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.Calendar" %>
<%@page import="java.util.GregorianCalendar" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.Arrays" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.text.SimpleDateFormat" %>
<html>
<head>
<title>Listado de Horas</title>
</head>
<body>
	<form method="post" action="listadoHoras.jsp">
		<h1>Listado</h1>
		<table>
			<tr>
				<td>Mes:</td>
				<td><select name="month">
				<%
					int mesAcarreado = 0;
					if (request.getParameter("month") != null) {
						mesAcarreado = Integer.parseInt(request.getParameter("month"));
					}
					Calendar cal = new GregorianCalendar();
					Date creationDate = cal.getTime();
					int mesSeleccionado = mesAcarreado==0 ? cal.get(Calendar.MONTH) : mesAcarreado;
					
					String meses[] = { "Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic" };
					String optionPrefix = "<option value =\"";
					String optionSuffix = "</option>"; 
					for (int i = 0; i < meses.length; i++) {
						if (i==mesSeleccionado) {
							out.write(optionPrefix + i + "\" selected>" + meses[i] + optionSuffix);
						} else {
							out.write(optionPrefix + i + "\" >" + meses[i] + optionSuffix);
						}
					}
				%>
				<!--    td><input name="month"></td    -->
				</select></td>
				<td>Año:</td>
				<!--    td><input name="year"></td    -->
				<td><select name="year">
					<option value="2013">2013</option>
					<option value="2012">2012</option>
					<option value="2011">2011</option>
					<option value="2010">2010</option>
				</select></td>
				<td><input type="submit" value="Consultar"></td>
			</tr>
		</table>
		<%
			String theMonthStr = request.getParameter("month");
			String theYearStr = request.getParameter("year");
			if ( (theMonthStr != null) && (theYearStr != null ) ) {
				int month = Integer.parseInt(theMonthStr) + 1; // ajuste: el arreglo de meses empieza en 0, en queries se usa 1-12
				int year = Integer.parseInt(theYearStr);

				if ((month >= 1) && (month <= 12)) {
					// Reuso
					ListadoHorasInteractor listadoHoras = new ListadoHorasInteractor(
							TimesheetRepositoryImplDB.getInstance());
					RequestModelListaHoras requestListadoHoras = new RequestModelListaHoras(
							month, year);
					ResponseModelListaHoras responseListadoHoras = listadoHoras
							.generaListado(requestListadoHoras);
					//System.out.println("Listado...\n" + responseListadoHoras.toString());
					List<Timesheet> listado = responseListadoHoras.getListado();
					Timesheet tms;
					Iterator<Timesheet> iter = listado.iterator();
					out.write("<table>");
					out.write("<tr><th>correo-e</th><th>Fecha</th><th>Horas fact</th><th>Horas No fact</th></tr>");
					
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
					while (iter.hasNext()) {
						tms = iter.next();
						out.write("<tr>");
						out.write("<td>" + tms.getEmail() + "</td>");
						out.write("<td>" + dateFormatter.format(tms.getWeekStart().getTime()) + "</td>");
						out.write("<td>" + tms.getBillableHours() + "</td>");
						out.write("<td>" + tms.getNonBillableHours() + "</td>");
						out.write("</tr>");
					}
					out.write("</table>");
					//out.write(responseListadoHoras.toString());
					out.write("<br>");
					out.write("Horas fact: " + responseListadoHoras.getTotalHorasFacturables());
					out.write("<br>");
					out.write("Horas no fact: " + responseListadoHoras.getTotalHorasNoFacturables());
				} else {
					out.write("El mes no es válido.");
				}
			}
		%>
	</form>
	<a href="index.jsp">Inicio</a>
</body>
</html>