package core.basesyntax.service;

public class UserExistExeption extends RuntimeException {
    public UserExistExeption(String message) {
        super(message);
    }
}
