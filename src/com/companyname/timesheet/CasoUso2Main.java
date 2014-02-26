package com.companyname.timesheet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.companyname.timesheet.interactor.ListadoHorasInteractor;
import com.companyname.timesheet.repository.TimesheetRepositoryImpl;
import com.companyname.timesheet.requestmodel.RequestModelListaHoras;
import com.companyname.timesheet.responsemodel.ResponseModelListaHoras;

// Listar ts y sus totales, de acuerdo con el mes y año dados
public class CasoUso2Main {

	public static void main(String[] args) {
		//ListadoHorasInteractor listadoHoras = new ListadoHorasInteractor(TimesheetRepositoryImpl.getInstance());
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ListadoHorasInteractor listadoHoras = appContext.getBean("listadoHorasInteractor", ListadoHorasInteractor.class);
		RequestModelListaHoras requestListadoHoras = new RequestModelListaHoras(11, 2013);
		ResponseModelListaHoras responseListadoHoras = listadoHoras.generaListado(requestListadoHoras);
		System.out.println(responseListadoHoras.isExito());
	}
}
