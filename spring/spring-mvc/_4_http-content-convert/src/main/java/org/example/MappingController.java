package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MappingController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * http://localhost:8080/test1?name=11&address=123&id=12
     * method: get/post
     */
    @RequestMapping(
            value = {"mapping1", "mapping2"},
            method = {RequestMethod.GET, RequestMethod.POST},
            //exist name, not exist password, address equal 123, id not equal 11
            params = {"name", "!password", "address=123", "id!=11"},
            //host equal localhost:8080
            headers = {"Host=localhost:8080"}
    )
    public String mapping() {
        return "index";
    }

    /**
     * ?: 任意单个字符
     * /a?a
     * http://localhost:8080/aaa -> ok
     * http://localhost:8080/a1a -> ok
     * http://localhost:8080/aa -> 404
     *
     * *: 任意0个或多个字符
     * /a*a
     *
     * **: 任意一层或多层目录
     * /**
     */
    @RequestMapping("/a?a")
    public String expressionMatch() {
        return "index";
    }

    /**
     * PathVariable convert path var
     * http://localhost:8080/user/11
     */
    @RequestMapping("/user/{id}")
    public String pathVarTest(@PathVariable("id") String id) {
        return "index";
    }

    /**
     * http://localhost:8080/paramTest?name=22
     * mapping http servlet request to method param
     */
    @RequestMapping("paramTest")
    public String paramTest(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "index";
    }

    /**
     * mapping param to method param
     * http://localhost:8080/paramStr?name=22&address=a&address=b
     */
    @RequestMapping("paramStr")
    public String paramStr(String name, String[] address) {
        return "index";
    }

    /**
     * mapping some of param to method param
     * http://localhost:8080/requestParam?na_me=22
     *
     * RequestParam:
     * name
     * value
     * required
     * defaultValue
     */
    @RequestMapping("requestParam")
    public String requestParam(@RequestParam("na_me") String name) {
        return "index";
    }

    /**
     * mapping request header to method param
     * http://localhost:8080/header
     */
    @RequestMapping("/header")
    public String header(@RequestHeader("Host") String host) {
        return "index";
    }

    /**
     * mapping request cookies to method param
     */
    @RequestMapping("/cookie")
    public String cookie(@CookieValue("JESSIONID") String jessionid) {
        return "index";
    }

    /**
     * mapping request param to method param
     * http://localhost:8080/objParam?name=22&address=a&address=b
     */
    @RequestMapping("/objParam")
    public String objParam(User user) {
        return "index";
    }
}
