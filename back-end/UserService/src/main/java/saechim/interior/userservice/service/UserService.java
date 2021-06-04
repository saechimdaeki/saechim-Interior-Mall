package saechim.interior.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.UserEntity;
import saechim.interior.userservice.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Transactional
    public void JoinUser(UserDto userDto){
        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .encryptedPwd(passwordEncoder.encode(userDto.getPwd()))
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
        userRepository.save(userEntity);

        //TODO
    }

    public UserResponseDto findByUserId(String userId){
        return userRepository.findByUserIdForResponse(userId);
    }

    @Transactional
    public UserEntity uploadPicture(String userId, MultipartFile file) throws IOException {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
        userEntity.setUserProfileImage(file.getBytes());
        return userEntity;
    }

    public List<UserEntity> findAllUserInfo(){
        return userRepository.findAllUserInfo();
    }

    public UserDto findByUserEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if(userEntity.isEmpty()) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity.get(),UserDto.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(username);

        if(userEntity.isEmpty())
            throw new UsernameNotFoundException(username);
        UserEntity user=userEntity.get();

        return new User(user.getEmail(),user.getEncryptedPwd(),
                true,true,true,true, new ArrayList<>()/* */ );
    }
}
