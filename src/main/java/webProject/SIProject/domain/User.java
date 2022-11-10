package webProject.SIProject.domain;


import lombok.*;

//import java.security.Timestamp;
import java.sql.Timestamp;

@Data
public class User {


    private String id;

    private String username;

    private String password;


    private Role role;

    private Timestamp createTime;

    public User(String id,String username, String password, Role role, Timestamp createTime) {
        this.id =id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createTime = createTime;
    }

}
