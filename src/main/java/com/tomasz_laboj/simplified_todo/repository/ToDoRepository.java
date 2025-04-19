package com.tomasz_laboj.simplified_todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoItem, Long> {

    List<ToDoItem> findByTitle(String title);

    ToDoItem findById(long id);
}
