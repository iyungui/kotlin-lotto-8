package lotto

import camp.nextstep.edu.missionutils.Console

class InputView {
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
}