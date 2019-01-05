package com.manalili.advent

import java.util.*
import kotlin.math.abs


class Day06(val input: List<String>){
    var coordinates: List<ChronalCoordinate>
    var height: IntRange
    var width: IntRange
    init {
        val alpha = generateAlphaStack()

        coordinates = input.map {
            val (x, y) = it.split(", ")
            ChronalCoordinate(x.toInt(), y.toInt())
        }
        width = IntRange(coordinates.minBy { it.w }!!.w, coordinates.maxBy { it.w }!!.w)
        height = IntRange(coordinates.minBy { it.h }!!.h, coordinates.maxBy { it.h }!!.h)

    }

    fun largestArea(): Int {
        val area = mutableMapOf<Pair<Int,Int>, ChronalCoordinate?>()
        for (i in height) {
            for (j in width) {
                val coordinateList = coordinates.map { it to it.distance(j, i) }
                    .groupBy { it.second }
                    .minBy { it.key }
                area[i to j] = if (coordinateList!!.value.size> 1) {
                    null
                } else {
                    coordinateList.value.first().first.also { it.units++ }
                }
            }
        }
        return coordinates.filter { !it.isInfinite(height, width) }.maxBy { it.units }!!.units

    }

    fun generateAlphaStack(): ArrayDeque<String> {
        val a = CharRange('A', 'F')
        val result = Stack<String>()
        val result2 = ArrayDeque<String>()
        for (alpha in a) {
            result2.add(alpha.toString())
            for (beta in a) {
                result.push("""$alpha$beta""")
            }
        }

        return result2
    }
}

data class ChronalCoordinate(val w: Int, val h: Int){
    var units: Int = 0

    fun distance(x: Int, y: Int): Int {
        return abs(this.w - x) + abs(this.h - y)
    }

    fun isInfinite(height: IntRange, width: IntRange): Boolean{
        return this.let { w == width.first || w == width.last || h == height.first || h == height.last }
    }
}