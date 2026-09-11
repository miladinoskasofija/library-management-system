package mk.ukim.finki.lab.events;

import lombok.Data;
import lombok.Getter;
import mk.ukim.finki.lab.model.domain.Author;
import org.springframework.context.ApplicationEvent;
import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}