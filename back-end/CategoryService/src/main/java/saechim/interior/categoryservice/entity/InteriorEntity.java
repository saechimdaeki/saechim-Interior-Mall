package saechim.interior.categoryservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @DynamicUpdate
public class InteriorEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interior_id")
    private Long id;

    private String interiorId;

    @Enumerated(EnumType.STRING)
    private InteriorType interiorType; //인테리어 타입 :  가구,캠핑,라이프,패브릭,사무용품,디지털

    private String title; //제목

    private String brandName; //브랜드이름

    private String seller; //파는사람

    @ColumnDefault(value = "0.0")
    private Double rateStarAvg=0.0; //평균 평점

    private Integer stock; //수량

    private Integer price; // 가격

    @ElementCollection
    @Column(length = 36050379)
    private List<byte[]> interiorImages=new ArrayList<>();

    @OneToMany(mappedBy = "interiorEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CategoryReview> categoryReviews=new ArrayList<>();

    @OneToMany(mappedBy = "interiorEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CategoryFaq> categoryFaqs=new ArrayList<>();

    @Builder
    public InteriorEntity(InteriorType interiorType, String title, String brandName, String seller, Integer stock,
                          Integer price){
        this.interiorId= RandomStringUtils.random(6);
        this.interiorType=interiorType;
        this.title=title;
        this.brandName=brandName;
        this.seller=seller;
        this.stock=stock;
        this.price=price;
    }

    public void addInteriorImages(List<byte[]> images){
        this.interiorImages.addAll(images);
    }

    public void setStock(Integer stock){
        this.stock=stock;
    }
}
