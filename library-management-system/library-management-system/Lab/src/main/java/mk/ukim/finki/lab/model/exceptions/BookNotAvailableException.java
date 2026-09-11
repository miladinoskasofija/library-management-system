package mk.ukim.finki.lab.model.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(){
        super("Not Available");
    }
}
