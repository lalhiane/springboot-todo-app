package com.todo_app.back_end.todo_app_back_end.todos.controllers;

import org.springframework.security.core.context.SecurityContextHolder;

import com.todo_app.back_end.todo_app_back_end.security.models.User;

public class BaseController {

    public User getCurrentUser() {

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

}
