package guru.springframework.spring6restmvc.controller;

import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    public String sayHello() {
        System.out.println("Now in TestController");
        return "Hello World! - from TestController";
    }
}
