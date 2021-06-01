package saechim.interior.userservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, message = "2자 이상 입력")
    private String name;

    @Email(message = "아이디@도메인으로 입력")
    @Column(unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String userId;

    @Column(nullable = false,unique = true)
    private String encryptedPwd;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Builder
    public User(String name, String email, String userId, String encryptedPwd, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
        this.phoneNumber = phoneNumber;
    }
}
