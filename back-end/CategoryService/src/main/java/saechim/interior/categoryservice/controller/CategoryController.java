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

    // todo 유저가 주문한 리스트 유저마이크로서비스에 보내고

    // todo orderservice에 저장시키자.


}
