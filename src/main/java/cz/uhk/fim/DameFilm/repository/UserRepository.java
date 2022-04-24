package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
