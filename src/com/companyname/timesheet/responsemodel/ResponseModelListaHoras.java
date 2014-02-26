package com.companyname.timesheet.responsemodel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.companyname.timesheet.entity.Timesheet;

public class ResponseModelListaHoras {
	
	private List<Timesheet> listado;
	long totalHorasFacturables;
	long totalHorasNoFacturables;

	public ResponseModelListaHoras(List<Timesheet> listado,
			long totalHorasFacturables, long totalHorasNoFacturables) {
		this.totalHorasNoFacturables = totalHorasNoFacturables;
		this.totalHorasFacturables = totalHorasFacturables;
		this.listado = listado;
	}

	public boolean isExito() {
		return listado!=null;
	}

	public long getTotalHorasFacturables() {
		return totalHorasFacturables;
	}

	public long getTotalHorasNoFacturables() {
		return totalHorasNoFacturables;
	}

	public List<Timesheet> getListado() {
		return listado;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		Iterator<Timesheet> iterTs = listado.iterator();
		Timesheet ts = null;
		while (iterTs.hasNext()) {
			ts = iterTs.next();
			str.append(ts.toString()).append("\n");
		}
		return str.toString();
	}

}
