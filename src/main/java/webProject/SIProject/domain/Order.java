package webProject.SIProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Setter
@Getter
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;


    //User = 1 : Order = many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="code")
    private User user;


    //Order = 1 : PalletItem = many
    @OneToMany(mappedBy = "Order")
    private List<Reservation> reservations = new ArrayList<Reservation>();
}