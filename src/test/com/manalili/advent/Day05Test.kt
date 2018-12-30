package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File

class Day05Test {

    @Test
    fun react() {
        val sample = "dabAcCaCBAcCcaDA"
        assertEquals("dabCBAcaDA".length, Day05(sample).react())
        val file = File("input/day05.txt")
        println(Day05(file.readText()).react())
    }
}