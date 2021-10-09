package za.co.nwu.accountsystemapi.milesaccount;

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
class MilesAccountServiceTest {

    @Mock
    private MilesAccountRepository milesAccountRepository;
    private MilesAccountService milesAccountServiceUnderTest;
    private MilesAccount existingMilesAccount;

    @BeforeEach
    void setUp() {
        existingMilesAccount = new MilesAccount(1, 1, 100);
        milesAccountServiceUnderTest = new MilesAccountService(milesAccountRepository);
    }

    @Test
    void getMilesAccountsTest() {
        // when
        milesAccountServiceUnderTest.getMilesAccounts();

        // then
        verify(milesAccountRepository).findAll();
    }

    @Test
    void canMilesAccountTest() {
        Mockito.when(milesAccountRepository.findById(existingMilesAccount.getId())).thenReturn(Optional.of(existingMilesAccount));
        MilesAccount foundMilesAccount = milesAccountServiceUnderTest.getMilesAccount(existingMilesAccount.getId());
        assertThat(foundMilesAccount.getId()).isEqualTo(foundMilesAccount.getId());
    }

    @Test
    void canNotMilesAccountTest() {
        Mockito.when(milesAccountRepository.findById(existingMilesAccount.getId())).thenReturn(Optional.empty());
        assertThrows(ApiRequestException.class, () ->
                milesAccountServiceUnderTest.getMilesAccount(existingMilesAccount.getId())
        );
    }

    @Test
    void canCreateMilesAccount() {
        //given
        MilesAccount newMilesAccount = new MilesAccount(1, 100);

        //when
        milesAccountServiceUnderTest.createMilesAccount(newMilesAccount);

        //then
        ArgumentCaptor<MilesAccount> milesAccountArgumentCaptor = ArgumentCaptor.forClass(MilesAccount.class);

        verify(milesAccountRepository).save(milesAccountArgumentCaptor.capture());

        MilesAccount capturedAccount = milesAccountArgumentCaptor.getValue();

        assertThat(capturedAccount).isEqualTo(newMilesAccount);
    }

    @Test
    void canNotCreateMilesAccount() {
        // given
        MilesAccount newMilesAccount = new MilesAccount(1, 100);

        given(milesAccountRepository.findMilesAccountByUserId(newMilesAccount.getUserId()))
                .willReturn(Optional.of(newMilesAccount));

        // when
        // then
        assertThatThrownBy(() -> milesAccountServiceUnderTest.createMilesAccount(newMilesAccount))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("This user already has an MilesAccount");
    }

    @Test
    void addMilesToAccount() {
    }

    @Test
    void removeMilesFromAccount() {
    }

    @Test
    void deleteMilesAccount() {
        Mockito.when(milesAccountRepository.findById(existingMilesAccount.getId())).thenReturn(Optional.of(existingMilesAccount));
        assertDoesNotThrow(()-> milesAccountServiceUnderTest.deleteMilesAccount(existingMilesAccount.getId()));
    }
}