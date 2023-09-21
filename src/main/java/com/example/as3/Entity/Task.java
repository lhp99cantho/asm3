package com.example.as3.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int taskId;

    @Column(length = 250, nullable = false)
    @NotNull
    protected String title;

    @Column(length = 250)
    protected String description;

    @Column(nullable = false)
    @NotNull
    protected Date dueDate;

    @Column(nullable = false)
    protected boolean status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonBackReference
    protected User user;
}
