package lotto

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 1부터 45 사이가 아니면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 46)) }
            .isInstanceOf(IllegalArgumentException::class.java)

        assertThatThrownBy { Lotto(listOf(0, 2, 3, 4, 5, 6)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 정상적으로 생성된다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto).isNotNull
    }

    @Test
    fun `당첨 번호와 일치하는 개수를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = listOf(1, 2, 3, 7, 8, 9)

        assertThat(lotto.countMatch(winningNumbers)).isEqualTo(3)
    }

    @Test
    fun `보너스 번호 포함 여부를 확인한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto.containsBonus(3)).isTrue
        assertThat(lotto.containsBonus(7)).isFalse
    }
}