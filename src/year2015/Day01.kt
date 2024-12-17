package year2015

import println
import readInput

fun main() {
    fun part1(input: String): Int {
        var total = 0
        for (c in input) {
            if (c == '(') {
                total++
            } else {
                total--
            }
        }
        return total
    }

    fun part2(input: String): Int {
        var total2 = 0
        var i = 0
        while(total2 >= 0 && i < input.length ) {
            val c = input[i]
            if (c == '(') {
                total2++
            } else {
                total2--
            }
            i++
        }
        return i
    }

    // Test if implementation meets criteria from the description, like:
    check(part1("(())") == 0)
    check(part1("(())") == 0)
    check(part1("(((") == 3)
    check(part1("(()(()(") == 3)
    check(part1("))(((((") == 3)
    check(part1("())") == -1)
    check(part1("))(") == -1)
    check(part1(")))") == -3)
    check(part1(")())())") == -3)
    check(part2(")") == 1)
    check(part2("()())")== 5)

    val input = readInput("year2015/Day01").first()
    part1(input).println()
    part2(input).println()

    // Answers
    check(part1(input) == 74)
    check(part2(input) == 1795)
}
