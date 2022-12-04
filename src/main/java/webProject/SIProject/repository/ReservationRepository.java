package webProject.SIProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.Reservation;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByOrderList_Id(Long id);

    Optional<Reservation> findByPalletItem_CategoryAndPalletItem_Standard_type(String category, String standard_type);




}
