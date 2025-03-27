package com.tdd.testdrivendev.service;

import com.tdd.testdrivendev.model.Task;
import com.tdd.testdrivendev.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task update(int id, String name) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task task1 = task.get();
            task1.setName(name);
            return taskRepository.save(task1);
        }
        return null;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
}
