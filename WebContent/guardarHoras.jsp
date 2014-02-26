<%@page import="com.companyname.timesheet.requestmodel.RequestModelRegistroHoras"%>
<%@page import="com.companyname.timesheet.interactor.RegistroHorasInteractor" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date" %>
<%
String email = request.getParameter("email");
out.write("email es " + email + "<br>");

GregorianCalendar weekStart = new GregorianCalendar();
Date date = new Date(request.getParameter("semanaFecha"));
out.write("semana es " + date.toString() + "<br>");

weekStart.setTime(date); 
String billHours = request.getParameter("horasFacturables");
out.write("horasFacturables es " + billHours + "<br>");

String nonBillHours = request.getParameter("horasNoFacturables");
out.write("horasNoFacturables es " + nonBillHours + "<br>");

// Instanciar request model (a partir de aqui se reutiliza codigo) 
RequestModelRegistroHoras requestModelRegistroHoras = new RequestModelRegistroHoras(email, weekStart, Integer.parseInt(billHours), Integer.parseInt(nonBillHours));
RegistroHorasInteractor registroHorasInteractor = new RegistroHorasInteractor();
registroHorasInteractor.registrarHoras(requestModelRegistroHoras);

out.write("Horas registradas.");
%>
<br>
<a href="index.jsp">Inicio</a>
