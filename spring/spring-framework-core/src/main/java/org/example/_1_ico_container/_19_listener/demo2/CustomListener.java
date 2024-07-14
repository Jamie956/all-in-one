package org.example._1_ico_container._19_listener.demo2;

import org.springframework.context.ApplicationListener;

public class CustomListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("ApplicationListener.onApplicationEvent: " + event.toString());
    }
}
