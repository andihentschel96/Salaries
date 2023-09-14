package com.exercise.salaries2021.handler;

import com.exercise.salaries2021.exception.InvalidQueryParameterException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value =  {InvalidQueryParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<String> handleInvalidQueryParameter(InvalidQueryParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The request failed, error message: " + e.getLocalizedMessage());
    }
}
