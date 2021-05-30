package saechim.interior.categoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categoryservice")
@RestController
public class CategoryController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-CategoryService-World";
    }
}
