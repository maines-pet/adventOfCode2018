package com.manalili.advent

import org.junit.Test

import org.junit.Assert.*
import java.io.File
import kotlin.test.assertTrue

class Day07Test {

    val input = """
Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin.""".trim()
    @Test
    fun orderOfInstructions() {
        assertEquals("CABDFE", Day07(input.split("\n")).orderOfInstructions())
        val inputFile = File("input/day07.txt")
        println(Day07(inputFile.readLines()).orderOfInstructions())
    }

    @Test
    fun regionDistanceAcross() {
    }
}