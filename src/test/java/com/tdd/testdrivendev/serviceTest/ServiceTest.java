package com.tdd.testdrivendev.serviceTest;

import com.tdd.testdrivendev.model.Task;
import com.tdd.testdrivendev.repository.TaskRepository;
import com.tdd.testdrivendev.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @ParameterizedTest
    @CsvSource({
            "101, 'Updated Task-1'"
    })
    void updateTaskNameServiceTest(int id, String name){

        //arrange
        Task task = new Task();
        task.setId(101);
        task.setName("Initial Name");

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));
                //.thenReturn(task);
        assertNotNull(task);

        //act
        Task updatedTask = taskService.update(id, name);

        //assert
        assertNotNull(updatedTask);
        assertEquals(name, updatedTask.getName());

        verify(taskRepository, times(1)).findById(id);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void findAllTasksServiceTest(){
        when(taskRepository.findAll()).thenReturn(List.of(new Task(111,"Task", "Initial Name")));

        List<Task> tasks = taskService.getAllTasks();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals(111, tasks.getFirst().getId());

        //Verify that task-repository is being called.
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void findTaskByIdServiceTest(){
        Task task = new Task();
        task.setId(101);
        task.setName("Initial Name");

        when(taskRepository.findById(101)).thenReturn(Optional.of(task));

        Task taskById = taskService.getTaskById(101);
        assertNotNull(taskById);
        assertEquals(task, taskById);
        verify(taskRepository, times(1)).findById(101);
    }

    @Test
    void findTaskById_NotFoundTest(){
        Task task = new Task();
        task.setId(102);
        task.setName("Initial Name-2");

        when(taskRepository.findById(122)).thenReturn(Optional.empty());

        Task taskById = taskService.getTaskById(122);
        assertNull(taskById);

        verify(taskRepository, times(1)).findById(122);

    }

    @Test
    void saveTaskServiceTest(){
        Task task = new Task();
        task.setId(101);
        task.setName("Task");
        task.setDescription("Initial Name-1");

        when(taskRepository.save(any(Task.class))).thenReturn(new Task(101,"Task", "Initial Name-1"));

        Task savedTask = taskService.saveTask(task);

        assertNotNull(savedTask);
        assertEquals(task.getId(),savedTask.getId());

        verify(taskRepository, times(1)).save(any(Task.class));
    }
}
