package saechim.interiror.saechiminteriorapp.model;

import java.time.LocalDateTime;

public class Coupon {
    private Long id;

    private String couponName;

    private String couponDescription;

    private LocalDateTime createDate;

    public Long getId() {
        return id;
    }

    public String getCouponName() {
        return couponName;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
