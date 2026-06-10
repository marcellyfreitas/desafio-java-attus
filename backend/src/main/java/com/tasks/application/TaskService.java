package com.tasks.application;

import com.tasks.domain.Task;
import com.tasks.domain.TaskRepository;
import com.tasks.domain.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, String description) {
        Task task = new Task(title, description, TaskStatus.PENDING);
        return repository.save(task);
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task update(Long id, String title, String description, TaskStatus status) {
        Task task = findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        return repository.save(task);
    }

    public void delete(Long id) {
        Task task = findById(id);
        repository.delete(task);
    }
}
