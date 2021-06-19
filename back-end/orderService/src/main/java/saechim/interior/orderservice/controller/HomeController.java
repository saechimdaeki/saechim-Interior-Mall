package saechim.interior.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/homeservice")
@RestController
public class HomeController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-HomeService-World";
    }
}
