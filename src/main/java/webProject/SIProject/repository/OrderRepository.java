package webProject.SIProject.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.OrderList;
import webProject.SIProject.domain.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderList, Long> {
    List<OrderList> findByUser_EmailAndStatus(String email, String status);

    List<OrderList> findByUser_Email(String email);



    Optional<OrderList> findByStatus(String status);

    boolean existsByUser_Email(String email);

    Optional<OrderList> findByStatusAndUser_Email(String status, String email);

    Optional<OrderList> findByIdAndUser_Email(Long id, String email);


    long deleteByStatus(String status);

    long deleteByUser(User user);



    long deleteByIdAndUser(Long id, User user);

    @Override
    Optional<OrderList> findById(Long aLong);
}
