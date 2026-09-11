package mk.ukim.finki.lab.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.dto.CreateUserDTO;
import mk.ukim.finki.lab.dto.DisplayUserDTO;
import mk.ukim.finki.lab.dto.LoginResponseDTO;
import mk.ukim.finki.lab.dto.LoginUserDTO;
import mk.ukim.finki.lab.model.projections.UserProjection;
import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDTO> register(CreateUserDTO createUserDto);
    Optional<LoginResponseDTO> login(LoginUserDTO loginUserDto);
    Optional<DisplayUserDTO> findByUsername(String username);
    List<UserProjection> getAllUserNames();
}

