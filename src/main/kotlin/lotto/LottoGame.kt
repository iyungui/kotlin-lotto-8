package lotto

import camp.nextstep.edu.missionutils.Randoms

class LottoGame(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun run() {
        val amount = inputView.readPurchaseAmount()
        val lottos = purchaseLottos(amount)
        outputView.printLottos(lottos)

        val winningNumbers = inputView.readWinningNumbers()
        val bonusNumber = inputView.readBonusNumber(winningNumbers)

        calculateAndPrintResult(lottos, winningNumbers, bonusNumber, amount)
    }

    private fun purchaseLottos(amount: Int): List<Lotto> {
        val lottoCount = amount / 1000
        return List(lottoCount) {
            val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            Lotto(numbers.sorted())
        }
    }

    private fun calculateAndPrintResult(
        lottos: List<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
        amount: Int
    ) {
        val lottoResult = LottoResult(lottos)
        val rankCount = lottoResult.calculate(winningNumbers, bonusNumber)
        val profitRate = lottoResult.calculateProfitRate(rankCount, amount)
        outputView.printStatistics(rankCount, profitRate)
    }
}