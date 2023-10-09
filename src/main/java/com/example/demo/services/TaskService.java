package com.example.demo.services;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.enums.TaskPriority;
import com.example.demo.exceptions.CreateTaskException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.UpdateTaskException;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;


public interface TaskService {
    List<TaskDTO> getAllTasks(TaskPriority priority, Boolean completed, String sortField, Direction sortDirection);

    TaskDTO getTaskById(Long id) throws TaskNotFoundException;

    TaskDTO createTask(TaskDTO taskDTO) throws CreateTaskException;

    String updateTask(TaskDTO taskDTO) throws UpdateTaskException;

    String deteleTask(Long id) throws TaskNotFoundException;
}
