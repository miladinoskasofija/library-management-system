package mk.ukim.finki.lab.service.application;

import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.dto.CreateAuthorDTO;
import mk.ukim.finki.lab.dto.DisplayAuthorDTO;
import mk.ukim.finki.lab.model.views.BooksPerAuthorView;
import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDTO> findAll();
    Optional<DisplayAuthorDTO> findById(long id);
    void deleteById(long id);
    Optional<DisplayAuthorDTO> save(CreateAuthorDTO author);
    Optional<DisplayAuthorDTO> update(long id,CreateAuthorDTO author);
    List<BooksPerAuthorView> findAllBooksPerAuthor();
    BooksPerAuthorView findBooksPerAuthor(Long id);
    void refreshMaterializedView();
}