package mk.ukim.finki.lab.service.domain.impl;

import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.repository.UserRepository;
import mk.ukim.finki.lab.service.domain.BookService;
import mk.ukim.finki.lab.service.domain.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    public BookServiceImpl(BookRepository bookRepository, UserService userService, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }
    @Override
    public Optional<Book> update(long id, Book book) {
        return bookRepository.findById(id).map(b->{
            if(book.getAuthor()!=null){
                b.setAuthor(book.getAuthor());
            }
            if(book.getName()!=null){
                b.setName(book.getName());
            }
            if(book.getCategory()!=null){
                b.setCategory(book.getCategory());
            }
            if(book.getAvailableCopies()!=null){
                b.setAvailableCopies(book.getAvailableCopies());
            }
            return bookRepository.save(b);
        });
    }
    @Override
    public void addBookToWishList(Long id, String token) {
        User authUser = userService.getAuthenticatedUser(token);
        Book book = findById(id).orElseThrow();
        authUser.getBookWishlist().add(book);
        userRepository.save(authUser);
    }
    @Override
    public void removeBookFromWishList(Long id, String token) {
        User authUser = userService.getAuthenticatedUser(token);
        authUser.getBookWishlist().removeIf(b->b.getId().equals(id));
        userRepository.save(authUser);
    }
    @Override
    public List<Book> findAllInWishList(String token) {
        return userService.getAuthenticatedUser(token).getBookWishlist();
    }
    @Override
    public boolean rentAllFromWishList(String token) {
        User user = userService.getAuthenticatedUser(token);
        return !user.getBookWishlist().stream().map(b-> rentBook(b.getId(), token)).toList().contains(false);
    }
    @Override
    public boolean rentBook(Long id, String token) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.getAvailableCopies()==0){
            return false;
        }
        User authUser = userService.getAuthenticatedUser(token);
        authUser.getRentedBooks().add(book);
        removeBookFromWishList(id, token);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        userRepository.save(authUser);
        return true;
    }
    @Override
    public void returnBook(Long id, String token) {
        Book book = bookRepository.findById(id).orElseThrow();
        User authUser = userService.getAuthenticatedUser(token);
        authUser.getRentedBooks().remove(book);
        book.setAvailableCopies(book.getAvailableCopies()+1);
        userRepository.save(authUser);
        bookRepository.save(book);
    }
}