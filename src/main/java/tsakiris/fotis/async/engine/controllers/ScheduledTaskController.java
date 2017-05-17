package tsakiris.fotis.async.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsakiris.fotis.async.engine.domain.ScheduledTask;
import tsakiris.fotis.async.engine.services.ScheduledTaskService;

@RestController
public class ScheduledTaskController extends AbstractController {

    public static final String SVC_PATH = "/scheduledTask";
    public static final String ID_SEARCH_PATH = SVC_PATH + "/search/findById";

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ScheduledTask scheduledTask) {
        return response(scheduledTaskService.create(scheduledTask), HttpStatus.CREATED);
    }

}