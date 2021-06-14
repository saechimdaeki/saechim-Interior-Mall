package saechim.interior.etcservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import saechim.interior.etcservice.entity.Faq;

@Data
@AllArgsConstructor @Builder
public class FaqDto {
    private String title;
    private String content;

    public static Faq toEntity(FaqDto faqDto){
        return Faq.builder()
                .title(faqDto.title)
                .content(faqDto.content)
                .build();
    }
}
