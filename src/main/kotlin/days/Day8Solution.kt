package days

import BaseSolution

class Day8Solution(inputList: List<String>) : BaseSolution(inputList) {
    val signals = inputList.map { it.split("|").first().split(" ").filterNot { it == "" }.map { it.trim() } }
    private val output = inputList.map {
        it.split("|")[1].split(" ").filterNot { it == "" }.map { it.trim() }
    }

    override fun part1() {
        println(output.flatten().count { listOf(2, 3, 4, 7).contains(it.length) })
    }

    override fun part2() {
        signals.mapIndexed { index, signal ->
            val numberMap = MutableList<String>(10) { "" }.apply { this[8] = "abcdefg" } // add 8
            with(signal) {
                find { it.length == 2 }?.let { numberMap[1] = it } // add 1
                find { it.length == 4 }?.let { numberMap[4] = it } // add 4
                find { it.length == 3 }?.let { numberMap[7] = it } // add 7
                filter { it.length == 6 }.forEach {
                    if (it.toSet().containsAll(numberMap[7].toSet()).not()) {
                        numberMap[6] = it // find 6
                    }
                }
                filter { it.length == 5 }.forEach {
                    if (it.toSet().containsAll(numberMap[1].toSet())) {
                        numberMap[3] = it // find 3
                    }
                }
                filter { it.length == 6 && it != numberMap[6] }.forEach {
                    if (it.toSet().containsAll(numberMap[3].toSet())) {
                        numberMap[9] = it // find 9
                    }
                }
                filter { it.length == 5 && it != numberMap[3] }.forEach {
                    if (numberMap[6].toSet().containsAll(it.toSet())) {
                        numberMap[5] = it// find 5
                    }
                }
                find { it.length == 5 && it != numberMap[3] && it != numberMap[5] }?.let { 
                    numberMap[2] = it 
                }
                find { it.length == 6 && it != numberMap[6] && it != numberMap[9] }?.let { 
                    numberMap[0] = it 
                }
            }
            val outputString = output[index].map { output ->
                val key = numberMap.find {
                    it.toSet().containsAll(output.toSet()) && output.toSet().containsAll(it.toSet())
                }
                numberMap.indexOf(key)
            }
            outputString.joinToString("").toInt()
        }.sum().also {
            println(it)
        }
    }
}
