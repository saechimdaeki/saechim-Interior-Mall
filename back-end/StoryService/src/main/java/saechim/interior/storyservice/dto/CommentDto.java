package saechim.interior.storyservice.dto;

import lombok.Data;
import saechim.interior.storyservice.entity.Comment;

@Data
public class CommentDto {
    private String userId;

    private String content;

    public static Comment toEntity(CommentDto dto){
        return Comment.builder()
                .userId(dto.userId)
                .content(dto.content)
                .build();
    }
}
