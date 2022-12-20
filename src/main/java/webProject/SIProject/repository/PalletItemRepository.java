package webProject.SIProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webProject.SIProject.domain.PalletItem;
import webProject.SIProject.domain.User;

import java.util.List;
import java.util.Optional;


public interface PalletItemRepository extends JpaRepository<PalletItem, Long> {
    
    Optional <PalletItem> findByStandard(String standard);
}
