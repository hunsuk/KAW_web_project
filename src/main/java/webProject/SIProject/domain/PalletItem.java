package webProject.SIProject.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PalletItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PALLET_ID")
    private Long id;
    private String category;
    private String standard = "-";
    private String size;
    private String weight;
    private String material;
    private String uniqueness;
    private String img_uri;
    private String front_type;

}

