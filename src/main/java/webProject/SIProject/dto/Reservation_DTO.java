package webProject.SIProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation_DTO {
    private String[] selected;
    private String[] count;
    private String[] rant_day;
    private String userAbout;

}
