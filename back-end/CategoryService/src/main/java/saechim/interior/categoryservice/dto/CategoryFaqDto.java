package saechim.interior.categoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data
@Builder @NoArgsConstructor
public class CategoryFaqDto {
    private Long id;

    private String title;

    private String userId;
}
