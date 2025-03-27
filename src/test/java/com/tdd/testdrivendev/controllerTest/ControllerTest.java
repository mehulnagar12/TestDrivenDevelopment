package com.tdd.testdrivendev.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.testdrivendev.controller.TaskController;
import com.tdd.testdrivendev.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.tdd.testdrivendev.model.Task;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class ControllerTest {

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTasksTest() throws Exception {

        //arrange
        List<Task> tasks = Arrays.asList(new Task(102,"Task-2","description-2"),
                                        new Task(103,"Task-3","description-3"));
        when(taskService.getAllTasks()).thenReturn(tasks);

        //act & assert
        mockMvc.perform(get("/alltasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void getTaskByIdTest() throws Exception {
        Task task = new Task(101,"Task-1","description-1");

        when(taskService.getTaskById(101)).thenReturn(task);

        mockMvc.perform(get("/task/101").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addTaskTest() throws Exception {
        Task task = new Task(102,"Task-2","description-2");
        String json = objectMapper.writeValueAsString(task);

        when(taskService.saveTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/task/add").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(taskService, times(1)).saveTask(any(Task.class));
    }
}
