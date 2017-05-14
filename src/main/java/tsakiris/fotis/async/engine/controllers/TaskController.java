package tsakiris.fotis.async.engine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tsakiris.fotis.async.engine.domain.Task;
import tsakiris.fotis.async.engine.services.TaskService;

@RestController
public class TaskController extends AbstractController {

    public static final String SVC_PATH = "/task";
    public static final String ID_PARAMETER = "id";
    public static final String ID_SEARCH_PATH = SVC_PATH + "/search/findById";

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = SVC_PATH, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Task task) {
//        repository.save(task);
        return response(taskService.sendToJms(task), HttpStatus.CREATED);
    }

}