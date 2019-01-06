package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import kotlin.test.assertTrue

class Day06Test {

    val input = """
1, 1
1, 6
8, 3
3, 4
5, 5
8, 9""".trim()
    @Test
    fun largestArea() {
        assertEquals(17, Day06(input.split("\n")).largestArea())
        val inputFile = File("input/day06.txt")
        println(Day06(inputFile.readLines()).largestArea())
    }

    @Test
    fun regionDistanceAcross() {
        assertEquals(16, Day06(input.split("\n")).regionDistanceAcross(32))
        val inputFile = File("input/day06.txt")
        println(Day06(inputFile.readLines()).regionDistanceAcross(10000))
    }
}