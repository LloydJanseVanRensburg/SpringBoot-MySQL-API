package za.co.nwu.accountsystemapi.milesaccount;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MilesAccountRepositoryTest {

    @Autowired
    private MilesAccountRepository milesAccountRepositoryTest;

    @AfterEach
    void tearDown() {
        milesAccountRepositoryTest.deleteAll();
    }

    @Test
    void itShouldFindMilesAccountByUserId() {
        int userId = 1;

        // given
        MilesAccount milesAccount = new MilesAccount(userId, 100);
        milesAccountRepositoryTest.save(milesAccount);

        // when
        Optional<MilesAccount> foundMilesAccount = milesAccountRepositoryTest.findMilesAccountByUserId(userId);

        // then
        assertThat(foundMilesAccount.isPresent()).isTrue();
    }

    @Test
    void itShouldNotFindMilesAccountByUserId() {
        int userId = 3;

        // when
        Optional<MilesAccount> foundMilesAccount = milesAccountRepositoryTest.findMilesAccountByUserId(userId);

        // then
        assertThat(foundMilesAccount.isPresent()).isFalse();
    }
}