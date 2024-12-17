package year2024

import inputListToCharGrid
import print
import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val grid = inputListToCharGrid(input)

        val antennas = hashMapOf<Char, MutableList<Pair<Int, Int>>>()

        input.forEachIndexed { rowNum, string ->
            string.forEachIndexed { colNum, c ->
                if (c != '.') {
                    val list = antennas.getOrDefault(c, mutableListOf())
                    list.add(Pair(rowNum, colNum))
                    antennas[c] = list
                }
            }
        }

        var total = 0
        for (key in antennas.keys) {
            val list = antennas[key]

            // get all permutations of the pair
            // a,b,c,d
            // a-b, a-c, a-d
            // b-c, b-d
            // c-d
            list?.forEachIndexed { index, _ ->
                val pair1 = list[index]

                for (j in index + 1..<list.size) {
                    val pair2 = list[j]

                    val rowDiff = pair2.first - pair1.first
                    val colDiff = pair2.second - pair1.second

                    val newRow1 = pair1.first - rowDiff
                    val newCol1 = pair1.second - colDiff

                    val newRow2 = pair2.first + rowDiff
                    val newCol2 = pair2.second + colDiff

                    try {

                        // test location to see if it's out of bounds
                        grid[newRow1][newCol1]

                        if (grid[newRow1][newCol1] != '#') {
                            grid[newRow1][newCol1] = '#'
                            total++
                        }
                    } catch (e: Exception) {
//                        println("out of bounds")
                    }

                    try {
                        if (grid[newRow2][newCol2] != '#') {
                            grid[newRow2][newCol2] = '#'
                            total++
                        }
                    } catch (e: Exception) {
//                        println("out of bounds")
                    }

                    grid.print()
                    println("\n")
                }

            }


        }

        return total
    }

    fun part2(input: List<String>): Int {
        val grid = inputListToCharGrid(input)

        val antennas = hashMapOf<Char, MutableList<Pair<Int, Int>>>()

        input.forEachIndexed { rowNum, string ->
            string.forEachIndexed { colNum, c ->
                grid[rowNum][colNum] = c

                if (c != '.') {
                    val list = antennas.getOrDefault(c, mutableListOf())
                    list.add(Pair(rowNum, colNum))
                    antennas[c] = list
                }
            }
        }

        var total = 0
        for (key in antennas.keys) {
            val list = antennas[key]

            // get all permutations of the pair
            // a,b,c,d
            // a-b, a-c, a-d
            // b-c, b-d
            // c-d
            println("Values for $key: $list")
            list?.forEachIndexed { index, _ ->
                val pair1 = list[index]
                println("Pair 1: $pair1")

                for (j in index + 1..<list.size) {
                    val pair2 = list[j]
                    println("Pair 2: $pair2")

                    if (grid[pair1.first][pair1.second] != '#') {
                        grid[pair1.first][pair1.second] = '#'
                        total++
                    }

                    if (grid[pair2.first][pair2.second] != '#') {
                        grid[pair2.first][pair2.second] = '#'
                        total++
                    }

                    val rowDiff = pair2.first - pair1.first
                    val colDiff = pair2.second - pair1.second

                    println("Diff: $rowDiff, $colDiff")
                    try {
                        var newRow1 = pair1.first - rowDiff
                        var newCol1 = pair1.second - colDiff

                        while (true) {
                            if (grid[newRow1][newCol1] != '#') {
                                grid[newRow1][newCol1] = '#'
                                total++
                            }

                            newRow1 -= rowDiff
                            newCol1 -= colDiff
                        }
                    } catch (e: Exception) {
                        println("out of bounds")
                    }

                    try {
                        var newRow2 = pair2.first + rowDiff
                        var newCol2 = pair2.second + colDiff

                        while (true) {
                            if (grid[newRow2][newCol2] != '#') {
                                grid[newRow2][newCol2] = '#'
                                total++
                            }

                            newRow2 += rowDiff
                            newCol2 += colDiff
                        }
                    } catch (e: Exception) {
                        println("out of bounds")
                    }

                    grid.print()
                    println("\n")
                }

            }


        }

        return total
    }

    // Tests
    val testInput = readInput("year2024/Day08_test")
    check(part1(testInput) == 14)
    check(part2(testInput) == 34)

    val input = readInput("year2024/Day08")
    val part1Answer = part1(input)
    part1Answer.println()
    val part2Answer = part2(input)
    part2Answer.println()

    // Answers
    check(part1Answer == 220)
    check(part2Answer == 813)
}