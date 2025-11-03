package lotto

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val amount = inputView.readPurchaseAmount()

    val lottoCount = amount / 1000
    val lottos = mutableListOf<Lotto>()

    repeat(lottoCount) {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        val lotto = Lotto(numbers.sorted())
        lottos.add(lotto)
    }

    outputView.printLottos(lottos)

    val winningNumbers = inputView.readWinningNumbers()
    val bonusNumber = inputView.readBonusNumber(winningNumbers)

    val lottoResult = LottoResult(lottos)
    val rankCount = lottoResult.calculate(winningNumbers, bonusNumber)
    val profitRate = lottoResult.calculateProfitRate(rankCount, amount)

    outputView.printStatistics(rankCount, profitRate)
}
