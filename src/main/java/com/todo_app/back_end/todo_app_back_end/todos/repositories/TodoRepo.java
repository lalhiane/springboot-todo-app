package com.todo_app.back_end.todo_app_back_end.todos.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_app.back_end.todo_app_back_end.todos.model.entity.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    List<Todo> findByUserId(Integer userId);

    Optional<Todo> findByTitle(String title);

    Optional<Todo> findByTitleAndUserId(String title, Integer userId);

    boolean existsByIdAndUserId(Integer id, Integer userId);

    boolean existsByTitle(String title);

    boolean existsByTitleAndUserId(String title, Integer userId);

}
