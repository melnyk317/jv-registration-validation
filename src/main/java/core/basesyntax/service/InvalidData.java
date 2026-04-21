package core.basesyntax.service;

public class InvalidData extends RuntimeException {
    public InvalidData(String message) {
        super(message);
    }
}
