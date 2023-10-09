package com.example.demo.models;

import com.example.demo.enums.TaskPriority;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "subtask")
public class SubtaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private boolean completed;

    @Enumerated(EnumType.ORDINAL)
    private TaskPriority priority;

    @Column(name = "creationdate", updatable=false)
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name = "updateddate")
    @UpdateTimestamp
    private Timestamp updatedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;
}