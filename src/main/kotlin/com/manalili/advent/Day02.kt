package com.manalili.advent

class Day02 {
    fun checksum(input: List<String>): Int {
        var twos = 0
        var threes = 0
        input.map { boxId: String ->
            val uniqueChars = boxId.toSet()
            val uniqueCharsCount =
                uniqueChars.map { boxId.count { c -> it == c } }.filter { it in 2..3 }
            if (uniqueCharsCount.contains(2)) twos++
            if (uniqueCharsCount.contains(3)) threes++
        }
        return twos * threes
    }

    fun rightBoxId(ids: List<String>): String{
        var match = ""
        for (i in ids.indices) {
            //last element ie nothing to compare
            if (i == ids.size - 1) {
                break
            }

            for (j in i + 1 until ids.size){
                val commonLetters = ids[i] diff ids[j]
                if (commonLetters != null) {
                    match = commonLetters
                    break
                }
            }
        }

        return match
    }

}

infix fun String.diff(other: String): String? {
    var numOfCharsDiff = 0
    var posOfDifference: Int? = null
    for (i in this.indices) {
        if (this[i] != other[i]) {
            posOfDifference = i
            numOfCharsDiff++
        }
    }
    return if (numOfCharsDiff != 1) null else this.filterIndexed { index, _ ->
        index != posOfDifference
    }
}

