package com.example.demo.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort.Direction;

public class StringToSortDirectionEnumConverter implements Converter<String, Direction> {
    @Override
    public Direction convert(String direction) {
        return Direction.valueOf(direction.toUpperCase());
    }
}
