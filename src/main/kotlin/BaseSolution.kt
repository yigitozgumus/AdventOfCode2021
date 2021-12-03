
interface BaseSolution {
    val inputList: List<String>
    fun part1()
    fun part2()

    fun defineCounter() = MutableList(inputList.first().length) { 0 }
    fun Boolean.toInt(): Int = if (this) 1 else 0
}
