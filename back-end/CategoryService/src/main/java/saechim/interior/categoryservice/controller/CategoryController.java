package saechim.interior.categoryservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import saechim.interior.categoryservice.dto.CategoryFaqDto;
import saechim.interior.categoryservice.dto.InteriorDto;
import saechim.interior.categoryservice.dto.KafkaOrderDto;
import saechim.interior.categoryservice.repository.InteriorEntityRepository;
import saechim.interior.categoryservice.service.CategoryFaqService;
import saechim.interior.categoryservice.service.CategoryReviewService;
import saechim.interior.categoryservice.service.InteriorService;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor @Slf4j
public class CategoryController {

    private final ObjectMapper objectMapper;
    private final InteriorService interiorService;
    private final CategoryFaqService categoryFaqService;
    private final CategoryReviewService categoryReviewService;
    private final InteriorEntityRepository interiorEntityRepository;

    // todo 유저가 주문한 리스트 유저마이크로서비스에 보내고
    // todo orderservice에 저장시키자.
    private final KafkaTemplate<String, String> kafkaTemplate;

//    @PostMapping("/kafka") //kafka Test
//    public void test(@RequestBody CategoryFaqDto message){
//        String json ="";
//        try{
//            json=objectMapper.writeValueAsString(message);
//        }catch (JsonProcessingException ex){
//            ex.printStackTrace();
//        }
//        this.kafkaTemplate.send("order-saechim",json);
//    }

    @PostMapping("/interior")
    public ResponseEntity<?> createProdInterior(@RequestBody InteriorDto interiorDto){
        String interiorProduct = interiorService.createInteriorProduct(interiorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(interiorProduct);
    }

    @PostMapping("/interior/{interiorId}")
    public ResponseEntity<?> orderInteriorProduct(@PathVariable String interiorId,
                                                  @RequestParam("count") int orderCnt ,
                                                  @RequestParam("userId") String userId){
        KafkaOrderDto kafkaOrderDto = interiorService.orderInteriorProduct(interiorId, orderCnt,userId);
        String json="";
        try{
            json=objectMapper.writeValueAsString(kafkaOrderDto);
        }catch (JsonProcessingException ex){
            log.debug("\n kafkaOrderDto Error : {} ",ex.toString());
        }
        this.kafkaTemplate.send("interiorDto",json);
        return ResponseEntity.status(HttpStatus.OK).body(kafkaOrderDto);
    }

    @GetMapping("/test")
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok(interiorEntityRepository.findAll());
    }

}
