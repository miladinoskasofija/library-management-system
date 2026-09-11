package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.enumerations.CategoryEnum;

import java.util.List;

public record CreateBookDTO(String name,CategoryEnum category,Long author,Integer availableCopies) {

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
    public static CreateBookDTO from(Book book) {
        return new CreateBookDTO(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
    }
    public static List<CreateBookDTO> from(List<Book> books) {
        return books.stream()
                .map(b -> new CreateBookDTO(b.getName(), b.getCategory(), b.getAuthor().getId(), b.getAvailableCopies()))
                .toList();
    }
}
