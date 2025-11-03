package lotto

fun main() {
    val inputView = InputView()

    val amount = inputView.readPurchaseAmount()
    val winningNumbers = inputView.readWinningNumbers()
    val bonusNumber = inputView.readBonusNumber()

    println("입력한 금액: ${amount}원")
    println("입력한 당첨 번호: $winningNumbers")
    println("입력한 보너스 번호: $bonusNumber")
}
