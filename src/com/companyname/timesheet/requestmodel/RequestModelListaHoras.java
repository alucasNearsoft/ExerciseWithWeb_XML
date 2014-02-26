package com.companyname.timesheet.requestmodel;

// Una simple estructura de datos
public class RequestModelListaHoras {

	private int month;
	private int year;
	
	public RequestModelListaHoras(int month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	

}
