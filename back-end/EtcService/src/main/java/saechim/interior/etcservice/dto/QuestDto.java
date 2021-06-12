package saechim.interior.etcservice.dto;

import lombok.Data;
import saechim.interior.etcservice.entity.ConstructQuest;
import saechim.interior.etcservice.entity.UserInfo;

@Data
public class QuestDto {
    private String title;

    private String questContent;

    private String name;

    private String email;

    private String phoneNumber;

    public static ConstructQuest toEntity(QuestDto dto){
        return ConstructQuest.builder()
                .title(dto.title)
                .questContent(dto.questContent)
                .userInfo(UserInfo.builder()
                        .email(dto.email)
                        .name(dto.name)
                        .phoneNumber(dto.phoneNumber)
                        .build())
                .build();
    }
}
