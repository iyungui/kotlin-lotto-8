package lotto

class OutputView {
    fun printLottos(lottos: List<Lotto>) {
        println()
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { lotto ->
            println(lotto)
        }
    }

    fun printStatistics(rankCount: Map<Rank, Int>, profitRate: Double) {
        println()
        println("당첨 통계")
        println("---")
        println("3개 일치 (5,000원) - ${rankCount[Rank.FIFTH]}개")
        println("4개 일치 (50,000원) - ${rankCount[Rank.FOURTH]}개")
        println("5개 일치 (1,500,000원) - ${rankCount[Rank.THIRD]}개")
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - ${rankCount[Rank.SECOND]}개")
        println("6개 일치 (2,000,000,000원) - ${rankCount[Rank.FIRST]}개")
        println("총 수익률은 %.1f%%입니다.".format(profitRate))
    }
}