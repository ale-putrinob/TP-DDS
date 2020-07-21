package dominio.Quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;


public class EjecutarReporteGastoMensual {

public static void main(String[] args) {

		try {
// Creacion de una instancia de Scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			System.out.println("Iniciando Scheduler...");
			scheduler.start();
// Creacion una instancia de JobDetail
			JobDetail jobDetail = new JobDetail(
					"EjecutarReporteGastoMensual",
					Scheduler.DEFAULT_GROUP,
					ReportarGastoMensual.class);

// Creacion de un Trigger donde indicamos
//que el Job se
// ejecutara de inmediato y a partir de ahi en un lapso
// de 2592000 segundos (30 días) por 12 veces mas (un año).
			Trigger trigger = new SimpleTrigger(
					"EjecutarReporteGastoMensualTrigger",
					Scheduler.DEFAULT_GROUP,
					1, 2592000);

// Registro dentro del Scheduler
			scheduler.scheduleJob(jobDetail, trigger);

// Damos tiempo a que el Trigger registrado
//termine su periodo
// de vida dentro del scheduler
			Thread.sleep(60000);

// Detenemos la ejecución de la
// instancia de Scheduler
			scheduler.shutdown();

		} catch(Exception e) {
			System.out.println("Ocurrió una excepción");
}
}

}

