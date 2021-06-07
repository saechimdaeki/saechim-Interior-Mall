package saechim.interiror.saechiminteriorapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @Builder
public class Coupon {
    private Long id;

    private String couponName;

    private String couponDescription;

    private LocalDateTime createDate;

}
