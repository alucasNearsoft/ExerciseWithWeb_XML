package com.companyname.timesheet.requestmodel;

import java.util.GregorianCalendar;

public class RequestModelRegistroHoras {

	private String email;
	private GregorianCalendar weekStart;
	private int billableHours;
	private int nonBillableHours;
	
	public RequestModelRegistroHoras(String email, GregorianCalendar weekStart,
			int billableHours, int nonBillableHours) {
		this.email = email;
		this.weekStart = weekStart;
		this.billableHours = billableHours;
		this.nonBillableHours = nonBillableHours;
	}

	public String getEmail() {
		return email;
	}

	public GregorianCalendar getWeekStart() {
		return weekStart;
	}

	public int getBillableHours() {
		return billableHours;
	}

	public int getNonBillableHours() {
		return nonBillableHours;
	}

	
}
