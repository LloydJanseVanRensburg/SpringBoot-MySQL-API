package za.co.nwu.accountsystemapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    private UserService userServiceTest;

    @BeforeEach
    void setUp() {
        userServiceTest = new UserService();
    }

    @Test
    void getUsers() {
        // when
        userServiceTest.getUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void getUserById() {
    }

    @Test
    @Disabled
    void createUser() {
    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    @Disabled
    void removeUserWithId() {
    }
}