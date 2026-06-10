package com.tasks.application;

import com.tasks.domain.Task;
import com.tasks.domain.TaskRepository;
import com.tasks.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService(repository);
    }

    @Test
    void create_shouldSaveTaskWithPendingStatus() {
        when(repository.save(any(Task.class))).thenAnswer(i -> {
            Task t = i.getArgument(0);
            t.setId(1L);
            t.setCreatedAt(java.time.LocalDateTime.now());
            return t;
        });

        Task result = service.create("Nova tarefa", "Descricao");

        assertEquals("Nova tarefa", result.getTitle());
        assertEquals("Descricao", result.getDescription());
        assertEquals(TaskStatus.PENDING, result.getStatus());
        assertNotNull(result.getCreatedAt());

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        verify(repository).save(captor.capture());
        assertEquals(TaskStatus.PENDING, captor.getValue().getStatus());
    }

    @Test
    void findById_shouldReturnTaskWhenFound() {
        Task task = new Task("Titulo", "Desc", TaskStatus.IN_PROGRESS);
        task.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task result = service.findById(1L);
        assertEquals("Titulo", result.getTitle());
    }

    @Test
    void findById_shouldThrowWhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void update_shouldModifyTaskFields() {
        Task existing = new Task("Old", "Old desc", TaskStatus.PENDING);
        existing.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task result = service.update(1L, "New title", "New desc", TaskStatus.DONE);

        assertEquals("New title", result.getTitle());
        assertEquals("New desc", result.getDescription());
        assertEquals(TaskStatus.DONE, result.getStatus());
    }

    @Test
    void delete_shouldRemoveTask() {
        Task task = new Task("Titulo", "Desc", TaskStatus.PENDING);
        task.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(repository).delete(task);

        service.delete(1L);
        verify(repository).delete(task);
    }
}
