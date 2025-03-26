package com.tdd.testdrivendev.controller;

import com.tdd.testdrivendev.model.Task;
import com.tdd.testdrivendev.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/alltasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}
