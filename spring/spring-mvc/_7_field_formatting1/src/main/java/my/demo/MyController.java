package my.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @GetMapping("/ack")
    public String ack() {
        return "ack";
    }

    @RequestMapping("/testFormat")
    public String testFormat(Address address){
        return "ok";
    }

}
