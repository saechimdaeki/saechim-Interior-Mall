package saechim.interior.categoryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.categoryservice.dto.InteriorDto;
import saechim.interior.categoryservice.dto.KafkaOrderDto;
import saechim.interior.categoryservice.entity.InteriorEntity;
import saechim.interior.categoryservice.repository.InteriorEntityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InteriorService {
    private final InteriorEntityRepository interiorEntityRepository;

    @Transactional
    public String createInteriorProduct(InteriorDto interiorDto){ //상품 생성
        InteriorEntity interiorEntity = InteriorDto.toEntity(interiorDto);
        interiorEntityRepository.save(interiorEntity);
        return interiorEntity.getInteriorId(); // random String 6 length
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public KafkaOrderDto orderInteriorProduct(String interiorId, int orderCnt,String userId){
        Optional<InteriorEntity> byInteriorId = interiorEntityRepository.findByInteriorId(interiorId);
        if(byInteriorId.isEmpty()) throw new RuntimeException("존재하지 않는 상품입니다");
            InteriorEntity interiorEntity = byInteriorId.get();
            int stock = interiorEntity.getStock() - orderCnt;
            if(stock<0) throw new RuntimeException("수량 부족");
            interiorEntity.setStock(stock);
            int totalPrice = interiorEntity.getPrice() * orderCnt;

        return KafkaOrderDto.builder()
                .interiorType(interiorEntity.getInteriorType().toString())
                .interiorId(interiorId)
                .brandName(interiorEntity.getBrandName())
                .orderCnt(orderCnt)
                .price(interiorEntity.getPrice())
                .seller(interiorEntity.getSeller())
                .totalPrice(totalPrice)
                .userId(userId)
                .build();

    }
}
