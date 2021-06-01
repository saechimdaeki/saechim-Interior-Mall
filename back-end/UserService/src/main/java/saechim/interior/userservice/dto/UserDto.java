package saechim.interior.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor @Builder
@NoArgsConstructor
public class UserDto {

    @NotBlank
    @Size(min = 2, message = "2자 이상 입력")
    private String name;

    @Email(message = "아이디@도메인으로 입력")
    @NotBlank
    private String email;

    @NotBlank(message = "아이디는 필수 입력사항입니다")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력사항입니다.")
    private String pwd;

    @NotBlank(message = "전화번호는 필수 입력사항입니다.")
    private String phoneNumber;
}
