package saechim.interior.orderservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderNo")
    private Long no;

    private String interiorId;

    private Integer qty; //여기서는 주문수

    private Integer unitPrice;

    private Integer totalPrice;

    private String userId;

    private String orderId;

    @Builder
    public OrderEntity(String interiorId, Integer qty, Integer unitPrice, Integer totalPrice, String userId){
        this.interiorId=interiorId;
        this.qty=qty;
        this.unitPrice=unitPrice;
        this.totalPrice= totalPrice;
        this.userId=userId;
        this.orderId= UUID.randomUUID().toString().substring(0,8);
    }

    public void changeQty(Integer qty){
        this.qty=qty;
        this.totalPrice=qty*this.unitPrice;
    }

}
