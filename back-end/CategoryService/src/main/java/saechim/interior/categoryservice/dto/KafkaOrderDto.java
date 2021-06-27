package saechim.interior.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class KafkaOrderDto {
    private String interiorId; // 고유 번호
    private String seller;  // 파는 사람
    private Integer orderCnt; // 주문 량
    private String brandName; // 브랜드이름
    private Integer totalPrice; // 총 가격
    private Integer price; // 단일 가격
    private String interiorType; // 상품 종류
    private String userId; //주문자의 id (FE단에서 처리)
}
