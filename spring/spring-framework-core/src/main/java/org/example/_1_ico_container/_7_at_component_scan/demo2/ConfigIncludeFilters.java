package org.example._1_ico_container._7_at_component_scan.demo2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(value = "org.example._1_ico_container._7_at_component_scan.demo2",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MyComponent.class)
)
public class ConfigIncludeFilters {
}
