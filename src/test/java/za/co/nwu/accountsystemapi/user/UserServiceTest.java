package za.co.nwu.accountsystemapi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.nwu.accountsystemapi.exception.ApiRequestException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepositoryTest;
    private UserService userServiceTest;
    private User existingUser;

    @BeforeEach
    void setUp() {
        existingUser = new User(1,"Lloyd", "JvR", "lloyd@gmail.com");
        userServiceTest = new UserService(userRepositoryTest);
    }

    @Test
    void getUsersTest() {
        // when
        userServiceTest.getUsers();

        // then
        verify(userRepositoryTest).findAll();
    }

    @Test
    void getUserById() {
        Mockito.when(userRepositoryTest.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        User foundUser = userServiceTest.getUserById(existingUser.getId());
        assertThat(foundUser.getEmail()).isEqualTo(existingUser.getEmail());
    }

    @Test
    void canNotFindUserById() {
        Mockito.when(userRepositoryTest.findById(existingUser.getId())).thenReturn(Optional.empty());
        assertThrows(ApiRequestException.class, () -> userServiceTest.getUserById(existingUser.getId())
        );
    }

    @Test
    void canCreateUser() {
        // given
        String email = "lloyd@gmail.com";
        User user = new User(
               "Lloyd",
               "JvR",
               email
        );

        // when
        userServiceTest.createUser(user);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepositoryTest).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canNotCreateUser() {
        // given
        String email = "lloyd@gmail.com";
        User user = new User(
                "Lloyd",
                "JvR",
                email
        );

        given(userRepositoryTest.findUserByEmail(user.getEmail())).willReturn(java.util.Optional.of(user));

        // when
        // then
        assertThatThrownBy(() -> userServiceTest.createUser(user))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User already exists");
    }

    @Test
    void canUpdateUser() {
        // given
        int userID = 13;

        User user = new User(
                "Lloyd",
                "JvR",
                "lloyd@gmail.com"
        );

        given(userRepositoryTest.findById(userID)).willReturn(java.util.Optional.of(user));

        // when
        userServiceTest.updateUser(userID, user);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepositoryTest)
                .save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canNotUpdateUser() {
        Mockito.when(userRepositoryTest.findById(existingUser.getId())).thenReturn(Optional.empty());
        assertThrows(ApiRequestException.class, ()-> userServiceTest.removeUserWithId(existingUser.getId()));
    }

    @Test
    void removeUserWithId() {
        Mockito.when(userRepositoryTest.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        assertDoesNotThrow(()-> userServiceTest.removeUserWithId(existingUser.getId()));
    }
}