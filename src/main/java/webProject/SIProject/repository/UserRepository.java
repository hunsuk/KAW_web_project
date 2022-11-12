package webProject.SIProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
