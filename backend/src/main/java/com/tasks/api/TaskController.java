package com.tasks.api;

import com.tasks.application.TaskService;
import com.tasks.domain.Task;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
        Task task = service.create(request.getTitle(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskResponse.fromEntity(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> tasks = service.findAll().stream()
                .map(TaskResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable Long id) {
        Task task = service.findById(id);
        return ResponseEntity.ok(TaskResponse.fromEntity(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        Task task = service.update(id, request.getTitle(), request.getDescription(), request.getStatus());
        return ResponseEntity.ok(TaskResponse.fromEntity(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
