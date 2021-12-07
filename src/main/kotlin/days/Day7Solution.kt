package days

import BaseSolution
import kotlin.math.abs

class Day7Solution(inputList: List<String>): BaseSolution(inputList) {

    private val positionList = inputList.first().split(",").map { it.toInt() }

    override fun part1() {
       (0..positionList.maxOf { it }).asSequence().map { position ->
           positionList.sumOf { abs(it - position) }
        }.also {
            println(it.minOf { it })
       }

    }

    override fun part2() {
        (0..positionList.maxOf { it }).asSequence().map { position ->
            positionList.sumOf { calculateCost(abs(it - position)) }
        }.also {
            println(it.minOf { it })
        }
    }

    private fun calculateCost(step: Int) = (step * (step + 1))/ 2
}
