package com.todo_app.back_end.todo_app_back_end.Exceptions;

import org.springframework.graphql.execution.ErrorType;

public abstract class ApiBaseException extends RuntimeException {

    public ApiBaseException(String message) {

        super(message);

    }

    public abstract ErrorType getErrorType();

}
