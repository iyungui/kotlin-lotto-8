package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
    }

    fun countMatch(winningNumbers: List<Int>): Int {
        return numbers.count { it in winningNumbers }
    }

    fun containsBonus(bonusNumber: Int): Boolean {
        return bonusNumber in numbers
    }

    override fun toString(): String {
        return numbers.toString()
    }
}
