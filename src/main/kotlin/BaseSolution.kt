
interface BaseSolution {
    fun part1()
    fun part2()


    fun Boolean.toInt(): Int = if (this) 1 else 0
}
