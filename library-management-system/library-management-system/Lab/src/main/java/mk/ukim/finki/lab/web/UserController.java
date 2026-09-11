package mk.ukim.finki.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.dto.CreateUserDTO;
import mk.ukim.finki.lab.dto.DisplayUserDTO;
import mk.ukim.finki.lab.dto.LoginResponseDTO;
import mk.ukim.finki.lab.dto.LoginUserDTO;
import mk.ukim.finki.lab.model.projections.UserProjection;
import mk.ukim.finki.lab.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {
    private final UserApplicationService userApplicationService;
    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDTO> register(@RequestBody CreateUserDTO createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginUserDTO loginUserDto) {
        LoginResponseDTO body = userApplicationService.login(loginUserDto).orElseThrow();
        return ResponseEntity.ok(body);
    }
    @GetMapping("/role")
    public ResponseEntity<?> getRole(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(userDetails);
    }
    @GetMapping("/names")
    public List<UserProjection> getAllUserNames() {
        return userApplicationService.getAllUserNames();
    }
}