package com.example.demo.converters;


import com.example.demo.enums.TaskPriority;
import org.springframework.core.convert.converter.Converter;

public class StringToTaskPriorityEnumConverter implements Converter<String, TaskPriority> {
    @Override
    public TaskPriority convert(String priority) {
        return TaskPriority.valueOf(priority.toUpperCase());
    }
}