package com.manalili.advent

import java.lang.StringBuilder
import java.util.*


class Day07(input: List<String>){
    val instructions: MutableMap<Char, MutableList<Char>> = mutableMapOf()
    private val regex = Regex("""Step ([A-z]) must be finished before step ([A-z]) can begin.""")
    init {
        input.forEach {
            val (step : String, next : String) = regex.find(it)!!.destructured
            instructions.getOrPut(step[0]) { mutableListOf() }
                .add(next[0])
        }

    }
    fun orderOfInstructions(): String {
        val stepsWithPrereqs = instructions.flatMap { it.value }
            .groupingBy { it }.eachCount().toMutableMap()
        val edges = instructions.keys.filterNot { stepsWithPrereqs.contains(it) }
            .toSortedSet()
        val queue = TreeSet<Char>(edges)
        val result = StringBuilder()

        while (true) {
            val current = queue.pollFirst()
            result.append(current)
            instructions[current]?.let {
                it.forEach { requisite ->
                    val lock = stepsWithPrereqs.merge(requisite, 1, Int::minus)
                    if (lock == 0) {
                        queue.add(requisite)
                    }
                }
            }
            if (queue.size == 0) break
        }

        return result.toString()
    }

}

class Instruction(val step: Char, nextStep: Char? = null){
    var next: MutableList<Instruction> = mutableListOf()
    init {
        nextStep?.let { addRequisite(it) }
    }

    fun addRequisite(next: Char) {
        this.next.add(Instruction(next))
    }
    override fun toString(): String {
        return """step = $step next = $next"""
    }
}
