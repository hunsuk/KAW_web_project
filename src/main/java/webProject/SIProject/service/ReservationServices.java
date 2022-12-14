package webProject.SIProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.PalletItemRepository;
import webProject.SIProject.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReservationServices {
    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;

    private final PalletItemRepository palletItemRepository;
    @Transactional
    public void save2(Optional<Reservation> reservation){
        reservationRepository.save(Reservation.builder()
                .count(reservation.get().getCount())
                .rent_day(reservation.get().getRent_day())
                .orderList(reservation.get().getOrderList())
                .standardPallet(reservation.get().getStandardPallet())
                .status(reservation.get().getStatus())
                .build());
    }
    @Transactional
    public void save(String email, OrderList orderList, Reservation_DTO infoDto, String status) {
            orderList.setStatus("sent");
//        Optional<OrderList> orderList = orderRepository.findByStatusAndUser_Email(status,email);
//        if(orderList.isPresent()){
//            OrderList orderLists = orderList.get();
            int len = infoDto.getSelected().length;
            int i;
            for(i = 0; i < len; i++) {
                String countDto = infoDto.getCount()[i];
                String rentDay = infoDto.getRant_day()[i];
                String selected = infoDto.getSelected()[i];
                reservationRepository.save(Reservation.builder()
                        .count(countDto)
                        .rent_day(rentDay)
                        .orderList(orderList)
                        .standardPallet(selected)
                        .status(status)
                        .build());
            }

            String req = infoDto.getUserAbout();
            orderList.setRequest(req);
        }
//    }

    public Optional<Reservation> readbyID(long id){
        return reservationRepository.findById(id);
    }


    //orderid ???????????? ?????? ?????? reservation??? ?????? ?????? ??? dto????????? ???????????? ??? ??????
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
        String req = infoDto.getUserAbout();
        orderList.setRequest(infoDto.getUserAbout());
    }

    // email status standardPallet ????????? ?????? reservation ??????
    @Transactional
    public void delete(String email, String status, String standardPallet){
        OrderList orderList = orderRepository.findByStatusAndUser_Email(status,email)
                .orElseThrow(IllegalArgumentException::new);
        reservationRepository.deleteByOrderListAndStandardPallet(orderList,standardPallet);
    }

    // orderid standardPallet????????? ??????
    @Transactional
    public void delete(Long orderid, String standardPallet){
        OrderList orderList = orderRepository.findById(orderid)
                .orElseThrow(IllegalArgumentException::new);
        reservationRepository.deleteByOrderListAndStandardPallet(orderList,standardPallet);
    }
    @Transactional
    public void delete(Long id){
        reservationRepository.deleteById(id);
    }

    //Order ID ?????? ??????
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