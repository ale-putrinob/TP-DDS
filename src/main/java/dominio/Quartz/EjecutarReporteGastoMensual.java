package dominio.Quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;

public class EjecutarReporteGastoMensual {

	public static void main(String[] args) throws SchedulerException {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		scheduler.start();
		
		JobDetail job = newJob(ReportarGastoMensual.class)
						.withIdentity("reporte-gastos-mensual","group1")
						.build();
		
		CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("unTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * ? * *"))
                .build();
		
	scheduler.scheduleJob(job,trigger);
}

}

