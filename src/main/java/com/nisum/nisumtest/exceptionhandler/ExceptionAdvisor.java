package com.nisum.nisumtest.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nisum.nisumtest.exceptionhandler.custom.MailException;
import com.nisum.nisumtest.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(MailException.class)
    public ResponseEntity<String> handleMailException(MailException mailException){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("mensaje", mailException.getMessage());

        return new ResponseEntity<>(Utils.toJSONFromObject(errorMap), httpStatus);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("mensaje", ex.getMessage());

        return new ResponseEntity<>(Utils.toJSONFromObject(errorMap), httpStatus);

    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<String> handleJsonProcessingException(JsonProcessingException ex) {
        String exText = ex.getLocalizedMessage();
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(exText, httpStatus);
    }

}
