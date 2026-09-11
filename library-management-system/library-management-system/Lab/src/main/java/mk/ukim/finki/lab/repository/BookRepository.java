package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.enumerations.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import mk.ukim.finki.lab.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
