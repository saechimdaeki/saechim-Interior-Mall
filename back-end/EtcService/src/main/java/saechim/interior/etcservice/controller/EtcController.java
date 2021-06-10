package saechim.interior.etcservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class EtcController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-EtcService-World";
    }
}
