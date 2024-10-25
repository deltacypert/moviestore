package com.aca.moviestore.controller;

import com.aca.moviestore.model.ExceptionResponse;
import com.aca.moviestore.model.MovieException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<ExceptionResponse> handleMovieException(
            MovieException movieException, HttpServletRequest request) {

        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(movieException.getMessage());
        response.setRequestURI(request.getRequestURI());

        return ResponseEntity.badRequest().body(response);
    }

}
