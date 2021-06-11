package saechim.interior.etcservice.dto;

import lombok.Data;
import saechim.interior.etcservice.entity.Notice;
import saechim.interior.etcservice.entity.NoticeType;


@Data
public class NoticeDto {
    private String title;

    private String content;

    private NoticeType type;

    public static Notice toEntity(NoticeDto dto){
        return Notice.builder()
                .title(dto.title)
                .content(dto.content)
                .type(dto.type)
                .build();
    }
}
