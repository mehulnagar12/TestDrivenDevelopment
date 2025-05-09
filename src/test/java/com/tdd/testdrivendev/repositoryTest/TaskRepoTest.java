package com.tdd.testdrivendev.repositoryTest;

import com.tdd.testdrivendev.model.Task;
import com.tdd.testdrivendev.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepoTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void saveTaskTest(){
        Task task = new Task();
        task.setId(101);
        task.setName("Task-1");
        task.setDescription("Sample Task 1");

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask);
        assertEquals("Task-1", savedTask.getName());
    }

    @Test
    void findAllTasksTest(){
        List<Task> tasksList = taskRepository.findAll();

        assertNotNull(tasksList);
        assertEquals(1, tasksList.size());
    }

    @Test
    void findTaskByIdTest(){
        Optional<Task> task = taskRepository.findById(101);

        assertNotNull(task);
        assertFalse(task.isEmpty());
        assertEquals(101, task.get().getId());
    }

    @Test
    void deleteTaskTest(){
        Task task = new Task();
        task.setId(102);
        task.setName("Task-2");

        Task savedTask = taskRepository.save(task);
        Optional<Task> taskOptional = taskRepository.findById(102);
        assertTrue(taskOptional.isPresent(), "Task should exist");

        taskRepository.deleteById(102);

        Optional<Task> deletedTask = taskRepository.findById(102);
        assertFalse(deletedTask.isPresent(), "Task should not exist");
    }

    @Test
    void updateTaskTest(){
        Task task = new Task();
        task.setId(102);
        task.setName("Task-2");

        Task savedTask = taskRepository.save(task);
        Optional<Task> taskOptional = taskRepository.findById(102);
        assertTrue(taskOptional.isPresent(), "Task should exist");

        taskOptional.get().setName("Update Task-2");
        Task updatedTask = taskRepository.save(taskOptional.get());
        assertEquals("Update Task-2", updatedTask.getName());
    }
}
