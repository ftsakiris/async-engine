package tsakiris.fotis.async.engine.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tsakiris.fotis.async.engine.Consts;

public abstract class AbstractController<T> {

    protected static final String PAGE = "page";
    protected static final String SIZE = "size";

    protected ResponseEntity<T> response(T body, HttpStatus httpStatus) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Consts.APPLICATION_HEADER, Consts.APP_NAME);
        return new ResponseEntity<>(body, httpHeaders, httpStatus);
    }

}
