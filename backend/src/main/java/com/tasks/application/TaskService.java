package com.tasks.application;

import com.tasks.domain.Task;
import com.tasks.domain.TaskRepository;
import com.tasks.domain.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title, String description) {
        Task task = new Task(title, description, TaskStatus.PENDING);
        Task saved = repository.save(task);
        log.info("Tarefa criada: id={}, título='{}'", saved.getId(), saved.getTitle());
        return saved;
    }

    public List<Task> findAll() {
        List<Task> tasks = repository.findAll();
        log.debug("Listadas {} tarefas", tasks.size());
        return tasks;
    }

    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tarefa não encontrada: id={}", id);
                    return new TaskNotFoundException(id);
                });
    }

    public Task update(Long id, String title, String description, TaskStatus status) {
        Task task = findById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        Task saved = repository.save(task);
        log.info("Tarefa atualizada: id={}, título='{}', status={}", saved.getId(), saved.getTitle(), saved.getStatus());
        return saved;
    }

    public void delete(Long id) {
        Task task = findById(id);
        repository.delete(task);
        log.info("Tarefa removida: id={}, título='{}'", id, task.getTitle());
    }
}
