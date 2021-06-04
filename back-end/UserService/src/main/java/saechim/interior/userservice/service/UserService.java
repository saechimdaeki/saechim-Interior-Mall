package saechim.interior.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.UserEntity;

import java.io.IOException;
import java.util.List;

public interface UserService extends UserDetailsService {
    void JoinUser(UserDto userDto);
    UserResponseDto findByUserId(String userId);
    UserEntity uploadPicture(String userId, MultipartFile file) throws IOException;
    List<UserEntity> findAllUserInfo();
    UserDto findByUserEmail(String email);

}
