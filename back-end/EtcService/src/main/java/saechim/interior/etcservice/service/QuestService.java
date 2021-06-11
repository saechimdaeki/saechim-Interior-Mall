package saechim.interior.etcservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.etcservice.dto.QuestDto;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.entity.ConstructQuest;
import saechim.interior.etcservice.repository.ConsturctQuestRepository;

import java.io.IOException;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class QuestService {
    private final ConsturctQuestRepository consturctQuestRepository;
    private final ModelMapper mapper;

    @Transactional
    public String createQuest(QuestDto dto){
        ConstructQuest constructQuest = QuestDto.toEntity(dto);
        ConstructQuest entity = consturctQuestRepository.save(constructQuest);
        return entity.getTitle();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseQuestDto addPictures(Long questId, MultipartFile[] files) throws IOException {
        ConstructQuest constructQuest = consturctQuestRepository.findById(questId).orElseThrow(RuntimeException::new);
        ArrayList<byte[]> filesData=new ArrayList<>();
        for (MultipartFile file : files) {
            filesData.add(file.getBytes());
        }
        constructQuest.addImages(filesData);
        return mapper.map(constructQuest,ResponseQuestDto.class);
    }

    public Page<ResponseQuestDto> retrieveRequestQuestList(Pageable pageable){
        return consturctQuestRepository.findAllQuestList(pageable);
    }
}
