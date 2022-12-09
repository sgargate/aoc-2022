fun main() {
    val sums = ranges().count { (first, second) ->
        first.fullyOverlaps(second) || second.fullyOverlaps(first)
    }
    println(sums)

    val sum2 = ranges().count { (first, second) ->
        first.overlaps(second)
    }
    println(sum2)

}

private fun ranges() = getLines("day04/input2.txt")
    .map {
        val s = it.split(",")
        s[0].toIntRange() to s[1].toIntRange()

    }
