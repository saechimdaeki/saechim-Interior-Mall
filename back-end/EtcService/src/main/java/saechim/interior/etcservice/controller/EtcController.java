package saechim.interior.etcservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.etcservice.dto.FaqDto;
import saechim.interior.etcservice.dto.NoticeDto;
import saechim.interior.etcservice.dto.QuestDto;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.service.FaqService;
import saechim.interior.etcservice.service.NoticeService;
import saechim.interior.etcservice.service.QuestService;

import java.io.IOException;
import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class EtcController {

    private final QuestService questService;
    private final NoticeService noticeService;
    private final FaqService faqService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-EtcService-World";
    }

    @GetMapping("/quest")
    public ResponseEntity<?> getQuestLists(Pageable pageable){
        Page<ResponseQuestDto> responseQuestList = questService.retrieveRequestQuestList(pageable);
        return ResponseEntity.ok(responseQuestList);
    }

    @PostMapping("/quest")
    public ResponseEntity<?> postQuest(@RequestBody QuestDto questDto){
        return ResponseEntity.ok(questService.createQuest(questDto));
    }

    @PostMapping("/quest/{id}/upload")
    public ResponseEntity<?> addImagesAtQuest(@PathVariable Long id,@RequestParam("file") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(questService.addPictures(id,files));
    }

    @PostMapping("/faq")
    public ResponseEntity<?> createFaq(@RequestBody FaqDto faqDto){
        return ResponseEntity.ok(faqService.createFaq(faqDto));
    }

    @PostMapping("/notice")
    public ResponseEntity<?> createNotice(@RequestBody NoticeDto noticeDto){
        return ResponseEntity.ok(noticeService.postNotice(noticeDto));
    }

    @PostMapping("/notice/{id}/upload")
    public ResponseEntity<?> addImagePost(@PathVariable Long id,@RequestParam("file") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(noticeService.addPictures(id,files));
    }

    @GetMapping("/notice")
    public ResponseEntity<?> getNoticePageable(Pageable pageable){
        return ResponseEntity.ok(noticeService.getNotices(pageable));
    }


    @DeleteMapping("/faq/{id}")
    public ResponseEntity<?> deleteFaq(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(faqService.deleteFaq(id));
    }
}
