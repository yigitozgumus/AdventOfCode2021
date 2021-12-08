import days.*
import java.io.File

fun main() {
    val inputList = File("inputs/day8.txt").readLines()
    Day8Solution(inputList).part1()
    Day8Solution(inputList).part2()
}
