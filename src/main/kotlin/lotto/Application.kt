package lotto

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val inputView = InputView()

    val amount = inputView.readPurchaseAmount()
    val winningNumbers = inputView.readWinningNumbers()
    val bonusNumber = inputView.readBonusNumber(winningNumbers)

    val lottoCount = amount / 1000
    val lottos = mutableListOf<Lotto>()

    repeat(lottoCount) {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        val lotto = Lotto(numbers.sorted())
        lottos.add(lotto)
    }

    println()
    println("${lottoCount}개를 구매했습니다.")

    lottos.forEach { lotto ->
        val matchCount = lotto.countMatch(winningNumbers)
        val hasBonus = lotto.containsBonus(bonusNumber)
        println("$lotto - 일치: ${matchCount}개, 보너스: $hasBonus")

        when (matchCount) {
            6 -> println("-> ${Rank.FIRST}, 상금: ${Rank.FIRST.prize}원")
            5 -> println("-> ${Rank.THIRD}, 상금: ${Rank.THIRD.prize}원")
            4 -> println("-> ${Rank.FOURTH}, 상금: ${Rank.FOURTH.prize}원")
            3 -> println("-> ${Rank.FIFTH}, 상금: ${Rank.FIFTH.prize}원")
            else -> println("-> ${Rank.NONE}, 상금: ${Rank.NONE.prize}원")
        }
    }
}
