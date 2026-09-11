package mk.ukim.finki.lab.service.domain.impl;

import mk.ukim.finki.lab.model.domain.Country;
import mk.ukim.finki.lab.repository.CountryRepository;
import mk.ukim.finki.lab.service.domain.CountryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    @Override
    public Optional<Country> findById(long id) {
        return countryRepository.findById(id);
    }
    @Override
    public void deleteById(long id) {
        countryRepository.deleteById(id);
    }
    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }
    @Override
    public Optional<Country> update(long id, Country country) {
        return countryRepository.findById(id).map(c->{
            if(country.getName()!=null){
                c.setName(country.getName());
            }
            if(country.getContinent()!=null){
                c.setContinent(country.getContinent());
            }
            return countryRepository.save(c);
        });
    }
}