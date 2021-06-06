package saechim.interior.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailDto {
    private Long id;

    private String type;

    private String title;

    private String userId;

    private String content;

    private Long postView;

    private List<byte[]> postImages=new ArrayList<>();

    private List<Comment> comments=new ArrayList<>();
}