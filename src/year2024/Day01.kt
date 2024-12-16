package year2024

import kotlin.math.abs
import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        input.forEach { line ->
            val nums = line.split("   ")
            list1.add(nums[0].toInt())
            list2.add(nums[1].toInt())
        }

        list1.sort()
        list2.sort()

        var sum = 0
        for (i in 0..<list1.size) {
            sum += abs(list1[i] - list2[i])
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val list1 = mutableListOf<Int>()
        val map = mutableMapOf<Int, Int>()

        input.forEach { line ->
            val nums = line.split("   ")
            list1.add(nums[0].toInt())

            val num2 = nums[1].toInt()
            val count = map.getOrDefault(num2, 0)
            map[num2] = count + 1
        }

        var sum = 0

        for (i in list1) {
            sum += i * map.getOrDefault(i, 0)
        }
        return sum
    }

    // Test if implementation meets criteria from the description, like:
    // check(part1(listOf("test_input")) == 1)

    val testInput = readInput("year2024/Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("year2024/Day01")
    part1(input).println()
    part2(input).println()

    // Answers
    check(part1(input) == 936063)
    check(part2(input) == 23150395)
}
