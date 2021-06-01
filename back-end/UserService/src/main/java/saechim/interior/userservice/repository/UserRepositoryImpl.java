package saechim.interior.userservice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;

import javax.persistence.EntityManager;

import static saechim.interior.userservice.entity.QMember.*;

public class UserRepositoryImpl implements CustomUserRepository {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserResponseDto findMemberUsingUserId(String userId) {
        return queryFactory.select(Projections.constructor(
                UserResponseDto.class,
                member.name,
                member.email,
                member.userId,
                member.phoneNumber)).from(member).where(member.userId.eq(userId)).fetchOne();
    }
}
