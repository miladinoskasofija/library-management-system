package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.domain.User;
import java.util.List;

public record DisplayUserDTO (String username, String name, Role role, List<Long>wishlist,List<Long>rented){
    public static DisplayUserDTO from(User user){
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getBookWishlist().stream().map(Book::getId).toList(),
                user.getRentedBooks().stream().map(Book::getId).toList()
        );
    }
}