package saechim.interior.etcservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import saechim.interior.etcservice.entity.UserInfo;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @Builder
public class ResponseQuestDto {
    private String title;

    private String questContent;

    private UserInfo userInfo;

    @Builder.Default
    private List<byte[]> questImage=new ArrayList<>(); /* 사진첨부는 세장만 하자 (프론트에서 처리) */
}
