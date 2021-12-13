package days

import BaseSolution

class Day10Solution(inputList: List<String>) : BaseSolution(inputList) {

    private fun getClosing(operator: Char): Char = when (operator) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        else -> '>'
    }
    private fun isOpen(operator: Char): Boolean = listOf('(', '[', '{', '<').contains(operator)
    private fun isClose(operator: Char): Boolean = listOf(')', ']', '}', '>').contains(operator)
    private fun mapToScore(operator: Char) = when (operator) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        else -> 25137
    }
    private fun mapToAutoComplete(operator: Char) = when (operator) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        else -> 4
    }

    override fun part1() {
        val errorList = mutableListOf<Char>()
        inputList.forEachIndexed { _, line ->
            val lookupTable = ArrayDeque<Char>()
            lookupTable.addFirst(line.first())
            line.substring(1).forEach { c ->
                when {
                    lookupTable.isEmpty() && isOpen(c) -> lookupTable.addFirst(c)
                    lookupTable.isEmpty() && isClose(c) -> {
                        errorList.add(c)
                        return@forEachIndexed
                    }
                    getClosing(lookupTable.first()) == c -> lookupTable.removeFirst()
                    getClosing(lookupTable.first()) != c && isOpen(c) -> lookupTable.addFirst(c)
                    else -> {
                        errorList.add(c)
                        return@forEachIndexed
                    }
                }
            }
        }
        println(errorList.map { mapToScore(it) }.sum())
    }

    override fun part2() {
        val autoCompleteList = mutableListOf<List<Char>>()
        inputList.forEachIndexed { _, s ->
            val lookupTable = ArrayDeque<Char>()
            lookupTable.addFirst(s.first())
            s.substring(1).forEach { c ->
                when {
                    lookupTable.isEmpty() && isOpen(c) -> lookupTable.addFirst(c)
                    lookupTable.isEmpty() && isClose(c) -> {
                        return@forEachIndexed
                    }
                    getClosing(lookupTable.first()) == c -> lookupTable.removeFirst()
                    getClosing(lookupTable.first()) != c && isOpen(c) -> lookupTable.addFirst(c)
                    else -> {
                        return@forEachIndexed
                    }
                }
            }
            autoCompleteList.add(lookupTable.map { getClosing(it) })
        }
        println(autoCompleteList.map {
            var score: Long = 0L
            it.forEach {
                score *= 5L
                score += mapToAutoComplete(it)
            }
            score
        }.sorted()[autoCompleteList.size /2])
    }
}
