package com.todo_app.back_end.todo_app_back_end.Exceptions;

import org.springframework.graphql.execution.ErrorType;

public class ConflictException extends ApiBaseException {

    public ConflictException(String message) {

        super(message);

    }

    @Override
    public ErrorType getErrorType() {

        return ErrorType.INTERNAL_ERROR;

    }

}
