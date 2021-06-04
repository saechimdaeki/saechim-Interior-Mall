package saechim.interior.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.userservice.dto.CouponDto;
import saechim.interior.userservice.entity.Coupon;
import saechim.interior.userservice.entity.UserEntity;
import saechim.interior.userservice.repository.CouponRepository;
import saechim.interior.userservice.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public void giveToCoupon(String userId, CouponDto couponDto){
        Coupon coupon = CouponDto.toCoupon(couponDto);
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
        userEntity.addCoupon(coupon);
        coupon.setUserEntity(userEntity);
    }

    public List<Coupon> findAllCoupon(){
        return couponRepository.findAll();
    }
}
