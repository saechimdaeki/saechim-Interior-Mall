package saechim.interior.etcservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import saechim.interior.etcservice.dto.ResponseQuestDto;


public interface CustomConstructQuest {
     Page<ResponseQuestDto> findAllQuestList(Pageable pageable);
}
