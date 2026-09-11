package mk.ukim.finki.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.domain.Country;
import mk.ukim.finki.lab.model.domain.User;
import mk.ukim.finki.lab.model.enumerations.CategoryEnum;
import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.repository.AuthorRepository;
import mk.ukim.finki.lab.repository.BookRepository;
import mk.ukim.finki.lab.repository.CountryRepository;
import mk.ukim.finki.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init() {
        Country mk = new Country("Mk", "E");
        Country a = new Country("Australia", "Australia");
        countryRepository.save(mk);
        countryRepository.save(a);
        Author a1 = new Author("Name1", "Surname1", mk);
        Author a2 = new Author("Second name", "Second surname", a);
        authorRepository.save(a1);
        authorRepository.save(a2);
        Book b1 = new Book("book1", CategoryEnum.BIOGRAPHY, a1, 10);
        Book b2 = new Book("book2", CategoryEnum.DRAMA, a2, 0);
        Book b3 = new Book("book3", CategoryEnum.NOVEL, a1, 1);
        bookRepository.save(b1);
        bookRepository.save(b2);
        bookRepository.save(b3);
        List<Book> listB = new ArrayList<>();
        listB.add(b1);
        listB.add(b2);
        bookRepository.save(b3);
        User u1 = new User("user", passwordEncoder.encode("user"), "User name", "User surname", Role.ROLE_USER);
        userRepository.save(u1);
        userRepository.save(new User("lib", passwordEncoder.encode("lib"), "Librarian name", "Librarian surname", Role.ROLE_LIBRARIAN));
    }
}