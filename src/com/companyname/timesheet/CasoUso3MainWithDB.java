package com.companyname.timesheet;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.companyname.timesheet.interactor.ListadoHorasInteractor;
import com.companyname.timesheet.interactor.RegistroHorasInteractor;
import com.companyname.timesheet.repository.TimesheetRepositoryImpl;
import com.companyname.timesheet.repository.TimesheetRepositoryImplDB;
import com.companyname.timesheet.requestmodel.RequestModelListaHoras;
import com.companyname.timesheet.requestmodel.RequestModelRegistroHoras;
import com.companyname.timesheet.responsemodel.ResponseModelListaHoras;

public class CasoUso3MainWithDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//RegistroHorasInteractor registroHorasInteractor = new RegistroHorasInteractor();
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RegistroHorasInteractor registroHorasInteractor = appContext.getBean("registroHorasInteractor", RegistroHorasInteractor.class);

		GregorianCalendar fecha = new GregorianCalendar();
		fecha.set(2013, 11, 25);
		// call -> RegistroHorasRequestModel(email, fecha, horas fact., horas no fact.) 
		registroHorasInteractor.registrarHoras(new RequestModelRegistroHoras("a@a.com", fecha, 38, 2));
		registroHorasInteractor.registrarHoras(new RequestModelRegistroHoras("a2@a.com", fecha, 40, 0));
		
		//ListadoHorasInteractor listadoHoras = new ListadoHorasInteractor(TimesheetRepositoryImplDB.getInstance());
		ListadoHorasInteractor listadoHoras = appContext.getBean("listadoHorasInteractor", ListadoHorasInteractor.class);
		RequestModelListaHoras requestListadoHoras = new RequestModelListaHoras(11, 2013);
		ResponseModelListaHoras responseListadoHoras = listadoHoras.generaListado(requestListadoHoras);
		System.out.println("Listado...\n" + responseListadoHoras.toString());
		System.out.println("Total hrs. fac.: " + responseListadoHoras.getTotalHorasFacturables() );
		System.out.println("Total hrs. no fac.: " + responseListadoHoras.getTotalHorasNoFacturables() );
		System.out.println(responseListadoHoras.isExito());
	}

}
