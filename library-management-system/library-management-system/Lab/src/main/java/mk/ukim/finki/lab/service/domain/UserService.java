package mk.ukim.finki.lab.service.domain;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.model.projections.UserProjection;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, Role role);
    User login(String username, String password);
    User getAuthenticatedUser(String token);
    User findByUsername(String username);
    List<UserProjection> getAllUserNames();

}