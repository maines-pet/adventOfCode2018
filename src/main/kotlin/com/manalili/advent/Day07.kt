package com.manalili.advent

import java.util.*


class Day07(input: List<String>) {
    private val instructions: MutableMap<Char, MutableList<Char>> = mutableMapOf()
    private val regex = Regex("""Step ([A-z]) must be finished before step ([A-z]) can begin.""")

    init {
        input.forEach {
            val (step: String, next: String) = regex.find(it)!!.destructured
            instructions.getOrPut(step[0]) { mutableListOf() }
                .add(next[0])
        }
    }

    fun orderOfInstructions(): String {
        val stepsWithPrereqs = instructions.flatMap { it.value }
            .groupingBy { it }.eachCount().toMutableMap()
        val edges = instructions.keys.filterNot { stepsWithPrereqs.contains(it) }
        val queue = TreeSet<Char>(edges)
        val result = StringBuilder()

        while (true) {
            val current = queue.pollFirst()
                .also { result.append(it) }
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

    fun part2(): Int {
        val workers = 5
        val steps = instructions.values.flatten()

        //Steps and their NUMBER of prerequisites to be fulfilled before they can be started
        val stepsWithPrereqCount: MutableMap<Char, Int> =
            ('A'..'Z').associateWith { steps.count { c -> c == it } }.toMutableMap()

        //Use SortedMap data structure to keep the priorities of steps according to
        //their alphabetical order and put time remaining as Value
        val workQueue = sortedMapOf<Char, Int>()

        //get all edges ie steps with no prerequisites
        stepsWithPrereqCount.filterValues { it == 0 }.keys.forEach {
            workQueue[it] = it.toDuration()
        }

        //Steps with time remaining
        var totalTimeWorked = 0

        while (workQueue.isNotEmpty()) {
            val inProgress = workQueue.keys.take(minOf(workers, workQueue.size))
            val min = workQueue[inProgress.minBy { workQueue[it]!! }]!!
            totalTimeWorked += min

            //subtract to inprogress
            inProgress.forEach {

                //subtract the time spent
                val timeRemaining = workQueue.merge(it, min, Int::minus)
                if (timeRemaining == 0) {
                    //remove from queue
                    workQueue.remove(it)
                    //inspect children
                    instructions[it]?.forEach { child ->
                        val numOfStepsLeft = stepsWithPrereqCount.merge(child, 1, Int::minus)
                        //release and add to queue if no more prereq
                        if (numOfStepsLeft == 0) {
                            workQueue[child] = child.toDuration()
                        }
                    }
                }
            }
        }
        return totalTimeWorked
    }

    private fun Char.toDuration() = this - 'A' + 61
}
