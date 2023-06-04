package com.todo_app.back_end.todo_app_back_end.todos.model.dto;

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
public class TodoDto {

    private Integer id;

    private String title;

    private Date createdDate;

    private Integer userId;

    public static TodoDto fromEntityToDto(Todo todo) {

        return TodoDto.builder()

                .id(todo.getId())

                .title(todo.getTitle())

                .createdDate(todo.getCreatedDate())

                .userId(todo.getUserId())

                .build();

    }

}
