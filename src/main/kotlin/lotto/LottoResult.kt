package lotto

class LottoResult(private val lottos: List<Lotto>) {
    fun calculate(winningNumbers: List<Int>, bonusNumber: Int): Map<Rank, Int> {
        val rankCount = mutableMapOf<Rank, Int>()

        // 모든 등수를 0으로 초기화
        Rank.values().forEach { rank ->
            rankCount[rank] = 0
        }

        // 각 로또의 등수 계산
        lottos.forEach { lotto ->
            val matchCount = lotto.countMatch(winningNumbers)
            val hasBonus = lotto.containsBonus(bonusNumber)
            val rank = Rank.from(matchCount, hasBonus)

            rankCount[rank] = rankCount[rank]!! + 1
        }

        return rankCount
    }

    fun calculateProfitRate(rankCount: Map<Rank, Int>, purchaseAmount: Int): Double {
        val totalPrize = rankCount.entries.sumOf { (rank, count) ->
            rank.prize.toLong() * count
        }

        return (totalPrize.toDouble() / purchaseAmount) * 100
    }
}