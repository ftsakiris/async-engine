package tsakiris.fotis.async.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsakiris.fotis.async.engine.domain.Task;
import tsakiris.fotis.async.engine.services.TaskService;

@RestController
public class TaskController extends AbstractController {

    public static final String SVC_PATH = "/task";
    public static final String RETRY_PATH = SVC_PATH + "/retry";

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Task task) {
        return response(taskService.create(task), HttpStatus.CREATED);
    }

    @RequestMapping(value = RETRY_PATH + ID_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> retry(@PathVariable String id) {
        return response(taskService.run(taskService.get(id)), HttpStatus.OK);
    }

    @RequestMapping(value = SVC_PATH + ID_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String id) {
        return response(taskService.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = SVC_PATH, method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return response(taskService.getAll(), HttpStatus.OK);
    }

}