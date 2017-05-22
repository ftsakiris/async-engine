package tsakiris.fotis.async.engine.services;

import com.cronutils.mapper.CronMapper;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import tsakiris.fotis.async.engine.common.CommonUtils;
import tsakiris.fotis.async.engine.domain.QuartzJob;
import tsakiris.fotis.async.engine.domain.ScheduledTask;
import tsakiris.fotis.async.engine.persistence.ScheduledTaskRepository;

import java.text.ParseException;
import java.util.Hashtable;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static tsakiris.fotis.async.engine.common.Consts.*;

@Component
public class ScheduledTaskService extends AbstractService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskService.class);

    private final Scheduler scheduler;

    @Autowired
    private Environment env;

    @Autowired
    private ScheduledTaskRepository scheduledTaskRepository;

    private Map<String, String> properties;

    public ScheduledTaskService() throws SchedulerException {

        // Grab the Scheduler instance from the Factory
        scheduler = StdSchedulerFactory.getDefaultScheduler();

        // and start it off
        scheduler.start();
    }

    public ScheduledTask create(ScheduledTask scheduledTask) {
        try {
            scheduledTaskRepository.save(scheduledTask);
            createQuartzJob(scheduledTask);
        } catch (SchedulerException | ParseException e) {
            LOGGER.error("SchedulerException | ParseException", e);
        }
        return scheduledTask;
    }

    private void createQuartzJob(ScheduledTask scheduledTask) throws SchedulerException, ParseException {
        initProperties();
        // define the job and tie it to our QuartzJob class
        final JobDetail job = newJob(QuartzJob.class)
                .withIdentity(genJobKey(scheduledTask))
                .build();

        job.getJobDataMap().put(Map.class.getCanonicalName(), properties);

        final CronExpression cronExpression = new CronExpression(parseQuartzCron("* * * * *"));

        final Trigger trigger = newTrigger()
                .withIdentity(genTriggerKey(scheduledTask))
                .startNow()
                .withSchedule(cronSchedule(cronExpression))
                .forJob(genJobKey(scheduledTask))
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);
    }

    private void initProperties() {
        if (CommonUtils.isEmpty(properties)) {
            properties = new Hashtable<>();

            properties.put(SERVER_IP, env.getProperty(SERVER_IP));
            properties.put(SERVER_PORT, env.getProperty(SERVER_PORT));

            final StringBuilder stringBuilder = new StringBuilder(env.getProperty(SERVER_CONTEXT_PATH));
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            final String serverContextPath = stringBuilder.toString();
            properties.put(SERVER_CONTEXT_PATH, serverContextPath);
        }
    }

    private JobKey genJobKey(ScheduledTask scheduledTask) {
        final String keyName = scheduledTask.getId();
        final String keyGroup = ScheduledTask.class.getCanonicalName();
        return genJobKey(keyName, keyGroup);
    }

    private JobKey genJobKey(String jobKey, String jobGroup) {
        return new JobKey(jobKey, jobGroup);
    }

    private TriggerKey genTriggerKey(ScheduledTask scheduledTask) {
        final String keyName = scheduledTask.getId();
        final String keyGroup = ScheduledTask.class.getCanonicalName();
        return new TriggerKey(keyName, keyGroup);
    }

    private static String parseQuartzCron(final String cron) {

        final CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.CRON4J);
        //create a parser based on provided definition
        final com.cronutils.parser.CronParser parser = new com.cronutils.parser.CronParser(cronDefinition);
        final Cron cron4jCron = parser.parse(cron);
        cron4jCron.validate();

        // Migrate types
        final CronMapper cronMapper = CronMapper.fromCron4jToQuartz();
        final Cron quartzCron = cronMapper.map(cron4jCron);

        return quartzCron.asString();
    }
}