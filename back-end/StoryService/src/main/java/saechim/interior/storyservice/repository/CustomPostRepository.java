package saechim.interior.storyservice.repository;

import saechim.interior.storyservice.dto.ResponseMyPostDto;

import java.util.List;

public interface CustomPostRepository {
    List<ResponseMyPostDto> findMyPostByUserId(String userId);
}
