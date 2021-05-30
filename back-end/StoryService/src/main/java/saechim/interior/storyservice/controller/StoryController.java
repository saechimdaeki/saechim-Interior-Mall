package saechim.interior.storyservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/storyservice")
@RestController
public class StoryController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-StoryService-World";
    }
}
