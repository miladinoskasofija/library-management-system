package mk.ukim.finki.lab.service.application;

import mk.ukim.finki.lab.dto.CreateBookDTO;
import mk.ukim.finki.lab.dto.DisplayBookDTO;
import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDTO> findAll();
    Optional<DisplayBookDTO> findById(long id);
    void deleteById(long id);
    Optional<DisplayBookDTO> save(CreateBookDTO book);
    Optional<DisplayBookDTO> update(long id,CreateBookDTO book);
    void addBookToWishList(Long id, String token);
    void removeBookFromWishList(Long id, String token);
    List<DisplayBookDTO> findAllInWishList(String token);
    boolean rentAllFromWishList(String token);
    boolean rentBook(Long id, String token);
    void returnBook(Long id, String token);
}