package saechim.interior.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/userservice")
@RestController
public class UserController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-UserService-World";
    }
}
