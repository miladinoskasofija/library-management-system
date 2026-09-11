package mk.ukim.finki.lab.service.application.impl;

import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.dto.CreateBookDTO;
import mk.ukim.finki.lab.dto.DisplayBookDTO;
import mk.ukim.finki.lab.service.application.BookApplicationService;
import mk.ukim.finki.lab.service.domain.AuthorService;
import mk.ukim.finki.lab.service.domain.BookService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService{
    public final BookService bookService;
    public final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @Override
    public List<DisplayBookDTO> findAll() {
        return bookService.findAll().stream()
                .map(DisplayBookDTO::from)
                .toList();
    }
    @Override
    public Optional<DisplayBookDTO> findById(long id) {
        return bookService.findById(id).map(DisplayBookDTO::from);
    }
    @Override
    public void deleteById(long id) {
        bookService.deleteById(id);
    }
    @Override
    public Optional<DisplayBookDTO> save(CreateBookDTO book) {
        Author author = authorService.findById(book.author()).orElseThrow();
        Book book1 = book.toBook(author);
        return bookService.save(book1).map(DisplayBookDTO::from);
    }
    @Override
    public Optional<DisplayBookDTO> update(long id, CreateBookDTO book) {
        Author author = authorService.findById(book.author()).orElseThrow();
        Book book1 = book.toBook(author);
        return bookService.update(id,book1).map(DisplayBookDTO::from);
    }
    @Override
    public void addBookToWishList(Long id, String token) {
        bookService.addBookToWishList(id,token);
    }
    @Override
    public void removeBookFromWishList(Long id, String token) {
        bookService.removeBookFromWishList(id,token);
    }
    @Override
    public List<DisplayBookDTO> findAllInWishList(String token) {
        return bookService.findAllInWishList(token).stream()
                .map(DisplayBookDTO::from)
                .toList();
    }
    @Override
    public boolean rentAllFromWishList(String token) {
        return bookService.rentAllFromWishList(token);
    }
    @Override
    public boolean rentBook(Long id, String token) {
        return bookService.rentBook(id,token);
    }
    @Override
    public void returnBook(Long id, String token) {
        bookService.returnBook(id,token);
    }
}