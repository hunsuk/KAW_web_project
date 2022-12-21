package webProject.SIProject.dto;

import lombok.*;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.PalletItem;
import webProject.SIProject.domain.User;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Reservation_add_User_DTO {
    private Long id;
    private String count;
    private String rent_day;
    private String standardPallet;
    private String status;
    private String request;
    private User user;
    private PalletItem palletItem;
    @Builder
    public Reservation_add_User_DTO(Long id,String count, String rent_day, String standardPallet, String status, String request,User user,PalletItem palletItem) {
        this.id =id;
        this.count = count;
        this.rent_day = rent_day;
        this.standardPallet = standardPallet;
        this.status = status;
        this.request =request;
        this.user = user;
        this.palletItem = palletItem;
    }
}
