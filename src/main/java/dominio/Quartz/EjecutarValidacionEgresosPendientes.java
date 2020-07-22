package dominio.Quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.JobDetail;

public class EjecutarValidacionEgresosPendientes {
	
	public static void main(String[] args) throws SchedulerException {

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		scheduler.start();
		
		JobDetail job = newJob(ValidacionEgresosPendientes.class)
						.withIdentity("reporte-gastos-mensual")
						.build();
		
		SimpleTrigger trigger = newTrigger()
                .withIdentity("unTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever())
                .build();
		
	scheduler.scheduleJob(job,trigger);
}

}

