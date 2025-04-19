package com.tomasz_laboj.simplified_todo.controller;

import java.util.List;
import java.util.Optional;

import com.tomasz_laboj.simplified_todo.repository.ToDoItem;
import com.tomasz_laboj.simplified_todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoController {
    @Autowired
    private ToDoRepository repository;

    @GetMapping("/")
    public Iterable<ToDoItem> get() {

        return repository.findAll();
    }

    @PostMapping("/")
    public ToDoItem postToDoItem(@RequestBody String title) {
        ToDoItem toDoItem = new ToDoItem(title, false);
        ToDoItem inserted = repository.save(toDoItem);
        return inserted;
    }

    // example curl
    // curl -X PUT localhost:8080/1 -H "content-type:application/json" -d '{"title": "task updated", "isDone": true}'
    @PutMapping("/{id}")
    public ToDoItem updateToDoItem(@RequestBody ToDoItem updatedToDo, @PathVariable Long id) {
        Optional<ToDoItem> toDoItem = repository.findById(id);

        if (toDoItem.isPresent()) {
            ToDoItem item = toDoItem.get();
            item.setTitle(updatedToDo.getTitle());
            item.setIsDone(updatedToDo.isDone());
            return repository.save(item);
        } else {
            return null;
        }
    }

}