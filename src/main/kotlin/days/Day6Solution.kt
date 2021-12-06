package days

import BaseSolution

class Day6Solution(inputList: List<String>) : BaseSolution(inputList) {

    private val lanternList = inputList.first().split(",").map { it.toInt() }

    override fun part1() {
        var generations = lanternList
        (1..80).forEach { cycle ->
            val counterTimes = generations.count { it == 0 }
            generations = generations.map { if (it == 0) 6 else it - 1 }
            generations = generations + List(counterTimes) { 8 }
        }
        println(generations.size)
    }

    override fun part2() {
        val lanternCounter = MutableList(9){ 0L }
        lanternList.forEach { lanternCounter[it] += 1L }
        (1..256).forEach { day ->
            val fishToGenerate = lanternCounter.first()
            (0..lanternCounter.size -2).forEach { lanternCounter[it] = lanternCounter[it+1] }
            lanternCounter[6] += fishToGenerate 
            lanternCounter[8] = fishToGenerate
        }
        println(lanternCounter.sum())
    }

}
