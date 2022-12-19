package webProject.SIProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation_DTO {
    private String[] selected;
    private String[] count;
    private String[] rant_day;
    private String userabout = "";
}
