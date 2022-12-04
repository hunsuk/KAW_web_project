package webProject.SIProject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import webProject.SIProject.dto.User_DTO;
import webProject.SIProject.repository.UserRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional 테스트 후 RollBack
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    void save() {
        User_DTO userDto = new User_DTO();
        userDto.setAuth("ROLE_USER");
        userDto.setEmail("khu1234");
        userDto.setPhone("010-2103-3096");
        userDto.setCorpName("한일상사");
        userDto.setPassword("1234");
        userDto.setManagerName("김현석");
        System.out.print(userDto);
        userService.save(userDto);

    }
}