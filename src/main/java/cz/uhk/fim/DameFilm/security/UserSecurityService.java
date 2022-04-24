package cz.uhk.fim.DameFilm.security;

import cz.uhk.fim.DameFilm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found by email + " + email));
    }

    public long loadIdByEmail(String email) {
        return userRepository.findByEmail(email).get().getUserId();
    }
}
