package com.companyname.timesheet.repository;

import java.util.List;
import java.util.Set;

import com.companyname.timesheet.entity.Timesheet;

public interface TimesheetRepository {
	
	public void save(Timesheet tms);

	public List<Timesheet> getListadoPor(int month, int year);
}
