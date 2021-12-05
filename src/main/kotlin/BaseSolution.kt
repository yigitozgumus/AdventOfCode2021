
abstract class BaseSolution(val inputList: List<String>) {
    abstract fun part1()
    abstract fun part2()

    fun Boolean.toInt(): Int = if (this) 1 else 0
}
