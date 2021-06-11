package saechim.interior.etcservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import saechim.interior.etcservice.entity.NoticeType;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @Builder
@Data
public class ResponseNoticeDto {
    private String title;

    private String content;

    private NoticeType type;

    @Builder.Default
    private List<byte[]> noticeImage=new ArrayList<>(); /* 사진첨부는 세장만 하자 (프론트에서 처리) */
}
