package saechim.interior.storyservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.storyservice.dto.*;
import saechim.interior.storyservice.entity.Comment;
import saechim.interior.storyservice.entity.Post;
import saechim.interior.storyservice.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional(rollbackFor = RuntimeException.class)
    public PostDto addPictures(Long postId, MultipartFile[] files) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        ArrayList<byte[]> filesData=new ArrayList<>();
        for (MultipartFile file : files) {
            filesData.add(file.getBytes());
        }
        post.addImages(filesData);
        return mapper.map(post,PostDto.class);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public String writeComment(Long postId,CommentDto commentDto){
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        Comment comment= CommentDto.toEntity(commentDto);
        post.addComment(comment);
        comment.setPost(post);
        return "댓글 작성 완료";
    }


    @Transactional
    public PostDetailDto getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        post.addViewCount();
        return postRepository.findPostDetail(postId);
    }

    /* 테스트 용도 */
    public Post getPostTest(Long postId){
        return postRepository.findById(postId).get();
    }
}
