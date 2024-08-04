package org.example;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    private final PropertyEditorRegistrar customPropertyEditorRegistrar;

    MyController(PropertyEditorRegistrar propertyEditorRegistrar) {
        this.customPropertyEditorRegistrar = propertyEditorRegistrar;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        this.customPropertyEditorRegistrar.registerCustomEditors(binder);
    }

    @GetMapping("/a")
    @ResponseBody
    public String a() {
        return "ack";
    }

    @PostMapping("/b")
    @ResponseBody
    public String b(@RequestBody DependsOnExoticType o) {
        return o.getType().getName();
    }

    @PostMapping("/c")
    @ResponseBody
    public String c(@RequestBody ExoticType a) {
        return a.getName();
    }

}
