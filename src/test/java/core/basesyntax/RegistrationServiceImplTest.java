package core.basesyntax;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import core.basesyntax.model.User;
import core.basesyntax.service.InvalidAgeException;
import core.basesyntax.service.InvalidData;
import core.basesyntax.service.InvalidDataLength;
import core.basesyntax.service.RegistrationServiceImpl;
import core.basesyntax.service.UserExistExeption;

class RegistrationServiceImplTest {
    private RegistrationServiceImpl registrationServiceImpl = new RegistrationServiceImpl();
    private User testUser = new User();

    @Test
    void register_validAge_ok() {
        testUser.setAge(20);
        testUser.setLogin("firstLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertTrue(testUser.equals(actual));
    }

    @Test
    void register_underAge_notOk() {
        testUser.setAge(17);
        testUser.setLogin("secondLogin");
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
        assertThrows(InvalidData.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_validLogin_ok() {
        testUser.setAge(20);
        testUser.setLogin("validLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertTrue(testUser.equals(actual));
    }

    @Test
    void register_nullLogin_notOk() {
        testUser.setAge(20);
        testUser.setLogin(null);
        testUser.setPassword("thePassword");
        assertThrows(InvalidData.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_shortLogin_notOk() {
        testUser.setAge(20);
        testUser.setLogin("shotr");
        testUser.setPassword("thePassword");
        assertThrows(InvalidDataLength.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_validPassword_ok() {
        testUser.setAge(20);
        testUser.setLogin("fourthLogin");
        testUser.setPassword("validPassword");
        User actual = registrationServiceImpl.register(testUser);
        assertTrue(testUser.equals(actual));
    }

    @Test
    void register_nullPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("fifthLogin");
        testUser.setPassword(null);
        assertThrows(InvalidData.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_shortPassword_notOk() {
        testUser.setAge(20);
        testUser.setLogin("sixthLogin");
        testUser.setPassword("short");
        assertThrows(InvalidDataLength.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }

    @Test
    void register_userDoesNotExist_ok() {
        testUser.setAge(20);
        testUser.setLogin("seventhLogin");
        testUser.setPassword("thePassword");
        User actual = registrationServiceImpl.register(testUser);
        assertTrue(testUser.equals(actual));
    }

    @Test
    void register_userAlreadyExist_notOk() {
        User existedUser = new User();
        existedUser.setAge(20);
        existedUser.setLogin("takenLogin");
        existedUser.setPassword("thePassword");
        registrationServiceImpl.register(existedUser);
        testUser.setAge(20);
        testUser.setLogin("takenLogin");
        testUser.setPassword("thePassword");
        assertThrows(UserExistExeption.class, () -> {
            registrationServiceImpl.register(testUser);
        });
    }
}
