package com.example.demo.specifications;

import com.example.demo.enums.TaskPriority;
import com.example.demo.models.TaskEntity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@NoArgsConstructor
public class TaskSpecification {

    public static Specification<TaskEntity> byCompletionStatus(Boolean completed) {
        return (root, query, builder) -> Objects.nonNull(completed) ? builder.equal(root.get("completed"), completed) : null;
    }

    public static Specification<TaskEntity> byPriority(TaskPriority priority) {
        return (root, query, builder) -> Objects.nonNull(priority) ? builder.equal(root.get("priority"), priority) : null;
    }
}
