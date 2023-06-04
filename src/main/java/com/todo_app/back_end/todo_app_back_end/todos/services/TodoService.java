package com.todo_app.back_end.todo_app_back_end.todos.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.todo_app.back_end.todo_app_back_end.Exceptions.ConflictException;

import com.todo_app.back_end.todo_app_back_end.Exceptions.NotFoundException;

import com.todo_app.back_end.todo_app_back_end.todos.model.dto.TodoDto;

import com.todo_app.back_end.todo_app_back_end.todos.model.entity.Todo;

import com.todo_app.back_end.todo_app_back_end.todos.model.todo_input.TodoInput;

import com.todo_app.back_end.todo_app_back_end.todos.repositories.TodoRepo;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;

    public List<Todo> findAll(Integer userId) {

        return todoRepo.findByUserId(userId);

    }

    public TodoDto findById(Integer id, Integer userId) {

        if (todoRepo.existsByIdAndUserId(id, userId)) {

            Optional<Todo> todo = todoRepo.findById(id);

            return TodoDto.fromEntityToDto(todo.get());

        }

        throw new NotFoundException("No Record With The Id " + id + " Was Found!");

    }

    public TodoDto findByTitle(String title, Integer userId) {

        if (todoRepo.existsByTitleAndUserId(title, userId)) {

            Optional<Todo> todo = todoRepo.findByTitleAndUserId(title, userId);

            return TodoDto.fromEntityToDto(todo.get());

        }

        throw new NotFoundException("No Record With The Title " + "' " + title + " '" + " Was Found!");

    }

    public TodoDto save(TodoInput todoInput) {

        if (!todoRepo.existsByTitleAndUserId(todoInput.getTitle(), todoInput.getUserId())) {

            Todo todo = todoRepo.save(todoInput.getTodoEntity());

            return TodoDto.fromEntityToDto(todo);

        }

        throw new ConflictException(String.format("Todo With Title '%s' Is Already Exists!", todoInput.getTitle()));

    }

    public Integer deleteById(Integer id, Integer userId) {

        if (findById(id, userId) != null) {

            todoRepo.deleteById(id);

            return id;

        }

        return -1;

    }

}
