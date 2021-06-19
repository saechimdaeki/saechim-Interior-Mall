package saechim.interior.categoryservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryReview extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String userId;

    private String reviewContent;

    private Double rateStar;

    @Lob
    private byte[] reviewPic;

    private Long recommendNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interior_id")
    @JsonBackReference
    private InteriorEntity interiorEntity;
}
