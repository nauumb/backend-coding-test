package com.example.demo.dtos;

import com.example.demo.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TaskDTO {
    private Long id;

    private String description;

    private boolean completed;

    private TaskPriority priority;

    private Timestamp creationDate;

    private Timestamp updatedDate;

    List<SubtaskDTO> subtasks = new ArrayList<>();
}
