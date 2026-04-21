package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import core.basesyntax.service.InvalidAgeException;
import core.basesyntax.service.InvalidDataLengthException;
import core.basesyntax.service.NullDataException;
import core.basesyntax.service.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private StorageDaoImpl storageDaoImpl = new StorageDaoImpl();

    @BeforeEach
    void setUp() {
        storageDaoImpl = new StorageDaoImpl();
    }

    @Test
    void register_validAge_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("firstLogin");
        testUser.setPassword("thePassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_exactly18Age_ok() {
        User testUser = new User();
        testUser.setAge(18);
        testUser.setLogin("eidgthLogin");
        testUser.setPassword("thePassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_underAge_notOk() {
        User testUser = new User();
        testUser.setAge(7);
        testUser.setLogin("secondLogin");
        testUser.setPassword("thePassword");
        assertThrows(InvalidAgeException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_negativeAge_notOk() {
        User testUser = new User();
        testUser.setAge(-7);
        testUser.setLogin("ninethLogin");
        testUser.setPassword("thePassword");
        assertThrows(InvalidAgeException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_nullAge_notOk() {
        User testUser = new User();
        testUser.setAge(null);
        testUser.setLogin("thirdLogin");
        testUser.setPassword("thePassword");
        assertThrows(NullDataException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_validLogin_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("validLogin");
        testUser.setPassword("thePassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_smallerValidLogin_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("qwdfhj");
        testUser.setPassword("thePassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_nullLogin_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin(null);
        testUser.setPassword("thePassword");
        assertThrows(NullDataException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_3shortLogin_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_2shortLogin_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("vor");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_1shortLogin_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("gbmki");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_shortLogin_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("shotr");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_validPassword_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("fourthLogin");
        testUser.setPassword("validPassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_smallerValidPassword_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("10thLogin");
        testUser.setPassword("asvkdn");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_nullPassword_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword(null);
        assertThrows(NullDataException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_3shortPassword_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_2shortPassword_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("mdg");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_1shortPassword_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("dolbr");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_shortPassword_notOk() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("sixthLogin");
        testUser.setPassword("short");
        assertThrows(InvalidDataLengthException.class, () -> {
            storageDaoImpl.add(testUser);
        });
    }

    @Test
    void register_userDoesNotExist_ok() {
        User testUser = new User();
        testUser.setAge(20);
        testUser.setLogin("seventhLogin");
        testUser.setPassword("thePassword");
        User actual = storageDaoImpl.add(testUser);
        User expected = storageDaoImpl.add(testUser);
        assertEquals(expected, actual);
    }

    @Test
    void register_userWasAdded_ok() {
        User testUser = new User();
        StorageDaoImpl storageDaoImpl = new StorageDaoImpl();
        String login = "12thLogin";
        testUser.setAge(20);
        testUser.setLogin(login);
        testUser.setPassword("thePassword");
        User expected = storageDaoImpl.add(testUser);
        User actual = storageDaoImpl.get(login);
        assertEquals(expected, actual);
    }

    @Test
    void register_userAlreadyExist_notOk() {
        final User testUser = new User();
        final User existedUser = new User();
        existedUser.setAge(20);
        existedUser.setLogin("takenLogin");
        existedUser.setPassword("thePassword");
        Storage.people.add(existedUser);
        testUser.setAge(20);
        testUser.setLogin("takenLogin");
        testUser.setPassword("thePassword");
        assertThrows(UserExistException.class, () -> {
            Storage.people.add(testUser);
        });
    }
}
