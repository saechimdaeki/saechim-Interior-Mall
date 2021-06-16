package saechim.interiror.saechiminteriorapp.dto;


import lombok.Data;

@Data
public class ResponseMyPostDto {
    private Long id;
    private String type; //Type...?
    private String userId;
    private String title;
    private String content;
    private Long postView;
}
