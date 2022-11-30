package webProject.SIProject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
@Getter
@Setter
public class PalletItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PALLET_ID")
    private Long id;

    private String category;
    private String standard_type = "-";
    private String size;
    private String weight;
    private String material;
    private String uniqueness;
    private String img_uri;
    private String front_type;

}