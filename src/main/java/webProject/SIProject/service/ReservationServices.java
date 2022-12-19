package webProject.SIProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServices {
    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void save(String email, String status, Reservation_DTO infoDto) {
        if(status != "ing") {
            return;
        }
        Optional<OrderList> orderList = orderRepository.findByStatusAndUser_Email(status,email);
        if(orderList.isPresent()){
            OrderList orderLists = orderList.get();
            int len = infoDto.getSelected().length;
            int i;
            for(i = 0; i < len; i++) {
                String countDto = infoDto.getCount()[i];
                String rentDay = infoDto.getRant_day()[i];
                String selected = infoDto.getSelected()[i];
                reservationRepository.save(Reservation.builder()
                        .count(countDto)
                        .rent_day(rentDay)
                        .orderList(orderLists)
                        .standardPallet(selected)
                        .build());
            }
            orderLists.setStatus("sent");
            String req = infoDto.getUserabout();
            orderLists.setRequest(req);
        }
    }

    //orderid 기반으로 찾고 딸린 reservation들 전부 삭제 후 dto로부터 읽어와서 재 저장
    @Transactional
    public void update(Long orderid, String toStatus, Reservation_DTO infoDto) {
        OrderList orderList = orderRepository.findById(orderid)
                .orElseThrow(IllegalArgumentException::new);
        int len = infoDto.getSelected().length;
        int i;
        reservationRepository.deleteByOrderList(orderList);
        for(i = 0; i < len; i++) {
            String countDto = infoDto.getCount()[i];
            String rentDay = infoDto.getRant_day()[i];
            String selected = infoDto.getSelected()[i];
            reservationRepository.save(Reservation.builder()
                    .count(countDto)
                    .rent_day(rentDay)
                    .orderList(orderList)
                    .standardPallet(selected)
                    .build());
        }
        orderList.setStatus(toStatus);
        String req = infoDto.getUserabout();
        orderList.setRequest(infoDto.getUserabout());
    }

    // email status standardPallet 받아서 각각 reservation 삭제
    @Transactional
    public void delete(String email, String status, String standardPallet){
        OrderList orderList = orderRepository.findByStatusAndUser_Email(status,email)
                .orElseThrow(IllegalArgumentException::new);
        reservationRepository.deleteByOrderListAndStandardPallet(orderList,standardPallet);
    }

    // orderid standardPallet받아서 삭제
    @Transactional
    public void delete(Long orderid, String standardPallet){
        OrderList orderList = orderRepository.findById(orderid)
                .orElseThrow(IllegalArgumentException::new);
        reservationRepository.deleteByOrderListAndStandardPallet(orderList,standardPallet);
    }

    //Order ID 기반 찾기
    public List<Reservation> read(Long orderId) {
        List<Reservation> reservation = null;
        Optional<OrderList> orderList = orderRepository.findById(orderId);
        if (orderList.isPresent()) {
            OrderList ord = orderList.get();
            reservation = reservationRepository.findByOrderList(ord);
        }
        return reservation;
    }
}