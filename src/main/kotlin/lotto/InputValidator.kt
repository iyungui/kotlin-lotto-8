package lotto

object InputValidator {
    fun validatePurchaseAmount(amount: Int) {
        require(amount > 0) { "[ERROR] 구입 금액은 0원보다 커야 합니다." }
        require(amount % 1000 == 0) { "[ERROR] 구입 금액은 1,000원 단위여야 합니다." }
    }

    fun validateWinningNumbers(numbers: List<Int>) {
        require(numbers.size == 6) { "[ERROR] 당첨 번호는 6개여야 합니다." }
        require(numbers.all { it in 1..45 }) { "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(numbers.size == numbers.toSet().size) { "[ERROR] 당첨 번호는 중복될 수 없습니다." }
    }

    fun validateBonusNumber(bonusNumber: Int, winningNumbers: List<Int>) {
        require(bonusNumber in 1..45) { "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(bonusNumber !in winningNumbers) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
    }
}