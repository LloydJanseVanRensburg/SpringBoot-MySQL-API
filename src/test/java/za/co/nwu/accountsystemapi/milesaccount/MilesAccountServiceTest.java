package za.co.nwu.accountsystemapi.milesaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MilesAccountServiceTest {

    @Mock
    private MilesAccountRepository milesAccountRepository;
    private MilesAccountService milesAccountServiceUnderTest;
    private MilesAccount existingMilesAccount;

    @BeforeEach
    void setUp() {
        existingMilesAccount = new MilesAccount();
        milesAccountServiceUnderTest = new MilesAccountService(milesAccountRepository);
    }

    @Test
    void getMilesAccounts() {
    }

    @Test
    void getMilesAccount() {
    }

    @Test
    void createMilesAccount() {
    }

    @Test
    void addMilesToAccount() {
    }

    @Test
    void removeMilesFromAccount() {
    }

    @Test
    void deleteMilesAccount() {
    }
}