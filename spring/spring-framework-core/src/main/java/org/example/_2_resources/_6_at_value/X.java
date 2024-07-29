package org.example._2_resources._6_at_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


public class X {
    @Value("org/example/_2_resources/_4_class_path/Main.class")
    public Resource resource;
}
