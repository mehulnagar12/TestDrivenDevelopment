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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}
