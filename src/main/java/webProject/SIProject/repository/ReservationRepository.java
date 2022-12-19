package webProject.SIProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.PalletItem;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.domain.OrderList;

import java.util.List;
import java.util.Optional;
import java.util.Iterator;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOrderList(OrderList orderList);


    Optional<Reservation> findByStandardPalletAndOrderList_Id(String standardPallet, Long id);

    long deleteByOrderList(OrderList orderList);



    long deleteByOrderListAndStandardPallet(OrderList orderList, String standardPallet);










}
