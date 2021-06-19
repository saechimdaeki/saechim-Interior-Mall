package saechim.interior.categoryservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InteriorEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interior_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private InteriorType interiorType;

    private String title;

    private String brandName;

    private String seller;

    @ColumnDefault(value = "0.0")
    private Double rateStarAvg=0.0;




    @ElementCollection
    @Column(length = 36050379)
    private List<byte[]> interiorImages=new ArrayList<>();

    private BigDecimal price;


    @OneToMany(mappedBy = "interiorEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CategoryReview> categoryReviews=new ArrayList<>();

    @OneToMany(mappedBy = "interiorEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CategoryFaq> categoryFaqs=new ArrayList<>();

}
