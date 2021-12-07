import days.*
import java.io.File

fun main() {
    val inputList = File("inputs/day7.txt").readLines()
    Day7Solution(inputList).part2()
}
