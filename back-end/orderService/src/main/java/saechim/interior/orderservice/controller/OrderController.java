package saechim.interior.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class OrderController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-HomeService-World";
    }
}
