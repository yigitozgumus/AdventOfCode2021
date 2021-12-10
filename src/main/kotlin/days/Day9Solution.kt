package days

import BaseSolution

class Day9Solution(inputList: List<String>) : BaseSolution(inputList) {

    private val grids = inputList.mapTo(mutableListOf()) {
        it.split("").filter { it != "" }.mapTo(mutableListOf()) { it.toInt() }
    }.also {
        it.forEach { grid ->
            grid.add(grid.size, 9)
            grid.add(0, 9)
        }
        it.add(it.size, MutableList(it.first().size) { 9 })
        it.add(0, MutableList(it.first().size) { 9 })
    }

    private fun getNeighbors(row: Int, col: Int): List<Int> =
        listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).map { grids[row + it.first][col + it.second] }

    private fun getCheckList(row: Int, col: Int): Boolean =
        getNeighbors(row, col).minOf { it } > grids[row][col]

    override fun part1() {
        (1..grids.size - 2).map { row ->
            (1..grids.first().size - 2).map { col ->
                if (getCheckList(row, col)) grids[row][col] + 1 else 0
            }
        }.sumOf { it.sum() }.also { println(it) }
    }

    override fun part2() {
        (1..grids.size - 2).map { row ->
            (1..grids.first().size - 2).map { col ->
                if (getCheckList(row, col)) computeBasinSize(row, col) else 0
            }
        }.flatten().sortedBy { it }.reversed().also { println(it.take(3).reduce { acc, i -> acc * i }) }
    }

    private fun computeBasinSize(row: Int, col: Int): Int {
        val visited = mutableListOf<Pair<Int, Int>>()
        fun search(row: Int, col: Int) {
            visited.add(row to col)
            listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).map { row + it.first to col + it.second }.forEach {
                if (grids[it.first][it.second] == 9) return@forEach
                if (visited.contains(it).not()) {
                    search(it.first, it.second)
                } else return@forEach
            }
        }
        search(row, col)
        return visited.count()
    }
}
