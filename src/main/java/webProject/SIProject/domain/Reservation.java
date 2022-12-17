package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.*;

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
    private String userAbout;

    //Reservation = 1 -> PalletItem = 1 
    //Reservation이 단방향으로 PalletItem 정보 참조 가능.
    @OneToOne
    @JoinColumn(name="PALLET_ID")
    private PalletItem palletItem;

    //Order = 1 : Reservation = many
    //Order하나에 여러개의 Reservation 정보 참조.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ORDERLIST_ID")
    private OrderList orderList;

    @Builder
    public Reservation(String count, String rent_day, OrderList orderList,String standardPallet,String userAbout) {
        this.count = count;
        this.rent_day = rent_day;
        this.orderList = orderList;
        this.standardPallet = standardPallet;
    }
    
}