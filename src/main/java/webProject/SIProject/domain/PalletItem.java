package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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