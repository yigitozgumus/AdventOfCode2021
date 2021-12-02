package days

import BaseSolution
import java.io.File

const val FORWARD = "forward"
const val DOWN = "down"
const val UP = "up"

data class Command(val direction: String, val amount: Int)
data class Location(var horizontal: Int, var depth: Int)
data class LocationWithAim(var aim: Int, var horizontal: Int, var depth: Int)

fun Location.updateLocation(command: Command) {
    when(command.direction) {
        FORWARD -> this.horizontal += command.amount
        DOWN -> this.depth += command.amount
        UP -> this.depth -= command.amount
    }
}

fun LocationWithAim.updateLocation(command: Command) {
    when(command.direction) {
        FORWARD -> {
            this.horizontal += command.amount
            this.depth += this.aim * command.amount
        }
        UP -> this.aim -= command.amount
        DOWN -> this.aim += command.amount
    }
}

class Day2Solution: BaseSolution {

    private fun createCommandList() = File("inputs/day2.txt")
        .readLines()
        .map { it.split(" ") }
        .map { Command(it[0], it[1].toInt()) }

    override fun solve() {
        val commandList = createCommandList()
        val location = Location(0,0)
        commandList.forEach { location.updateLocation(it) }
        println(location.depth * location.horizontal)
    }

    override fun solveAnother() {
        val location = LocationWithAim(0,0,0)
        createCommandList().forEach { location.updateLocation(it) }
        println(location.depth * location.horizontal)
    }
}
