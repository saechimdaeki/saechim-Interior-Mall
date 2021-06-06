package saechim.interior.userservice.dto;

import lombok.Data;

@Data
public class Comment {
    private Long id;

    private String userId;

    private String content;
}
