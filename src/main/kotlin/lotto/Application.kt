package lotto

import camp.nextstep.edu.missionutils.Console

fun main() {
    println("구입금액을 입력해주세요")
    val input = Console.readLine()
    val amount = input.toInt()

    println("입력한 금액: ${amount}원")
}
