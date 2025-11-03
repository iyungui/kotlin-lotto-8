package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
    val amount = readPurchaseAmount()
    val numbers = readWinningNumbers()
    val bonusNumber = readBonusNumber(numbers)
    println("입력한 금액: ${amount}원")
    println("입력한 당첨 번호: $numbers")
    println("입력한 보너스 번호: $bonusNumber")
}

fun readPurchaseAmount(): Int {
    while (true) {
        try {
            println("구입금액을 입력해 주세요.")
            val input = Console.readLine()
            val amount = input.toInt()

            validatePurchaseAmount(amount)
            return amount
        } catch (e: NumberFormatException) {
            println(e.message)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validatePurchaseAmount(amount: Int) {
    if (amount <= 0) {
        throw IllegalArgumentException("[ERROR] 구입 금액은 0원보다 커야 합니다.")
    }
    if (amount % 1000 != 0) {
        throw IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.")
    }
}

fun readWinningNumbers(): List<Int> {
    while (true) {
        try {
            println("당첨 번호를 입력해 주세요.")
            val input = Console.readLine()
            val numbers = input.split(",").map { it.trim().toInt() }

            validateWinningNumbers(numbers)
            return numbers
        } catch (e: NumberFormatException) {
            println("[ERROR] 당첨 번호는 숫자여야 합니다.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validateWinningNumbers(numbers: List<Int>) {
    if (numbers.size != 6) {
        throw IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.")
    }
    if (numbers.any { it !in 1..45} ) {
        throw IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
    }
    if (numbers.size != numbers.toSet().size) {
        throw IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.")
    }
}

fun readBonusNumber(winningNumbers: List<Int>): Int {
    while (true) {
        try {
            println("보너스 번호를 입력해 주세요.")
            val input = Console.readLine()
            val bonusNumber = input.toInt()

            validateBonusNumber(bonusNumber, winningNumbers)
            return bonusNumber
        } catch (e: NumberFormatException) {
            println("[ERROR] 보너스 번호는 숫자여야 합니다.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun validateBonusNumber(bonusNumber: Int, winningNumbers: List<Int>) {
    if (bonusNumber !in 1..45) {
        throw IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
    }
    if (bonusNumber in winningNumbers) {
        throw IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.")
    }
}
