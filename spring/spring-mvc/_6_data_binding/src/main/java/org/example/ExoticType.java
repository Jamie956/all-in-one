package org.example;

public class ExoticType {

    private String name;

    // http 400 if ignore default constructor
    public ExoticType() {
    }

    public ExoticType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
