package webProject.SIProject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.User;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.dto.SelectPalletItem_DTO;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.ReservationRepository;
import webProject.SIProject.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
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

    //orderList 상태 바꾸는 함수
    @Transactional
    public void update(Long orderid, String email, String toChange) {
        OrderList orderList = orderRepository.findById(orderid)
                .orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderList.setStatus(toChange);
    }

    //orderList request 추가하는 함수.
    @Transactional
    public void update(Long orderid, String toChange, Reservation_DTO rdto) {
        OrderList orderList = orderRepository.findById(orderid)
                .orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findByEmail(orderList.getUser().getEmail())
                .orElseThrow(IllegalArgumentException::new);
        orderList.setStatus(toChange);
        if(!rdto.getUserabout().isEmpty()){
            String req = rdto.getUserabout();
            orderList.setRequest(req.getBytes(StandardCharsets.UTF_8));
        }
    }


    private boolean isStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Transactional
    public void delete(Long orderid, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderRepository.deleteByIdAndUser(orderid,user);
    }

    // email에 연관된 기반한 Order 모두찾기
    public List<OrderList> read(String email){
        List<OrderList> orderList = orderRepository.findByUser_Email(email);
        return orderList;
    }

    // email의 유일한 ing 주문 찾기.
    public OrderList read(String email,String status){
        OrderList orderList = orderRepository.findByStatusAndUser_Email(status,email)
                .orElseThrow(IllegalArgumentException::new);
        return orderList;
    }

}
