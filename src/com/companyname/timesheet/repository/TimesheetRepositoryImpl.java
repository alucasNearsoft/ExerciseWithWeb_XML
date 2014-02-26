package com.companyname.timesheet.repository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.companyname.timesheet.entity.Timesheet;

public class TimesheetRepositoryImpl implements TimesheetRepository {

	private static TimesheetRepositoryImpl repo;
	private List<Timesheet> ListTimesheet;
	
	private TimesheetRepositoryImpl() {
		ListTimesheet = new LinkedList<>();
	}

	public static TimesheetRepository getInstance() {
		if (repo == null) {
			repo = new TimesheetRepositoryImpl();
		}
		return repo;
	}

	@Override
	public void save(Timesheet tms) {
		//if (tms == null) {/* lanzar error */}
		ListTimesheet.add(tms);
	}

	@Override
	public List<Timesheet> getListadoPor(int month, int year) {
		// Devolver en otra collection
		List<Timesheet> filtrados = new LinkedList<>();

		// Recorrer ConjuntoTimesheet
		Iterator<Timesheet> iter = ListTimesheet.iterator();
		Timesheet ts = null;
		GregorianCalendar tsWeekStart = null;
		
		while (iter.hasNext()) {
			ts = iter.next();
			tsWeekStart = ts.getWeekStart();
			if ((tsWeekStart.get(Calendar.MONTH) == month) & (tsWeekStart.get(Calendar.YEAR) == year)) {
				filtrados.add(ts);
			}
		}
		
		return filtrados;
	}

}
