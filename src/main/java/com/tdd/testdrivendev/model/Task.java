package com.tdd.testdrivendev.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Task {

    @Id
    private int id;
    private String name;
    private String description;

    public Task() {

    }
}
