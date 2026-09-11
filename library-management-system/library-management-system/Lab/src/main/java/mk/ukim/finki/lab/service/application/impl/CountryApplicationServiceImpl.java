package mk.ukim.finki.lab.service.application.impl;

import mk.ukim.finki.lab.model.domain.Country;
import mk.ukim.finki.lab.dto.CreateCountryDTO;
import mk.ukim.finki.lab.dto.DisplayCountryDTO;
import mk.ukim.finki.lab.model.views.AuthorsPerCountryView;
import mk.ukim.finki.lab.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.lab.service.application.CountryApplicationService;
import mk.ukim.finki.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }
    @Override
    public List<DisplayCountryDTO> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDTO::from)
                .toList();
    }
    @Override
    public Optional<DisplayCountryDTO> findById(long id) {
        return countryService.findById(id).map(DisplayCountryDTO::from);
    }
    @Override
    public void deleteById(long id) {
        countryService.deleteById(id);
    }
    @Override
    public Optional<DisplayCountryDTO> save(CreateCountryDTO country) {
        Country country1 = country.toCountry();
        return countryService.save(country1).map(DisplayCountryDTO::from);
    }
    @Override
    public Optional<DisplayCountryDTO> update(long id, CreateCountryDTO country) {
        return countryService.update(id,country.toCountry()).map(DisplayCountryDTO::from);
    }
    @Override
    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }
    @Override
    public AuthorsPerCountryView findAuthorsPerCountry(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow();
    }
    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
