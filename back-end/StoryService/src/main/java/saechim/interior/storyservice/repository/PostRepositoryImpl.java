package saechim.interior.storyservice.repository;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import saechim.interior.storyservice.dto.ResponseMyPostDto;
import saechim.interior.storyservice.entity.QPost;

import javax.persistence.EntityManager;
import java.util.List;

import static saechim.interior.storyservice.entity.QPost.*;

public class PostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory queryFactory;
    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResponseMyPostDto> findMyPostByUserId(String userId) {
        return queryFactory.select(Projections.constructor(
                ResponseMyPostDto.class,
                post.id,
                post.type,
                post.userId,
                post.title,
                post.content,
                post.postView
        )).from(post).where(post.userId.eq(userId)).fetch();
    }
}
