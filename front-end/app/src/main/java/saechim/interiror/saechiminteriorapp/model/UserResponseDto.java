package saechim.interiror.saechiminteriorapp.model;

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

    private String userImage; //byte
}
