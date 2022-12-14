package webProject.SIProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webProject.SIProject.domain.PalletItem;
import webProject.SIProject.domain.Reservation;
import webProject.SIProject.domain.User;
import webProject.SIProject.dto.PalletItem_DTO;
import webProject.SIProject.dto.User_DTO;
import webProject.SIProject.repository.PalletItemRepository;
import webProject.SIProject.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PalletItemService  {
    private final PalletItemRepository palletItemRepository;

    public PalletItem loadPalletItemByStandard(String standard)  {
        return palletItemRepository.findByStandard(standard).orElseThrow(() -> new UsernameNotFoundException(standard));
    }
    @Transactional(readOnly = true)
    public List<PalletItem> getPallet() {
        return palletItemRepository.findAll();
    }



}
