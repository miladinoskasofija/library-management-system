package mk.ukim.finki.lab.dto;

import mk.ukim.finki.lab.model.domain.Author;
import mk.ukim.finki.lab.model.domain.Book;
import mk.ukim.finki.lab.model.enumerations.CategoryEnum;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDTO(Long id, String name,CategoryEnum category, Long author,Integer availableCopies) {
    public Book toBook(Author author){
        return new Book(name,category,author,availableCopies);
    }
    public static DisplayBookDTO from(Book book){
        return new DisplayBookDTO(book.getId(),book.getName(),book.getCategory(),book.getAuthor().getId(),book.getAvailableCopies());
    }
    public static List<DisplayBookDTO> from(List<Book> books){
        return books.stream()
                .map(b->new DisplayBookDTO(b.getId(),b.getName(),b.getCategory(),b.getAuthor().getId(),b.getAvailableCopies()))
                .toList();
    }
}