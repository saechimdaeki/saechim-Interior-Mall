package saechim.interior.etcservice.entity;

import javax.persistence.Embeddable;

@Embeddable
public class UserInfo {

    private String Name;

    private String email;

    private String phoneNumber;
}
