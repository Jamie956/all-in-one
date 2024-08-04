package org.example;


import java.beans.PropertyEditorSupport;

// converts string representation to ExoticType object
public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(new ExoticType(text.toUpperCase()));
    }
}