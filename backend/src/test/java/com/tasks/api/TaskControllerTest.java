package com.tasks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.application.TaskService;
import com.tasks.domain.Task;
import com.tasks.domain.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private TaskService service;

    private Task createTask(Long id, String title, String description, TaskStatus status) {
        Task t = new Task(title, description, status);
        t.setId(id);
        t.setCreatedAt(LocalDateTime.now());
        return t;
    }

    @Test
    void post_shouldCreateTask() throws Exception {
        Task saved = createTask(1L, "Tarefa", "Desc", TaskStatus.PENDING);
        when(service.create(anyString(), anyString())).thenReturn(saved);

        TaskRequest request = new TaskRequest();
        request.setTitle("Tarefa");
        request.setDescription("Desc");

        mvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Tarefa"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void get_shouldListAllTasks() throws Exception {
        List<Task> tasks = List.of(
                createTask(1L, "Task 1", "Desc 1", TaskStatus.PENDING),
                createTask(2L, "Task 2", "Desc 2", TaskStatus.DONE)
        );
        when(service.findAll()).thenReturn(tasks);

        mvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].status").value("DONE"));
    }

    @Test
    void post_shouldReturn400WhenTitleBlank() throws Exception {
        TaskRequest request = new TaskRequest();
        request.setTitle("");

        mvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }
}
