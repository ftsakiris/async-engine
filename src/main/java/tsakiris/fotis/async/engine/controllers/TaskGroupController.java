package tsakiris.fotis.async.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsakiris.fotis.async.engine.domain.TaskGroup;
import tsakiris.fotis.async.engine.persistence.TaskGroupRepository;

@RestController
public class TaskGroupController extends AbstractController {

    public static final String SVC_PATH = "/task/group";

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TaskGroup taskGroup) {
        taskGroupRepository.saveEntity(taskGroup);
        return response(taskGroup, HttpStatus.CREATED);
    }

    @RequestMapping(value = SVC_PATH, method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return response(taskGroupRepository.findAll(), HttpStatus.OK);
    }

}