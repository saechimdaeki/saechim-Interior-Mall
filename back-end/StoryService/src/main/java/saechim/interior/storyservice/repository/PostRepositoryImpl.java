package saechim.interior.storyservice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import saechim.interior.storyservice.dto.PostDetailDto;
import saechim.interior.storyservice.dto.ResponseMyPostDto;
import saechim.interior.storyservice.entity.Post;
import saechim.interior.storyservice.entity.QComment;
import saechim.interior.storyservice.entity.QPost;

import javax.persistence.EntityManager;
import java.util.List;

import static saechim.interior.storyservice.entity.QComment.comment;
import static saechim.interior.storyservice.entity.QPost.*;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

public class PostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory queryFactory;
    private final ModelMapper mapper;
    public PostRepositoryImpl(EntityManager em,ModelMapper mapper) {
        this.queryFactory = new JPAQueryFactory(em);
        this.mapper=mapper;
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

    @Override
    public PostDetailDto findPostDetail(Long postId) {
        Post post = queryFactory.selectFrom(QPost.post)
                .join(QPost.post.comments, comment)
                .where(QPost.post.id.eq(postId))
                .fetchJoin().fetchOne();
        return mapper.map(post,PostDetailDto.class);
    }
}
