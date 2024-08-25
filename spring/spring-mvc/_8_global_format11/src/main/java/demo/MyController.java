package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping(value = "/ack", method = RequestMethod.GET)
    public String ack() {
        return "ack";
    }

    @RequestMapping("/testFormat1")
    public String testFormat(Address address){
        return "ok";
    }

}
