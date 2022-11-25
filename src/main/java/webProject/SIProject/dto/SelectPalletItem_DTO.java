package webProject.SIProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SelectPalletItem_DTO {
    private String select_pallet;

    public SelectPalletItem_DTO(){
        this.select_pallet = "-";
    }
}
