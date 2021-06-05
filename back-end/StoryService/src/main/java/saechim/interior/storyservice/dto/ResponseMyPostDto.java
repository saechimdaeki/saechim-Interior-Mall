package saechim.interior.storyservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import saechim.interior.storyservice.entity.Type;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class ResponseMyPostDto {
    private Long id;
    private Type type;
    private String userId;
    private String title;
    private String content;
    private Long postView;
}
