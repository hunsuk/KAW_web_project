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
    private boolean isStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Transactional
    public void delete(Long orderid, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderRepository.deleteByIdAndUser(orderid,user);
    }

    // email에 기반한 Order과 Reservation 찾기
    public String read(String email){
        String result = "";
        String status = "";
        Optional<OrderList> orderList = orderRepository.findByUser_Email(email);
        if(orderList.isPresent()) {
            OrderList ord = orderList.get();
            List<Reservation> reservation = reservationRepository.findByOrderList(ord);
            Iterator<Reservation> lreservation = reservation.iterator();
            Reservation tmp;
            String count;
            String rday;
            String spallet;
            while(lreservation.hasNext()){
                tmp = lreservation.next();
                result += count = tmp.getCount();
                result += rday = tmp.getRent_day();
                result += spallet = tmp.getStandardPallet();
            }
            status = ord.getStatus();
            result += status;
        }
        return result;
    }

    //email과 status에 기반한 Order와 Reservation 찾기
    public String read(String email, String status){
        String result = "";
        Optional<OrderList> orderList = orderRepository.findByStatusAndUser_Email(status,email);
        if(orderList.isPresent()) {
            OrderList ord = orderList.get();
            List<Reservation> reservation = reservationRepository.findByOrderList(ord);
            Iterator<Reservation> lreservation = reservation.iterator();
            Reservation tmp;
            String count;
            String rday;
            String spallet;
            while(lreservation.hasNext()){
                tmp = lreservation.next();
                result += count = tmp.getCount();
                result += rday = tmp.getRent_day();
                result += spallet = tmp.getStandardPallet();
            }
            result += status;
            }
        return result;
    }



}
