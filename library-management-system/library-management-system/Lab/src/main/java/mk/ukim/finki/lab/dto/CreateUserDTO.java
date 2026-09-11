package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.domain.User;

public record CreateUserDTO(
        String username,
        String password,
        String repeatPassword,
        String name,
        Role role
) {
    public User toUser() {
        return new User(username, password, name, "User surname", role);
    }
}