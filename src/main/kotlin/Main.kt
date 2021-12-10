import days.*
import java.io.File

fun main() {
    val inputList = File("inputs/day9.txt").readLines()
    Day9Solution(inputList).part1()
    Day9Solution(inputList).part2()
}
