package com.manalili.advent

class Day05(val input: String) {
    fun react(): Int {
        var polymer = input
        var index = 0

        while (true) {
            if (index >= polymer.length - 1) break
            if (polymer[index] same polymer[index + 1]) {
                polymer = polymer.removeRange(index..index + 1)
                if (index != 0) index--
            } else {
                index++
            }
        }

        return polymer.length
    }


    private infix fun Char.same(other: Char) : Boolean {
        return when {
            this.isLowerCase() -> this.toUpperCase() == other
            this.isUpperCase() -> this.toLowerCase() == other
            else -> false
        }
    }

}