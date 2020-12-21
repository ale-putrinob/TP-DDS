package dominio.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import dominio.organizacion.*;

public class ValidacionEgresosPendientes {  //implements Job{
	Organizacion organizacion;
	
	public void execute(){ //JobExecutionContext context) throws JobExecutionException{
		organizacion.validarOperacionesPendientes();
		System.out.println("Generando reporte de gastos mensuales...");
	}

}
