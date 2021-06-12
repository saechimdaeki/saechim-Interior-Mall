package saechim.interior.etcservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saechim.interior.etcservice.dto.QuestDto;
import saechim.interior.etcservice.dto.ResponseQuestDto;
import saechim.interior.etcservice.service.NoticeService;
import saechim.interior.etcservice.service.QuestService;

import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class EtcController {

    private final QuestService questService;
    private final NoticeService noticeService;

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
}
