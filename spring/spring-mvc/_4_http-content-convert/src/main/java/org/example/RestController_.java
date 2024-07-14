package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//all method under this class able to parse return value as response body
@RestController
public class RestController_ {
    /**
     * RestController include ResponseBody able to convert return value
     * method: get
     * url: http://localhost:8080/rest
     */
    @RequestMapping("/rest")
    //@ResponseBody include ResponseBody
    public User rest() {
        return new User("aa", "11", "aq");
    }
}

