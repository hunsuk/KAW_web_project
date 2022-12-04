package webProject.SIProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.Reservation_DTO;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.dto.User_DTO;
import webProject.SIProject.repository.OrderRepository;
import webProject.SIProject.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ReservationServices {
    private final ReservationRepository reservationRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void save(long ORDERLIST_ID, Reservation_DTO infoDto) {
        OrderList orderList = orderRepository.findByUser_OrderLists_Id(ORDERLIST_ID)
                .orElseThrow(IllegalArgumentException::new);
        int len = infoDto.getSelected().length;
        int i;
        for(i = 0; i < len; i++) {
            String countDto = infoDto.getCount()[i];
            String rentDay = infoDto.getRant_day()[i];
            orderList.addReservation(Reservation.builder()
                    .count(countDto)
                    .rent_day(rentDay)
                    .build());
        }
    }
}
