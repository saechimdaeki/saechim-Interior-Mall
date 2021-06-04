package saechim.interior.userservice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.UserEntity;

import javax.persistence.EntityManager;

import java.util.List;

import static saechim.interior.userservice.entity.QUser.*;


public class UserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserResponseDto findByUserIdForResponse(String userId) {
        return queryFactory.select(Projections.constructor(
                UserResponseDto.class,
                user.name,
                user.email,
                user.userId,
                user.phoneNumber)).from(user).where(user.userId.eq(userId)).fetchOne();
    }

    @Override
    public List<UserEntity> findAllUserInfo() {
        return queryFactory.selectFrom(user).fetch();
    }
}
