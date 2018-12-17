package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File

class Day1Test {

    @Test
    fun readFile() {
//        println(Day1().part1())
        assertEquals(5, Day1().part2(arrayListOf(-6,3,8,5,-6)))
        assertEquals(10, Day1().part2(arrayListOf(3,3,4,-2,-4)))
        val file = File("input/input.txt")
        val inputList: ArrayList<Int> = arrayListOf()
        file.forEachLine {
            inputList.add(it.toInt())
        }
        println(Day1().part2(inputList))

//        println(Day01(file.readLines()).solvePart2())

    }
}

