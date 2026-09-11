package mk.ukim.finki.lab.service.domain;

import mk.ukim.finki.lab.model.domain.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(long id);
    void deleteById(long id);
    Optional<Country> save(Country country);
    Optional<Country> update(long id,Country country);
}
