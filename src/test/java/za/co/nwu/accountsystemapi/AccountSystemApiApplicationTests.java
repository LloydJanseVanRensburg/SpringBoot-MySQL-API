package za.co.nwu.accountsystemapi;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AccountSystemApiApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddToNumbers() {
        // given
        int numberOne = 20;
        int numberTwo = 30;

        // when
        int result = underTest.add(numberOne, numberTwo);

        // then
        int expected = 50;
        assertThat(result).isEqualTo(expected);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}
