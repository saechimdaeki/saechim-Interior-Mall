package saechim.interior.userservice.cleint;

 import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 import saechim.interior.userservice.dto.PostDetailDto;
 import saechim.interior.userservice.dto.ResponseMyPostDto;

import java.util.List;

@FeignClient(name = "storyservice")
public interface StoryServiceClient {

    @GetMapping("/posts/{userId}")
    List<ResponseMyPostDto> getUsersPost(@PathVariable("userId") String userId);

    @GetMapping("/post/{id}")
    PostDetailDto getPostDetails(@PathVariable("id") Long id);
}
