package mk.ukim.finki.lab.service.application.impl;

import mk.ukim.finki.lab.events.AuthorChangedEvent;
import mk.ukim.finki.lab.events.AuthorCreatedEvent;
import mk.ukim.finki.lab.events.AuthorDeletedEvent;
import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Country;
import mk.ukim.finki.lab.dto.CreateAuthorDTO;
import mk.ukim.finki.lab.dto.DisplayAuthorDTO;
import mk.ukim.finki.lab.model.views.BooksPerAuthorView;
import mk.ukim.finki.lab.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.lab.service.application.AuthorApplicationService;
import mk.ukim.finki.lab.service.domain.AuthorService;
import mk.ukim.finki.lab.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Override
    public List<DisplayAuthorDTO> findAll() {
        return authorService.findAll().stream()
                .map(DisplayAuthorDTO::from)
                .toList();
    }
    @Override
    public Optional<DisplayAuthorDTO> findById(long id) {
        return authorService.findById(id).map(DisplayAuthorDTO::from);
    }
    @Override
    public void deleteById(long id) {
        Author author =authorService.findById(id).orElseThrow();
        authorService.deleteById(id);
        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(author));
    }
    @Override
    public Optional<DisplayAuthorDTO> save(CreateAuthorDTO author) {
        Optional<Country> country = countryService.findById(author.country());
        Author author1 = author.toAuthor(country.orElse(null));
        this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(author1));
        return authorService.save(author1).map(DisplayAuthorDTO::from);
    }
    @Override
    public Optional<DisplayAuthorDTO> update(long id, CreateAuthorDTO author) {
        Country country = countryService.findById(author.country()).orElseThrow();
        Author author1 = author.toAuthor(country);
        this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(author1));
        return authorService.update(id, author1).map(DisplayAuthorDTO::from);
    }
    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }
    @Override
    public BooksPerAuthorView findBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }
    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }
}