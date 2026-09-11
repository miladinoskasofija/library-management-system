package mk.ukim.finki.lab.service.domain;

import mk.ukim.finki.lab.model.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(long id);
    void deleteById(long id);
    Optional<Book> save(Book book);
    Optional<Book> update(long id,Book book);
    void addBookToWishList(Long id, String token);
    void removeBookFromWishList(Long id, String token);
    List<Book> findAllInWishList(String token);
    boolean rentAllFromWishList(String token);
    boolean rentBook(Long id, String token);
    void returnBook(Long id, String token);
}