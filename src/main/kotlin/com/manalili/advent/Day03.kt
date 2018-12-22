package com.manalili.advent

typealias Coordinates = Pair<Int, Int>
class Day03(input: List<String>) {
    val claims = input.map { Claim.destructureClaim(it) }
    var singleClaimedSections: Set<Coordinates>? = null


    fun overlapSection(): Int{
        val sections: MutableMap<Coordinates, Int> = mutableMapOf()

        claims.forEach { claim ->
            claim.area().forEach { sections[it] = sections.getOrDefault(it.first to it.second, 0) + 1}
        }
        singleClaimedSections = sections.filterValues { it == 1 }.keys
        return sections.count { it.value > 1 }
    }

    fun nonOverlapSection(): Int {
        if (singleClaimedSections == null) {
            overlapSection()
        }

        return claims.first { claim ->
            claim.area().all { singleClaimedSections?.contains(it)!! }
        }.id
    }
}

class Claim private constructor(val id: Int, val x: Int, val y: Int, val width: Int, val length: Int){
    fun area(): List<Coordinates>{
        val section = mutableListOf<Coordinates>()
        for (i in x until x + width) {
            for (j in y until y + length) {
                section.add(i to j)
            }
        }
        return section.toList()
    }

    companion object {
        //#1 @ 1,3: 4x4
        fun destructureClaim(s: String): Claim {
            val (id, x, y, length, width)
                    = Regex("""^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""").find(s)!!.destructured
            return Claim(id.toInt(), x.toInt(), y.toInt(), length.toInt(), width.toInt())
        }
    }
}
