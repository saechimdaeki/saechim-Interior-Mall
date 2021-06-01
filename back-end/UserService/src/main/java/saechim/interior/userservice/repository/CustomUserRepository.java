package saechim.interior.userservice.repository;


import saechim.interior.userservice.dto.UserResponseDto;

public interface CustomUserRepository {
    UserResponseDto findMemberUsingUserId(String userId);
}
