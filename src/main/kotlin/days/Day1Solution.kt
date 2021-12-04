package days

import BaseSolution
import java.io.File

class Day1Solution(val inputList: List<String>): BaseSolution {

    override fun part1() {
       val numberList = File("inputs/day1.txt").readLines().map { it.trim().toInt() }

        println(numberList.windowed(size = 2, step = 1).count { it[0] < it[1] })
    }

    override fun part2() {
        val numberList = File("inputs/day1.txt").readLines().map { it.trim().toInt() }
        val windowed = numberList.windowed(size = 3, step = 1).map { it.sum() }
        val windowIncrease = windowed.windowed(size = 2, step = 1).count { it[0] < it[1] }
        println(windowIncrease)
    }
}
