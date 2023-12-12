package com.sfeiropensource.schoolapp.interceptor;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public interface ExceptionInterceptor {

    @ExceptionHandler()
    default ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    default ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
