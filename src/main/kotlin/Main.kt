import days.Day3Solution
import days.Day4Solution
import days.Day5Solution
import java.io.File

fun main() {
    val inputList = File("inputs/day5.txt").readLines()
    Day5Solution(inputList).part2()
}
