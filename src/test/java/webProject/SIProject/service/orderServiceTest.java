package webProject.SIProject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import webProject.SIProject.dto.User_DTO;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.dto.SelectPalletItem_DTO;
import webProject.SIProject.repository.UserRepository;
import webProject.SIProject.service.ReservationServices;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional 테스트 후 RollBack
@SpringBootTest
class orderServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ReservationServices reservationServices;
    @Autowired
    OrderServices orderServices;

    @Test
    void save() {
        User_DTO userDto = new User_DTO();
        Reservation_DTO rDTO = new Reservation_DTO();
        SelectPalletItem_DTO sDTO = new SelectPalletItem_DTO("i");

        orderServices.save("daw564@naver.com",sDTO);

        rDTO.setCount(new String[]{"1","2","3"});
        rDTO.setRant_day(new String[]{"1","2","3"});
        rDTO.setSelected(new String[]{"1","2","3"});

        reservationServices.save("daw564@naver.com","ing",rDTO);

    }
}