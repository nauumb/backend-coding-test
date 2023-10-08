package com.example.demo.models;

import com.example.demo.enums.TaskPriority;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "priority")
    @Enumerated(EnumType.ORDINAL)
    private TaskPriority priority;

    @Column(name = "creationdate", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name = "updateddate")
    @UpdateTimestamp
    private Timestamp updatedDate;
}