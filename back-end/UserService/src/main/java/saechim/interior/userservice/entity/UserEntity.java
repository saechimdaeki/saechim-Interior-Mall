package saechim.interior.userservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long no;

    @Size(min = 2, message = "2자 이상 입력")
    private String name;

    @Email(message = "아이디@도메인으로 입력")
    @Column(unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String userId;

    @Column(nullable = false,unique = true)
    private String encryptedPwd;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Lob
    @Nullable
    private byte[] userPic;

    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Coupon> coupons=new ArrayList<>();

    @Builder
    public UserEntity(String name, String email, String userId, String encryptedPwd, String phoneNumber, byte[] userPic) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
        this.phoneNumber = phoneNumber;
        this.userPic=userPic;
    }

    public void setUserProfileImage(byte[] userPic){
        this.userPic=userPic;
    }

    public void addCoupons(List<Coupon> coupons){
        this.coupons.addAll(coupons);
    }

    public void addCoupon(Coupon coupon){
        this.coupons.add(coupon);
    }
}
