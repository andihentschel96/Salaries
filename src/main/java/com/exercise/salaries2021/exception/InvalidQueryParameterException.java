package com.exercise.salaries2021.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidQueryParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidQueryParameterException(String message) {
        super(message);
    }
}
