package saechim.interior.storyservice.dto;

import lombok.Data;
import saechim.interior.storyservice.entity.Post;
import saechim.interior.storyservice.entity.Type;

import javax.validation.constraints.NotBlank;

@Data
public class PostDto {
    @NotBlank(message = "type is must required")
    private Type type;

    @NotBlank(message = "title is must required")
    private String title;

    @NotBlank(message = "userId is must required")
    private String userId;

    @NotBlank(message = "content is must required")
    private String content;

    public static Post toEntity(PostDto postDto){
        return Post.builder()
                .type(postDto.type)
                .title(postDto.title)
                .userId(postDto.userId)
                .content(postDto.content)
                .postView(0L)
                .build();

    }
}
