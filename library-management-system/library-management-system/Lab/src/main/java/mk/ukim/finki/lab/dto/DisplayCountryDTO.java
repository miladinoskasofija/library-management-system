package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Country;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO (Long id, String name, String continent){
    public Country toCountry(){
        return new Country(name,continent);
    }
    public static DisplayCountryDTO from(Country country){
        return new DisplayCountryDTO(country.getId(),country.getName(),country.getContinent());
    }
    public static List<DisplayCountryDTO> from(List<Country> countryList){
        return countryList.stream()
                .map(c->new DisplayCountryDTO(c.getId(),c.getName(),c.getContinent()))
                .toList();
    }
}
