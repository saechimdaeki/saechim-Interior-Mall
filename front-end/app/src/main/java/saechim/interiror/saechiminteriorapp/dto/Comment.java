package saechim.interiror.saechiminteriorapp.dto;

import lombok.Data;

@Data
public class Comment {
    private Long id;

    private String userId;

    private String content;
}
