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
    public void save(String email, String items) {
        if(isStringEmpty(items)){
        }else{
            User user = userRepository.findByEmail(email)
                    .orElseThrow(IllegalArgumentException::new);
            orderRepository.save(OrderList.builder()
                    .status("ing")
                    .user(user)
                    .items(items)
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
        String req = rdto.getUserAbout();
        orderList.setRequest(req);
    }


    private boolean isStringEmpty(String str) {
        return str == null || str.isEmpty();
    }

    //유저 특정장부 제거(reservation까지 지워짐)
    @Transactional
    public void delete(Long orderid, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderRepository.deleteByIdAndUser(orderid,user);
    }

    //유저관련 장부 전부제거
    @Transactional
    public void deleteAll(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(IllegalArgumentException::new);
        orderRepository.deleteByUser(user);
    }

    // email에 연관된 기반한 Order 모두찾기
    public List<OrderList> read(String email){
        List<OrderList> orderList = orderRepository.findByUser_Email(email);
        return orderList;
    }

    public List<OrderList> allRead(){
        return orderRepository.findAll();
    }

    // email의 status 주문 찾기. ing의 경우 여러개면 0번째인 ing만 반환하고 전부 제거.
    public OrderList read(String email,String status){
        List<OrderList> orderList = orderRepository.findByUser_EmailAndStatus(email,status);
        Iterator<OrderList> lorderlist = orderList.iterator();
        OrderList ord = null;
        if(lorderlist.hasNext()){
            ord = lorderlist.next();
        }
        if(status == "ing"){
            while(lorderlist.hasNext()){
                orderRepository.deleteById(lorderlist.next().getId());
            }
        }
        return ord;
    }

}
