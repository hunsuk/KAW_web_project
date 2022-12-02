package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    private String count;
    private String rent_day;
    private String status;

    //Reservation = 1 -> PalletItem = 1 
    //Reservation이 단방향으로 PalletItem 정보 참조 가능.
    @OneToOne
    @JoinColumn(name="PALLET_ID")
    private PalletItem palletItem;

    //Order = 1 : Reservation = many
    //Order하나에 여러개의 Reservation 정보 참조.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private OrderList orderList;
    
}

