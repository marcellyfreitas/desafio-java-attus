package com.tasks.api;

import com.tasks.domain.Task;
import com.tasks.domain.TaskStatus;
import java.time.LocalDateTime;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    public static TaskResponse fromEntity(Task task) {
        TaskResponse r = new TaskResponse();
        r.id = task.getId();
        r.title = task.getTitle();
        r.description = task.getDescription();
        r.status = task.getStatus().name();
        r.createdAt = task.getCreatedAt();
        return r;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
