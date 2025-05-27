package com.tomasz_laboj.simplified_todo.repository;


import java.io.Serializable;

import org.apache.kafka.common.protocol.Message;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDoItem implements Serializable {

    public enum Label {
        GREEN,
        BLUE,
        RED
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;
    private String label;
    private boolean isDone;

    protected ToDoItem() {}

    public ToDoItem(String title, String label, boolean isDone) {
        this.title = title;
        this.label = label;
        this.isDone = isDone;
        this.label = label;
    }

    public ToDoItem(Long id, String title, String label, boolean isDone) {
        this.id = id;
        this.title = title;
        this.label = label;
        this.isDone = isDone;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; };

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public boolean isDone() {return isDone;}
    public void setIsDone(boolean isDone) { this.isDone = isDone; }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", label='" + label + '\'' +
                ", isDone=" + isDone +
                '}';
    }
}