package com.tomasz_laboj.simplified_todo.controller;

import java.util.List;
import java.util.Optional;

import com.tomasz_laboj.simplified_todo.kafka.KafkaProducer;
import com.tomasz_laboj.simplified_todo.repository.ToDoItem;
import com.tomasz_laboj.simplified_todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoController {
    @Autowired
    private ToDoRepository repository;

    @Autowired
    private KafkaProducer producer;

    @GetMapping("/")
    public Iterable<ToDoItem> get() {
        return repository.findAll();
    }

    @PostMapping("/")
    public ToDoItem postToDoItem(@RequestBody ToDoItemRequest toDoItemRequest) {
        ToDoItem toDoItem = new ToDoItem(toDoItemRequest.getTitle(), toDoItemRequest.getLabel(), false);
        ToDoItem inserted = repository.save(toDoItem);

        producer.sendCreated(toDoItem);
        return inserted;
    }

    @PutMapping("/{id}")
    public ToDoItem updateToDoItem(@RequestBody ToDoItem updatedToDo, @PathVariable Long id) {
        Optional<ToDoItem> toDoItem = repository.findById(id);

        if (toDoItem.isPresent()) {
            ToDoItem itemToUpdate = toDoItem.get();
            ToDoItem originalTodo = new ToDoItem(toDoItem.get().getId(), toDoItem.get().getTitle(), toDoItem.get().getLabel(), toDoItem.get().isDone());

            itemToUpdate.setTitle(updatedToDo.getTitle());
            itemToUpdate.setLabel(updatedToDo.getLabel());
            itemToUpdate.setIsDone(updatedToDo.isDone());
            producer.sendUpdated(originalTodo, itemToUpdate);
            return repository.save(itemToUpdate);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteToDoItem(@PathVariable Long id) {
        Optional<ToDoItem> toDoItem = repository.findById(id);
        if (toDoItem.isPresent()) {
            producer.sendDeleted(toDoItem.get());
        }
        repository.deleteById(id);
    }

}