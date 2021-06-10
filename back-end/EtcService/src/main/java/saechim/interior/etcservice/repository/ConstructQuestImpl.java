package saechim.interior.etcservice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.entity.ConstructQuest;

import javax.persistence.EntityManager;
import java.util.List;

import static saechim.interior.etcservice.entity.QConstructQuest.constructQuest;

public class ConstructQuestImpl implements CustomConstructQuest{

    private final JPAQueryFactory queryFactory;

    public ConstructQuestImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResponseQuestDto> findAllQuestList(Pageable pageable) {

        List<ResponseQuestDto> content=queryFactory.select(Projections.constructor(
                ResponseQuestDto.class,
                constructQuest.title,
                constructQuest.questContent,
                constructQuest.userInfo,
                constructQuest.questImage
        ))
                .from(constructQuest).fetch();

        JPAQuery<ConstructQuest> count=queryFactory.selectFrom(constructQuest);

        return PageableExecutionUtils.getPage(content, pageable, count::fetchCount);
    }
}
