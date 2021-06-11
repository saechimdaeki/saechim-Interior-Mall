package saechim.interior.etcservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.etcservice.dto.NoticeDto;
import saechim.interior.etcservice.dto.ResponseNoticeDto;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.entity.ConstructQuest;
import saechim.interior.etcservice.entity.Notice;
import saechim.interior.etcservice.repository.NoticeRepository;

import java.io.IOException;
import java.util.ArrayList;

import static saechim.interior.etcservice.entity.QConstructQuest.constructQuest;

@Service
@RequiredArgsConstructor
@Slf4j @Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final ModelMapper mapper;


    @Transactional
    public String postNotice(NoticeDto noticeDto){
        Notice notice = NoticeDto.toEntity(noticeDto);
        noticeRepository.save(notice);
        return notice.getTitle();
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseNoticeDto addPictures(Long noticeId, MultipartFile[] files) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(RuntimeException::new);
        ArrayList<byte[]> filesData=new ArrayList<>();
        for (MultipartFile file : files) {
            filesData.add(file.getBytes());
        }
        notice.addImages(filesData);
        return mapper.map(notice,ResponseNoticeDto.class);
    }

}
