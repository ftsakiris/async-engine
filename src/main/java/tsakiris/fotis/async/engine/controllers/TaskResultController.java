package tsakiris.fotis.async.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tsakiris.fotis.async.engine.domain.TaskResult;
import tsakiris.fotis.async.engine.persistence.TaskResultRepository;

@RestController
public class TaskResultController extends AbstractController {

    public static final String SVC_PATH = "/task/result";
    public static final String FIND_BY_TASK_ID_PATH = SVC_PATH + "/search/findByTaskId";

    @Autowired
    private TaskResultRepository taskResultRepository;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TaskResult taskResult) {
        taskResultRepository.saveEntity(taskResult);
        return response(taskResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = SVC_PATH + ID_VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable String id) {
        return response(taskResultRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = SVC_PATH, method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return response(taskResultRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = FIND_BY_TASK_ID_PATH + VALUE, method = RequestMethod.GET)
    public ResponseEntity<?> findByTaskId(@PathVariable String value) {
        return response(taskResultRepository.findByTaskId(value), HttpStatus.OK);
    }

}