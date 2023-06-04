package com.todo_app.back_end.todo_app_back_end.Exceptions;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;

import org.springframework.stereotype.Component;

import graphql.GraphQLError;

import graphql.GraphqlErrorBuilder;

import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQlExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ApiBaseException e) {

            return GraphqlErrorBuilder.newError()

                    .errorType(e.getErrorType())

                    .message("Received Message: " + ex.getMessage())

                    .path(env.getExecutionStepInfo().getPath())

                    .location(env.getField().getSourceLocation())

                    .build();

        } else {

            return null;

        }

    }

}
