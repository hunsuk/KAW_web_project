package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERLIST_ID")
    private Long id;
    private String status;
    //Order = 1 : reservations = many
    @OneToMany(mappedBy = "orderList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<Reservation>();

    //User = 1 : Order = many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    private User user;

    @Builder
    public OrderList(String status,User user) {
        this.status = status;
        this.user = user;
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
}
