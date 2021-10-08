package za.co.nwu.accountsystemapi.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private  UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findUserByEmail() {
        // given
        String email = "lloydjvrensburg@gmail.com";


        User user = new User(
                "Lloyd",
                "Janse van Rensburg",
                email
        );

        underTest.save(user);

        // when
        Optional<User> exists = underTest.findUserByEmail(email);

        // then
        assertThat(exists).isNotNull();
    }
}
