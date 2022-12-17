package webProject.SIProject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.User;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.dto.SelectPalletItem_DTO;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.ReservationRepository;
import webProject.SIProject.repository.UserRepository;

import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServices {

    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;

    private final UserRepository userRepository;



    @Transactional
    public void save(String email, SelectPalletItem_DTO infoDto) {
        String statusOrder = infoDto.getSelect_pallet();
        if(isStringEmpty(statusOrder)){
        }else{
            User user = userRepository.findByEmail(email)
                    .orElseThrow(IllegalArgumentException::new);
            orderRepository.save(OrderList.builder()
                    .status("ing")
                    .user(user)
                    .build());
        }
    }
    private boolean isStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Transactional
    public void delete(String email, String status){
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderRepository.deleteByStatusAndUser(status,user);
    }

    // email에 기반한 Order과 Reservation 찾기
    public List<Reservation> read(String email){
        OrderList orderList = orderRepository.findByUser_Email(email)
                .orElseThrow(IllegalArgumentException::new);
        String status = orderList.getStatus();
        List<Reservation> reservations = orderList.getReservations();
        return reservations;
    }

    public List<Reservation> read(String email, String status){
        OrderList orderList = orderRepository.findByStatusAndUser_Email(status,email)
                .orElseThrow(IllegalArgumentException::new);
        List<Reservation> reservations = orderList.getReservations();
        return reservations;
    }
}
