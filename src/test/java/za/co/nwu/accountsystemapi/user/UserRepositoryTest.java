package za.co.nwu.accountsystemapi.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryTest {

    @Autowired
    private  UserRepository underTest;

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

    }
}
