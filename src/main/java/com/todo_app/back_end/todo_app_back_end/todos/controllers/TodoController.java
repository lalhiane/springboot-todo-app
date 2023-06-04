package com.todo_app.back_end.todo_app_back_end.todos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.graphql.data.method.annotation.Argument;

import org.springframework.graphql.data.method.annotation.MutationMapping;

import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.security.access.annotation.Secured;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;

import com.todo_app.back_end.todo_app_back_end.todos.model.dto.TodoDto;

import com.todo_app.back_end.todo_app_back_end.todos.model.entity.Todo;

import com.todo_app.back_end.todo_app_back_end.todos.model.todo_input.TodoInput;

import com.todo_app.back_end.todo_app_back_end.todos.services.TodoService;

import jakarta.validation.Valid;

@Controller
public class TodoController extends BaseController {

    @Autowired
    private TodoService todoService;

    @Secured("ROLE_USER")
    @QueryMapping
    public List<Todo> findAllTodos() {

        return todoService.findAll(getCurrentUser().getId());

    }

    @Secured("ROLE_USER")
    @QueryMapping
    public TodoDto findTodoById(@Argument Integer id) {

        return todoService.findById(id, getCurrentUser().getId());

    }

    @Secured("ROLE_USER")
    @QueryMapping
    public TodoDto findTodoByTitle(@Argument String title) {

        return todoService.findByTitle(title, getCurrentUser().getId());

    }

    @PreAuthorize("hasRole('USER')")
    @MutationMapping
    public TodoDto saveTodo(@Valid @Argument(name = "input") TodoInput todoInput) {

        todoInput.setUserId(getCurrentUser().getId());

        return todoService.save(todoInput);

    }

    @Secured("ROLE_USER")
    @MutationMapping
    public Integer deleteTodoById(@Argument Integer id) {

        return todoService.deleteById(id, getCurrentUser().getId());

    }

}
