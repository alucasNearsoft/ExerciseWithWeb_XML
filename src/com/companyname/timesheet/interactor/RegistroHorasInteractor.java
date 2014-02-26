package com.companyname.timesheet.interactor;

import com.companyname.timesheet.entity.Timesheet;
import com.companyname.timesheet.repository.TimesheetRepository;
import com.companyname.timesheet.repository.TimesheetRepositoryImpl;
import com.companyname.timesheet.repository.TimesheetRepositoryImplDB;
import com.companyname.timesheet.requestmodel.RequestModelRegistroHoras;
import com.companyname.timesheet.responsemodel.ResponseModelRegistroHoras;

public class RegistroHorasInteractor {

	public RegistroHorasInteractor() {
		
	}

	public ResponseModelRegistroHoras registrarHoras(
			RequestModelRegistroHoras request) {
		// Obtener los datos de entrada rel Request model: email, Fecha de inicio de semana, Horas fact., Horas No Fact.
		// 
		Timesheet timesheet =  new Timesheet();
		
		timesheet.setEmail(request.getEmail());
		timesheet.setBillableHours(request.getBillableHours());
		timesheet.setNonBillableHours(request.getNonBillableHours());
		timesheet.setWeekStart(request.getWeekStart());
		
		// Al repositorio...
		//Repository repositorio = new RepositoryMemory();
		//TimesheetRepository repositorio = TimesheetRepositoryImpl.getInstance();
		TimesheetRepository repositorio = TimesheetRepositoryImplDB.getInstance();
		
		repositorio.save(timesheet);
		
		ResponseModelRegistroHoras tempResponse =  new ResponseModelRegistroHoras(true);
		
		return tempResponse;
	}

}
