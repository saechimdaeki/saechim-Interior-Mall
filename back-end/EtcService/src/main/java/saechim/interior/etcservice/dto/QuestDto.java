package saechim.interior.etcservice.dto;

import lombok.Data;
import saechim.interior.etcservice.entity.ConstructQuest;
import saechim.interior.etcservice.entity.UserInfo;

@Data
public class QuestDto {
    private String title;

    private String questContent;

    private UserInfo userInfo;

    public static ConstructQuest toEntity(QuestDto dto){
        return ConstructQuest.builder()
                .title(dto.title)
                .questContent(dto.questContent)
                .userInfo(dto.userInfo)
                .build();
    }
}
