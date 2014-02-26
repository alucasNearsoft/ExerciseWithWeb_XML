package com.companyname.timesheet.interactor;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.companyname.timesheet.entity.Timesheet;
import com.companyname.timesheet.repository.TimesheetRepository;
import com.companyname.timesheet.requestmodel.RequestModelListaHoras;
import com.companyname.timesheet.responsemodel.ResponseModelListaHoras;

public class ListadoHorasInteractor {
	
	final int FACTURABLES = 0;
	final int NO_FACTURABLES = 1;
	private TimesheetRepository repository;
	
	public ListadoHorasInteractor(TimesheetRepository repository) {
		this.repository = repository;
	}
	
	public ResponseModelListaHoras generaListado(
			RequestModelListaHoras requestListadoHoras) {

		// Obtener los campos de entrada month y year
		int month = requestListadoHoras.getMonth();
		int year  = requestListadoHoras.getYear();
		
		// Obtener el listado con base en month y year
		List<Timesheet> listado = repository.getListadoPor(month, year);
		
		// Hacer el calculo
		//listado.calcularTotales();
		
		long totalHorasFacturables = obtenerTotalHrs(listado, FACTURABLES);
		//long totalHorasFacturables = listado.obtenTotal(FACTURABLES);
		long totalHorasNoFacturables = obtenerTotalHrs(listado, NO_FACTURABLES);
		//long totalHorasNoFacturables = listado.obtenTotal(NO_FACTURABLES);
		
		// Preparar response con el listado
		ResponseModelListaHoras tempResponse = new ResponseModelListaHoras(listado, totalHorasFacturables, totalHorasNoFacturables);
		
		return tempResponse;
	}

	private long obtenerTotalHrs(List<Timesheet> listado, int tipo) {
		Iterator<Timesheet> iter = listado.iterator();
		long total = 0;
		Timesheet ts = null;
		while (iter.hasNext()) {
			ts = iter.next();
			if (tipo == FACTURABLES) {
				total += ts.getBillableHours();
			}
			if (tipo == NO_FACTURABLES) {
				total += ts.getNonBillableHours();
			}
		}
		return total;
	}

}
