package webProject.SIProject.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class User_DTO {
    private String email;
    private String password;
    private String auth;
    private String corpName;
    private String phone;
    private String location;
    private String managerName;

}
