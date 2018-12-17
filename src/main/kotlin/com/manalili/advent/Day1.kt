package com.manalili.advent

import java.io.File
import java.lang.NumberFormatException

class Day1 {
    fun part1(): Int{
        var result = 0
        val file = File("input/input.txt")
        file.forEachLine {
            try {
                result += it.toInt()
            } catch (e: NumberFormatException){
                println("Invalid number")
            }
        }
        return result
    }


    fun part2(input: ArrayList<Int>, listOfSums: ArrayList<Int> = arrayListOf(0)): Int? {
        var result: Int? = null
        for (i in input.indices) {
            val sum = input[i] + listOfSums.last()
            if (listOfSums.indexOf(sum) > 0) {
                result = sum
                listOfSums.add(sum)
                break
            }
            listOfSums.add(sum)
        }
        return result ?: part2(input, listOfSums)
    }
}
