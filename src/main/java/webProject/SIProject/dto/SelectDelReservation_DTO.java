package webProject.SIProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SelectDelReservation_DTO {
    private String selected;

    public SelectDelReservation_DTO(){
        this.selected = "-";
    }
}
