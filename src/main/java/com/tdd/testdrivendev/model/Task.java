package com.tdd.testdrivendev.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Task {

    @Id
    private int id;
    private String name;
    private String description;
}
