package com.example.demo6;

import com.example.SharedServer;

public class TimeServer {
    public static void main(String[] args) {
        new SharedServer().run(new TimeServerHandler(), 8081);
    }

}
