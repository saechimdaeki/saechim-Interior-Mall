package saechim.interiror.saechiminteriorapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginDto {

    private String userId;

    private String pwd;
}
