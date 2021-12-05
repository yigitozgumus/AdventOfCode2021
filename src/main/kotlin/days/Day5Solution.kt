package days

import BaseSolution
import kotlin.math.abs

data class Point(val x: Int, val y: Int)

fun createPoint(locationList: List<Int>) = Point(locationList[0], locationList[1])

data class Line(val start: Point, val end: Point) {

    fun isHorizontalOrVertical(): Boolean = start.x == end.x || start.y == end.y
    fun isDiagonal(): Boolean = abs(start.x - end.x) == abs(start.y - end.y)

    fun generatePointMap(): List<Point> = when {
        start.x == end.x && start.y < end.y -> getRange(start.y, end.y).map { Point(start.x, it) }
        start.x == end.x -> getRange(start.y, end.y).map { Point(start.x, it) }
        start.y == end.y && start.x < end.x -> getRange(start.x, end.x).map { Point(it, start.y) }
        start.y == end.y -> getRange(start.x, end.x).map { Point(it, start.y) }
        else -> getRange(start.x, end.x).zip(getRange(start.y, end.y)).map { Point(it.first, it.second) }
    }
}

fun createLine(pointList: List<Point>) = Line(pointList[0], pointList[1])
fun getRange(start: Int, end: Int) = if (start < end) (start..end) else (start downTo end)

class Day5Solution(inputList: List<String>) : BaseSolution(inputList) {

    private val lineList = inputList.map { pointPair ->
        pointPair.split(" -> ").map { point ->
            createPoint(point.split(",").map { it.toInt() })
        }
    }.map { createLine(it) }.asSequence()

    override fun part1() {
        lineList.filter { it.isHorizontalOrVertical() }
            .flatMap { line -> line.generatePointMap() }
            .groupingBy { it }
            .eachCount().values.filter { it > 1 }.size
            .also { println(it) }
    }

    override fun part2() {
        lineList.filter { it.isDiagonal() || it.isHorizontalOrVertical() }
            .flatMap { line -> line.generatePointMap() }
            .groupingBy { it }
            .eachCount().values.filter { it > 1 }.size
            .also { println(it) }
    }
}
