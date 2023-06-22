package ru.skypro.lessons.springboot.hw_springboot.mistakes;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.sql.*;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    //ошибка 404, при возникновении IOException.
    @ExceptionHandler
    public ResponseEntity<?> handleIOException(IOException ioException) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //ошибка 403, при возникновении Exception.
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception exception) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    //ошибка 500, при возникновении SQLException.
    @ExceptionHandler
    public ResponseEntity<?> handleSQLException(SQLException sqlException) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
