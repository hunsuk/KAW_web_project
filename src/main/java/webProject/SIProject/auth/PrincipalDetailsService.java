package webProject.SIProject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webProject.SIProject.PrincipalDetails;
import webProject.SIProject.domain.User;
import webProject.SIProject.repository.UserRepository;

import java.sql.SQLException;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = null;
        try {
            byUsername = userRepository.findByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        return null;
    }
}
