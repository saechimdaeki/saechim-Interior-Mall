package saechim.interior.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import saechim.interior.userservice.entity.Coupon;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder @Data
@NoArgsConstructor
public class CouponDto {
    @NotBlank
    private String couponName;

    private String couponDescription;

    public static Coupon toCoupon(CouponDto couponDto){
        return Coupon.builder()
                .couponName(couponDto.couponName)
                .couponDescription(couponDto.couponDescription)
                .build();
    }
}
