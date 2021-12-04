import days.Day3Solution
import days.Day4Solution
import java.io.File

fun main() {
    val inputList = File("inputs/day4.txt").readLines()
    Day4Solution(inputList).part2()
}
