package saechim.interior.etcservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor @Builder
public class UserInfo {

    private String name;

    private String email;

    private String phoneNumber;

    public UserInfo() {

    }
}
