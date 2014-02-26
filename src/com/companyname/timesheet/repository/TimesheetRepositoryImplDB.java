package com.companyname.timesheet.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.companyname.timesheet.entity.Timesheet;

public class TimesheetRepositoryImplDB implements TimesheetRepository {

	private static TimesheetRepositoryImplDB repo;
	
	// JDBC driver name and database URL
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost:3306/timesheetapp";

	//  Database credentials
	final String USER = "timesheetappuser";
	final String PASS = "ptimesheetapp";

	private TimesheetRepositoryImplDB() {
		
	}
	
	public static TimesheetRepositoryImplDB getInstance() {
		if (repo == null) {
			repo = new TimesheetRepositoryImplDB();
		}
		return repo;
	}

	@Override
	public void save(Timesheet tms) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating preparedStatement...");
			String sql = "INSERT into TIMESHEET (email, weekStart, billableHours, nonBillableHours)" +
					"VALUES (?, ?, ?, ?)" ;

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tms.getEmail());
			GregorianCalendar date = tms.getWeekStart();
			String dateFormat = "" + date.get(Calendar.YEAR) + 
					date.get(Calendar.MONTH) + 
					date.get(Calendar.DAY_OF_MONTH);
			stmt.setString(2, dateFormat);
			stmt.setInt   (3, tms.getBillableHours());
			stmt.setInt   (4, tms.getNonBillableHours());
			
			stmt.executeUpdate();
			System.out.println("Done preparedStatement.");

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null) { 
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} // finally
	}

	@Override
	public List<Timesheet> getListadoPor(int month, int year) {
		
		List<Timesheet> conjuntoTimesheet = new LinkedList<>();
		
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Creating Statement...");
			StringBuilder sql = new StringBuilder("SELECT * from timesheet WHERE MONTH(weekstart) = ");
			sql.append(Integer.toString(month));
			sql.append(" AND YEAR(weekstart) = ");
			sql.append(Integer.toString(year));
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql.toString());
			
			// (email, weekStart, billableHours, nonBillableHours)
			Timesheet tms;
			while (rs.next()) {
				//weekstart
				Date weekStartDate = rs.getDate("weekStart");
				GregorianCalendar weekStart = new GregorianCalendar();
				weekStart.setTime(weekStartDate);
				
				tms = new Timesheet();
				tms.setWeekStart(weekStart);
				tms.setEmail(rs.getString("email"));
				tms.setBillableHours(rs.getInt("billableHours"));
				tms.setNonBillableHours(rs.getInt("nonBillableHours"));
		
				conjuntoTimesheet.add(tms);
			}
			
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null) { 
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} // finally
		
		return conjuntoTimesheet;
	}

}
