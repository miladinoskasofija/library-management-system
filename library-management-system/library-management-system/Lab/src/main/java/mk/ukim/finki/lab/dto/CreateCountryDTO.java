package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Country;

import java.util.List;

public record CreateCountryDTO (String name, String continent){
    public Country toCountry(){
        return new Country(name,continent);
    }
    public static CreateCountryDTO from(Country country){
        return new CreateCountryDTO(country.getName(),country.getContinent());
    }
    public static List<CreateCountryDTO> from(List<Country> countryList){
        return countryList.stream()
                .map(c->new CreateCountryDTO(c.getName(),c.getContinent()))
                .toList();
    }
}
