package webProject.SIProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webProject.SIProject.domain.Role;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.Join_DTO;
import webProject.SIProject.repository.UserRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Controller
public class UserController {
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Join_DTO join_dto) throws SQLException {


        String encodePwd = bCryptPasswordEncoder.encode(join_dto.getPassword());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(UUID.randomUUID().toString(),join_dto.getUsername(),encodePwd, Role.USER,timestamp);
        userRepository.save(user);  //반드시 패스워드 암호화해야함

        return "redirect:/loginForm";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }
}
