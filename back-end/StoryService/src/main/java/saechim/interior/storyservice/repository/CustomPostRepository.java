package saechim.interior.storyservice.repository;

import saechim.interior.storyservice.dto.PostDetailDto;
import saechim.interior.storyservice.dto.ResponseMyPostDto;
import saechim.interior.storyservice.entity.Post;

import java.util.List;

public interface CustomPostRepository {
    List<ResponseMyPostDto> findMyPostByUserId(String userId);
    PostDetailDto findPostDetail(Long postId);
}
