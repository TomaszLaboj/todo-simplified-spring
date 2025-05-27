package com.tomasz_laboj.simplified_todo.controller;

public class ToDoItemRequest {
    private String title;
    private String label;

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
