package dominio.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import dominio.organizacion.*;
import dominio.operacionDeEgreso.*;

public class ReportarGastoMensual implements Job{
	Entidad entidad;
	Etiqueta etiqueta;
	
	public void execute(JobExecutionContext context) throws JobExecutionException{
		entidad.ReporteGastosMensuales(etiqueta);
		System.out.println("Generando reporte de gastos mensuales...");
	}

}
