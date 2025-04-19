package com.tomasz_laboj.simplified_todo.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDoItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;

    private boolean isDone;

    protected ToDoItem() {}

    public ToDoItem(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; };
    public boolean isDone() {
        return isDone;
    }
    public void setIsDone(boolean isDone) { this.isDone = isDone; }
}