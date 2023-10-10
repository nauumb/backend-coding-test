package com.example.demo.controllers;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.enums.TaskPriority;
import com.example.demo.services.TaskService;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratedpower/v1/task")
public class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get all tasks from the database. Can be filtered by priority and/or completation. Also ordered by any attribute of a task.
     * @param priority Priority level of a task. Accepted values defined by TaskPriority enum.
     * @param completed Completion status of a task.
     * @param sortField Defines the field to be ordered by. Can be ordered by any attribute of a task. By default, creation date descendant.
     * @param sortDirection Defines de sort direction. Can be ascendant or descendant.
     * @return a response entity with a list of task and code status 200 in case of no error.
     */
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false, defaultValue = "creationDate") String sortField,
            @RequestParam(required = false, defaultValue = "DESC") Direction sortDirection) {
        return new ResponseEntity(taskService.getAllTasks(priority, completed, sortField, sortDirection), HttpStatus.OK);
    }

    /**
     * Search a task by the specified id.
     * @param id Identifier of the task to be searched.
     * @return a response entity with task dto of the specified id and code status 200 in case of no error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return new ResponseEntity(taskService.getTaskById(id), HttpStatus.OK);
    }

    /**
     * Create a new task with the specified data.
     * @param taskDTO the task to be persisted.
     * @return a response entity with the task dto created and code status 201 in case of no error.
     */
    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return new ResponseEntity(taskService.createTask(taskDTO), HttpStatus.CREATED);
    }

    /**
     * Update a task with the specified data.
     * @param taskDTO the task to be updated.
     * @return a response entity with the task dto updated and a code status 200 in case of no error.
     */
    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
        return new ResponseEntity(taskService.updateTask(taskDTO), HttpStatus.OK);
    }

    /**
     * Delete a task by identifier.
     * @param id identifier of the task to be deleted.
     * @return a response entity with a confirmation message and a code status 200 in case of no error.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        return new ResponseEntity(taskService.deteleTask(id), HttpStatus.OK);
    }
}
