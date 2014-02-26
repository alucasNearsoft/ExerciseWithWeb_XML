package com.companyname.timesheet.entity;

import java.util.Calendar;
import java.util.GregorianCalendar;

enum NonBillableType { PTO, Vacation, Holiday };

/**
 * This class means the timesheet of an employee
 *
 */
public class Timesheet implements Comparable<Timesheet> {
	
	final int LIMITE_HORAS = 168; // a rule to not overpass the hours per week: 7 * 24 -> 168
			
	private String email;
	private GregorianCalendar weekStart;
	private int billableHours;
	private int nonBillableHours;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		// a basic validator using regular expresion for email-addr
		String emailReg = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}"; 
		if (!email.matches(emailReg)) {
			System.out.println("email address no es valido");
		}
		else {
			this.email = email;
		}
	}
	public int getBillableHours() {
		return billableHours;
	}
	public void setBillableHours(int billableHours) {
		this.billableHours = setHours(billableHours);
	}
	public GregorianCalendar getWeekStart() {
		return weekStart;
	}
	public void setWeekStart(GregorianCalendar weekStart) {
		this.weekStart = weekStart;
	}
	public int getNonBillableHours() {
		return nonBillableHours;
	}
	public void setNonBillableHours(int nonBillableHours) {
		this.nonBillableHours = setHours(nonBillableHours);
	}
	
	private int setHours(int hours) {
		// a rule to not overpass the hours per week: 7 * 24 -> 168
		if ((hours < 0) || (hours > LIMITE_HORAS)) {
			System.out.println("Número de horas:" + hours + " fuera del rango: 0-" + LIMITE_HORAS);
		}
		return hours;
	}
	
	@Override
	public int compareTo(Timesheet ts) {
		return email.compareTo(ts.getEmail());
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(email);
		str.append(", week:").append(weekStart.get(Calendar.YEAR)).append("/").append(weekStart.get(Calendar.MONTH)).append("/").append(weekStart.get(Calendar.DAY_OF_MONTH));
		str.append(", billable hours: ").append(billableHours);
		str.append(", non billable hours: ").append(nonBillableHours);
		return str.toString();
	}
}