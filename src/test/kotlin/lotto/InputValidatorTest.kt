package lotto

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test

class InputValidatorTest {
    @Test
    fun `구입 금액이 1000원 단위가 아니면 예외가 발생한다`() {
        assertThatThrownBy { InputValidator.validatePurchaseAmount(1500) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `구입 금액이 0원 이하면 예외가 발생한다`() {
        assertThatThrownBy { InputValidator.validatePurchaseAmount(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")

        assertThatThrownBy { InputValidator.validatePurchaseAmount(-1000) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `구입 금액이 정상적이면 예외가 발생하지 않는다`() {
        assertThatCode { InputValidator.validatePurchaseAmount(1000) }
            .doesNotThrowAnyException()

        assertThatCode { InputValidator.validatePurchaseAmount(5000) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `당첨 번호가 6개가 아니면 예외가 발생한다`() {
        assertThatThrownBy { InputValidator.validateWinningNumbers(listOf(1, 2, 3, 4, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `당첨 번호에 1부터 45 범위 밖의 숫자가 있으면 예외가 발생한다`() {
        assertThatThrownBy { InputValidator.validateWinningNumbers(listOf(1, 2, 3, 4, 5, 46)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `당첨 번호에 중복이 있으면 예외가 발생한다`() {
        assertThatThrownBy { InputValidator.validateWinningNumbers(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `당첨 번호가 정상적이면 예외가 발생하지 않는다`() {
        assertThatCode { InputValidator.validateWinningNumbers(listOf(1, 2, 3, 4, 5, 6)) }
            .doesNotThrowAnyException()
    }

    @Test
    fun `보너스 번호가 1부터 45 범위 밖이면 예외가 발생한다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)

        assertThatThrownBy { InputValidator.validateBonusNumber(46, winningNumbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)

        assertThatThrownBy { InputValidator.validateBonusNumber(3, winningNumbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `보너스 번호가 정상적이면 예외가 발생하지 않는다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)

        assertThatCode { InputValidator.validateBonusNumber(7, winningNumbers) }
            .doesNotThrowAnyException()
    }
}