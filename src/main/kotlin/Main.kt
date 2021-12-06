import days.Day3Solution
import days.Day4Solution
import days.Day5Solution
import days.Day6Solution
import java.io.File

fun main() {
    val inputList = File("inputs/day6.txt").readLines()
    Day6Solution(inputList).part2()
}
