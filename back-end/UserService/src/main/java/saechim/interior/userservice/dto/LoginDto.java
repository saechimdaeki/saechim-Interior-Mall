package saechim.interior.userservice.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class LoginDto {

    @NotBlank(message = "userId cannot be null")
    private String userId;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
