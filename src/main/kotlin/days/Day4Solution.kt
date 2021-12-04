package days

import BaseSolution

class BingoBoard(rows: List<String>) {
    private val rowCounter = MutableList(rows.size) { 0 }
    private val colCounter = MutableList(rows.size) { 0 }
    var winner = false

    private val board = rows.toMutableList().mapTo(mutableListOf()) { row ->
        row.split(" ").filter { it != "" }.mapTo(mutableListOf()) { it.toInt() }
    }

    fun isBingoAfterCheck(nextNumber: Int): Boolean {
        val rowIndex = board.indexOfFirst { it.contains(nextNumber) }
        return if (rowIndex != -1) {
            val colIndex = board[rowIndex].indexOf(nextNumber)
            colCounter[colIndex]++
            rowCounter[rowIndex]++
            board[rowIndex][colIndex] = -1
            rowCounter[rowIndex] == 5 || colCounter[colIndex] == 5
        } else false
    }

    fun computeScore(nextNumber: Int) = board.sumOf { it.filter { num -> num > 0 }.sum() } * nextNumber
}

class Day4Solution(inputList: List<String>) : BaseSolution {

    private val numberList = inputList.first().split(",").map { it.toInt() }
    private val boardList = inputList.subList(1, inputList.size).filterNot { it == "" }
        .windowed(size = 5, step = 5).map { BingoBoard(it) }

    override fun part1() {
        numberList.forEach { number ->
            boardList.forEach { board ->
                if (board.isBingoAfterCheck(number)) {
                    println(board.computeScore(number))
                    return
                }
            }
        }
    }

    override fun part2() {
        numberList.forEach { number ->
            boardList.forEach { board ->
                if (board.isBingoAfterCheck(number) && board.winner.not()) {
                    board.winner = true
                    if (boardList.all { it.winner }) {
                        println(board.computeScore(number))
                        return
                    }
                }
            }
        }
    }
}
