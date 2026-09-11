package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Country;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDTO(Long id, String name,String surname, Long country) {
    public Author toAuthor(Country country){
        return new Author(name,surname,country);
    }
    public static DisplayAuthorDTO from(Author author){
        return new DisplayAuthorDTO(author.getId(),author.getName(),author.getSurname(),author.getCountry().getId());
    }
    public static List<DisplayAuthorDTO> from(List<Author> authors){
        return authors.stream()
                .map(a->new DisplayAuthorDTO(a.getId(),a.getName(),a.getSurname(),a.getCountry().getId()))
                .toList();
    }
}
