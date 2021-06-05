package saechim.interior.storyservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.storyservice.dto.PostDto;
import saechim.interior.storyservice.dto.ResponseMyPostDto;
import saechim.interior.storyservice.entity.Post;
import saechim.interior.storyservice.repository.PostRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    @Transactional
    public List<ResponseMyPostDto> findMyPostsByUserId(String userId) {
        return postRepository.findMyPostByUserId(userId);
    }

    @Transactional
    public String writePost(PostDto postDto){
        Post post = PostDto.toEntity(postDto);
        postRepository.save(post);
        return "포스트 작성 성공";
    }
}
