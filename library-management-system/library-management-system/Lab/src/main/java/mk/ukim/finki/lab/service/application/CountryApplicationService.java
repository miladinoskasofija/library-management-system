package mk.ukim.finki.lab.service.application;

import mk.ukim.finki.lab.model.domain.Country;
import mk.ukim.finki.lab.dto.CreateBookDTO;
import mk.ukim.finki.lab.dto.CreateCountryDTO;
import mk.ukim.finki.lab.dto.DisplayCountryDTO;
import mk.ukim.finki.lab.model.views.AuthorsPerCountryView;
import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDTO> findAll();
    Optional<DisplayCountryDTO> findById(long id);
    void deleteById(long id);
    Optional<DisplayCountryDTO> save(CreateCountryDTO country);
    Optional<DisplayCountryDTO> update(long id, CreateCountryDTO country);
    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
    AuthorsPerCountryView findAuthorsPerCountry(Long id);
    void refreshMaterializedView();
}
