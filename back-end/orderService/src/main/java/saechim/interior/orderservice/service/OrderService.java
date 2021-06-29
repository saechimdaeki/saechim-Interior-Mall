package saechim.interior.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.orderservice.dto.KafkaOrderDto;
import saechim.interior.orderservice.entity.OrderEntity;
import saechim.interior.orderservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor @Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

//    @KafkaListener(topics = "order-saechim", groupId = "saechim-interior")
//    public void gg(String message){
//        CategoryFaqDto categoryFaqDto = CategoryFaqDto.builder().build();
//        try{
//            categoryFaqDto=objectMapper.readValue(message, new TypeReference<CategoryFaqDto>() {});
//        }catch (JsonProcessingException ex){
//            ex.printStackTrace();
//        }
//        log.debug("\n categoryFaqDto = {} ",categoryFaqDto.toString());
//    }

    @KafkaListener(topics = "interiorDto", groupId = "saechimdaeki")
    public void retrieveInteriorOrderProcess(String message){
        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder().build();
        try{
            kafkaOrderDto=objectMapper.readValue(message, new TypeReference<KafkaOrderDto>() {});
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
        log.debug("\n kafkaOrderDto = {} ",kafkaOrderDto.toString());
        OrderEntity orderEntity = OrderEntity.builder()
                .interiorId(kafkaOrderDto.getInteriorId())
                .qty(kafkaOrderDto.getOrderCnt())
                .totalPrice(kafkaOrderDto.getTotalPrice())
                .unitPrice(kafkaOrderDto.getPrice())
                .userId(kafkaOrderDto.getUserId())
                .build();
        orderRepository.save(orderEntity);
    }
}
