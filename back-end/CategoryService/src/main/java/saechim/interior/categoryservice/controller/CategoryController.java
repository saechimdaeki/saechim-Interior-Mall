package saechim.interior.categoryservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-CategoryService-World";
    }

    // todo 유저가 주문한 리스트 유저마이크로서비스에 보내고

    // todo orderservice에 저장시키자.
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka/{message}")
    public void test(@PathVariable String message){
        this.kafkaTemplate.send("order-saechim",message);
    }

}
