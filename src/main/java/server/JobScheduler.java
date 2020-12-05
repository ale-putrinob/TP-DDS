package server;

import dominio.Quartz.ValidacionEgresosPendientes;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForever;
import static org.quartz.TriggerBuilder.newTrigger;

class JobScheduler {

    public static void execute(String[] args) throws Exception {
        Scheduler scheduler =  StdSchedulerFactory.getDefaultScheduler();

        (scheduler).start();

        JobDetail jobDetail = newJob(ValidacionEgresosPendientes.class)
                .build();

        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(repeatSecondlyForever(2))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}