package webProject.SIProject.dto;

import lombok.Data;

@Data
public class Front_main_DTO {
    private String coname;

    public Front_main_DTO(String coname){
        this.coname = coname;
    }
}
