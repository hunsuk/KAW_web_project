package webProject.SIProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PalletItem_DTO {
    private String category;
    private String standard_type = "-";
    private String size;
    private String weight;
    private String material;
    private String uniqueness;
    private String img_uri;
    private String front_type;
}
