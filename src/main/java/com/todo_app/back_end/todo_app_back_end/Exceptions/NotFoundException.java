package com.todo_app.back_end.todo_app_back_end.Exceptions;

import org.springframework.graphql.execution.ErrorType;

public class NotFoundException extends ApiBaseException {

    public NotFoundException(String message) {

        super(message);

    }

    @Override
    public ErrorType getErrorType() {

        return ErrorType.NOT_FOUND;

    }

}
