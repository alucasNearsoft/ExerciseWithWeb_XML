package com.companyname.timesheet;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.companyname.timesheet.interactor.RegistroHorasInteractor;
import com.companyname.timesheet.requestmodel.RequestModelRegistroHoras;

public class CasoUsoHorasFueraRango {
	
	public static void main(String args[]) { 
		//RegistroHorasInteractor registroHorasInteractor = new RegistroHorasInteractor();
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RegistroHorasInteractor registroHorasInteractor = appContext.getBean("registroHorasInteractor", RegistroHorasInteractor.class);
		GregorianCalendar fecha = new GregorianCalendar();
		fecha.set(2013, 11, 25); 
		registroHorasInteractor.registrarHoras(new RequestModelRegistroHoras("a@a.com", fecha, 500, 2));
		registroHorasInteractor.registrarHoras(new RequestModelRegistroHoras("a@a.com", fecha, -500, 2));
	}

}
