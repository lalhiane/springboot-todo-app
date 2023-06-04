package com.todo_app.back_end.todo_app_back_end.todos.model.todo_input;

import java.util.Date;

import com.todo_app.back_end.todo_app_back_end.todos.model.entity.Todo;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoInput {

    private String title;

    private Integer userId;

    public Todo getTodoEntity() {

        return Todo.builder()

                .title(title)

                .createdDate(new Date())

                .userId(userId)

                .build();

    }

}
