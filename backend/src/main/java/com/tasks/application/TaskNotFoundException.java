package com.tasks.application;

public class TaskNotFoundException extends RuntimeException {
    private final Long id;

    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
        this.id = id;
    }

    public Long getTaskId() { return id; }
}
