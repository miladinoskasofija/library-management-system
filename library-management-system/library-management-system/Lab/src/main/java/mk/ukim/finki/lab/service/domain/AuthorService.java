package mk.ukim.finki.lab.service.domain;

import mk.ukim.finki.lab.model.domain.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(long id);
    void deleteById(long id);
    Optional<Author> save(Author author);
    Optional<Author> update(long id,Author author);
}
