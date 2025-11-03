package lotto

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val inputView = InputView()

    val amount = inputView.readPurchaseAmount()
//    val winningNumbers = inputView.readWinningNumbers()
//    val bonusNumber = inputView.readBonusNumber()

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
        println(lotto)
    }
}
