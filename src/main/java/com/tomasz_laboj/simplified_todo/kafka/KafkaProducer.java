package com.tomasz_laboj.simplified_todo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.tomasz_laboj.simplified_todo.repository.ToDoItem;

@Component
public class KafkaProducer {

    private class TodoItemUpdated {

        public ToDoItem original;
        public ToDoItem updated;

        public TodoItemUpdated(ToDoItem original, ToDoItem updated) {
            this.original = original;
            this.updated = updated;
        }
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendCreated(ToDoItem toDoItem) {
        kafkaTemplate.send("created", toDoItem);
    };

    public void sendUpdated(ToDoItem originalToDoItem, ToDoItem updatedToDoItem) {
        kafkaTemplate.send("updated", new TodoItemUpdated( originalToDoItem, updatedToDoItem));
    }

    public void sendDeleted(ToDoItem toDoItem) {
        kafkaTemplate.send("deleted", toDoItem);
    }
}
