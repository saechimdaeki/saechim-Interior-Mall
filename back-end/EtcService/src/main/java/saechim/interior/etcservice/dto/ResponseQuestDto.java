package saechim.interior.etcservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import saechim.interior.etcservice.entity.UserInfo;


import java.util.List;

@Data
@AllArgsConstructor @Builder
@NoArgsConstructor
public class ResponseQuestDto {

    private Long id;

    private String title;

    private String questContent;

    private String name;

    private String email;

    private String phoneNumber;

    private List<byte[]> questImage; /* 사진첨부는 세장만 하자 (프론트에서 처리) */


}
