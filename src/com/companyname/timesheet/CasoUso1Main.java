package com.companyname.timesheet;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.companyname.timesheet.interactor.RegistroHorasInteractor;
import com.companyname.timesheet.requestmodel.RequestModelRegistroHoras;
import com.companyname.timesheet.responsemodel.ResponseModelRegistroHoras;

public class CasoUso1Main {
	
	public static void main(String[] args) {
		//RegistroHorasInteractor it = new RegistroHorasInteractor();
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RegistroHorasInteractor registroHorasInteractor = appContext.getBean("registroHorasInteractor", RegistroHorasInteractor.class);

		GregorianCalendar fecha = new GregorianCalendar();
		fecha.set(2013, 11, 25);
		// call -> RegistroHorasRequestModel(email, fecha, horas fact., horas no fact.) 
		RequestModelRegistroHoras request = new RequestModelRegistroHoras("a@a.com", fecha, 38, 2);
		ResponseModelRegistroHoras response = registroHorasInteractor.registrarHoras(request);
		System.out.println(response.isExito());
	}
}
