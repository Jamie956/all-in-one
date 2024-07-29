package org.example._2_resources._7_autowire_loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

public class X {
    @Autowired
    public ResourceLoader loader;
}
