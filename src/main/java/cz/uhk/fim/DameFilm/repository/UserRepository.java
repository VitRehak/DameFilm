package cz.uhk.fim.DameFilm.repository;

import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.security.JwtUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
