package year2024

import println
import readInput

fun main() {
    fun part1(input: List<String>, numRows: Int, numCols: Int): Long {
//    val q = sample1
//    val q = sample2
        val q = input

//    val gridX = 11
//    val gridY = 7
//        val gridX = 101
//        val gridY = 103


        val iterations = 100

        val array = Array(numRows) { Array<Int?>(numCols) { null } }

        q.forEach { line ->
            val split = line.split(" ")
            val p = split[0].split("p=")[1].split(",")
            val v = split[1].split("v=")[1].split(",")

            val x = p[0].toInt()
            val y = p[1].toInt()

            val xVelocity = v[0].toInt()
            val yVelocity = v[1].toInt()
//        println("$x, $y, $xVelocity, $yVelocity")

            val xAbsVelocity = if (xVelocity < 0) {
                numCols + xVelocity
            } else {
                xVelocity
            }

            val yAbsVelocity = if (yVelocity < 0) {
                numRows + yVelocity
            } else {
                yVelocity
            }

            val xFinal = ((x + xAbsVelocity * iterations.toLong()) % numCols).toInt()
            val yFinal = ((y + yAbsVelocity * iterations.toLong()) % numRows).toInt()

//        println("$xFinal $yFinal")

            val position = array[yFinal][xFinal]

            if (position == null) {
                array[yFinal][xFinal] = 1
            } else {
                array[yFinal][xFinal] = position + 1
            }
        }

        printArray(array)

        // calculate quadrants
        val midX = numCols / 2
        val midY = numRows / 2

        println("Midpoints: $midX, $midY")

        val quad1 = calculateQuadrant(array, 0, 0, midX - 1, midY - 1)
        val quad2 = calculateQuadrant(array, midX + 1, 0, numCols - 1, midY - 1)
        val quad3 = calculateQuadrant(array, 0, midY + 1, midX - 1, numRows - 1)
        val quad4 = calculateQuadrant(array, midX + 1, midY + 1, numCols - 1, numRows - 1)

        println(quad1)
        println(quad2)
        println(quad3)
        println(quad4)

        return quad1 * quad2 * quad3 * quad4
    }

    fun part2(input: List<String>, numRows: Int, numCols: Int): Int {
        var seconds = 0

        while (seconds < 10000) {
            val array = Array(numRows) { Array<Int?>(numCols) { null } }

            input.forEach { line ->
                val split = line.split(" ")
                val p = split[0].split("p=")[1].split(",")
                val v = split[1].split("v=")[1].split(",")

                val x = p[0].toInt()
                val y = p[1].toInt()

                val xVelocity = v[0].toInt()
                val yVelocity = v[1].toInt()
//        println("$x, $y, $xVelocity, $yVelocity")

                val xAbsVelocity = if (xVelocity < 0) {
                    numCols + xVelocity
                } else {
                    xVelocity
                }

                val yAbsVelocity = if (yVelocity < 0) {
                    numRows + yVelocity
                } else {
                    yVelocity
                }

                val xFinal = ((x + xAbsVelocity * seconds.toLong()) % numCols).toInt()
                val yFinal = ((y + yAbsVelocity * seconds.toLong()) % numRows).toInt()

//        println("$xFinal $yFinal")

                val position = array[yFinal][xFinal]

                if (position == null) {
                    array[yFinal][xFinal] = 1
                } else {
                    array[yFinal][xFinal] = position + 1
                }
            }

            for (row in 0..<numRows) {
                var str = ""
                array[row].forEach { i ->
                    val c = i?.toString() ?: "."
                    str += c
                }
                if (str.contains("11111111111111")) {
                    println("Possible tree at $seconds, array:")
                    printArray(array)
                    return seconds
                }
            }

            seconds++
        }

        return -1
    }

    // Tests
    val testInput = readInput("year2024/Day14_test")
    check(part1(testInput, 7, 11) == 12L)

    val input = readInput("year2024/Day14")
    val part1Answer = part1(input, 103, 101)
    part1Answer.println()
    val part2Answer = part2(input, 103, 101)
    part2Answer.println()

    // Answers
    check(part1Answer == 217132650L)
    check(part2Answer == 6516)
}

fun printArray(array: Array<Array<Int?>>) {
    array.forEach { line ->
        line.forEach { i ->
            val c = i?.toString() ?: "."
            print(c)
        }
        println()
    }
}

fun calculateQuadrant(array: Array<Array<Int?>>, startX: Int, startY: Int, endX: Int, endY: Int): Long {
    var total = 0L
    for(row in startY..endY) {
        for(col in startX..endX) {
            val cell = array[row][col]
            if (cell != null) total += cell.toLong()
        }
    }
    return total
}