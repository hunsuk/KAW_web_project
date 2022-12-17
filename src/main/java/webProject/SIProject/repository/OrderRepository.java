package webProject.SIProject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.OrderList;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderList, Long> {

    Optional<OrderList> findByUser_Email(String email);

    Optional<OrderList> findByUser_OrderLists_Id(Long id);

    Optional<OrderList> findByStatus(String status);

}
