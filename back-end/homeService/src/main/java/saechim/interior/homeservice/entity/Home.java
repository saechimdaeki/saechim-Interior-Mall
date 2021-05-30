package saechim.interior.homeservice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Home {

    @Id
    @GeneratedValue
    @Column(name = "home_id")
    private Long id;
}
