import days.*
import java.io.File

fun main() {
    val inputList = File("inputs/day10.txt").readLines()
    Day10Solution(inputList).part1()
    Day10Solution(inputList).part2()
}
