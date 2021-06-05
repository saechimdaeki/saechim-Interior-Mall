package saechim.interior.storyservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saechim.interior.storyservice.dto.PostDto;
import saechim.interior.storyservice.dto.ResponseMyPostDto;
import saechim.interior.storyservice.service.PostService;

import java.util.List;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class StoryController {

    private final PostService postService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello-StoryService-World";
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<List<ResponseMyPostDto>> findPostsByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(postService.findMyPostsByUserId(userId));
    }

    @PostMapping("/posts")
    public ResponseEntity<?> writePost(@RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.writePost(postDto));
    }
}
