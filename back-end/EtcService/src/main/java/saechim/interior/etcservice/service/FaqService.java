package saechim.interior.etcservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.etcservice.dto.FaqDto;
import saechim.interior.etcservice.entity.Faq;
import saechim.interior.etcservice.repository.FaqRepository;

@Service
@Slf4j
@RequiredArgsConstructor @Transactional(readOnly = true)
public class FaqService {

    private final FaqRepository faqRepository;

    @Transactional
    public Long createFaq(FaqDto faqDto){
        Faq faq = FaqDto.toEntity(faqDto);
        faqRepository.save(faq);
        return faq.getId();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public String deleteFaq(Long faqId) {
        Faq faq = faqRepository.findById(faqId).orElseThrow(RuntimeException::new);
        faqRepository.delete(faq);
        return "삭제 완료";
    }
}
