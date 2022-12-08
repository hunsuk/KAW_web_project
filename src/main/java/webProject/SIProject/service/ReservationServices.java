package webProject.SIProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.ReservationRepository;

@RequiredArgsConstructor
@Service
public class ReservationServices {
    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public void save(String status, Reservation_DTO infoDto) {
        if(status != "ing") {
            return;
        }
        OrderList orderList = orderRepository.findByStatus(status)
                .orElseThrow(IllegalArgumentException::new);

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
                    .build());
        }
        orderList.setStatus("sent");
    }
}
