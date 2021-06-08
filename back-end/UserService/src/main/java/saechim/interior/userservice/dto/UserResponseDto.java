package saechim.interior.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String name;

    private String email;

    private String userId;


    private String phoneNumber;

    private byte[] userImage;
}
