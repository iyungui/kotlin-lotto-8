package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
    val amount = readPurchaseAmount()
    println("입력한 금액: ${amount}원")
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