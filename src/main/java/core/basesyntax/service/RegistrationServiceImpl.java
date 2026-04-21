package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getAge() == null) {
            throw new NullDataException("Your age can`t be null");
        }
        if (user.getLogin() == null) {
            throw new NullDataException("Your login can`t be null");
        }
        if (user.getPassword() == null) {
            throw new NullDataException("Your login can`t be null");
        }
        if (user.getAge() < 18) {
            throw new InvalidAgeException("Your are too young, or your age is invalid");
        }
        if (user.getPassword().length() < 6) {
            throw new InvalidDataLengthException("Login length must be at least 6");
        }
        if (user.getLogin().length() < 6) {
            throw new InvalidDataLengthException("Password length must be at least 6");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new UserExistException("This login is already taken");
        }
        storageDao.add(user);
        return user;
    }
}
