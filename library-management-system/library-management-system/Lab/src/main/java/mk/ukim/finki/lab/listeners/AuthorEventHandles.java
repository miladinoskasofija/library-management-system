package mk.ukim.finki.lab.listeners;

import mk.ukim.finki.lab.events.AuthorCreatedEvent;
import mk.ukim.finki.lab.service.domain.AuthorService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandles {
    private final AuthorService authorService;
    public AuthorEventHandles(AuthorService authorService) {
        this.authorService = authorService;
    }
}
