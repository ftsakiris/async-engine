package tsakiris.fotis.async.engine.controllers;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsakiris.fotis.async.engine.domain.ScheduledTask;
import tsakiris.fotis.async.engine.services.ScheduledTaskService;

import java.text.ParseException;

import static tsakiris.fotis.async.engine.Application.REST_PATH;

@RestController
public class ScheduledTaskController extends AbstractController {

    public static final String SVC_PATH = REST_PATH + "/scheduledTask";

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ScheduledTask scheduledTask) throws SchedulerException, ParseException {
        return response(scheduledTaskService.create(scheduledTask), HttpStatus.CREATED);
    }

}