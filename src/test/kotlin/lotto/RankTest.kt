package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 일치하면 1등이다`() {
        val rank = Rank.from(6, false)

        assertThat(rank).isEqualTo(Rank.FIRST)
        assertThat(rank.prize).isEqualTo(2_000_000_000)
    }

    @Test
    fun `5개 일치하고 보너스 볼이 일치하면 2등이다`() {
        val rank = Rank.from(5, true)

        assertThat(rank).isEqualTo(Rank.SECOND)
        assertThat(rank.prize).isEqualTo(30_000_000)
    }

    @Test
    fun `5개 일치하고 보너스 볼이 불일치하면 3등이다`() {
        val rank = Rank.from(5, false)

        assertThat(rank).isEqualTo(Rank.THIRD)
        assertThat(rank.prize).isEqualTo(1_500_000)
    }

    @Test
    fun `4개 일치하면 4등이다`() {
        val rank = Rank.from(4, false)

        assertThat(rank).isEqualTo(Rank.FOURTH)
        assertThat(rank.prize).isEqualTo(50_000)
    }

    @Test
    fun `3개 일치하면 5등이다`() {
        val rank = Rank.from(3, false)

        assertThat(rank).isEqualTo(Rank.FIFTH)
        assertThat(rank.prize).isEqualTo(5_000)
    }

    @Test
    fun `2개 이하 일치하면 낙첨이다`() {
        assertThat(Rank.from(2, false)).isEqualTo(Rank.NONE)
        assertThat(Rank.from(1, false)).isEqualTo(Rank.NONE)
        assertThat(Rank.from(0, false)).isEqualTo(Rank.NONE)
    }
}