package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import core.basesyntax.service.InvalidAgeException;
import core.basesyntax.service.InvalidDataLengthException;
import core.basesyntax.service.NullDataException;
import core.basesyntax.service.RegistrationServiceImpl;
import core.basesyntax.service.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private RegistrationServiceImpl registrationServiceImpl;
    private User testUser;

    @BeforeEach
    void setUp() {
        registrationServiceImpl = new RegistrationServiceImpl();
        testUser = new User();
    }

    @Test
    void register_validAge_ok() {
        testUser.setAge(20);
        testUser.setLogin("firstLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_exactly18Age_ok() {
        testUser.setAge(18);
        testUser.setLogin("eidgthLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_underAge_notOk() {
        testUser.setAge(7);
        testUser.setLogin("secondLogin");
        testUser.setPassword("thePassword");
        assertThrows(InvalidAgeException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_negativeAge_notOk() {
        testUser.setAge(-7);
        testUser.setLogin("ninethLogin");
        testUser.setPassword("thePassword");
        assertThrows(InvalidAgeException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_nullAge_notOk() {
        testUser.setAge(null);
        testUser.setLogin("thirdLogin");
        testUser.setPassword("thePassword");
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_validLogin_ok() {
        testUser.setAge(20);
        testUser.setLogin("validLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_nullLogin_notOk() {
        testUser.setAge(20);
        testUser.setLogin(null);
        testUser.setPassword("thePassword");
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_shortLogin_notOk() {
        testUser.setAge(20);
        testUser.setLogin("shotr");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLengthException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_validPassword_ok() {
        testUser.setAge(20);
        testUser.setLogin("fourthLogin");
        testUser.setPassword("validPassword");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_smallerValidPassword_ok() {
        testUser.setAge(20);
        testUser.setLogin("10thLogin");
        testUser.setPassword("asvkdn");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_nullPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword(null);
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_3shortPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("");
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_2shortPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("mdg");
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_1shortPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword("dolbr");
        assertThrows(NullDataException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_shortPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("sixthLogin");
        testUser.setPassword("short");
        assertThrows(InvalidDataLengthException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_userDoesNotExist_ok() {
        testUser.setAge(20);
        testUser.setLogin("seventhLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertEquals(testUser, actual);
    }

    @Test
    void register_userWasAdded_ok() {
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
        User existedUser = new User();
        existedUser.setAge(20);
        existedUser.setLogin("takenLogin");
        existedUser.setPassword("thePassword");
        Storage.people.add(existedUser);
        testUser.setAge(20);
        testUser.setLogin("takenLogin");
        testUser.setPassword("thePassword");
        assertThrows(UserExistException.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }
}
