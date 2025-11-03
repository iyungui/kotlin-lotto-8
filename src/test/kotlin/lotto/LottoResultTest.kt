package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `당첨 통계를 정확히 계산한다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),  // 6개 일치
            Lotto(listOf(1, 2, 3, 4, 5, 7)),  // 5개 일치 + 보너스
            Lotto(listOf(1, 2, 3, 4, 5, 8)),  // 5개 일치
            Lotto(listOf(1, 2, 3, 4, 9, 10)), // 4개 일치
            Lotto(listOf(1, 2, 3, 11, 12, 13)), // 3개 일치
            Lotto(listOf(1, 2, 14, 15, 16, 17))  // 2개 일치
        )
        val lottoResult = LottoResult(lottos)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val rankCount = lottoResult.calculate(winningNumbers, bonusNumber)

        assertThat(rankCount[Rank.FIRST]).isEqualTo(1)
        assertThat(rankCount[Rank.SECOND]).isEqualTo(1)
        assertThat(rankCount[Rank.THIRD]).isEqualTo(1)
        assertThat(rankCount[Rank.FOURTH]).isEqualTo(1)
        assertThat(rankCount[Rank.FIFTH]).isEqualTo(1)
        assertThat(rankCount[Rank.NONE]).isEqualTo(1)
    }

    @Test
    fun `당첨이 없을 때 통계를 정확히 계산한다`() {
        val lottos = listOf(
            Lotto(listOf(10, 11, 12, 13, 14, 15)),
            Lotto(listOf(20, 21, 22, 23, 24, 25))
        )
        val lottoResult = LottoResult(lottos)
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        val rankCount = lottoResult.calculate(winningNumbers, bonusNumber)

        assertThat(rankCount[Rank.NONE]).isEqualTo(2)
    }

    @Test
    fun `수익률을 정확히 계산한다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6))  // 1등: 2,000,000,000원
        )
        val lottoResult = LottoResult(lottos)
        val rankCount = mapOf(
            Rank.FIRST to 1,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0,
            Rank.NONE to 0
        )

        val profitRate = lottoResult.calculateProfitRate(rankCount, 1000)

        assertThat(profitRate).isEqualTo(200000000.0)
    }

    @Test
    fun `5등만 당첨되었을 때 수익률을 정확히 계산한다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 10, 11, 12))  // 5등: 5,000원
        )
        val lottoResult = LottoResult(lottos)
        val rankCount = mapOf(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 1,
            Rank.NONE to 7
        )

        val profitRate = lottoResult.calculateProfitRate(rankCount, 8000)

        assertThat(profitRate).isEqualTo(62.5)
    }

    @Test
    fun `당첨이 없을 때 수익률은 0이다`() {
        val rankCount = mapOf(
            Rank.FIRST to 0,
            Rank.SECOND to 0,
            Rank.THIRD to 0,
            Rank.FOURTH to 0,
            Rank.FIFTH to 0,
            Rank.NONE to 8
        )
        val lottos = emptyList<Lotto>()
        val lottoResult = LottoResult(lottos)

        val profitRate = lottoResult.calculateProfitRate(rankCount, 8000)

        assertThat(profitRate).isEqualTo(0.0)
    }
}