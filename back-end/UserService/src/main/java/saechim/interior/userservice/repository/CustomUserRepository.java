package saechim.interior.userservice.repository;


import saechim.interior.userservice.dto.CouponDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.Coupon;
import saechim.interior.userservice.entity.UserEntity;

import java.util.List;

public interface CustomUserRepository {
    UserResponseDto findByUserIdForResponse(String userId);
    List<UserEntity> findAllUserInfo();
    List<Coupon> findCouponsByUserId(String userId);
}
