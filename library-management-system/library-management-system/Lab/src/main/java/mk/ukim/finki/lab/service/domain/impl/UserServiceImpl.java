package mk.ukim.finki.lab.service.domain.impl;

import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.model.projections.UserProjection;
import mk.ukim.finki.lab.repository.UserRepository;
import mk.ukim.finki.lab.security.JwtHelper;
import mk.ukim.finki.lab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }
    @Override
    public User register(String username, String password, String repeatPassword, String name, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException();
        if (!password.equals(repeatPassword)) throw new RuntimeException();
        if (userRepository.findById(username).isPresent())
            throw new RuntimeException(username);
        User user = new User(username, passwordEncoder.encode(password), name, "User surname", role);
        return userRepository.save(user);
    }
    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }
        User user = findByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException();
        return user;
    }
    @Override
    public User getAuthenticatedUser(String token) {
        String username = jwtHelper.extractUsername(token);
        return findByUsername(username);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow();
    }
    @Override
    public List<UserProjection> getAllUserNames() {
        return userRepository.findAllProjectedBy();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow();
    }
}