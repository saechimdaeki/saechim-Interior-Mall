package saechim.interior.storyservice.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import saechim.interior.storyservice.entity.Comment;
import saechim.interior.storyservice.entity.Type;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor
public class PostDetailDto {
    private Long id;

    private Type type;

    private String title;

    private String userId;

    private String content;

    private Long postView;

    private List<byte[]> postImages=new ArrayList<>();

    private List<Comment> comments=new ArrayList<>();
}
