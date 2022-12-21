package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.*;
import org.springframework.security.access.method.P;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;
    private String count;
    private String rent_day;
    private String standardPallet;
    private String status;

    //Order = 1 : Reservation = many
    //Order하나에 여러개의 Reservation 정보 참조.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDERLIST_ID")
    private OrderList orderList;

    @Builder
    public Reservation(String count, String rent_day, OrderList orderList, String standardPallet, String status) {
        this.count = count;
        this.rent_day = rent_day;
        this.orderList = orderList;
        this.standardPallet = standardPallet;
        this.status = status;
    }
    
}