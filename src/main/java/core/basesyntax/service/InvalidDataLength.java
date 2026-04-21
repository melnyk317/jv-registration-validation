package core.basesyntax.service;

public class InvalidDataLength extends RuntimeException {
    public InvalidDataLength(String message) { 
        super(message);
    }
}
