package saechim.interior.userservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Coupon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String couponName;

    private String couponDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    @JsonBackReference
    private UserEntity userEntity;

    @Builder
    public Coupon(String couponName, String couponDescription){
        this.couponName=couponName;
        this.couponDescription=couponDescription;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }
}
