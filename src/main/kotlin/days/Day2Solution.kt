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

class Day2Solution(inputList: List<String>): BaseSolution(inputList) {

    private fun createCommandList() = inputList
        .map { it.split(" ") }
        .map { Command(it[0], it[1].toInt()) }

    override fun part1() {
        val commandList = createCommandList()
        val location = Location(0,0)
        commandList.forEach { location.updateLocation(it) }
        println(location.depth * location.horizontal)
    }

    override fun part2() {
        val location = LocationWithAim(0,0,0)
        createCommandList().forEach { location.updateLocation(it) }
        println(location.depth * location.horizontal)
    }
}
