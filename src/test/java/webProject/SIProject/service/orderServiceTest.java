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
        rDTO.setUserabout("hello");

        long i = 1;
        reservationServices.save("daw564@naver.com","ing",rDTO);

        rDTO.setCount(new String[]{"4","5","6"});
        rDTO.setRant_day(new String[]{"4","5","6"});
        rDTO.setSelected(new String[]{"11A형","12A형","13B형"});
        rDTO.setUserabout("한글도 잘들어갑니다.");

        reservationServices.update(i,"ok", rDTO);

        //reservationServices.delete("daw564@naver.com","ing","4");

        reservationServices.delete(i, "11A형");

        //orderServices.update(i,"daw564@naver.com","ok");

        //orderServices.delete(i,"daw564@naver.com");

        //reservationServices.read(i);

        //orderServices.read("daw564@naver.com");

        //orderServices.read("daw564@naver.com", "ok");

        Reservation_DTO r2DTO = new Reservation_DTO();
        SelectPalletItem_DTO s2DTO = new SelectPalletItem_DTO("i");

        orderServices.save("daw564@naver.com",s2DTO);

        r2DTO.setCount(new String[]{"7","8","9"});
        r2DTO.setRant_day(new String[]{"7","8","9"});
        r2DTO.setSelected(new String[]{"15A형","국판형","11A형"});
        r2DTO.setUserabout("한글왜넣어요");

        reservationServices.save("daw564@naver.com","ing",r2DTO);


        Reservation_DTO r3DTO = new Reservation_DTO();
        SelectPalletItem_DTO s3DTO = new SelectPalletItem_DTO("i");

        orderServices.save("daw564@naver.com",s3DTO);

        r3DTO.setCount(new String[]{"73","82","91"});
        r3DTO.setRant_day(new String[]{"73","82","91"});
        r3DTO.setSelected(new String[]{"15A형","국판형","13B형"});
        r3DTO.setUserabout("한글좋아요");

        reservationServices.save("daw564@naver.com","ing",r3DTO);

        //orderServices.deleteAll("daw564@naver.com");

    }
}