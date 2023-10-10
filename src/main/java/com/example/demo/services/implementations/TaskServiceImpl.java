package com.example.demo.services.implementations;

import com.example.demo.dtos.TaskDTO;
import com.example.demo.enums.TaskPriority;
import com.example.demo.exceptions.CreateTaskException;
import com.example.demo.exceptions.TaskNotFoundException;
import com.example.demo.exceptions.UpdateTaskException;
import com.example.demo.models.TaskEntity;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.demo.specifications.TaskSpecification.byCompletionStatus;
import static com.example.demo.specifications.TaskSpecification.byPriority;
import static java.text.MessageFormat.format;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper taskMapper;

    TaskServiceImpl(TaskRepository taskRepository, ModelMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Get all tasks from the database. Can be filtered by priority and/or completation. Also ordered by any attribute of a task.
     * @param priority Priority level of a task. Accepted values defined by TaskPriority enum.
     * @param completed Completion status of a task.
     * @param sortField Defines the field to be ordered by. Can be ordered by any attribute of a task.
     * @param sortDirection Defines de sort direction. Can be ascendant or descendant.
     * @return A list of task dto that meet the search criteria.
     */
    @Override
    public List<TaskDTO> getAllTasks(TaskPriority priority, Boolean completed, String sortField, Direction sortDirection) {

        Specification<TaskEntity> filters = Specification
                .where(byCompletionStatus(completed))
                .and(byPriority(priority));

        Sort sort = Sort.by(sortDirection, sortField);

        return taskRepository.findAll(filters, sort)
                .stream()
                .map(task -> taskMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Search a task by the specified id.
     * @param id Identifier of the task to be searched.
     * @return a task dto of the specified id.
     * @throws TaskNotFoundException throw if the specified id does not match with any task stored in the database.
     */
    @Override
    public TaskDTO getTaskById(Long id) throws TaskNotFoundException {
        return taskMapper.map(taskRepository.findById(id).orElseThrow(() -> {
            throw new TaskNotFoundException(format("Task not found with id [{0}].", id));
        }), TaskDTO.class);
    }

    /**
     * Create a new task with the specified data.
     * @param taskDTO the task to be persisted.
     * @return the task persisted.
     * @throws CreateTaskException throw if specifies an identifier for the task to be created.
     */
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) throws CreateTaskException {

        boolean taskWithId = !Objects.isNull(taskDTO.getId());
        if (taskWithId) {
            throw new CreateTaskException("Identifiers must not be specified in the creation process.");
        }

        TaskEntity taskEntity = taskMapper.map(taskDTO, TaskEntity.class);
        //assing parent task to all subtasks.
        taskEntity.getSubtasks().forEach(subtaskEntity -> subtaskEntity.setTask(taskEntity));

        return taskMapper.map(taskRepository.save(taskEntity), TaskDTO.class);
    }

    /**
     * Update a task with the specified data.
     * @param taskDTO the task to be updated.
     * @return the task updated.
     * @throws UpdateTaskException throw if the identifier is not specified.
     */
    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) throws UpdateTaskException {

        Long id = taskDTO.getId();
        if (Objects.isNull(id)) {
            throw new UpdateTaskException("Task identifier wasn't specified.");
        }

        boolean taskNotExist = !taskRepository.existsById(id);
        if (taskNotExist) {
            throw new TaskNotFoundException(format("Task not found with id [{0}].", id));
        }

        TaskEntity taskEntity = taskMapper.map(taskDTO, TaskEntity.class);
        //assing parent task to all subtasks.
        taskEntity.getSubtasks().forEach(subtaskEntity -> subtaskEntity.setTask(taskEntity));

        return taskMapper.map(taskRepository.save(taskEntity), TaskDTO.class);
    }

    /**
     * Delete a task by identifier.
     * @param id identifier of the task to be deleted.
     * @return a confirmation message.
     * @throws TaskNotFoundException throw if the specified identifier does not exist.
     */
    @Override
    public String deteleTask(Long id) throws TaskNotFoundException {
        taskRepository.delete(taskRepository.findById(id).orElseThrow(() -> {
            throw new TaskNotFoundException(format("Task not found with id [{0}].", id));
        }));
        return format("Task with id [{0}] deleted.", id);
    }
}