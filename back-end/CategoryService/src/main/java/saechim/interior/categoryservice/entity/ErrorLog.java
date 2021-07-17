package saechim.interior.categoryservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ErrorLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interiorId;

    private int orderCnt;

    private String userId;

    private String errorMsg;

    @Builder
    public ErrorLog(String interiorId, int orderCnt, String userId, String errorMsg) {
        this.interiorId = interiorId;
        this.orderCnt = orderCnt;
        this.userId = userId;
        this.errorMsg = errorMsg;
    }
}
