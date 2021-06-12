package saechim.interior.etcservice.repository;

import com.google.common.reflect.TypeToken;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.entity.ConstructQuest;
import saechim.interior.etcservice.entity.QConstructQuest;

import javax.persistence.EntityManager;
import java.lang.reflect.Type;
import java.util.List;

import static saechim.interior.etcservice.entity.QConstructQuest.*;


public class ConstructQuestRepositoryImpl implements CustomConstructQuest{

    private final JPAQueryFactory queryFactory;
    private final ModelMapper mapper;
    public ConstructQuestRepositoryImpl(EntityManager em,ModelMapper mapper) {
        this.queryFactory = new JPAQueryFactory(em);
        this.mapper=mapper;
    }

    @Override
    public Page<ResponseQuestDto> retrieveQuestList(Pageable pageable) {
        List<ConstructQuest> fetch = queryFactory.selectFrom(constructQuest)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Type list=new TypeToken<List<ResponseQuestDto>>(){}.getType();
        List<ResponseQuestDto> responseQuestDtoQueryResults=mapper.map(fetch,list);

        JPAQuery<ConstructQuest> constructQuestJPAQuery = queryFactory.selectFrom(constructQuest);

        return PageableExecutionUtils.getPage(responseQuestDtoQueryResults,pageable,constructQuestJPAQuery::fetchCount);


    }
}
