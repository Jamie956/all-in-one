package org.example;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertController {
    /**
     * convert request json to method param string
     * <p>
     * method: post
     * url: http://localhost:8080/convertBody
     * body: { "name": "aa", "age": 11}
     */
    @RequestMapping("/convertBody")
    public String convertBody(@RequestBody String body) {
        System.out.println("------->RequestBody: " + body);
        return "index";
    }

    /**
     * mapping RequestEntity to method param
     * <p>
     * method: post
     * url: http://localhost:8080/convertRequestEntity
     * body: { "name": "aa", "age": 11}
     */
    @RequestMapping("/convertRequestEntity")
    public String convertRequestEntity(RequestEntity<String> requestEntity) {
        System.out.println("------->requestHeader: " + requestEntity.getHeaders());
        System.out.println("------->requestBody: " + requestEntity.getBody());
        return "index";
    }

    /**
     * convert response from object to json
     * Add dependency jackson-databind parse object to json for response body
     * <p>
     * method: get
     * url: http://localhost:8080/convertResponse
     */
    @RequestMapping("/convertResponse")
    @ResponseBody
    public User convertResponse() {
        return new User("aa", "11", "aa");
    }

}
