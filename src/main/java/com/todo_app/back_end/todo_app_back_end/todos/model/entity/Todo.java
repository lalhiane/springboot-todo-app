package com.todo_app.back_end.todo_app_back_end.todos.model.entity;

import java.util.Date;

import com.todo_app.back_end.todo_app_back_end.todos.model.dto.TodoDto;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

@Table(name = "todos")
@Entity
@Data
@Builder
@AllArgsConstructor
@Valid
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    @NotNull(message = "The Title Is Required!")
    @Size(min = 1, message = "The Title Can't Be Empty!")
    private String title;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdDate;

    @Column(name = "user_id")
    private Integer userId;

    public Todo() {

        this.createdDate = new Date();

    }

    public static Todo fromDtoToEntity(TodoDto todoDto) {

        return Todo.builder()

                .id(todoDto.getId())

                .title(todoDto.getTitle())

                .createdDate(todoDto.getCreatedDate())

                .userId(todoDto.getUserId())

                .build();

    }

}
