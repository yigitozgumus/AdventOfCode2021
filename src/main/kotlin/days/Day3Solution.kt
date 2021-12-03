package days

import BaseSolution
import java.io.File
import kotlin.math.ceil

const val zeroCode = '0'.code

class Day3Solution(val pathName: String) : BaseSolution {

    override val inputList by lazy {File(pathName).readLines()}
    val formattedInput by lazy { inputList.map { it.toCharArray().map { it.code - zeroCode } } }

    private fun List<Boolean>.mapToRate(): Int = Integer.parseInt(
        this.map { it.compareTo(false) }.joinToString(""),
        2
    )

    private fun List<Int>.mapToRating(): Int = Integer.parseInt(
        this.joinToString(""), 2
    )

    override fun part1() {
        val totalCount = defineCounter()
        formattedInput.forEach { it.forEachIndexed { index, i -> totalCount[index] += i } }
        val gammaRate = totalCount.map { it >= inputList.size / 2 }.mapToRate()
        val epsilonRate = totalCount.map { it < inputList.size / 2 }.mapToRate()
        println(gammaRate * epsilonRate)
    }

    override fun part2() {
        var oxygenRatingList = formattedInput
        var co2RatingList = formattedInput
        var index = 0
        while (oxygenRatingList.size > 1) {
            val totalCount = oxygenRatingList.map { it[index] }.sum()
            val converted = (totalCount >= ceil(oxygenRatingList.size.toDouble().div(2))).toInt()
            oxygenRatingList = oxygenRatingList.filter { bitList -> bitList[index] == converted }
            index = index.inc()
        }
        index = 0
        while (co2RatingList.size > 1) {
            val totalCount = co2RatingList.map { it[index] }.sum()
            val converted = (totalCount >= ceil(co2RatingList.size.toDouble().div(2))).toInt()
            co2RatingList = co2RatingList.filter { bitList -> bitList[index] != converted }
            index = index.inc()
        }

        println(oxygenRatingList.first().mapToRating() * co2RatingList.first().mapToRating())
    }
}
