package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getAge() == null || user.getLogin() == null || user.getPassword() == null) {
            throw new InvalidData("Your data can`t be null");
        }
        if (user.getAge() < 18) {
            throw new InvalidAgeException("User is a minor");
        }
        if (user.getPassword().length() < 6 || user.getLogin().length() < 6) {
            throw new InvalidDataLength("Your password or your login is too short");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new UserExistExeption("This login is already taken");
        }
        storageDao.add(user);
        return user;
    }
}
