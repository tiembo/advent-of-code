import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Converts a list of Strings to a 2D array of [Char]s
 */
fun inputListToCharGrid(input: List<String>): Array<CharArray> {
    val numRows = input.count()
    val numCols = input.first().length
    val grid = Array(numRows) { CharArray(numCols) }

    input.forEachIndexed { rowNum, string ->
        string.forEachIndexed { colNum, c ->
            grid[rowNum][colNum] = c
        }
    }

    return grid
}

/**
 * Prints a 2D array of [Char]s
 */
fun Array<CharArray>.print() {
    forEach { line -> line.joinToString("").println() }
}