package cz.uhk.fim.DameFilm.service;

import cz.uhk.fim.DameFilm.dto.in.InUser;
import cz.uhk.fim.DameFilm.dto.in.LoginUser;
import cz.uhk.fim.DameFilm.dto.in.RegisterUser;
import cz.uhk.fim.DameFilm.dto.out.OutUserProfile;
import cz.uhk.fim.DameFilm.dto.out.OutUserLogin;
import cz.uhk.fim.DameFilm.entity.user.Role;
import cz.uhk.fim.DameFilm.entity.user.User;
import cz.uhk.fim.DameFilm.repository.UserRepository;
import cz.uhk.fim.DameFilm.security.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Clock clock;
    @Autowired
    private DateTimeFormatter customDateTimeFormatter;

    @Override
    public OutUserLogin login(LoginUser user) {

        Optional<User> userByEmailOp = userRepository.findByEmail(user.getEmail());
        if (userByEmailOp.isEmpty()) {
            log.error("User Not Found");
            return null;
        }
        User userByEmail = userByEmailOp.get();

        if (!BCrypt.checkpw(user.getPassword(), userByEmail.getPassword())) {
            log.error("Wrong Password");
            return null;
        }
        log.info("Logged User with username:" + userByEmail.getUsername());
        OutUserLogin token = new OutUserLogin();
        token.setToken(jwtTokenProvider.createToken(user.getEmail()));
        token.setRoles(userByEmail.getRoles());
        token.setUserId(userByEmail.getUserId());
        return token;
    }


    @Override
    public OutUserLogin register(RegisterUser user) {

        Optional<User> userByEmailOp = userRepository.findByEmail(user.getEmail());
        if (userByEmailOp.isPresent()) {
            log.error("Existing User");
            return null;
        }
        User userByEmail = modelMapper.map(user, User.class);
        userByEmail.setDateOfRegistration(ZonedDateTime.now(clock));
        userByEmail.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        if(userByEmail.getRoles().size() == 0)
            userByEmail.setRoles(List.of(Role.ROLE_USER));
        userRepository.save(userByEmail);

        log.info("Registered User with username:" + userByEmail.getUsername());
        OutUserLogin outUserLogin = new OutUserLogin();
        outUserLogin.setToken(jwtTokenProvider.createToken(user.getEmail()));
        outUserLogin.setRoles(userByEmail.getRoles());
        outUserLogin.setUserId(userByEmail.getUserId());
        return outUserLogin;
    }

    @Override
    public OutUserProfile getUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.info("User Not Found");
            return null;
        }
        log.error("User Loaded");
        OutUserProfile outUser = modelMapper.map(user.get(), OutUserProfile.class);
        outUser.setDateOfRegistration(user.get().getDateOfRegistration().format(customDateTimeFormatter));
        return outUser;
    }

    @Override
    public OutUserProfile deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.info("User Not Found");
            return null;
        }
        userRepository.delete(user.get());
        log.error("User Deleted");
        return modelMapper.map(user.get(), OutUserProfile.class);
    }

    @Override
    public OutUserProfile updateUser(InUser user, long id) {
        Optional<User> OUser = userRepository.findById(id);
        if (OUser.isEmpty()) {
            log.info("User Not Found");
            return null;
        }
        User dbUser = OUser.get();
        User appUser = modelMapper.map(user, User.class);

        dbUser.setUsername(appUser.getUsername());
        dbUser.setFirstname(appUser.getFirstname());
        dbUser.setLastname(appUser.getLastname());

        userRepository.save(dbUser);
        log.error("User Updated");
        return modelMapper.map(dbUser, OutUserProfile.class);
    }
}
