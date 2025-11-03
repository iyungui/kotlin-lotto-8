package lotto

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoGame = LottoGame(inputView, outputView)

    lottoGame.run()
}
