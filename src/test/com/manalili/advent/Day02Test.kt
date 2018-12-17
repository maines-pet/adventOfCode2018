package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File

class Day02Test {

    @Test
    fun checksum() {
        val file = File("input/day2test.txt")
        assertEquals(12, Day02().checksum(file.readLines()))
        val fileInput = File("input/day2.txt")
        println("""answer to part 1: ${Day02().checksum(fileInput.readLines())}""")
    }

    @Test
    fun rightBoxId() {
        val file = File("input/day2test2.txt")
        assertEquals("fgij", Day02().rightBoxId(file.readLines()))
        val fileInput = File("input/day2.txt")
        println("""answer to part 2: ${Day02().rightBoxId(fileInput.readLines())}""")
    }
}