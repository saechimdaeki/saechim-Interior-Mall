package saechim.interior.categoryservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.categoryservice.dto.InteriorDto;
import saechim.interior.categoryservice.dto.KafkaOrderDto;
import saechim.interior.categoryservice.entity.ErrorLog;
import saechim.interior.categoryservice.entity.InteriorEntity;
import saechim.interior.categoryservice.repository.ErrorLogRepository;
import saechim.interior.categoryservice.repository.InteriorEntityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class InteriorService {
    private final InteriorEntityRepository interiorEntityRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ErrorLogRepository errorLogRepository;


    @Transactional
    public String createInteriorProduct(InteriorDto interiorDto){ //상품 생성
        InteriorEntity interiorEntity = InteriorDto.toEntity(interiorDto);
        interiorEntityRepository.save(interiorEntity);
        return interiorEntity.getInteriorId(); // random String 6 length
    }

    @Transactional(noRollbackFor = {JsonProcessingException.class,RuntimeException.class})
    public Object orderInteriorProduct(String interiorId, int orderCnt, String userId){
        KafkaOrderDto kafkaOrderDto = null;
        try{
            kafkaOrderDto = orderInteriorKafka(interiorId, orderCnt, userId);
        }catch(Exception ex){
            String errorMessage = ex.toString();
            ErrorLog error = ErrorLog.builder().interiorId(interiorId)
                    .orderCnt(orderCnt)
                    .userId(userId)
                    .errorMsg(errorMessage)
                    .build();

            errorLogRepository.save(error);
        }
        return (kafkaOrderDto==null) ? "에러로그를 확인해 주세요" :kafkaOrderDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class ,timeout = 3)
    public KafkaOrderDto orderInteriorKafka(String interiorId, int orderCnt,String userId){
        Optional<InteriorEntity> byInteriorId = interiorEntityRepository.findByInteriorId(interiorId);
        if(byInteriorId.isEmpty()) throw new RuntimeException("존재하지 않는 상품입니다");
        InteriorEntity interiorEntity = byInteriorId.get();
        int stock = interiorEntity.getStock() - orderCnt;
        if(stock<0) throw new RuntimeException("수량 부족");
        interiorEntity.setStock(stock);
        int totalPrice = interiorEntity.getPrice() * orderCnt;

        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder()
                .interiorType(interiorEntity.getInteriorType().toString())
                .interiorId(interiorId)
                .brandName(interiorEntity.getBrandName())
                .orderCnt(orderCnt)
                .price(interiorEntity.getPrice())
                .seller(interiorEntity.getSeller())
                .totalPrice(totalPrice)
                .userId(userId)
                .build();

        String json="";
        try{
            json=objectMapper.writeValueAsString(kafkaOrderDto);
        }catch (JsonProcessingException ex){
            log.info("\n kafkaOrderDto Error : {} ",ex.toString());
        }
        this.kafkaTemplate.send("interiorDto",json);

        return kafkaOrderDto;
    }
}

