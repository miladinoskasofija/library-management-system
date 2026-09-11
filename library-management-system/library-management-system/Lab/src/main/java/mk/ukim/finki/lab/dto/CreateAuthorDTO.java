package mk.ukim.finki.lab.dto;


import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Country;

import java.util.List;

public record CreateAuthorDTO (String name,String surname,Long country) {
    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
    public static CreateAuthorDTO from(Author author) {
        return new CreateAuthorDTO(author.getName(), author.getSurname(), author.getCountry().getId());
    }
    public static List<CreateAuthorDTO> from(List<Author> authors) {
        return authors.stream()
                .map(a -> new CreateAuthorDTO(a.getName(), a.getSurname(), a.getCountry().getId()))
                .toList();
    }
}
