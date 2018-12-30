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

    @Test
    fun fullyReact() {
        val sample = "dabAcCaCBAcCcaDA"
        assertEquals("daDA".length, Day05(sample).fullyReact())
        val file = File("input/day05.txt")
        println(Day05(file.readText()).fullyReact())
    }
}