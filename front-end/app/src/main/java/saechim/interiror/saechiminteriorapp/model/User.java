package saechim.interiror.saechiminteriorapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @AllArgsConstructor @Builder
public class User {

    private Long no;

    private String name;

    private String email;

    private String userId;

    private String encryptedPwd;

    private String phoneNumber;

    private String userPic;

    private List<Coupon> coupons;

}
