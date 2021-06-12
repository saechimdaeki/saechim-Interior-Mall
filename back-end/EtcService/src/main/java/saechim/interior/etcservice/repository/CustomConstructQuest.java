package saechim.interior.etcservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import saechim.interior.etcservice.dto.ResponseQuestDto;

import java.util.List;


public interface CustomConstructQuest {
     Page<ResponseQuestDto> retrieveQuestList(Pageable pageable);
}
