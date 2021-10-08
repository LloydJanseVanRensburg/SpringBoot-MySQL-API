package za.co.nwu.accountsystemapi.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    @AfterEach
    void tearDown() {
        userRepositoryTest.deleteAll();
    }

    @Test
    void itShouldFindUserByEmail() {
        String email = "lloydjvrensburg@gmail.com";

        // given
        User user = new User("Lloyd", "Janse van Rensburg", email);
        userRepositoryTest.save(user);

        // when
        Optional<User> foundUser = userRepositoryTest.findUserByEmail(email);

        // then
        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindUserByEmail() {
        // given
        String email = "lloydjvrensburg@gmail.com";

        // when
        Optional<User> foundUser = userRepositoryTest.findUserByEmail(email);

        // then
        assertThat(foundUser.isPresent()).isFalse();
    }
}