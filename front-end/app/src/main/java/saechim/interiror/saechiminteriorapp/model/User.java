package saechim.interiror.saechiminteriorapp.model;

import java.util.List;

public class User {

    private Long no;

    private String name;

    private String email;

    private String userId;

    private String encryptedPwd;

    private String phoneNumber;

    private String userPic;

    private List<Coupon> coupons;

    public Long getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEncryptedPwd() {
        return encryptedPwd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserPic() {
        return userPic;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }
}
