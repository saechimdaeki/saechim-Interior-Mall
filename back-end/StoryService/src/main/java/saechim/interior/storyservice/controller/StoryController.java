package saechim.interior.storyservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.storyservice.dto.*;
import saechim.interior.storyservice.entity.Post;
import saechim.interior.storyservice.service.PostService;

import java.io.IOException;
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

    @PostMapping("/posts/{id}/upload")
    public ResponseEntity<?> addImagePost(@PathVariable Long id,@RequestParam("file") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(postService.addPictures(id,files));
    }

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestBody CommentDto commentDto){
        String s = postService.writeComment(id, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDetailDto> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping("/posttest/{id}")
    public Post test(@PathVariable Long id){
        return postService.getPostTest(id);
    }
}
