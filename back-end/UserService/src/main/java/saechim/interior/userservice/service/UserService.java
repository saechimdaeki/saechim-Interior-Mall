package saechim.interior.userservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saechim.interior.userservice.dto.UserDto;
import saechim.interior.userservice.dto.UserResponseDto;
import saechim.interior.userservice.entity.User;
import saechim.interior.userservice.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Transactional
    public void JoinUser(UserDto userDto){
        User user = User.builder()
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .encryptedPwd(passwordEncoder.encode(userDto.getPwd()))
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
        memberRepository.save(user);

        //TODO
    }

    public UserResponseDto findByUserId(String userId){
        return memberRepository.findMemberUsingUserId(userId);
    }
}
