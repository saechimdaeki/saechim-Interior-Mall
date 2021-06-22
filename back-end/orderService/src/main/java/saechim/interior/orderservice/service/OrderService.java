package saechim.interior.orderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.orderservice.repository.OrderRepository;

@Service
@RequiredArgsConstructor @Transactional(readOnly = true)
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;


    @KafkaListener(topics = "order-saechim", groupId = "saechim-interior")
    public void gg(String message){
        log.debug("\n ㄲㅣ이이이이약 {}",message);
        System.out.println(message);
    }
}
