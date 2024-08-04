package org.example._4_data_binding._2_custom_editor;


import java.beans.PropertyEditorSupport;

// converts string representation to ExoticType object
public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(new ExoticType(text.toUpperCase()));
    }
}