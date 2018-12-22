package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File

class Day03Test {

    @Test
    fun overlapSection() {
        val file = File("input/day03sample.txt")
        assertEquals(4, Day03(file.readLines()).overlapSection())
        val file2 = File("input/day03.txt")
        println(Day03(file2.readLines()).overlapSection())
    }

    @Test
    fun nonOverlapSection(){
        val file = File("input/day03sample.txt")
        assertEquals(3, Day03(file.readLines()).nonOverlapSection())
        val file2 = File("input/day03.txt")
        println(Day03(file2.readLines()).nonOverlapSection())
    }
}