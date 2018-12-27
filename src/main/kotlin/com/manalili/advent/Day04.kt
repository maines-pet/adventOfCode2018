package com.manalili.advent

class Day04(val logs: List<String>){
    private var guardList: List<Guard> = listOf()
        get(){
            val guardIdPattern = Regex("""\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2})] Guard #(\d+) begins shift""")
                val isSleepingPattern = Regex("""\[(\d{4}-\d{2}-\d{2} \d{2}:)(\d{2})] falls asleep""")
                val isAwakePattern = Regex("""\[(\d{4}-\d{2}-\d{2} \d{2}:)(\d{2})] wakes up""")
                val guardList = mutableListOf<Guard>()
                val sortedLogs = logs.sorted()

                var guard = Guard()
                for (i in sortedLogs.indices) {
                    val event = sortedLogs[i]
                    when {
                        event.contains("begins shift") -> {
                            if (guard.id != null) {
                                guardList.add(guard)
                            }
                            val id = guardIdPattern.find(event)!!.destructured.component2()
                            guard = Guard(id.toInt())
                        }
                        event.contains("falls asleep") -> {
                            val sleep = isSleepingPattern.find(event)!!.destructured.component2()
                            guard.minutesAsleep.add(sleep.toInt())

                        }
                        event.contains("wakes up")-> {
                            val awake = isAwakePattern.find(event)!!.destructured.component2()
                            guard.minutesAwake.add(awake.toInt())
                        }
                    }
                    if (i == (sortedLogs.size - 1)) guardList.add(guard)
                }

        return guardList

    }

    fun part01() : Int{
        val sleepyGuard = guardList.groupingBy { it.id }.aggregate { _, acc: Int?, elem, _ ->
            (acc ?: 0) + elem.totalMinutesAsleep
        }.maxBy { it.value }?.key

        return guardList.filter { it.id == sleepyGuard }
            .flatMap { it.rangeAsleep }.groupingBy { it }.eachCount()
            .maxBy { it.value }?.key!! * sleepyGuard!!
    }
}

data class Guard(
    var id: Int? = null, var minutesAsleep: MutableList<Int> = mutableListOf(),
    var minutesAwake: MutableList<Int> = mutableListOf()
) {
    val totalMinutesAsleep: Int
        get() = minutesAwake.sum() - minutesAsleep.sum()

    val rangeAsleep: List<Int>
        get() {
            val minutesMark = mutableListOf<Int>()
            for (i in minutesAsleep.indices){
                minutesMark.addAll((minutesAsleep[i] until minutesAwake[i]).map { it })
            }
            return minutesMark
        }
}


