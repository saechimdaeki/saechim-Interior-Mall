package saechim.interior.userservice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import saechim.interior.userservice.dto.CouponDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.Coupon;
import saechim.interior.userservice.entity.QCoupon;
import saechim.interior.userservice.entity.QUserEntity;
import saechim.interior.userservice.entity.UserEntity;

import javax.persistence.EntityManager;

import java.util.List;

import static saechim.interior.userservice.entity.QCoupon.coupon;
import static saechim.interior.userservice.entity.QUserEntity.*;


public class UserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserResponseDto findByUserIdForResponse(String userId) {
        return queryFactory.select(Projections.constructor(
                UserResponseDto.class,
                userEntity.name,
                userEntity.email,
                userEntity.userId,
                userEntity.phoneNumber,
                userEntity.userPic)).from(userEntity).where(userEntity.userId.eq(userId)).fetchOne();
    }

    @Override
    public List<UserEntity> findAllUserInfo() {
       return queryFactory.selectFrom(userEntity).fetch();

    }

    @Override
    public List<Coupon> findCouponsByUserId(String userId) {
        return queryFactory.selectFrom(coupon)
                .innerJoin(coupon.userEntity,userEntity).fetchJoin()
                .where(userEntity.userId.eq(userId))
                .fetch();

    }


}
