package com.example.demo.configuration;

import com.example.demo.converters.StringToSortDirectionEnumConverter;
import com.example.demo.converters.StringToTaskPriorityEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToTaskPriorityEnumConverter());
        registry.addConverter(new StringToSortDirectionEnumConverter());
    }
}
