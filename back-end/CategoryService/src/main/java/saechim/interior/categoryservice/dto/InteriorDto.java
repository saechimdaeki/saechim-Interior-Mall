package saechim.interior.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import saechim.interior.categoryservice.entity.InteriorEntity;
import saechim.interior.categoryservice.entity.InteriorType;



@Data @AllArgsConstructor @NoArgsConstructor
public class InteriorDto {


    private InteriorType interiorType; //인테리어 타입 :  가구,캠핑,라이프,패브릭,사무용품,디지털

    private String title; //제목

    private String brandName; //브랜드이름

    private String seller; //파는사람

    private Integer stock; //수량

    private Integer price; // 가격

    public static InteriorEntity toEntity(InteriorDto dto){
        return InteriorEntity.builder()
                .interiorType(dto.interiorType)
                .title(dto.title)
                .brandName(dto.brandName)
                .seller(dto.seller)
                .stock(dto.stock)
                .price(dto.price)
                .build();
    }
}
